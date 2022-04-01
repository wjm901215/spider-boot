
package com.spider.ma.db.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.db.base.entity.VillageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 小区表Dao
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-07-30 11:34:23
 */
@Mapper
public interface VillageDao extends BaseMapper<VillageEntity> {

    List<VillageEntity> page(Page<VillageEntity> page,Map params);

}