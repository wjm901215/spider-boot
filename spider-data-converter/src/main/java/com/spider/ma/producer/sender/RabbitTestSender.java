package com.spider.ma.producer.sender;

import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.db.msg.dto.MessageStructDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * rabbit生产者测试
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.producer.sender.RabbitTestSender,v 0.1 2021/8/1 11:20 Exp $$
 */
@Component
@Slf4j
public class RabbitTestSender {
    private final RabbitTemplate rabbitTemplate;


    public RabbitTestSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * rabbitmq 路由key1，匹配如下key
     *
     * @see RabbitConstants#TOPIC_ROUTING_KEY_ONE
     */
    private static final String ROUTING_KEY1 = "test.aaa.bbb";
    /**
     * rabbitmq 路由key2，匹配如下key
     *
     * @see RabbitConstants#TOPIC_ROUTING_KEY_TWO
     */
    private static final String ROUTING_KEY2 = "test.test";


    /**
     * TODO 直接模式发送
     * @param messageStructDTO   消息内容
     */
    public void sendDirect(MessageStructDTO messageStructDTO) {
        CorrelationData correlationData = new CorrelationData(messageStructDTO.getMessageId());
        rabbitTemplate.convertAndSend(RabbitConstants.DIRECT_MODE_QUEUE_ONE, messageStructDTO, correlationData);
    }

    /**
     * TODO 主题模式发送1
     * @param messageStructDTO   消息内容
     */
    public void sendTopic1(MessageStructDTO messageStructDTO) {
        CorrelationData correlationData = new CorrelationData(messageStructDTO.getMessageId());
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_MODE_QUEUE, ROUTING_KEY1,messageStructDTO, correlationData);
    }

    /**
     * TODO 主题模式发送2
     * <p>由于routingKey2为test.test，会同时匹配到QUEUE_TWO、QUEUE_THERE两个队列，消费者中对QUEUE_TWO、QUEUE_THERE都会监听到</p>
     * @param messageStructDTO   消息内容
     */
    public void sendTopic2(MessageStructDTO messageStructDTO) {
        CorrelationData correlationData = new CorrelationData(messageStructDTO.getMessageId());
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_MODE_QUEUE, ROUTING_KEY2, messageStructDTO, correlationData);
    }

    /**
     * TODO 延迟队列发送,延迟5秒发送
     * @param messageStructDTO   消息内容
     */
    public void sendDelay(MessageStructDTO messageStructDTO) {
        CorrelationData correlationData = new CorrelationData(messageStructDTO.getMessageId());
        rabbitTemplate.convertAndSend(RabbitConstants.DELAY_MODE_QUEUE, RabbitConstants.DELAY_QUEUE, messageStructDTO, _msg -> {
            _msg.getMessageProperties().setHeader("x-delay", 5000);
            return _msg;
        },correlationData);
    }

}
