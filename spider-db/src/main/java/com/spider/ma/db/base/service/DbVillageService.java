package com.spider.ma.db.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.db.base.dao.VillageDao;
import com.spider.ma.db.base.entity.VillageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 小区表Service实现
 *
 * @author SpiderMan
 * @email spiderMan@mail.com
 * @date 2021-07-30 11:34:23
 */
@Service("villageService")
@Slf4j
public class DbVillageService extends ServiceImpl<VillageDao, VillageEntity>  {
    @Autowired
    private VillageDao villageDao;

    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        page.setRecords(villageDao.page(page, params));
        return new PageUtils(page);
    }

}