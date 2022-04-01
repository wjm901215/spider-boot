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

package com.spider.ma.common.controller;

import com.spider.core.common.constant.Constant;
import com.spider.core.common.exception.BusinessException;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.redis.SysConfigRedis;
import com.spider.oss.OSSFactory;
import com.spider.oss.config.CloudStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("common")
public class UploadController {
    @Autowired
    private SysConfigRedis sysConfigRedis;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, @RequestParam("module") Integer module) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        CloudStorageConfig config = sysConfigRedis.getConfigObject(Constant.Config.ADMIN_CLOUD_STORAGE_CONFIG_KEY.name(), CloudStorageConfig.class);
        String url = OSSFactory.build(config).uploadSuffix(file.getBytes(), BaseConstants.ModuleType.getValue(module), suffix);
        return R.ok().put("url", url);
    }

}
