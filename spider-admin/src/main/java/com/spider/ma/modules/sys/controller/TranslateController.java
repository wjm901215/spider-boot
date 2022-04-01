package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.TranslateEntity;
import com.spider.ma.modules.sys.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/12/4
 */
@RestController
@RequestMapping("/sys/trans")
public class TranslateController {

    @Autowired
    private TranslateService translateService;


    @RequestMapping("queryTranslateById")
    public R queryTranslateById(@RequestBody String extId) {
        extId = extId.replaceAll("\"", "");
        List<TranslateEntity> translateEntities = translateService.queryTranslateByExtId(extId);
        return R.ok().put("data", translateEntities);
    }

    @RequestMapping("update")
    public R update(@RequestBody TranslateEntity entity) {
        translateService.update(entity);
        return R.ok();
    }

    @RequestMapping("save")
    public R save(@RequestBody TranslateEntity entity) {
        String id = translateService.add(entity);
        return R.ok().put("data", id);
    }


}
