package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.modules.sys.dao.RegionDao;
import com.spider.ma.modules.sys.entity.RegionEntity;
import com.spider.ma.modules.sys.model.RegionModel;
import com.spider.ma.modules.sys.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("regionService")
public class RegionServiceImpl extends ServiceImpl<RegionDao, RegionEntity> implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public List<RegionEntity> queryList(RegionModel model) {
        return regionDao.queryList(model);
    }

    @Override
    public RegionEntity queryById(Long id) {
        return regionDao.queryById(id);
    }

    @Override
    public List getRegionTree(String cityCode,String treeControls) {
        EntityWrapper<RegionEntity> ew = new EntityWrapper<>();
        ew.where("REGION_CODE ={0}",cityCode);
        RegionEntity region=this.selectOne(ew);
        List treeData= new ArrayList();
        if(null!=region) {
            Map<String, Object> treeRoot = new HashMap(){{
                put("value", region.getId());
                put("level",region.getRegionLevel());
                put(treeControls, region.getRegionName());
                if(BaseConstants.TreeControls.TREE.getValue().equals(treeControls)) {
                    put("expand", true);
                }
            }};
            //获取子区域
            List children = getChildNode(treeControls,region.getId());
            if (!CollectionUtils.isEmpty(children)) {
                treeRoot.put("children", (children));
            }
            treeData.add(treeRoot);
        }
        return treeData;
    }

    /**
     * 递归查询子区域
     *
     *
     * @param treeControls
     * @param parentId 父ID
     * @return
     */
    private List getChildNode(String treeControls, Long parentId) {
        EntityWrapper<RegionEntity> ew = new EntityWrapper<>();
        ew.where("PARENT_ID ={0}",parentId);
        List<RegionEntity> treeList = this.selectList(ew);
        if(CollectionUtils.isEmpty(treeList)){
            return null;
        }
        ArrayList<Map> treeData = new ArrayList<>();
        for (RegionEntity region:treeList) {
            Map treeNode = new HashMap(){{
                put("value", region.getId());
                put("level",region.getRegionLevel());
                put(treeControls, region.getRegionName());
                if(BaseConstants.TreeControls.TREE.getValue().equals(treeControls)) {
                    put("expand", true);
                }
            }};
            //获取子区域
            List children = getChildNode(treeControls, region.getId());
            if (!CollectionUtils.isEmpty(children)) {
                treeNode.put("children", (children));
            }
            treeData.add(treeNode);
        }
        return treeData;
    }

    @Override
    public Page<RegionEntity> queryPage(Page<RegionEntity> page,Map<String, Object> params) {
        return page.setRecords(regionDao.queryPageList(page, params));
    }

    @Override
    public List getRecursiveUpId(Long id) {
        return regionDao.queryRecursiveUpId(id);
    }

    @Override
    public List<RegionEntity> queryAllRegion(RegionModel regionModel) {
        return regionDao.queryAllRegion(regionModel);
    }

}
