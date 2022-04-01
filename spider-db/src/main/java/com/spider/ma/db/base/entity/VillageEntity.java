package com.spider.ma.db.base.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 小区表
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-07-30 11:34:23
 */
@TableName("base_village")
@Data
public class VillageEntity extends BaseEntity implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
    @TableId
    private String id;
	/**
	 * 街道编码
	 */
    private String streetCode;
	/**
	 * 社区编码
	 */
    private String commCode;
	/**
	 * 小区名称
	 */
    private String villName;
	/**
	 * 小区编码
	 */
    private String villCode;
	/**
	 * 地址
	 */
    private String address;
	/**
	 * 运营单位ID
	 */
    private String companyId;
	/**
	 * 图片地址
	 */
    private String photo;
	/**
	 * 是否覆盖
	 */
    private Integer status;
	/**
	 * 总户数
	 */
    private Integer totalNum;
	/**
	 * 常住户数
	 */
    private Integer permNum;
	/**
	 * 参与户数
	 */
    private Integer partNum;
	/**
	 * 经度（高德）
	 */
    private BigDecimal longitude;
	/**
	 * 纬度（高德）
	 */
    private BigDecimal latitude;
	/**
	 * 是否删除0:正常;1:删除
	 */
    private Integer deleted;
	/**
	 * 创建人
	 */
    private String createBy;
	/**
	 * 创建时间
	 */
    private LocalDateTime createTime;
	/**
	 * 更新人
	 */
    private String updateBy;
	/**
	 * 更新时间
	 */
    private LocalDateTime updateTime;

	/**
	 * 街道名称
	 */
	@TableField(exist = false)
	private String streetName;
	/**
	 * 社区名称
	 */
	@TableField(exist = false)
	private String commName;

	/**
	 * 运营单位ID
	 */
	@TableField(exist = false)
	private String company;

}