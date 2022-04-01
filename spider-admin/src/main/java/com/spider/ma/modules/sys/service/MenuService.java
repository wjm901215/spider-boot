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

package com.spider.ma.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.MenuEntity;

import java.util.List;


/**
 * 菜单管理
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<MenuEntity> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<MenuEntity> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> getUserMenuList(String userId);

    /**
     * 删除
     */
    void delete(String menuId);

    /**
     * 获取用户菜单按钮
     * @return
     */
    List<MenuEntity> queryMenuByUserId();


    MenuEntity queryById(String id);
}
