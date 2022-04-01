package com.spider.ma.modules.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.modules.test.dao.MqMessageDao;
import com.spider.ma.modules.test.entity.MqMessageEntity;
import com.spider.ma.modules.test.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * rabbitmq消息测试表Service实现
 *
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-01 15:47:21
 */
@Service("mqMessageService")
public class MqMessageServiceImpl extends ServiceImpl<MqMessageDao, MqMessageEntity> implements MqMessageService {
    @Autowired
    private MqMessageDao mqMessageDao;


    @Override
    public boolean update(MqMessageEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean insert(MqMessageEntity entity) {
        return super.insert(entity);
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        mqMessageDao.deleteBatchIds(ids);
    }
}
