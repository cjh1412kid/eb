package com.nuite.dao;

import com.nuite.entity.ShopShowControlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-24 17:35:23
 */
@Mapper
public interface ShopShowControlDao extends BaseMapper<ShopShowControlEntity> {

	void insertShopShowControl(@Param("shopShowControl")ShopShowControlEntity shopShowControl);
	
}
