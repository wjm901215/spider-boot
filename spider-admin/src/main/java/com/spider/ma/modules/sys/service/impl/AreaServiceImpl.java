package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.enumerate.BaseConstants.AreaLevelEnum;
import com.spider.ma.common.utils.MapTransformUtil;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.modules.sys.dao.AreaDao;
import com.spider.ma.modules.sys.dao.RegionNationalDao;
import com.spider.ma.modules.sys.entity.AreaEntity;
import com.spider.ma.modules.sys.entity.RegionNationalEntity;
import com.spider.ma.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("areaService")
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private RegionNationalDao regionNationalDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        page.setRecords(areaDao.page(params));
        return new PageUtils(page);
    }


    @Override
    public boolean update(AreaEntity entity) {
        double[] position = MapTransformUtil.gaode2Baidu(entity.getLongitudeGaode(), entity.getLatitudeGaode());
        entity.setLatitudeBaidu(position[0]);
        entity.setLongitudeBaidu(position[1]);
        return super.updateById(entity);
    }

    @Override
    public boolean insert(AreaEntity areaEntity) {
        //verify region
        Integer count = baseMapper.selectCount(new EntityWrapper<AreaEntity>() {{
            setEntity(new AreaEntity() {{
                setCode(areaEntity.getRegionCode());
                setDeleted(BaseConstants.Deleted.F.value);
            }});
        }});
        if (count > 0) {
            throw new BusinessException("该行政区已创建");
        }
        areaEntity.setDeleted(BaseConstants.Deleted.F.value);
        double[] position = MapTransformUtil.gaode2Baidu(areaEntity.getLongitudeGaode(), areaEntity.getLatitudeGaode());
        areaEntity.setLatitudeBaidu(position[0]);
        areaEntity.setLongitudeBaidu(position[1]);
        areaEntity.setCode(areaEntity.getRegionCode());
        areaEntity.setLevel(AreaLevelEnum.REGION_LEVEL_AREA.getValue());
        //verify province
        AreaEntity province = baseMapper.selectOne(new AreaEntity() {{
            setCode(areaEntity.getProvinceCode());
            setDeleted(BaseConstants.Deleted.F.value);
        }});
        if (province == null) {
            RegionNationalEntity provinceRegion = regionNationalDao.selectOne(new RegionNationalEntity() {{
                setRegionCode(Integer.valueOf(areaEntity.getProvinceCode()));
            }});
            province = new AreaEntity();
            province.setParentId("-1");
            province.setName(provinceRegion.getRegionName());
            province.setLevel(AreaLevelEnum.REGION_LEVEL_PROVINCE.getValue());
            province.setCode(provinceRegion.getRegionCode() + "");
            province.setLongitudeGaode(areaEntity.getLongitudeGaode());
            province.setLatitudeGaode(areaEntity.getLatitudeGaode());
            province.setLongitudeBaidu(areaEntity.getLongitudeBaidu());
            province.setLatitudeBaidu(areaEntity.getLatitudeBaidu());
            baseMapper.insert(province);

        }
        //verify city
        AreaEntity city = baseMapper.selectOne(new AreaEntity() {{
            setCode(areaEntity.getCityCode());
            setDeleted(BaseConstants.Deleted.F.value);
        }});
        if (city == null) {
            RegionNationalEntity cityRegion = regionNationalDao.selectOne(new RegionNationalEntity() {{
                setRegionCode(Integer.valueOf(areaEntity.getCityCode()));
            }});
            city = new AreaEntity();
            city.setParentId(province.getId());
            city.setName(cityRegion.getRegionName());
            city.setLevel(AreaLevelEnum.REGION_LEVEL_CITY.getValue());
            city.setCode(cityRegion.getRegionCode() + "");
            city.setLongitudeGaode(areaEntity.getLongitudeGaode());
            city.setLatitudeGaode(areaEntity.getLatitudeGaode());
            city.setLongitudeBaidu(areaEntity.getLongitudeBaidu());
            city.setLatitudeBaidu(areaEntity.getLatitudeBaidu());
            baseMapper.insert(city);
        }
        //get region
        RegionNationalEntity region = regionNationalDao.selectOne(new RegionNationalEntity() {{
            setRegionCode(Integer.valueOf(areaEntity.getRegionCode()));
        }});
        areaEntity.setName(region.getRegionName());
        areaEntity.setParentId(city.getId());
        return super.insert(areaEntity);
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        areaDao.deleteBatchIds(ids);
    }

    @Override
    public List<Map> queryCascadeList(AreaEntity entity) {
        return baseMapper.queryCascadeList(new HashMap() {{
            /*if (!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
                put("userId", ShiroUtils.getUserId());
            }*/
            if (entity != null) {
                put("level", entity.getLevel());
            }
        }});
    }

    @Override
    public List<Map> selectAreaOptions() {
        return areaDao.queryAreaOptions();
    }
}
