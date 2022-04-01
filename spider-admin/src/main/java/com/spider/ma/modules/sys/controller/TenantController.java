package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.entity.TenantEntity;
import com.spider.ma.modules.sys.entity.UserDataEntity;
import com.spider.ma.modules.sys.service.TenantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 租户表
 *
 * @author SpiderMan
 * @email SpiderMan@mail.com
 * @date 2019-07-24 17:03:34
 */
@RestController
@RequestMapping("base/tenant")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("base:tenant:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tenantService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    public R select(){
        List<TenantEntity> list = tenantService.queryTenantList();
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("base:tenant:info")
    public R info(@PathVariable("id") String id){
			TenantEntity tenant = tenantService.selectById(id);

        return R.ok().put("tenant", tenant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("base:tenant:save")
    public R save(@RequestBody TenantEntity tenant){
			    tenantService.insert(tenant);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("base:tenant:update")
    public R update(@RequestBody TenantEntity tenant){
			    tenantService.update(tenant);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("base:tenant:delete")
    public R delete(@RequestBody String[] ids){
			    tenantService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 用户数据权限
     */
    @RequestMapping("/selectTenantData")
    public R selectTenantData(){
        List<UserDataEntity> list= ShiroUtils.getUserEntity().getUserDataEntity();
        String tenantId=ShiroUtils.getUserEntity().getTenantId();
        Map result=new HashMap(){{
            put("list",list);
            put("tenantId",tenantId);
        }};
        return R.ok().put("result", result);
    }

}
