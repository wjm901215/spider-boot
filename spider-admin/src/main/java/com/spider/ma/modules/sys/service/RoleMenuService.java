package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.RoleMenuEntity;

import java.util.List;



/**
 * 角色与菜单对应关系
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {
	
	void saveOrUpdate(String roleId, List<String> menuIdList, List<String> halfMenuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	
}
