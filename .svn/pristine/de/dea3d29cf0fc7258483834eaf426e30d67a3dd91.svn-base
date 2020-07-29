package com.nuite.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TopDao {

	//试穿Top20：查询 NWGoods_TryShoesDetail_isValid_periodSeq表 多个门店 起止时间内 试穿数量从大到小前topNum的货号+试穿次数+平均试穿时间
	List<Map<String, Object>> getTopGoodIdTryCount(@Param("isValid")String isValid, 
					@Param("periodSeqList")List<Integer> periodSeqList, 
					@Param("shopSeqs")String shopSeqs, 
					@Param("startDate")Date startDate, 
					@Param("endDate")Date endDate, 
					@Param("topNum")Integer topNum);

	
	//试穿Top20中销量： 获取指定 '门店序号'，指定 '起止日期' 时间段内某 '货号' 的销售量
	Integer getShoeIdSalesFromShopsInSomeDate(@Param("periodSeq")Integer periodSeq, 
										@Param("shopSeqs")String shopSeqs, 
										@Param("shoeId")String shoeId, 
										@Param("startDate")Date startDate, 
										@Param("endDate")Date endDate);



	//销售、滞销Top20: 查询多个_波次分表 区域内 某时间段内销量top20
	List<Map<String, Object>> getTopGoodIdSaleCount(@Param("type")Integer type, 
								@Param("areaSeqs")String areaSeqs, 
								@Param("periodSeqList")List<Integer> periodSeqList, 
								@Param("startDate")Date startDate,
								@Param("endDate")Date endDate, 
								@Param("topNum")Integer topNum, 
								@Param("topType")Integer topType);

	
	//销售、滞销Top20中的试穿次数和时间： 查询某货号，试穿次数+总时间
	List<Map<String, Object>> getShoeIdTryCountTimesMap(@Param("periodSeq")Integer periodSeq, 
					@Param("shopSeqs")String shopSeqs, 
					@Param("shoeId")String shoeId, 
					@Param("startDate")Date startDate, 
					@Param("endDate")Date endDate);
	

	
	//大屏试穿Top20中销量：获取全国、本月、某 货号的销售量
	Integer getCountryMonthShoeIdSales(@Param("periodSeq")Integer periodSeq, 
								@Param("shoeId")String shoeId, 
								@Param("startDate")Date startDate);


	//大屏销售top20中试穿量和时间:  获取某货号试穿次数、试穿时间
	List<Map<String, Object>> getCountryMonthShoeIdTryCountMap(@Param("periodSeq")Integer periodSeq, 
								@Param("shoeId")String shoeId, 
								@Param("startDate")Date startDate);
	
}
