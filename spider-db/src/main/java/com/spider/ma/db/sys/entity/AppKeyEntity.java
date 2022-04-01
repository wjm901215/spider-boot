package com.spider.ma.db.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用接入密钥表
 *
 * @author Spiderman
 * @date 2018-09-18 10:47:01
 */
@Data
@TableName(value = "base_app_key")
public class AppKeyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 应用ID
     */
    private String tenantId;
    /**
     * 应用密钥值
     */
    private String appKey;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用状态(0.正常1.停用)
     */
    private String appStatus;
    /**
     * 应用描述
     */
    private String appDesc;
    /**
     * 是否删除
     */
    private int deleted = 0;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 创建时间,默认SYSDATE
     */
    private Date createTime;
    /**
     * 更新时间,默认SYSDATE
     */
    private Date updateTime;

}
