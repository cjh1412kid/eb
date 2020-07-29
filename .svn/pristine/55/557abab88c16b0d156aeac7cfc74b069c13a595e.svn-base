package com.nuite.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProductTopDao {

	//获取计算平均值的开始日期  （区域内一个月没有销量后的第一天，或者第一次开始有销量的那天）
	Date getSaleCountDayAvgStartDate(@Param("areaType")Integer areaType, 
									@Param("areaSeq")Integer areaSeq, 
									@Param("shoeSeq")Integer shoeSeq);
	
}
