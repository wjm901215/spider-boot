package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.BeanUtils;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.PersonnelDao;
import com.spider.ma.modules.sys.dao.UserDao;
import com.spider.ma.modules.sys.dao.UserDataDao;
import com.spider.ma.modules.sys.entity.*;
import com.spider.ma.common.utils.Query;
import com.spider.ma.modules.sys.enumerate.SysConstants;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.modules.sys.excel.expt.UserDataTemplate;
import com.spider.ma.modules.sys.redis.DictionaryRedis;
import com.spider.ma.modules.sys.service.RoleService;
import com.spider.ma.modules.sys.service.UserRoleService;
import com.spider.ma.modules.sys.service.UserService;
import com.spider.ma.modules.sys.entity.PersonnelEntity;
import com.spider.ma.modules.sys.entity.UserDataEntity;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.entity.UserRoleEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDataDao userDataDao;
    @Autowired
    private PersonnelDao personnelDao;
    @Autowired
    private DictionaryRedis dictionaryRedis;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserEntity> page = new Query<UserEntity>(params).getPage();
        page.setRecords(this.baseMapper.page(page, params));
        return new PageUtils(page);
    }

    @Override
    public List<String> queryAllPerms(String userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<String> queryAllMenuId(String userId) {

        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public UserEntity queryByUserName(String username) {
        UserEntity userEntity = baseMapper.selectOne(new UserEntity() {{
            setUsername(username);
            setDeleted(BaseConstants.Deleted.F.value);
            setStatus(BaseConstants.Status.ENABLE.key);
        }});
        return userEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserEntity user) throws BusinessException{
        UserEntity userEntity = baseMapper.selectOne(new UserEntity() {{
            setUsername(user.getAccount());
            setDeleted(BaseConstants.Deleted.F.value);
        }});
        if(userEntity!=null){
            throw new BusinessException(SysConstants.UserResponseEnum.USER_EXIST_ERROR.value, SysConstants.UserResponseEnum.USER_EXIST_ERROR.key);
        }
        //???????????????????????????????????????
        user.setTenantId(CollectionUtils.isNotEmpty(user.getTenantIds()) ? user.getTenantIds().get(0) : ShiroUtils.getUserEntity().getTenantId());
        PersonnelEntity personnelEntity = getPersonnelEntity(user);
        personnelEntity.setCreateBy(ShiroUtils.getUserId());
        personnelEntity.setCreateTime(LocalDateTime.now());
        personnelEntity.setJobType(SysConstants.JobType.USER.key);
        personnelEntity.setStatus(BaseConstants.IsIncumbent.INCUMBENT.key);
        personnelEntity.setHeadUrl(user.getHeadUrl());
        personnelDao.insert(personnelEntity);
        user.setPersonnelId(personnelEntity.getId());
        insert2User(user);
    }

    private void insert2User(UserEntity user) {
        //????????????????????????
        //checkRole(user);
        //sha256??????
        user.setUsername(user.getAccount());
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setCreateTime(LocalDateTime.now());
        user.setCreateBy(ShiroUtils.getUserId());
        user.setDeleted(BaseConstants.Deleted.F.value);
        user.setStatus(BaseConstants.Status.ENABLE.key);
        baseMapper.insert(user);
        //???????????????????????????
        userRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
        //??????????????????
        addUserData(user.getId(), user.getTenantIds());
    }

    /**
     * ??????????????????
     *
     * @param userId   ??????ID
     * @param tenantIds ??????ID
     */
    private void addUserData(String userId, List<String> tenantIds) {
        if (CollectionUtils.isNotEmpty(tenantIds)) {
            tenantIds.forEach((tenantId) -> {
                UserDataEntity userDataEntity = new UserDataEntity();
                userDataEntity.setUserId(userId);
                userDataEntity.setExtId(tenantId);
                userDataEntity.setExtType(BaseConstants.DataPermissionExtType.TENANT.key);
                userDataDao.insert(userDataEntity);
            });
        }else{
            List<UserDataEntity> list=ShiroUtils.getUserEntity().getUserDataEntity();
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach((userDataEntityShiro) -> {
                    UserDataEntity userDataEntity = new UserDataEntity();
                    userDataEntity.setUserId(userId);
                    userDataEntity.setExtId(userDataEntityShiro.getExtId());
                    userDataEntity.setExtType(BaseConstants.DataPermissionExtType.TENANT.key);
                    userDataDao.insert(userDataEntity);
                });
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserEntity user) {
        //???????????????????????????????????????
        user.setTenantId(CollectionUtils.isNotEmpty(user.getTenantIds()) ? user.getTenantIds().get(0) : ShiroUtils.getUserEntity().getTenantId());
        updateUser(user);
        //??????????????????
        UserEntity userEntity = this.queryUserDetail(user);
        PersonnelEntity personnelEntity = getPersonnelEntity(user);
        personnelEntity.setId(userEntity.getPersonnelId());
        personnelEntity.setHeadUrl(user.getHeadUrl());
        personnelDao.updateById(personnelEntity);
        //??????????????????
        userDataDao.delete(new EntityWrapper<UserDataEntity>() {{
            in("USER_ID", user.getId());
        }});
        //??????????????????
        addUserData(user.getId(), user.getTenantIds());
    }

    @Override
    public void deleteBatch(String[] userId) {
        //??????????????????
        Arrays.asList(userId).forEach(id -> {
            personnelDao.update(new PersonnelEntity() {{
                setDeleted(BaseConstants.Deleted.T.value);
            }}, new EntityWrapper<PersonnelEntity>() {{
                where("ID = (select u.personnel_id from sys_user u where u.id = {0})", id);
            }});
        });
        //????????????
        baseMapper.update(new UserEntity() {{
            setDeleted(BaseConstants.Deleted.T.value);
        }}, new EntityWrapper<UserEntity>() {{
            in("ID", userId);
        }});
        //????????????????????????
        userRoleService.delete(new EntityWrapper<UserRoleEntity>() {{
            in("USER_ID", userId);
        }});
        //??????????????????
        userDataDao.delete(new EntityWrapper<UserDataEntity>() {{
            in("USER_ID", userId);
        }});
    }

    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new EntityWrapper<UserEntity>().eq("id", userId).eq("password", password));
    }

    @Override
    public UserEntity queryUserDetail(UserEntity userEntity) {
        userEntity = baseMapper.queryUserDetail(userEntity.getId());
        return userEntity;
    }

    @Override
    public void switchLanguage(String lang) {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setId(ShiroUtils.getUserEntity().getPersonnelId());
        personnelEntity.setLang(lang);
        personnelDao.updateById(personnelEntity);
    }

    @Override
    public void switchTenantId(String tenantId) {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setId(ShiroUtils.getUserEntity().getPersonnelId());
        personnelEntity.setTenantId(tenantId);
        personnelDao.updateById(personnelEntity);
        UserEntity userEntity=new UserEntity();
        userEntity.setId(ShiroUtils.getUserId());
        userEntity.setTenantId(tenantId);
        baseMapper.updateById(userEntity);
    }

    @Override
    public void enableUser(String[] userIds) {
        baseMapper.update(new UserEntity() {{
            setStatus(BaseConstants.Status.ENABLE.key);
        }}, new EntityWrapper<UserEntity>() {{
            in("id", userIds);
        }});
    }

    @Override
    public void disableUser(String[] userIds) {
        baseMapper.update(new UserEntity() {{
            setStatus(BaseConstants.Status.DISABLE.key);
        }}, new EntityWrapper<UserEntity>() {{
            in("id", userIds);
        }});
    }

    @Override
    public void restPwd(UserEntity userEntity) {
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String pwd = new Sha256Hash(BaseConstants.BASE_PWD, salt).toHex();
        userEntity.setSalt(salt);
        userEntity.setPassword(pwd);
        baseMapper.updateById(userEntity);
    }

    @Override
    public void resignation(String[] userIds) {
        //???????????????????????????
        baseMapper.update(new UserEntity() {{
            setStatus(BaseConstants.Status.DISABLE.key);
        }}, new EntityWrapper<UserEntity>() {{
            in("id", userIds);
        }});
        Arrays.asList(userIds).forEach(id -> {
            //?????????????????????
            personnelDao.update(new PersonnelEntity() {{
                setStatus(BaseConstants.IsIncumbent.RESIGNATION.key);
            }}, new EntityWrapper<PersonnelEntity>() {{
                where("ID = (select u.personnel_id from sys_user u where u.id = {0})", id);
            }});
        });
    }



    /**
     * ????????????????????????
     */
    private void checkRole(UserEntity user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }
        //??????????????????????????????????????????????????????????????????????????????
        if (user.getCreateBy() == BaseConstants.SUPER_ADMIN) {
            return;
        }

        //?????????????????????????????????
        List<String> roleIdList = roleService.queryRoleIdList(user.getCreateBy());

        //??????????????????
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new BusinessException("?????????????????????????????????????????????");
        }
    }


    private PersonnelEntity getPersonnelEntity(UserEntity entity) {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setAddress(entity.getAddress());
        personnelEntity.setBirthDate(entity.getBirthDate());
        personnelEntity.setDeptId(entity.getDeptId());
        personnelEntity.setJobType(entity.getJobType());
        personnelEntity.setEntryDate(entity.getEntryDate());
        personnelEntity.setId(entity.getPersonnelId());
        personnelEntity.setIdentityNo(entity.getIdentityNo());
        personnelEntity.setTenantId(entity.getTenantId());
        personnelEntity.setName(entity.getName());
        personnelEntity.setSex(entity.getSex());
        personnelEntity.setStatus(entity.getWorkingStatus());
        personnelEntity.setMobile(entity.getMobile());
        personnelEntity.setLang(entity.getLang());
        return personnelEntity;

    }

    /**
     * ??????????????????
     *
     * @param user
     */
    private void updateUser(UserEntity user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
            user.setSalt(salt);
        }
        user.setUsername(user.getAccount());
        user.setUpdateBy(ShiroUtils.getUserId());
        user.setUpdateTime(LocalDateTime.now());
        this.updateById(user);
        userRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    @Override
    public List<UserDataTemplate> queryExportData(Map<String, Object> params) {
        params.put("page",BaseConstants.DEFAULT_PAGE);
        params.put("limit",BaseConstants.EXPORT_PAGE_SIZE);
        Page<UserEntity> page = new Query<UserEntity>(params).getPage();
        page.setSearchCount(false);
        List<UserEntity> entityList = this.baseMapper.page(page, params);
        List templateList= new ArrayList<UserDataTemplate>();
        entityList.forEach(userEntity -> {
            UserDataTemplate userDataTemplate = new UserDataTemplate();
            BeanUtils.copyNotNullProperties(userEntity,userDataTemplate);
            userDataTemplate.setPersonnelName(userEntity.getName());
            userDataTemplate.setSex(dictionaryRedis.getTextByCodeAndValue("SEX", userEntity.getSex()));
            userDataTemplate.setStatusName(dictionaryRedis.getTextByCodeAndValue("STATUS", userEntity.getStatus()));
            userDataTemplate.setWorkingStatusName( dictionaryRedis.getTextByCodeAndValue("JOB_STATUS", userEntity.getWorkingStatus()));
            templateList.add(userDataTemplate);
        });
        return templateList;
    }
}
