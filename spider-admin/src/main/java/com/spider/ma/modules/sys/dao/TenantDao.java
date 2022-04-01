package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.modules.sys.entity.TenantEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 租户表
 * 
 * @author SpiderMan
 * @email SpiderMan@mail.com
 * @date 2019-07-24 17:03:34
 */
@Mapper
public interface TenantDao extends BaseMapper<TenantEntity> {

    void deleteBatchIds(List<String> ids);

    List<TenantEntity> page(Page<TenantEntity> page, Map params);
	
}
