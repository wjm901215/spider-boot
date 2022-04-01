package com.spider.ma.modules.sys.redis;


import com.spider.ma.modules.sys.dao.ConfigDao;
import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.core.common.utils.FastJsonUtil;
import com.spider.core.common.utils.RedisKeys;
import com.spider.core.common.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017/7/18 21:08
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private ConfigDao configDao;


    public void saveOrUpdate(ConfigEntity config) {
        if (config == null) {
            return;
        }
        String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisUtils.set(key, config);
    }

    /**
     * 系统配置获取
     *
     * @param configKey
     * @return
     */
    public ConfigEntity getConfig(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        ConfigEntity configEntity = (ConfigEntity) redisUtils.get(key);
        if (null == configEntity) {
            configEntity = configDao.selectOne(new ConfigEntity() {{
                setParamKey(configKey);
            }});
            saveOrUpdate(configEntity);
        }
        return configEntity;
    }

    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getConfig(key).getParamValue();
        if (StringUtils.isNotBlank(value)) {
            return FastJsonUtil.getObject(value, clazz);
        }
        return null;
    }
}
