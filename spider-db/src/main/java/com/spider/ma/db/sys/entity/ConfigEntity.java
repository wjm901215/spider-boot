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

package com.spider.ma.db.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置信息
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年12月4日 下午6:43:36
 */
@TableName("sys_config")
@Data
public class ConfigEntity implements Serializable {

    @TableId
    private String id;
    /**
     * 配置类型(2后台系统,1POS机设备)
     */
    private Integer type;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * KEY
     */
    private String paramKey;
    /**
     * VALUE
     */
    private String paramValue;
    /**
     * 状态（0.停用；1.启用）
     */
    private Integer status;
    /**
     * 是否通用
     */
    private Integer isCommon;
    /**
     * 备注
     */
    private String remark;

    /**
     * 租户名
     */
    @TableField(exist = false)
    private String tenantName;
}
