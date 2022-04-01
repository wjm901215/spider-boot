package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.TenantEntity;

import java.util.List;
import java.util.Map;

/**
 * 租户表
 *
 * @author SpiderMan
 * @email SpiderMan@mail.com
 * @date 2019-07-24 17:03:34
 */
public interface TenantService extends IService<TenantEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean update(TenantEntity entity);

    boolean insert(TenantEntity entity);

    void deleteBatchIds(List<String> ids);

    List<Map<String, Object>> queryTenantForUser(String id);

    List<TenantEntity> queryTenantList();
}

