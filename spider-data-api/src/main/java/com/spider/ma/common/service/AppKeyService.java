package com.spider.ma.common.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.redis.AppRedis;
import com.spider.ma.db.sys.dao.AppKeyDao;
import com.spider.ma.db.sys.entity.AppKeyEntity;
import com.spider.ma.db.sys.service.DbAppKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用接入信息 service实现
 */
@Service("appKeyService")
public class AppKeyService extends ServiceImpl<AppKeyDao, AppKeyEntity> {
    @Autowired
    private DbAppKeyService dbAppKeyService;
    @Autowired
    private AppRedis appRedis;

    public String getKeyByAppId(String app_id) {
        AppKeyEntity appKeyEntity = appRedis.get(app_id);
        if (appKeyEntity == null) {
            appKeyEntity = dbAppKeyService.getKeyByAppInfo(app_id);
            appRedis.saveOrUpdate(appKeyEntity);
        }
        return appKeyEntity == null ? null : appKeyEntity.getAppKey();
    }

}
