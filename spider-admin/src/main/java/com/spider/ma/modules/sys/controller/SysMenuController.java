package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.entity.MenuEntity;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.modules.sys.enumerate.SysConstants;
import com.spider.ma.modules.sys.service.ShiroService;
import com.spider.ma.modules.sys.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.spider.ma.modules.sys.enumerate.SysConstants.MenuResponseEnum;
import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav() {
        List<MenuEntity> menuList = menuService.getUserMenuList(getUserId());
        if (CollectionUtils.isEmpty(menuList)) {
            return R.error(SysConstants.MenuResponseEnum.MENU_NOT_FOUND.key, SysConstants.MenuResponseEnum.MENU_NOT_FOUND.value);
        }
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<MenuEntity> list() {
        return menuService.queryMenuByUserId();
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public R select() {
        //查询列表数据
        List<MenuEntity> menuList = menuService.queryNotButtonList();

        //添加顶级菜单
        MenuEntity root = new MenuEntity();
        root.setMenuId("0");
        root.setName("一级菜单");
        root.setParentId("-1");
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") String menuId) {
        MenuEntity menu = menuService.selectById(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody MenuEntity menu) {
        //数据校验
        verifyForm(menu);
        menu.setCreateBy(ShiroUtils.getUserId());
        menuService.insert(menu);
        return R.ok().put("row", menuService.queryById(menu.getMenuId()));
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody MenuEntity menu) {
        //数据校验
        verifyForm(menu);
        menuService.updateById(menu);
        return R.ok().put("row", menuService.queryById(menu.getMenuId()));
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@PathVariable("menuId") String menuId) {
        if (menuId.length() < 2) {
            return R.error(MenuResponseEnum.MENU_SYS_MENU_NOT_DELETE.key,MenuResponseEnum.MENU_SYS_MENU_NOT_DELETE.value);
        }
        //判断是否有子菜单或按钮
        List<MenuEntity> menuList = menuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error(MenuResponseEnum.MENU_SUB_MENU_DELETE.key,MenuResponseEnum.MENU_SUB_MENU_DELETE.value);
        }
        menuService.delete(menuId);

        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(MenuEntity menu) {
        //菜单
        if (menu.getType() == BaseConstants.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new BusinessException(MenuResponseEnum.MENU_URL_EMPTY.value,MenuResponseEnum.MENU_URL_EMPTY.key);
            }
        }

        //上级菜单类型
        int parentType = BaseConstants.MenuType.CATALOG.getValue();
        if (!menu.getParentId().equalsIgnoreCase(BaseConstants.MENU_PARENT_ID)) {
            MenuEntity parentMenu = menuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == BaseConstants.MenuType.CATALOG.getValue() ||
                menu.getType() == BaseConstants.MenuType.MENU.getValue()) {
            if (parentType != BaseConstants.MenuType.CATALOG.getValue()) {
                throw new BusinessException(MenuResponseEnum.MENU_DIRECTORY_TYPE.value,MenuResponseEnum.MENU_DIRECTORY_TYPE.key);
            }
            return;
        }

        //按钮
        if (menu.getType() == BaseConstants.MenuType.BUTTON.getValue()) {
            if (parentType != BaseConstants.MenuType.MENU.getValue()) {
                throw new BusinessException(MenuResponseEnum.MENU_MENU_TYPE.value,MenuResponseEnum.MENU_MENU_TYPE.key);
            }
            return;
        }
    }
}
