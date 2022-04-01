package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.spider.ma.modules.sys.entity.RegionEntity;
import com.spider.ma.modules.sys.model.RegionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 行政区域信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 10:45:34
 */
@Mapper
public interface RegionDao extends BaseMapper<RegionEntity> {

    List<RegionEntity> queryList(RegionModel model);

    List<RegionEntity> queryPageList(Pagination page, Map<String, Object> params);

    RegionEntity queryById(Long id);

    List<RegionEntity> getRegionTree(@Param("regionCode") String cityCode);

    /**
     * 根据ID向上查找ID
     * @param id
     * @return
     */
    List queryRecursiveUpId(@Param("id") Long id);

    List<RegionEntity> queryAllRegion(RegionModel regionModel);
}
