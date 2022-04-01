package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.form.SysLoginForm;
import com.spider.ma.modules.sys.enumerate.SysConstants;
import com.spider.ma.modules.sys.service.MenuService;
import com.spider.ma.modules.sys.service.UserTokenService;
import com.spider.ma.modules.sys.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserTokenService sysUserTokenService;
	@Autowired
	private MenuService menuService;

	/**
	 * 登录
	 */
	@SysLog("用户登录")
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {
		//用户信息
		UserEntity user = userService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error(SysConstants.LoginResponseEnum.USER_NOT_FOUND_PASSWORD_ERROR.key,SysConstants.LoginResponseEnum.USER_NOT_FOUND_PASSWORD_ERROR.value);
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error(SysConstants.LoginResponseEnum.USER_LOCKING.key,SysConstants.LoginResponseEnum.USER_LOCKING.value);
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getId());
		return r;
	}


	/**
	 * 退出
	 */
	@SysLog("用户登出")
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
