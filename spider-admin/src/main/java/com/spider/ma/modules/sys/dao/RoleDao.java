package com.spider.ma.modules.sys.dao;

import com.spider.ma.modules.sys.entity.RoleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-24 11:54:16
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<String> queryRoleIdList(String createUserId);

}
