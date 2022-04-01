package com.spider.ma.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.http.HttpRequest;
import com.spider.core.common.utils.FastJsonUtil;
import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.db.msg.dto.TestMessage1DTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 测试队列1处理
 *
 * @author SpiderMan
 * @version : com.spider.ma.handler.QueueTest1Handler,v 0.1 2021/8/8 09:57 Exp $$
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstants.QUEUE_TEST1)
public class QueueTest1Handler {

    @RabbitHandler
    public void directHandlerManualAck(TestMessage1DTO messageTest1DTO,Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String messageId = messageTest1DTO.getMessageId();
            log.info("测试队列1，手动ACK，接收消息：{},messageId:{}", messageTest1DTO,messageId);
            //TODO 消息发送至第三方系统，示例
            String msg = FastJsonUtil.toJSONString(messageTest1DTO);
            String body = HttpRequest.post("https://www.baidu.com").body(msg).execute().body();
            log.info("消息response:{}",body);
            //通知 MQ 消息已被成功消费,可以ACK了
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
