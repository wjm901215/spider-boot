package com.spider.ma.modules.sys.model;


/**
 * 数据字典 model
 * @author wangjun
 * @date 2014-08-17
 */
public class DictionaryModel {
	
	/**id*/
	private Long id;
	/**信息类别*/
	private String codeType; 
	/**城市编码说*/
	private String cityCode; 
	/**信息值*/
	private String codeValue; 
	/**信息描述*/
	private String codeText; 
	/**信息名称*/
	private String codeName;
	/**代码状态：1-停用，0-正常*/
	private Integer state;
	/**排序号*/
	private Integer orderNum;
	/**是否通用*/
	private Integer isCommon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeText() {
		return codeText;
	}
	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getIsCommon() {
		return isCommon;
	}
	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}
	
}
