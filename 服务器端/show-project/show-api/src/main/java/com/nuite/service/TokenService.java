package com.nuite.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenService extends IService<TokenEntity> {

	/**
	 * 根据token值获取对象
	 * @param token
	 * @return
	 */
	TokenEntity queryByToken(String token);

	/**
	 * 生成token
	 * @param userSeq  用户Seq
	 * @return        返回token信息
	 */
	TokenEntity createToken(int userSeq);

	/**
	 * 使token过期
	 * @param userSeq 用户Seq
	 */
	void expireToken(int userSeq);

}
