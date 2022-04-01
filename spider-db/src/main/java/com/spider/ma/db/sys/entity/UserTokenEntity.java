package com.spider.ma.db.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token表
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.entity.UserTokenEntity,v 0.1 2021/7/28 11:51 Exp $$
 */
@TableName("sys_user_token")
@Data
public class UserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userId;
	/**
	 * TOKEN
	 */
	private String token;
	/**
	 * 登录IP
	 */
	private String createIp;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
