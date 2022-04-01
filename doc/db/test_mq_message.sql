CREATE TABLE `test_mq_message` (
  `ID` char(32) NOT NULL COMMENT 'ID',
  `message` varchar(500) NOT NULL COMMENT '消息内容',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='rabbitmq消息测试表';

CREATE TABLE `mq_broker_message_log` (
 `message_id` varchar(255) NOT NULL COMMENT '消息唯一ID',
 `message` varchar(4000) NOT NULL COMMENT '消息内容',
 `try_count` int(4) DEFAULT '0' COMMENT '重试次数',
 `status` int(2) DEFAULT '0' COMMENT '消息投递状态 0投递中，1投递成功，2投递失败',
 `next_retry` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下一次重试时间',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='rabbitmq 消息日志表';