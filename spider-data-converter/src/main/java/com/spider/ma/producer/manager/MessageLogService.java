package com.spider.ma.producer.manager;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.spider.ma.common.constant.RabbitConstants;
import com.spider.ma.common.enums.MessageStatusEnum;
import com.spider.ma.db.msg.service.DbMessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * mq消息日志 service 实现
 *
 * @author SpiderMan
 * @version : com.spider.ma.modules.base.service.MessageLogServiceImpl,v 0.1 2021/8/8 10:18 Exp $$
 */
@Service
public class MessageLogService {
    @Autowired
    private DbMessageLogService dbMessageLogService;

    public void addBrokerMessageLog(String messageId, String jsonStr, LocalDateTime time) {
        LocalDateTime nextRetryTime = LocalDateTimeUtil.offset(time, RabbitConstants.ORDER_TIMEOUT, ChronoUnit.MINUTES);
        dbMessageLogService.addBrokerMessageLog(messageId,jsonStr,time, MessageStatusEnum.MSG_SENDING.getValue(),nextRetryTime);
    }
}
