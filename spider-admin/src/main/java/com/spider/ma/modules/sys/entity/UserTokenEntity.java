package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统用户Token
 */
@TableName("sys_user_token")
@Data
public class UserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    @TableId(type = IdType.INPUT)
    private String userId;
    //token
    private String token;
    //过期时间
    private LocalDateTime expireTime;
    //更新时间
    private LocalDateTime updateTime;

}
