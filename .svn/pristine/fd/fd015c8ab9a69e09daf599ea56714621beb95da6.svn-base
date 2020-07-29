package com.nuite.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.UserEntity;


public interface UserService extends IService<UserEntity> {
    
	UserEntity getUserBySeq(int object);
	
	UserEntity getUserByUserName(String mobile);

	void createUser(UserEntity userEntity, Integer brandSeq);
	
	void updateUser(UserEntity userEntity);

	List<UserEntity> getCreatedUserList(Integer userSeq, Integer start, Integer num);

	List<UserEntity> getAllSubUserList(UserEntity loginUser, List<Integer> areaSeqList, Integer start, Integer num);
	

}

