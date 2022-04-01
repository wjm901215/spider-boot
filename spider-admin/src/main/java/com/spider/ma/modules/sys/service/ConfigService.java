package com.spider.ma.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.ConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年12月4日 下午6:49:01
 */
public interface ConfigService extends IService<ConfigEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 保存配置信息
	 */
	public void save(ConfigEntity config);
	
	/**
	 * 更新配置信息
	 */
	public void update(ConfigEntity config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 */
	public String getValue(String key);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);

	/**
	 * 批量插入配置数据
	 * @param list 配置文件集合
	 */
	void insertConfigDataBatch(List<ConfigEntity> list);
}
