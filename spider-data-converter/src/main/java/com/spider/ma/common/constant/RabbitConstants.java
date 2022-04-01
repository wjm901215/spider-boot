package com.spider.ma.common.constant;

/**
 * RabbitMQ常量池
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.common.constant.RabbitConstants,v 0.1 2021/7/30 09:52 Exp $$
 */
public interface RabbitConstants {
    /**
     * 分钟超时单位：min
     */
    int ORDER_TIMEOUT = 1;

    /**
     * 直接模式1,routing_key 完全匹配，交换机与MQ绑定
     */
    String DIRECT_MODE_QUEUE_ONE = "queue.direct";
    /**
     * 队列2
     */
    String QUEUE_TWO = "queue.two";

    /**
     * 队列3
     */
    String QUEUE_THREE = "queue.three";

    /**
     * 主题模式,routing_key 模糊匹配（*，#），交换机与MQ绑定
     */
    String TOPIC_MODE_QUEUE = "topic.mode";

    /**
     * 路由1
     */
    String TOPIC_ROUTING_KEY_ONE = "test.#";

    /**
     * 路由2
     */
    String TOPIC_ROUTING_KEY_TWO = "*.test";

    /**
     * 延迟队列交换器
     */
    String DELAY_MODE_QUEUE = "delay.mode";
    /**
     * 延迟队列
     */
    String DELAY_QUEUE = "queue.delay";

}
