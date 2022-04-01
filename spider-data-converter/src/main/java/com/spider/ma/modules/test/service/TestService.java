package com.spider.ma.modules.test.service;

/**
 * mq测试 service
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.test.service.TestService,v 0.1 2021/8/2 22:25 Exp $$
 */
public interface TestService {
    /**
     * 直接模式发送
     * <p>使用生产端可靠性消息投递方案，消息落库，对消息状态打标</p>
     * @param message 消息内容
     */
    void sendDirect(String message);
    /**
     * 主题模式发送1
     * @param message 消息内容
     */
    void sendTopic1(String message);
    /**
     * 主题模式发送2
     * <p>由于routingKey2为test.test，会同时匹配到QUEUE_TWO、QUEUE_THERE两个队列，消费者中对QUEUE_TWO、QUEUE_THERE都会监听到</p>
     * @param message 消息内容
     */
    void sendTopic2(String message);
    /**
     * 延迟队列发送
     * @param message 消息内容
     */
    void sendDelay(String message);
}
