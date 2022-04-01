package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门管理
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-28 16:25:59
 */
@TableName("sys_dept")
@Data
public class DeptEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门简称
     */
    private String shortName;
    /**
     * 部门编码
     */
    private String code;
    /**
     * 上级部门ID
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
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
     * 机构层级，顶层机构为1，二级机构为2，以此类推
     */
    private Integer level;
    /**
     * 备注
     */
    private String remark;

    /**
     * 部门名字
     */
    @TableField(exist = false)
    private String parentName;

}
