package com.spider.ma.modules.sys.dao;

import com.spider.ma.common.annotation.DataAssessAop;
import com.spider.ma.modules.sys.entity.DeptEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-28 16:25:59
 */
@Mapper
public interface DeptDao extends BaseMapper<DeptEntity> {

    void deleteBatchIds(List<String> ids);

    @DataAssessAop
    List<DeptEntity> page(Map params);

    List<Map> queryCascadeList(Map map);
	
}
