package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.RoleDao;
import com.spider.ma.modules.sys.entity.RoleEntity;
import com.spider.ma.modules.sys.service.RoleMenuService;
import com.spider.ma.modules.sys.service.RoleService;
import com.spider.ma.modules.sys.service.UserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        String createUserId = (String) params.get("createUserId");

        Page<RoleEntity> page = this.selectPage(
                new Query<RoleEntity>(params).getPage(),
                new EntityWrapper<RoleEntity>()
                        .where("DELETED = {0}", BaseConstants.Deleted.F.value)
                        .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                        .eq(createUserId != null, "create_by", createUserId)
//                        .eq(!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN), "tenant_id", ShiroUtils.getUserEntity().getTenantId())
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RoleEntity role) {
        role.setTenantId((StringUtils.isNotEmpty(role.getTenantId())?role.getTenantId():ShiroUtils.getUserEntity().getTenantId()));
        this.insert(role);
        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(), role.getHalfMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleEntity role) {
        role.setTenantId((StringUtils.isNotEmpty(role.getTenantId())?role.getTenantId():ShiroUtils.getUserEntity().getTenantId()));
        baseMapper.updateById(role);

        //更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(), role.getHalfMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));


        baseMapper.update(new RoleEntity() {{
            setDeleted(BaseConstants.Deleted.T.value);
        }}, new EntityWrapper<RoleEntity>() {{
            in("ROLE_ID", roleIds);
        }});

        //删除角色与菜单关联
        roleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        userRoleService.deleteBatch(roleIds);
    }


    @Override
    public List<String> queryRoleIdList(String createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    @Override
    public List<RoleEntity> queryRoleForSelector(String[] tenantIds) {
        return baseMapper.selectList(new EntityWrapper<RoleEntity>() {{
            /*if(ArrayUtils.isNotEmpty(tenantIds)){
                where("TENANT_ID in ({0}) ",tenantIds);
            }else if (!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
                where("TENANT_ID in (select sud.EXT_ID from sys_user_data sud where EXT_TYPE = {0} and USER_ID = {1}) ",
                        BaseConstants.DataPermissionExtType.TENANT.key, ShiroUtils.getUserId());
            }*/
        }});
    }

}
