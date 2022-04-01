package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.AreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 区域表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-06 15:33:41
 */
public interface AreaService extends IService<AreaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean update(AreaEntity entity);

    boolean insert(AreaEntity entity);

    void deleteBatchIds(List<String> ids);

    List<Map> queryCascadeList(AreaEntity entity);

    /**
     * 获取机构级联
     * @return
     */
    List<Map> selectAreaOptions();

}

