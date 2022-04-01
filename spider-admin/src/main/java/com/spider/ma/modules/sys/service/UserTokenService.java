package com.spider.ma.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.UserTokenEntity;

/**
 * 用户Token
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface UserTokenService extends IService<UserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

}
