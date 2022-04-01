package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.entity.CodeEntity;

import java.util.Map;

/**
 * 编码表
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-08 00:20:50
 */
public interface CodeService extends IService<CodeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CodeEntity getCodeByCityAndCodeType(Map<String,Object> map);
}

