package com.spider.ma.db.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.db.sys.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户、收费员token表
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.dao.UserTokenDao,v 0.1 2021/7/28 11:51 Exp $$
 */
@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenEntity> {

    void deleteBatchIds(List<String> ids);

    List<UserTokenEntity> page(Page<UserTokenEntity> page, Map params);

    /**
     * 根据sid查询用户id
     *
     * @param sid
     * @return
     */
    String getUserIdBySid(String sid);

    /**
     * 根据sid 获取
     *
     * @param sid
     * @return
     */
    UserTokenEntity getUserTokenBySid(String sid);
}
