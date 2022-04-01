package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.modules.sys.entity.AreaEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 区域表
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-06 15:33:41
 */
@Mapper
public interface AreaDao extends BaseMapper<AreaEntity> {

    void deleteBatchIds(List<String> ids);

    List<AreaEntity> page(Map params);

    List<Map> queryCascadeList(Map map);

    List<Map> queryAreaOptions();
}
