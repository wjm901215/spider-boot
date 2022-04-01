package com.spider.ma.db.msg.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.db.msg.entity.BrokerMessageLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * rabbitmq 消息日志表Dao
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-02 22:17:05
 */
@Mapper
public interface BrokerMessageLogDao extends BaseMapper<BrokerMessageLogEntity> {

    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId 消息唯一ID
     * @param status 消息状态
     * @param updateTime 更新时间
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") int status, @Param("updateTime") LocalDateTime updateTime);
}
