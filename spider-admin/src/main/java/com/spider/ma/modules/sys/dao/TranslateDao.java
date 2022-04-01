package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.modules.sys.entity.TranslateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/12/4
 */
@Mapper
public interface TranslateDao extends BaseMapper<TranslateEntity> {

    List<TranslateEntity> queryTranslateByExtId(String extId);
}
