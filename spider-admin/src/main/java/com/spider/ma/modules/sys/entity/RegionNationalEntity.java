package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2019-04-03 16:57:26
 */
@TableName("sys_region_national")
@Data
public class RegionNationalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 区域编号
	 */
	@TableId
	private Integer regionCode;
	/**
	 * 区域编号
	 */
	private String regionName;
	/**
	 * 父级区域ID
	 */
	private Integer parentCode;
	/**
	 * 级别  1：省；2：地市；3：区县
	 */
	private Integer level;
	/**
	 * 是否最后一级  0：不是最后一级；1：是最后一级
	 */
	private Integer isLastLevel;
	/**
	 * 中心点经度
	 */
	private BigDecimal longitude;
	/**
	 * 中心点纬度
	 */
	private BigDecimal latitude;

}
