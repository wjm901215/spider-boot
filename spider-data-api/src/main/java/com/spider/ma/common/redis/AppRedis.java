package com.spider.ma.common.redis;


import com.spider.core.common.utils.RedisKeys;
import com.spider.core.common.utils.RedisUtil;
import com.spider.ma.db.sys.dao.AppKeyDao;
import com.spider.ma.db.sys.entity.AppKeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 应用 redis
 * @author wangjunma
 */
@Component
public class AppRedis {
    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private AppKeyDao appKeyDao;

    public void saveOrUpdate(AppKeyEntity appKeyEntity) {
        if (appKeyEntity == null) {
            return;
        }
        String key = RedisKeys.getAppKey(appKeyEntity.getAppId());
        redisUtils.set(key, appKeyEntity);
    }

    public void delete(String appId) {
        String key = RedisKeys.getAppKey(appId);
        redisUtils.del(key);
    }

    public AppKeyEntity get(String appId) {
        String key = RedisKeys.getAppKey(appId);
        AppKeyEntity appKeyEntity = (AppKeyEntity) redisUtils.get(key);
        if (null == appKeyEntity) {
            appKeyEntity = appKeyDao.selectOne(new AppKeyEntity() {{
                setAppId(appId);
            }});
            saveOrUpdate(appKeyEntity);
        }
        return appKeyEntity;
    }
}
