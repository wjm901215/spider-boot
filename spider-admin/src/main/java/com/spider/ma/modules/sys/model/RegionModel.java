package com.spider.ma.modules.sys.model;


public class RegionModel {

    /**
     * 区域id
     */
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
     * 区域级别(0.根节点，1.市，2.区，3.街道）
     */
    private Integer regionLevel;
    /**
     * 上级区域id
     */
    private Long parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 中心点经度
     */
    private Double longitude;
    /**
     * 中心点维度
     */
    private Double latitude;
    /**
     * 备注
     */
    private String note;
    /**
     * 区域类型
     */
    private Integer regionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }
}
