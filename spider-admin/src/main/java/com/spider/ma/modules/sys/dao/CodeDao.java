package com.spider.ma.modules.sys.dao;

import com.spider.ma.modules.sys.entity.CodeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 编码表
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-08 00:20:50
 */
@Mapper
public interface CodeDao extends BaseMapper<CodeEntity> {

    CodeEntity getCodeByCityAndCodeType(Map<String,Object> map);
}
