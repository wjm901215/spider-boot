package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author SpiderMan
 * @created 2021/11/24
 */
@TableName("sys_user_tenant")
@Data
public class UserTenantEntity {

    @TableId
    private String id;

    private String userId;

    private String tenantId;
}
