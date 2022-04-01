package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.common.annotation.DataAssessAop;
import com.spider.ma.modules.sys.entity.PersonnelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 人员信息表
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-22 10:01:31
 */
@Mapper
public interface PersonnelDao extends BaseMapper<PersonnelEntity> {

    void deleteBatchIds(List<String> ids);

    @DataAssessAop
    List<PersonnelEntity> page(Page<PersonnelEntity> page, Map params);

    List<Map> getPersonnels(Map<String, Object> params);

    @DataAssessAop
    List<PersonnelEntity> pageService(Page<PersonnelEntity> page, Map params);
}
