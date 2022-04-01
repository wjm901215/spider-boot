/**
 * Copyright 2021 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.spider.ma.modules.sys.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.MenuDao;
import com.spider.ma.modules.sys.dao.TranslateDao;
import com.spider.ma.modules.sys.entity.MenuEntity;
import com.spider.ma.modules.sys.entity.TranslateEntity;
import com.spider.ma.modules.sys.service.MenuService;
import com.spider.ma.modules.sys.service.RoleMenuService;
import com.spider.ma.modules.sys.service.UserService;
import com.spider.core.common.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单管理
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 */
@Service("menuService")
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private TranslateDao translateDao;
    @Override
    public List<MenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
        List<MenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<MenuEntity> userMenuList = new ArrayList<>();
        for (MenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<MenuEntity> queryListParentId(String parentId) {
        HashMap params = new HashMap(4) {{
            put("parentId", parentId);
            put("lang", ShiroUtils.getUserEntity().getLang());
        }};
        return baseMapper.queryListParentId(params);
    }

    @Override
    public List<MenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList(new HashMap() {{
            put("lang", ShiroUtils.getUserEntity().getLang());
        }});
    }

    @Override
    public List<MenuEntity> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if (userId.equals(BaseConstants.SUPER_ADMIN)) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<String> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(String menuId) {
        //删除翻译
        translateDao.delete(new EntityWrapper<TranslateEntity>() {{
            where("EXT_ID = {0}", menuId);
            and("EXT_TYPE = {0}", BaseConstants.ExtType.MENU_NAME.value);
        }});
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        roleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    @Override
    public List<MenuEntity> queryMenuByUserId() {
        Map params = new HashMap();
        if (!BaseConstants.SUPER_ADMIN.equals(ShiroUtils.getUserId())) {
            params.put("userId", ShiroUtils.getUserId());
        }
        List<MenuEntity> menus = baseMapper.queryMenuByUserId(params);
        return menus;
    }

    @Override
    public MenuEntity queryById(String id) {
        return baseMapper.queryById(id);
    }

    /**
     * 获取所有菜单列表
     */
    private List<MenuEntity> getAllMenuList(List<String> menuIdList) {
        //查询根菜单列表
        List<MenuEntity> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<String> menuIdList) {
        List<MenuEntity> subMenuList = new ArrayList<>();

        for (MenuEntity entity : menuList) {
            //目录
            if (entity.getType() == BaseConstants.MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
