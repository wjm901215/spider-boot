package com.spider.ma.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spider.ma.modules.sys.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenEntity> {

    UserTokenEntity queryByToken(String token);

}
