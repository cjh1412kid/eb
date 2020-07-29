package com.nuite.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface TopService {
	
	List<Map<String, Object>> getTopGoodIdTryCount(List<Integer> shopSeqList, List<Integer> periodSeqList, Date startDate, Date endDate,
			Integer topNum);


	int getShoeIdSalesFromShopsInSomeDate(List<Integer> shopSeqList, Integer periodSeq, String shoeId, Date startDate, Date endDate);


	List<Map<String, Object>> getTopGoodIdSaleCount(Integer type, List<Integer> areaSeqList, List<Integer> periodSeqList, Date startDate,
			Date endDate, Integer topNum, Integer topType);

	
	Map<String, Object> getShoeIdTryCountTimesMap(List<Integer> shopSeqList, Integer periodSeq, String shoeID, Date startDate,
			Date endDate);


	List<Map<String, Object>> getCountryMonthTopGoodIdTryCount(Date startDate, Integer topNum);


	int getCountryMonthShoeIdSales(Integer periodSeq, String shoeID, Date startDate);


	List<Map<String, Object>> getCountryMonthTopGoodIdSaleCount(Date startDate, Integer topNum);


	Map<String, Object> getCountryMonthShoeIdTryCountMap(Integer periodSeq, String shoeID, Date startDate);
}

