package com.spider.ma.modules.sys.dao;

import com.spider.ma.modules.sys.entity.UserDataEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户数据权限表
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-29 10:01:39
 */
@Mapper
public interface UserDataDao extends BaseMapper<UserDataEntity> {

    void deleteBatchIds(List<String> ids);

    List<UserDataEntity> page(Page<UserDataEntity> page, Map params);

    /**
     * 根据userId 查找数据权限
     * @param userId
     * @return
     */
    List<UserDataEntity> queryUserDataByUserId(String userId);
}
