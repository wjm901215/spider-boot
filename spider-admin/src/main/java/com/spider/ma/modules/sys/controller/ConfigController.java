/**
 * Copyright 2021
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.spider.ma.modules.sys.controller;


import com.alibaba.excel.EasyExcel;
import com.google.common.eventbus.EventBus;
import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.validator.ValidatorUtils;
import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.ma.modules.sys.excel.event.ExcelProcessEvent;
import com.spider.ma.modules.sys.excel.handler.ExcelEventListener;
import com.spider.ma.modules.sys.excel.impt.ConfigData;
import com.spider.ma.modules.sys.excel.listener.ConfigDataListener;
import com.spider.ma.modules.sys.excel.rule.ImportRule;
import com.spider.ma.modules.sys.service.ConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class ConfigController extends AbstractController {
    @Autowired
    private ConfigService configService;
    @Autowired
    private EventBus eventBus;
    @Autowired
    private ExcelEventListener listener;
    @Autowired
    private ImportRule rule;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = configService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") String id) {
        ConfigEntity config = configService.selectById(id);
        return R.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody ConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        configService.save(config);
        return R.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody ConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        configService.update(config);
        return R.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody String[] ids) {
        configService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 配置管理导入
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ConfigData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ConfigDataListener}
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("importExcel")
    @ResponseBody
    public R importExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ConfigData.class, new ConfigDataListener(configService, eventBus, listener,rule)).sheet().doRead();
        ExcelProcessEvent result = listener.getProcessEventInListener();
        if (result.getAllOk()) {
            listener.resetEventHandled();
        }
        return R.ok().put("result",result);
    }

}
