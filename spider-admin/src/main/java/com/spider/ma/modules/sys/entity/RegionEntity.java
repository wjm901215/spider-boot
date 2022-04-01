package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 行政区域信息
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 10:45:34
 */
@TableName("BASE_REGION")
public class RegionEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 区域id
	 */
	@TableId
	private Long id;
	/**
	 * 区域代码
	 */
	private String regionCode;
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 区域简称
	 */
	private String shortName;
	/**
	 * 上级区域id
	 */
	private Long parentId;
	/**
	 * 区域级别(0.根节点，1.市，2.区，3.街道）
	 */
	private Integer regionLevel;
	/**
	 * 排序号
	 */
	private Integer orderNum;
	/**
	 * 中心点经度
	 */
	private Integer longitude;
	/**
	 * 中心点维度
	 */
	private Integer latitude;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 区域类型（0 普通区域 1 运营区域）
	 */
	private Integer regionType;

	/**
	 * 设置：区域id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：区域id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：区域代码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * 获取：区域代码
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * 设置：区域名称
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 获取：区域名称
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 设置：区域简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * 获取：区域简称
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * 设置：上级区域id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级区域id
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：区域级别(0.根节点，1.市，2.区，3.街道）
	 */
	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}
	/**
	 * 获取：区域级别(0.根节点，1.市，2.区，3.街道）
	 */
	public Integer getRegionLevel() {
		return regionLevel;
	}
	/**
	 * 设置：排序号
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序号
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：中心点经度
	 */
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：中心点经度
	 */
	public Integer getLongitude() {
		return longitude;
	}
	/**
	 * 设置：中心点维度
	 */
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：中心点维度
	 */
	public Integer getLatitude() {
		return latitude;
	}
	/**
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：区域类型（0 普通区域 1 运营区域）
	 */
	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}
	/**
	 * 获取：区域类型（0 普通区域 1 运营区域）
	 */
	public Integer getRegionType() {
		return regionType;
	}
}
