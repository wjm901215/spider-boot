package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.DeptEntity;
import com.spider.ma.modules.sys.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author SpiderMan
 * @created 2021/11/28
 */
@RestController
@RequestMapping("sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("queryCascadeList")
    public R queryCascadeList() {
        return R.ok().put("data", deptService.queryCascadeList());
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deptService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("id") String id){
        DeptEntity dept = deptService.selectById(id);
        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody DeptEntity dept){
        deptService.insert(dept);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody DeptEntity dept){
        deptService.update(dept);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(@RequestBody String[] ids){
        deptService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
