package com.nuite.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.UserEntity;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-09 09:26:54
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
