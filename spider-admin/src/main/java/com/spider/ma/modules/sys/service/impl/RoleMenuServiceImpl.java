package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.modules.sys.dao.RoleMenuDao;
import com.spider.ma.modules.sys.entity.RoleMenuEntity;
import com.spider.ma.modules.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



/**
 * 角色与菜单对应关系
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> menuIdList,List<String> halfMenuIdList) {
		//先删除角色与菜单关系
		deleteBatch(new String[]{roleId});
		if(menuIdList.size() == 0){
			return ;
		}
		//保存角色与菜单关系
		List<RoleMenuEntity> list = new ArrayList<>();
		for(String menuId : menuIdList){
			RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
			roleMenuEntity.setMenuId(menuId);
			roleMenuEntity.setRoleId(roleId);
			roleMenuEntity.setHalf(true);
			list.add(roleMenuEntity);
		}
		for(String menuId : halfMenuIdList){
			RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
			roleMenuEntity.setMenuId(menuId);
			roleMenuEntity.setRoleId(roleId);
			roleMenuEntity.setHalf(false);
			list.add(roleMenuEntity);
		}
		this.insertBatch(list);
	}

	@Override
	public List<String> queryMenuIdList(String roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	public int deleteBatch(String[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

}
