package com.nuite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.UserAreaSubscribedDao;
import com.nuite.entity.UserAreaSubscribedEntity;
import com.nuite.service.UserAreaSubscribedService;


@Service
public class UserAreaSubscribedServiceImpl extends ServiceImpl<UserAreaSubscribedDao, UserAreaSubscribedEntity> implements UserAreaSubscribedService {

	
    @Autowired
    private UserAreaSubscribedDao userAreaSubscribedDao;
	
	
	
	/**
	 * 获取用户已订阅区域
	 */
	@Override
	public List<UserAreaSubscribedEntity> getUserAreaSubscribed(Integer userSeq) {
		Wrapper<UserAreaSubscribedEntity> wrapper = new EntityWrapper<UserAreaSubscribedEntity>();
		wrapper.where("User_Seq = {0}", userSeq);
		return userAreaSubscribedDao.selectList(wrapper);
	}
	
	

}
