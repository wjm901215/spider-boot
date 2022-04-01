package com.spider.ma.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 事件总线配置
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.config.EventBusConfig,v 0.1 2021/7/20 11:27 Exp $$
 */
@Configuration
public class EventBusConfig {

    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }
}
