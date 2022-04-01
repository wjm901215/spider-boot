package com.spider.ma.modules.test.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * rabbitmq消息测试表
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-01 15:47:21
 */
@TableName("test_mq_message")
@Data
public class MqMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
    @TableId
    private String id;
	/**
	 * 消息内容
	 */
    private String message;

}
