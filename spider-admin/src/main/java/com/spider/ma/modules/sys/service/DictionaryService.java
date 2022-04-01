package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.DictionaryEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统字典表
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 14:48:35
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DictionaryEntity> queryInfoByCodeName(DictionaryEntity DictionaryEntity);

    void update(DictionaryEntity entity);

    void save(DictionaryEntity entity);

    void deleteByList(String[] ids);
}

