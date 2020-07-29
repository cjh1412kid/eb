package com.nuite.dao;

import com.nuite.entity.UserEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-11-21 09:27:49
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

	UserEntity getUserBySeq(@Param("userSeq")Integer userSeq);
	
	UserEntity getUserByUserName(@Param("userName")String userName);

	void insertUser(@Param("user")UserEntity userEntity);

	List<UserEntity> getAllSubUserList(Page<Map<String, Object>> page,
									@Param("brandSeq")Integer brandSeq, 
									@Param("roleSeq")Integer roleSeq, 
									@Param("areaSeqs")String areaSeqs);

	
}
