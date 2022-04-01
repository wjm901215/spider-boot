package com.spider.ma.db.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.db.sys.entity.AppKeyEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 应用接入密钥表
 *
 * @author Spiderman
 * @email professorX@mail.com
 * @date 2018-08-31 11:39:52
 */
@Mapper
public interface AppKeyDao extends BaseMapper<AppKeyEntity> {

    /**
     * 根据appid获取key
     * @param app_id
     * @return
     */
    AppKeyEntity queryKeyByAppId(String app_id);
}
