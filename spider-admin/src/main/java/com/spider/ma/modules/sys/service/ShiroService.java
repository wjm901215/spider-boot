package com.spider.ma.modules.sys.service;

import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.entity.UserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-06-06 8:49
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    UserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    UserEntity queryUser(String userId);
}
