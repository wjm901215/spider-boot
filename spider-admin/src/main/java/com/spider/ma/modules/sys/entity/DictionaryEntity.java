package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统字典表
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 14:48:35
 */
@TableName("sys_dictionary")
@Data
public class DictionaryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 信息类别
     */
    private String codeType;
    /**
     * 信息值
     */
    private String codeValue;
    /**
     * 信息描述
     */
    private String codeText;
    /**
     * 信息名称
     */
    private String codeName;
    /**
     * 代码状态（1.停用；0.正常）
     */
    private Integer state;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 是否通用（1.通用；0.非通用）
     */
    private Integer isCommon;

    /**
     * 翻译
     */
    @TableField(exist = false)
    private String translation;
    /**
     * 语言
     */
    @TableField(exist = false)
    private String lang;

}
