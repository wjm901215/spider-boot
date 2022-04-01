package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.RegionNationalEntity;
import com.spider.ma.modules.sys.service.RegionNationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2019-04-03 16:57:26
 */
@RestController
@RequestMapping("sys/regionnational")
public class RegionNationalController {
    @Autowired
    private RegionNationalService regionNationalService;

    /**
     * 列表
     * @param params
     */
    @RequestMapping("/list")
    public R list(@RequestBody RegionNationalEntity params) {
        List list = regionNationalService.list(params);
        return R.ok().put("list", list);
    }

}
