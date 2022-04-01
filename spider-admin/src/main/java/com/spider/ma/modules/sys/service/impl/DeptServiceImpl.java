package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.core.common.utils.OrderNumGen;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.MapTransformUtil;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.DeptDao;
import com.spider.ma.modules.sys.entity.DeptEntity;
import com.spider.ma.modules.sys.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/28
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptEntity> implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Map> queryCascadeList() {
        return deptDao.queryCascadeList(new HashMap() {{
            /*if (!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
                put("userId", ShiroUtils.getUserId());
            }*/
        }});
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        page.setRecords(deptDao.page(params));
        return new PageUtils(page);
    }


    @Override
    public boolean update(DeptEntity entity) {
        double[] position = MapTransformUtil.gaode2Baidu(entity.getLongitudeGaode(), entity.getLatitudeGaode());
        entity.setLatitudeBaidu(position[0]);
        entity.setLongitudeBaidu(position[1]);
        return super.updateById(entity);
    }

    @Override
    public boolean insert(DeptEntity entity) {
        entity.setTenantId(ShiroUtils.getUserEntity().getTenantId());
        entity.setCode(OrderNumGen.next(com.spider.core.common.constant.Constant.BusiTypeEnum.DEPT.key));
        entity.setDeleted(BaseConstants.Deleted.F.value);
        double[] position = MapTransformUtil.gaode2Baidu(entity.getLongitudeGaode(), entity.getLatitudeGaode());
        entity.setLatitudeBaidu(position[0]);
        entity.setLongitudeBaidu(position[1]);
        return super.insert(entity);
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        deptDao.deleteBatchIds(ids);
    }
}
