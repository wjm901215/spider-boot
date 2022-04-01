package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.dao.LogDao;
import com.spider.ma.common.utils.Query;
import com.spider.ma.modules.sys.entity.LogEntity;
import com.spider.ma.modules.sys.service.LogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<LogEntity> page = this.selectPage(
                new Query<LogEntity>(params).getPage(),
                new EntityWrapper<LogEntity>().like(StringUtils.isNotBlank(key), "username", key)
                        .or().like(StringUtils.isNotBlank(key), "operation", key).orderBy("CREATE_DATE",false)
        );

        return new PageUtils(page);
    }
}
