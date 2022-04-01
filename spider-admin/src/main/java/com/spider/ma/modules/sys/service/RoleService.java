package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
public interface RoleService extends IService<RoleEntity> {


    PageUtils queryPage(Map<String, Object> params);

    void save(RoleEntity role);

    void update(RoleEntity role);

    void deleteBatch(String[] roleIds);


    /**
     * 查询用户创建的角色ID列表
     */
    List<String> queryRoleIdList(String createUserId);

    List<RoleEntity> queryRoleForSelector(String[] tenantIds);
}
