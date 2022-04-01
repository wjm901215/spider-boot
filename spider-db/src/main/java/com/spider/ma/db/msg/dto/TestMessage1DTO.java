package com.spider.ma.db.msg.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO 业务测试1消息体
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.message.MessageStruct,v 0.1 2021/7/31 23:19 Exp $$
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestMessage1DTO implements Serializable {
    private static final long serialVersionUID = 392365881428311040L;
    /**
     * 消息内容
     */
    private String message;

    /**
     * 消息唯一ID，主要用于解决消息幂等
     */
    private String messageId;
    /**
     * 设备编号
     */
    private String code;
    /**
     * 当前时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
