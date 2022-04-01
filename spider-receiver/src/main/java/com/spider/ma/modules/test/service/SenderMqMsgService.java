package com.spider.ma.modules.test.service;

import com.spider.ma.db.msg.dto.TestMessage1DTO;

/**
 * TODO MQ业务消息发送 service
 *
 * @author SpiderMan
 * @version : com.spider.ma.modules.test.service.SenderMsg1Service,v 0.1 2021/8/8 10:37 Exp $$
 */
public interface SenderMqMsgService {
    /**
     * TODO 使用生产端可靠性消息投递方案，消息落库，对消息状态打标
     * @param messageTest1DTO
     */
    void sendMqMsg1(TestMessage1DTO messageTest1DTO);
}
