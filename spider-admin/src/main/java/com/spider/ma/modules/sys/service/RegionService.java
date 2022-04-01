package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.RegionEntity;
import com.spider.ma.modules.sys.model.RegionModel;

import java.util.List;
import java.util.Map;

/**
 * 行政区域信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 10:45:34
 */
public interface RegionService extends IService<RegionEntity> {

    Page<RegionEntity> queryPage(Page<RegionEntity> page,Map<String, Object> params);

    List<RegionEntity> queryList(RegionModel model);

    RegionEntity queryById(Long id);

    /**
     * 获取区域树
     * @param cityCode
     * @return
     */
    List getRegionTree(String cityCode,String treeControls);

    /**
     * 获取ID向上递归查找
     * @param id
     * @return
     */
    List getRecursiveUpId(Long id);

    List<RegionEntity> queryAllRegion(RegionModel regionModel);
}

