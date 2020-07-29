package com.nuite.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ConvertDao {

	
	//查询 NWGoods_TryShoesDetail_isValid_periodSeq表 多个门店 某一天 试穿次数
	Integer getShopsTryNumOneDay(@Param("isValid")String isValid, 
					@Param("periodSeq")Integer periodSeq, 
					@Param("shopSeqs")String shopSeqs, 
					@Param("date")Date date);
	
	
	//查询 NWGoods_TryShoesDetail_isValid_periodSeq表 多个门店 某一天 某一货号 试穿次数
	Integer getShopsShoeIdTryNumOneDay(@Param("isValid")String isValid, 
					@Param("periodSeq")Integer periodSeq, 
					@Param("shopSeqs")String shopSeqs, 
					@Param("shoeId")String shoeId, 
					@Param("date")Date date);
	
	
	//查询 _波次分表 区域内 某一天 总销量
	Integer getAreasSaleNumOneDay(@Param("periodSeq")Integer periodSeq, 
								@Param("type")Integer type, 
								@Param("areaSeqs")String areaSeqs, 
								@Param("date")Date date);

	
	//查询 _波次分表 区域内 某一天 某一货号销量
	Integer getAreasShoeIdSaleNumOneDay(@Param("periodSeq")Integer periodSeq, 
								@Param("type")Integer type, 
								@Param("areaSeqs")String areaSeqs, 
								@Param("shoeId")String shoeId, 
								@Param("date")Date date);


	
}
