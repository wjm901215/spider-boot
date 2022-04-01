package com.spider.ma.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.db.msg.dto.MessageStructDTO;
import com.spider.ma.db.msg.dto.TestMessage1DTO;
import com.spider.ma.modules.test.entity.MqMessageEntity;
import com.spider.ma.modules.test.service.MqMessageService;
import com.spider.ma.modules.test.service.SenderMqMsgService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 直接队列1 处理器
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.handler.DirectQueueOneHandler,v 0.1 2021/8/1 11:18 Exp $$
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstants.DIRECT_MODE_QUEUE_ONE)
public class DirectQueueOneHandler {
    @Autowired
    private MqMessageService mqMessageService;
    @Autowired
    private SenderMqMsgService senderMqMsgService;

    @RabbitHandler
    public void directHandlerManualAck(MessageStructDTO messageStructDTO,Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // TODO 获取消息Id，根据messageId判断该消息是否已经消费过
            String messageId = messageStructDTO.getMessageId();
            log.info("直接队列1，手动ACK，接收消息：{}，messageID：{}", messageStructDTO,messageId);
            MqMessageEntity mqMessageEntity = new MqMessageEntity();
            mqMessageEntity.setMessage(messageStructDTO.getMessage());
            mqMessageService.insert(mqMessageEntity);
            //TODO 消息发送到新的MQ，由 sender服务进行监听，并转发至第三方系统
            TestMessage1DTO messageTest1DTO = TestMessage1DTO.builder().message(messageId).time(LocalDateTime.now()).code("123456").build();
            senderMqMsgService.sendMqMsg1(messageTest1DTO);
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,直接拒绝，可以记录DB后续处理，或者加入死信队列
                channel.basicReject(deliveryTag,false);
            } catch (IOException e1) {
                log.error("消息拒绝失败{}", ExceptionUtil.stacktraceToString(e1));
            }
            log.error("消息处理失败{}", ExceptionUtil.stacktraceToString(e));
        }
    }
}
