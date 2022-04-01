package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.TranslateEntity;

import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/12/4
 */
public interface TranslateService extends IService<TranslateEntity> {

    List<TranslateEntity> queryTranslateByExtId(String extId);

    String add(TranslateEntity entity);

    void update(TranslateEntity entity);
}
