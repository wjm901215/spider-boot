package com.spider.ma.modules.sys.dao;

import com.spider.ma.common.annotation.DataAssessAop;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-24 11:29:36
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    void deleteBatchIds(List<String> ids);


    @DataAssessAop
    List<UserEntity> page(Page page, Map params);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 根据用户名，查询系统用户
     */
    UserEntity queryByUserName(String username);

    /**
     * 根据用户ID查询用户
     */
    UserEntity queryByUserId(String userId);


    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    UserEntity queryUserDetail(String userId);

}
