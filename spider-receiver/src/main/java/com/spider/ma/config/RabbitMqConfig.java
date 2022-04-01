package com.spider.ma.config;

import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.common.enums.MessageStatusEnum;
import com.spider.ma.db.msg.service.DbMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * RabbitMQ配置
 * <p>主要是配置队列，如果提前存在该队列，可以省略本配置类</p>
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.config.RabbitMqConfig,v 0.1 2021/7/30 10:13 Exp $$
 */
@Slf4j
@Configuration
public class RabbitMqConfig {
    @Autowired
    private DbMessageLogService messageLogService;

    /**
     * 回调函数: confirm确认
     * <p>消息确认，生产者投递消息后，Broker收到消息，给生产者应答</p>
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        log.info("correlationData:{} ", correlationData);
        if (null != correlationData) {
            String messageId = correlationData.getId();
            if (ack) {
                //如果confirm返回成功 则进行更新
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                messageLogService.changeBrokerMessageLogStatus(messageId, MessageStatusEnum.MSG_SEND_SUCCESS.getValue(), LocalDateTime.now());
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                log.error("异常处理...{}", cause);
            }
        }
    };

    /**
     * 回调函数：return消息
     * <p>用于处理一些不可路由的消息【匹配不到交换机，routingkey无法匹配】,会回调改函数</p>
     *
     * @param message 消息内容
     * @param replyCode 回复代码
     * @param replyText 回复说明
     * @param exchange 交换机
     * @param routingKey 路由key
     */
    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        //如果使用了延迟队列插件，那么一定会调用该callback方法，因为数据并没有提交上去，而是提交在交换器中，延迟时间到了才提交上去，并非是bug！
        //用if进行判断交换机名称来捕捉该报错
        log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
    };

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        return rabbitTemplate;
    }


    /**
     * 直接模式队列1
     */
    @Bean
    public Queue directOneQueueTest1() {
        return new Queue(RabbitConstants.DIRECT_MODE_QUEUE_TEST1);
    }

}
