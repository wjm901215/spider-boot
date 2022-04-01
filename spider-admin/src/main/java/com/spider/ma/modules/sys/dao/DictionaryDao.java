package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.modules.sys.entity.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统字典表
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 14:48:35
 */
@Mapper
public interface DictionaryDao extends BaseMapper<DictionaryEntity> {

    List<Map> selectDictionaryType(Page page, Map params);


    List<DictionaryEntity> queryInfoByCodeName(DictionaryEntity DictionaryEntity);


}
