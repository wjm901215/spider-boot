package com.spider.ma.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年9月18日 上午9:28:39
 */
@TableName("sys_user_role")
@Data
public class UserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private String id;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 角色ID
	 */
	private String roleId;


	
}
