package com.nuite.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface LargeScreenApiDao {

	
	//查询 NWGoods_TryShoesDetail_isValid_periodSeq表  开始时间后 内试穿次数（数据条数）
	Integer getCountryTryTimesAfterStartDate(@Param("isValid")String isValid, 
					@Param("periodSeq")Integer periodSeq, 
					@Param("startDate")String startDate);

	
	//查询时间段内、指定货号的全国销售双数
	Integer getSaleNumByGoodsIdList(@Param("startDate")Date startDate, 
					@Param("endDate")Date endDate, 
					@Param("goodsIds")String goodsIds);

	
	//查询时间段内、指定货号的全国进货双数
	Integer getTotalNumByShoeSeqList(@Param("startDate")Date startDate, 
					@Param("endDate")Date endDate, 
					@Param("shoeSeqs")String shoeSeqs);
	
}
