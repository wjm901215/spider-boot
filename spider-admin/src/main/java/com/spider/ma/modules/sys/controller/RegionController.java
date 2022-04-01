package com.spider.ma.modules.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.RegionEntity;
import com.spider.ma.modules.sys.model.RegionModel;
import com.spider.ma.modules.sys.service.RegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 行政区域信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 10:45:34
 */
@RestController
@RequestMapping("/sys/region")
public class RegionController extends AbstractController{
    @Autowired
    private RegionService regionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:region:list")
    public R list(Page<RegionEntity> page, @RequestParam Map<String, Object> params) {
        Page res = regionService.queryPage(page, params);
        return R.ok().put("page", res);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:region:info")
    public R info(@PathVariable("id") Integer id) {
        RegionEntity region = regionService.selectById(id);
        return R.ok().put("region", region);
    }

    /**
     * 保存page
     */
    @RequestMapping(value = "/insertOrUpdate")
    @RequiresPermissions("sys:region:insertOrUpdate")
    public R save(@RequestBody RegionEntity region) {
        regionService.insertOrUpdate(region);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delByList")
    @RequiresPermissions("sys:region:delByList")
    public R deleteByList(@RequestBody Long[] ids) {
        regionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/queryList")
    public R queryList(RegionModel model) {
        List<RegionEntity> regionList = regionService.queryList(model);
        return R.ok().put("rows", regionList);
    }

    /**
     * 获取ID向上递归查找
     *
     * @return
     */
    @RequestMapping("/getRecursiveUpId")
    public R getRecursiveUpId(@RequestBody Long id) {
        List list=regionService.getRecursiveUpId(id);
        return R.ok().put("rows",list);
    }
}
