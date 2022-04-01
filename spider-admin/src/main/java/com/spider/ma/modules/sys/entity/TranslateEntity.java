package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author SpiderMan
 * @created 2021/12/4
 */
@Data
@TableName("base_translate")
public class TranslateEntity {

    @TableId
    private String id;

    /**
     * 语言
     */
    private String lang;

    /**
     * 外部ID
     */
    private String extId;

    /**
     * 外部类型(1目录 2数据字典)
     */
    private String extType;

    /**
     * 翻译
     */
    private String translation;

    /**
     * 语言名
     */
    @TableField(exist = false)
    private String langName;
}
