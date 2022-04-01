package com.spider.ma.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年9月18日 上午9:28:13
 */
@TableName("sys_role_menu")
@Data
public class RoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private String id;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 菜单ID
	 */
	private String menuId;

	/**
	 * 节点是否半选 0否/1是
	 */
	private boolean isHalf;

}
