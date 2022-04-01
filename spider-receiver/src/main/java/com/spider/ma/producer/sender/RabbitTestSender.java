package com.spider.ma.producer.sender;

import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.db.msg.dto.TestMessage1DTO;
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
     * TODO 直接模式发送
     * @param messageTest1DTO   消息内容
     */
    public void sendDirect(TestMessage1DTO messageTest1DTO) {
        CorrelationData correlationData = new CorrelationData(messageTest1DTO.getMessageId());
        rabbitTemplate.convertAndSend(RabbitConstants.DIRECT_MODE_QUEUE_TEST1, messageTest1DTO, correlationData);
    }

}
