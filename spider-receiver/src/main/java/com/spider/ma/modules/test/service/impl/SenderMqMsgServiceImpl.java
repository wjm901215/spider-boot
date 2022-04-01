package com.spider.ma.modules.test.service.impl;

import com.spider.core.common.utils.FastJsonUtil;
import com.spider.ma.db.msg.dto.TestMessage1DTO;
import com.spider.ma.modules.test.service.SenderMqMsgService;
import com.spider.ma.producer.manager.MessageLogService;
import com.spider.ma.producer.sender.RabbitTestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * TODO MQ业务消息发送 service 实现
 * <p>具体说明</p>
 *
 * @author SpiderMan
 * @version : com.spider.ma.modules.test.service.impl.SenderMqMsgServiceImpl,v 0.1 2021/8/8 10:45 Exp $$
 */
@Service
public class SenderMqMsgServiceImpl implements SenderMqMsgService {
    @Autowired
    private MessageLogService messageLogService;
    @Autowired
    private RabbitTestSender rabbitTestSender;
    /**
     * TODO 使用生产端可靠性消息投递方案，消息落库，对消息状态打标
     * @param messageTest1DTO
     */
    @Override
    public void sendMqMsg1(TestMessage1DTO messageTest1DTO) {
        //TODO 业务数据处理
        //插入消息记录表
        String messageId= UUID.randomUUID().toString();
        messageTest1DTO.setMessageId(messageId);
        String jsonStr = FastJsonUtil.toJSONString(messageTest1DTO);
        messageLogService.addBrokerMessageLog(messageId, jsonStr, LocalDateTime.now());
        //发送消息
        rabbitTestSender.sendDirect(messageTest1DTO);
    }
}
