package com.spider.ma.db.msg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试消息体
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.message.MessageStruct,v 0.1 2021/7/31 23:19 Exp $$
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageStructDTO implements Serializable {
    private static final long serialVersionUID = 392365881428311040L;
    /**
     * 消息
     */
    private String message;
    /**
     * 消息唯一ID，主要用于解决消息幂等
     */
    private String messageId;
}
