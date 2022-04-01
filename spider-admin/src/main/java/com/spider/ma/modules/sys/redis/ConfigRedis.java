/**
 * Copyright 2021 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.spider.ma.modules.sys.redis;


import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.core.common.utils.RedisKeys;
import com.spider.core.common.utils.RedisUtil;
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
public class ConfigRedis {
    @Autowired
    private RedisUtil redisUtils;


    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.del(key);
    }

    public ConfigEntity get(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        return (ConfigEntity) redisUtils.get(key);
    }

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
        return (ConfigEntity) redisUtils.get(key);
    }

}
