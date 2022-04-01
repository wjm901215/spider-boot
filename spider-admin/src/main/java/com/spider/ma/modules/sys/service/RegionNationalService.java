package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.modules.sys.entity.RegionNationalEntity;

import java.util.List;

/**
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2019-04-03 16:57:26
 */
public interface RegionNationalService extends IService<RegionNationalEntity> {

    List list(RegionNationalEntity entity);

}

