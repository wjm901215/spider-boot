package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.modules.sys.dao.TranslateDao;
import com.spider.ma.modules.sys.entity.TranslateEntity;
import com.spider.ma.modules.sys.service.TranslateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/12/4
 */
@Service("translateService")
public class TranslateServiceImpl extends ServiceImpl<TranslateDao, TranslateEntity> implements TranslateService {

    @Override
    public List<TranslateEntity> queryTranslateByExtId(String extId) {
        return baseMapper.queryTranslateByExtId(extId);
    }

    @Override
    public String add(TranslateEntity entity) {
        baseMapper.insert(entity);
        return entity.getId();

    }

    @Override
    public void update(TranslateEntity entity) {
        baseMapper.updateById(entity);
    }
}
