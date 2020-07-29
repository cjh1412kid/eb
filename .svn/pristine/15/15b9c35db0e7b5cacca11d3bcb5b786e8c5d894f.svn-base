package com.nuite.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.entity.TryShoesDetailEntity;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-11-26 14:07:06
 */
@Mapper
public interface TryShoesDetailDao extends BaseMapper<TryShoesDetailEntity> {

	
	//查询 NWGoods_TryShoesDetail_isValid_periodSeq表 某一个门店 某时间段 内试穿次数
	Integer getShopTryNum(@Param("isValid")String isValid, 
					@Param("periodSeq")Integer periodSeq, 
					@Param("shopSeq")Integer shopSeq, 
					@Param("startDate")Date startDate,
					@Param("endDate")Date endDate);

	
}
