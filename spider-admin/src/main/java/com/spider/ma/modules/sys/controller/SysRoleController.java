package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.validator.ValidatorUtils;
import com.spider.ma.modules.sys.entity.RoleEntity;
import com.spider.ma.modules.sys.service.RoleService;
import com.spider.ma.modules.sys.service.RoleMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        PageUtils page = roleService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 角色下拉框列表
     */
    @PostMapping("/select")
    public R select(@RequestBody String[] tenantIds) {
        List<RoleEntity> list = roleService.queryRoleForSelector(tenantIds);
        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") String roleId) {
        RoleEntity role = roleService.selectById(roleId);

        //查询角色对应的菜单
        List<String> menuIdList = roleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody RoleEntity role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateBy(getUserId());
        roleService.save(role);

        return R.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody RoleEntity role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateBy(getUserId());
        roleService.update(role);

        return R.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody String[] roleIds) {
        roleService.deleteBatch(roleIds);

        return R.ok();
    }
}
