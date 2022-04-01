package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户数据权限表
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-29 10:01:39
 */
@TableName("sys_user_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 外部ID
	 */
	private String extId;
	/**
	 * 外部名称
	 */
	@TableField(exist = false)
	private String extName;
	/**
	 * 外部类型（10租户、20中队、30路段）
	 */
	private Integer extType;

}
