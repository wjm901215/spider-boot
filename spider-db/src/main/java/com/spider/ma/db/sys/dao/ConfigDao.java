package com.spider.ma.db.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.db.sys.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年12月4日 下午6:46:16
 */
@Mapper
public interface ConfigDao extends BaseMapper<ConfigEntity> {

    /**
     * 根据key，查询value
     */
    ConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

    List<ConfigEntity> queryList(Page page, Map params);
}
