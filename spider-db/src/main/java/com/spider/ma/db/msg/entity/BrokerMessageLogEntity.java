package com.spider.ma.db.msg.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * rabbitmq 消息日志表
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-02 22:17:05
 */
@TableName("mq_broker_message_log")
@Data
public class BrokerMessageLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 消息唯一ID
	 */
    private String messageId;
	/**
	 * 消息内容
	 */
    private String message;
	/**
	 * 重试次数
	 */
    private Integer tryCount;
	/**
	 * 消息投递状态 0投递中，1投递成功，2投递失败
	 */
    private Integer status;
	/**
	 * 下一次重试时间
	 */
    private LocalDateTime nextRetry;
	/**
	 * 创建时间
	 */
    private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
    private LocalDateTime updateTime;

}
