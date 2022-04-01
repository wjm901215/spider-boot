package com.spider.ma.modules.oss.controller;

import com.google.gson.Gson;
import com.spider.core.common.constant.Constant;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.validator.ValidatorUtils;
import com.spider.ma.common.validator.group.AliyunGroup;
import com.spider.ma.modules.oss.entity.SysOssEntity;
import com.spider.ma.modules.oss.service.SysOssService;
import com.spider.ma.modules.sys.redis.SysConfigRedis;
import com.spider.ma.modules.sys.service.ConfigService;
import com.spider.oss.OSSFactory;
import com.spider.oss.config.CloudStorageConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Map;

/**
 * 文件上传
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private SysConfigRedis sysConfigRedis;

    private final static String KEY = Constant.Config.ADMIN_CLOUD_STORAGE_CONFIG_KEY.name();

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysOssService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config() {
        CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


    /**
     * 保存云存储配置信息
     */
    @PostMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }
        configService.updateValueByKey(KEY, new Gson().toJson(config));

        return R.ok();
    }


    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        CloudStorageConfig config = sysConfigRedis.getConfigObject(Constant.Config.ADMIN_CLOUD_STORAGE_CONFIG_KEY.name(), CloudStorageConfig.class);
        String url = OSSFactory.build(config).uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        sysOssService.insert(ossEntity);

        return R.ok().put("url", url);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids) {
        sysOssService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
