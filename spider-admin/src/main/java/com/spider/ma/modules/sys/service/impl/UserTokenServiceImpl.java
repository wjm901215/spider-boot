package com.spider.ma.modules.sys.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.dao.UserTokenDao;
import com.spider.ma.modules.sys.entity.UserTokenEntity;
import com.spider.ma.modules.sys.oauth2.TokenGenerator;
import com.spider.ma.modules.sys.service.UserTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service("userTokenService")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenDao, UserTokenEntity> implements UserTokenService {
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 12;


    @Override
    public R createToken(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        final LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime= LocalDateTimeUtil.offset(now,EXPIRE, ChronoUnit.HOURS);

        //判断是否生成过token
        UserTokenEntity tokenEntity = this.selectById(userId);
        // 超级管理员可以同时登录
        if (tokenEntity == null) {
            tokenEntity = new UserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }
        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    @Override
    public void logout(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        UserTokenEntity tokenEntity = new UserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
