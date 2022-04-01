package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 租户表
 * 
 * @author SpiderMan
 * @email SpiderMan@mail.com
 * @date 2019-07-24 17:03:34
 */
@TableName("base_tenant")
@Data
public class TenantEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private String id;
	/**
	 * 租户名称
	 */
	private String name;
	/**
	 * 租户编码
	 */
	private String code;

}
