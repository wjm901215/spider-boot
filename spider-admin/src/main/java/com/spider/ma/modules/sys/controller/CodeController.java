package com.spider.ma.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.CodeEntity;
import com.spider.ma.modules.sys.service.CodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 编码表
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2021-06-08 00:20:50
 */
@RestController
@RequestMapping("/sys/code")
public class CodeController {
    @Autowired
    private CodeService codeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:code:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = codeService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:code:info")
    public R info(@PathVariable("id") Long id){
        CodeEntity code = codeService.selectById(id);
        return R.ok().put("code", code);
    }

    /**
     * 保存page
     */
    @RequestMapping("/insertOrUpdate")
    @RequiresPermissions("sys:code:insertOrUpdate")
    public R save(@RequestBody CodeEntity code){
        codeService.insertOrUpdate(code);
        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delByList")
    @RequiresPermissions("sys:code:delByList")
    public R deleteByList(@RequestBody Long[] ids){
        codeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
