package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.DeptEntity;

import java.util.List;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/28
 */
public interface DeptService extends IService<DeptEntity> {

    List<Map> queryCascadeList();

    PageUtils queryPage(Map<String, Object> params);

    boolean update(DeptEntity entity);

    boolean insert(DeptEntity entity);

    void deleteBatchIds(List<String> ids);
}
