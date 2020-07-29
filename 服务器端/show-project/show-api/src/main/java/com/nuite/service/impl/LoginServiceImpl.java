package com.nuite.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuite.common.exception.RRException;
import com.nuite.common.validator.Assert;
import com.nuite.entity.TokenEntity;
import com.nuite.entity.UserEntity;
import com.nuite.form.LoginForm;
import com.nuite.service.LoginService;
import com.nuite.service.TokenService;
import com.nuite.service.UserService;


@Service
public class LoginServiceImpl implements LoginService {
    
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;


	@Override
	public Map<String, Object> login(LoginForm form) {
		//根据用户名查询用户
		UserEntity user = userService.getUserByUserName(form.getUserName());
		Assert.isNull(user, "用户名或密码错误");

		//用户失效
        if (user.getIsUseful() != 1) {
            throw new RRException("账号已停用");
        }
        
		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException("用户名或密码错误");
		}

		/**  登录成功  **/
		//生成token存入tb_token表
		TokenEntity tokenEntity = tokenService.createToken(user.getSeq());

		Map<String, Object> map = new HashMap<>(2);
		map.put("token", tokenEntity.getToken());
		map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
		
		user.setPassword(null);
		map.put("user", user);

		return map;
	}

}
