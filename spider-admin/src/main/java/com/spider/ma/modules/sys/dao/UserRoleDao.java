package com.spider.ma.modules.sys.dao;

import com.spider.ma.modules.sys.entity.UserRoleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户角色关联表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-24 11:50:11
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

    void deleteBatchIds(List<String> ids);

    List<UserRoleEntity> page(Page<UserRoleEntity> page, Map params);


    /**
     * 根据用户ID，获取角色ID列表
     */
    List<String> queryRoleIdList(String userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(String[] roleIds);

}
