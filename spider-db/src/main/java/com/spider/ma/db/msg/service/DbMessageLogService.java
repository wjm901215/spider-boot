package com.spider.ma.db.msg.service;

import com.spider.ma.db.msg.dao.BrokerMessageLogDao;
import com.spider.ma.db.msg.entity.BrokerMessageLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Mq消息统一处理
 * <p>生产端可靠性消息投递方案，消息落库，对消息状态打标</p>
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.producer.message.manager.BrokerMessageLogManager,v 0.1 2021/8/2 22:31 Exp $$
 */
@Service
public class DbMessageLogService {
    @Autowired
    private BrokerMessageLogDao brokerMessageLogDao;

    /**
     * 消息落库，对消息状态打标
     * @param messageId 消息唯一ID
     * @param message 保存消息整体 转为JSON 格式存储入库
     * @param time 超时时间
     * @param status 消息投递状态 0投递中，1投递成功，2投递失败
     * @param nextRetryTime 下一次重试时间
     */
    public void addBrokerMessageLog(String messageId, String message, LocalDateTime time,int status,LocalDateTime nextRetryTime){
        BrokerMessageLogEntity brokerMessageLog = new BrokerMessageLogEntity();
        brokerMessageLog.setMessageId(messageId);
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(message);
        brokerMessageLog.setStatus(status);
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(nextRetryTime);
        brokerMessageLog.setCreateTime(LocalDateTime.now());
        brokerMessageLog.setUpdateTime(LocalDateTime.now());
        brokerMessageLogDao.insert(brokerMessageLog);
    }

    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId 消息唯一ID
     * @param status 消息状态
     * @param updateTime 更新时间
     */
    public void changeBrokerMessageLogStatus(String messageId, int status, LocalDateTime updateTime) {
        brokerMessageLogDao.changeBrokerMessageLogStatus(messageId,status,updateTime);
    }
}
