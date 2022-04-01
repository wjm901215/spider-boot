package com.spider.ma.modules.test.dao;

import com.spider.ma.modules.test.entity.MqMessageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * rabbitmq消息测试表Dao
 * 
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-08-01 15:47:21
 */
@Mapper
public interface MqMessageDao extends BaseMapper<MqMessageEntity> {

    void deleteBatchIds(List<String> ids);

    List<MqMessageEntity> page(Page<MqMessageEntity> page,Map params);
	
}
