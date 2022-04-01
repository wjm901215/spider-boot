package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.modules.sys.dao.RegionNationalDao;
import com.spider.ma.modules.sys.entity.RegionNationalEntity;
import com.spider.ma.modules.sys.service.RegionNationalService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("regionNationalService")
public class RegionNationalServiceImpl extends ServiceImpl<RegionNationalDao, RegionNationalEntity> implements RegionNationalService {

    @Override
    public List list(RegionNationalEntity regionNationalEntity) {
        return baseMapper.selectList(new EntityWrapper<RegionNationalEntity>(){{
            setEntity(regionNationalEntity);
        }});
    }
}
