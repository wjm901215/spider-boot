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

package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.ConfigDao;
import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.ma.modules.sys.redis.ConfigRedis;
import com.spider.ma.modules.sys.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.spider.ma.common.enumerate.ResponseConstants.ExcelResponseEnum.USER_EXIST_ERROR;

@Service("configService")
@Slf4j
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {
    @Autowired
    private ConfigRedis configRedis;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConfigEntity> page = new Query<ConfigEntity>(params).getPage();
        page.setRecords(baseMapper.queryList(page, params));
        return new PageUtils(page);
    }

    @Override
    public void save(ConfigEntity config) {
        config.setCreateBy(ShiroUtils.getUserId());
        config.setStatus(BaseConstants.Status.ENABLE.key);
        config.setDeleted(BaseConstants.Deleted.F.value);
        this.insert(config);
        configRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ConfigEntity config) {
        this.updateById(config);
        configRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        configRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] ids) {
        for (String id : ids) {
            ConfigEntity config = this.selectById(id);
            configRedis.delete(config.getParamKey());
        }

        this.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        ConfigEntity config = configRedis.get(key);
        if (config == null) {
            config = baseMapper.queryByKey(key);
            configRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getParamValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException("获取参数失败");
        }
    }

    /**
     * 批量插入配置数据
     * @param list 配置文件集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertConfigDataBatch(List<ConfigEntity> list) {
        try{
            this.insertBatch(list);
        }catch (Exception ex){
            log.error("批量插入配置数据异常",ex);
            throw new BusinessException(USER_EXIST_ERROR.value,USER_EXIST_ERROR.key);
        }
    }
}
