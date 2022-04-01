package com.spider.ma.modules.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.test.entity.MqMessageEntity;

import java.util.List;

/**
 * rabbitmq消息测试表Service
 *
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-01 15:47:21
 */
public interface MqMessageService extends IService<MqMessageEntity> {
    boolean update(MqMessageEntity entity);

    @Override
    boolean insert(MqMessageEntity entity);

    void deleteBatchIds(List<String> ids);
}

