package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.DictionaryEntity;
import com.spider.ma.modules.sys.service.DictionaryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 系统字典表
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-29 14:48:35
 */
@RestController
@RequestMapping("/sys/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/queryInfoByCodeName")
    public R queryInfoByCodeName(DictionaryEntity entity) {
        List<DictionaryEntity> dicList = dictionaryService.queryInfoByCodeName(entity);
        return R.ok().put("rows", dicList);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dictionary:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = dictionaryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dictionary:save")
    public R save(@RequestBody DictionaryEntity entity) {
        dictionaryService.save(entity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dictionary:update")
    public R update(@RequestBody DictionaryEntity entity) {
        dictionaryService.update(entity);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dictionary:delete")
    public R deleteByList(@RequestBody String[] ids) {
        dictionaryService.deleteByList(ids);
        return R.ok();
    }
}
