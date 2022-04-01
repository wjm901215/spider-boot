package com.spider.ma.db.sys.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.db.sys.dao.AppKeyDao;
import com.spider.ma.db.sys.entity.AppKeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * appKeyService实现
 */
@Service("dbAppKeyService")
public class DbAppKeyService extends ServiceImpl<AppKeyDao, AppKeyEntity> {
    @Autowired
    private AppKeyDao appKeyDao;

    /**
     * 根据应用ID获取应用信息
     *
     * @param app_id
     * @return
     */
    public AppKeyEntity getKeyByAppInfo(String app_id) {
        AppKeyEntity appKeyEntity = appKeyDao.queryKeyByAppId(app_id);
        return appKeyEntity;
    }
}
