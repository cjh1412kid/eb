package com.nuite.dao;

import com.nuite.entity.SaleAndStockEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-07 17:15:58
 */
@Mapper
public interface SaleAndStockDao extends BaseMapper<SaleAndStockEntity> {

	List<Map<String, Object>> getSaleCountTotalPriceOneSX3(@Param("shopSeq")Integer shopSeq, 
											@Param("sx3Code")String sx3Code, 
											@Param("startTimeStr")String startTimeStr, 
											@Param("endTimeStr")String endTimeStr);

	
	
	Integer getShoeIdNumOneSX3(@Param("shopSeq")Integer shopSeq, 
							@Param("sx3Code")String sx3Code, 
							@Param("startTimeStr")String startTimeStr, 
							@Param("endTimeStr")String endTimeStr);



	Integer getStockOneSX3(@Param("shopSeq")Integer shopSeq, 
						@Param("sx3Code")String sx3Code);
	
}
