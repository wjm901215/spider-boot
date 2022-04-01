package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.UserRoleEntity;

import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    void saveOrUpdate(String userId, List<String> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<String> queryRoleIdList(String userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(String[] roleIds);
}
