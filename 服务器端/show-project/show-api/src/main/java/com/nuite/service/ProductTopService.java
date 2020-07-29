package com.nuite.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface ProductTopService {
	
	List<Map<String, Object>> getTryTop(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum);

	List<Map<String, Object>> getSaleTop(int rankType, Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum);
	
	List<Map<String, Object>> getSaleLast(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum);
	
	

	List<Map<String, Object>> getSaleCountAndRankList(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff);

	List<Map<String, Object>> getInNumAndStockRankList(Integer areaType, Integer areaSeq, Integer periodSeq);

	Map<String, Object> getDaySaleCountAndAvgPrice(Integer areaType, Integer areaSeq, Integer shoeSeq, Date date);

	Integer getDayTryCount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date date);

	List<Map<String, Object>> getTryCountAndRankList(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff);

	Map<String, Object> getHistoryDayAvgLineMap(Integer areaType, Integer areaSeq, Integer shoeSeq) throws ParseException;

	Date getShoeCabinetDate(Integer areaType, Integer areaSeq, Integer shoeSeq);

	Integer getTryCountTrend(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer datediff);

	Map<String, Object> getSaleTrendMap(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer datediff);

	Integer getSaleCountBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, String startDate, String endDate);

	Integer getCabinetShopNumBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, String startDate, String endDate);

	Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq);

	Integer getBranchOfficeSeqByShopSeq(Integer shopSeq);

}

