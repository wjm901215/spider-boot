package com.spider.ma.modules.test.service.impl;

import com.spider.core.common.utils.FastJsonUtil;
import com.spider.ma.db.msg.dto.MessageStructDTO;
import com.spider.ma.modules.test.service.TestService;
import com.spider.ma.producer.manager.MessageLogService;
import com.spider.ma.producer.sender.RabbitTestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * mq测试 service 实现
 *
 * @author SpiderMan
 * @version : com.spider.ma.modules.test.service.impl.TestServiceImpl,v 0.1 2021/8/2 22:27 Exp $$
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MessageLogService messageLogService;
    @Autowired
    private RabbitTestSender rabbitTestSender;

    /**
     * TODO 使用生产端可靠性消息投递方案，消息落库，对消息状态打标
     *
     * @param message 消息内容
     */
    @Override
    public void sendDirect(String message) {
        //TODO 业务数据处理
        //插入消息记录表
        String messageId=UUID.randomUUID().toString();
        MessageStructDTO messageStructDTO = MessageStructDTO.builder().message(message).messageId(messageId).build();
        String jsonStr = FastJsonUtil.toJSONString(messageStructDTO);
        messageLogService.addBrokerMessageLog(messageId, jsonStr, LocalDateTime.now());
        //发送消息
        rabbitTestSender.sendDirect(messageStructDTO);
    }
    /**
     * TODO 主题模式发送1
     * @param message 消息内容Json格式
     */
    @Override
    public void sendTopic1(String message) {
        String messageId=UUID.randomUUID().toString();
        MessageStructDTO messageStructDTO = MessageStructDTO.builder().message(message).messageId(messageId).build();
        String jsonStr = FastJsonUtil.toJSONString(messageStructDTO);
        messageLogService.addBrokerMessageLog(messageId, jsonStr, LocalDateTime.now());
        rabbitTestSender.sendTopic1(messageStructDTO);
    }
    /**
     * TODO 主题模式发送2
     * <p>由于routingKey2为test.test，会同时匹配到QUEUE_TWO、QUEUE_THERE两个队列，消费者中对QUEUE_TWO、QUEUE_THERE都会监听到</p>
     * @param message 消息内容Json格式
     */
    @Override
    public void sendTopic2(String message) {
        String messageId=UUID.randomUUID().toString();
        MessageStructDTO messageStructDTO = MessageStructDTO.builder().message(message).messageId(messageId).build();
        String jsonStr = FastJsonUtil.toJSONString(messageStructDTO);
        messageLogService.addBrokerMessageLog(messageId, jsonStr, LocalDateTime.now());
        rabbitTestSender.sendTopic2(messageStructDTO);
    }
    /**
     * TODO 延迟队列发送
     * @param message 消息内容
     */
    @Override
    public void sendDelay(String message) {
        String messageId=UUID.randomUUID().toString();
        MessageStructDTO messageStructDTO = MessageStructDTO.builder().message(message).messageId(messageId).build();
        String jsonStr = FastJsonUtil.toJSONString(messageStructDTO);
        messageLogService.addBrokerMessageLog(messageId, jsonStr, LocalDateTime.now());
        rabbitTestSender.sendDelay(messageStructDTO);
    }
}
