package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.modules.sys.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     */
    List<MenuEntity> queryListParentId(Map map);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuEntity> queryNotButtonList(Map map);


    /**
     * 查询所有菜单按钮根据登陆用户
     */
    List<MenuEntity> queryMenuByUserId(Map params);


    MenuEntity queryById(String id);
}
