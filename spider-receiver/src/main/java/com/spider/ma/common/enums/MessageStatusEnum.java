package com.spider.ma.common.enums;

/**
 * 消息状态
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.common.enums.MessageStatusEnum,v 0.1 2021/8/2 22:39 Exp $$
 */
public enum MessageStatusEnum {
    /**
     * 发送中
     */
    MSG_SENDING(0),
    /**
     * 成功
     */
    MSG_SEND_SUCCESS(1),
    /**
     * 失败
     */
    MSG_SEND_FAILURE(2);
    private int value;

    MessageStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
