package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


import java.io.Serializable;

/**
 * 编码表
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-08 00:20:50
 */
@TableName("BASE_CODE")
public class CodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 编码类型
	 */
	private String codeType;
	/**
	 * 前缀
	 */
	private String prefix;
	/**
	 * 最大编号
	 */
	private Long maxCode;
	/**
	 * 最大长度
	 */
	private Integer maxLen;

	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：城市编码
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * 获取：城市编码
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * 设置：编码类型
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	/**
	 * 获取：编码类型
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * 设置：前缀
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * 获取：前缀
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * 设置：最大编号
	 */
	public void setMaxCode(Long maxCode) {
		this.maxCode = maxCode;
	}
	/**
	 * 获取：最大编号
	 */
	public Long getMaxCode() {
		return maxCode;
	}
	/**
	 * 设置：最大长度
	 */
	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}
	/**
	 * 获取：最大长度
	 */
	public Integer getMaxLen() {
		return maxLen;
	}
}
