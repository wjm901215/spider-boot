package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.excel.expt.UserDataTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 查询列表 分页
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 根据用户名，查询系统用户
     *
     * @param username
     * @return
     */
    UserEntity queryByUserName(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void save(UserEntity user);

    /**
     * 修改用户
     *
     * @param user
     */
    void update(UserEntity user);

    /**
     * 删除用户
     *
     * @param userIds
     */
    void deleteBatch(String[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     * @return
     */
    boolean updatePassword(String userId, String password, String newPassword);


    UserEntity queryUserDetail(UserEntity userEntity);

    void switchLanguage(String lang);


    void enableUser(String[] userIds);

    void disableUser(String[] userIds);

    void restPwd(UserEntity user);

    void resignation(String[] userIds);

    void switchTenantId(String tenantId);

    /**
     * 查询导出数据
     * @param params
     * @return
     */
    List<UserDataTemplate> queryExportData(Map<String, Object> params);
}
