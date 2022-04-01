package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 区域表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-06 15:33:41
 */
@TableName("sys_area")
@Data
public class AreaEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 上级区域ID
     */
    private String parentId;
    /**
     * 区域级别
     */
    private Integer level;
    /**
     * 经度（百度）
     */
    private Double longitudeBaidu;
    /**
     * 维度（百度）
     */
    private Double latitudeBaidu;
    /**
     * 经度（高德）
     */
    private Double longitudeGaode;
    /**
     * 维度（高德）
     */
    private Double latitudeGaode;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上级区域名
     */
    @TableField(exist = false)
    private String parentName;
    /**
     * 子级数据，页面上某个模块关联到区域下面用
     */
    @TableField(exist = false)
    private List<Map> childrenObj;
    /**
     *
     */
    @TableField(exist = false)
    private String provinceCode;
    /**
     *
     */
    @TableField(exist = false)
    private String cityCode;
    /**
     *
     */
    @TableField(exist = false)
    private String regionCode;

}
