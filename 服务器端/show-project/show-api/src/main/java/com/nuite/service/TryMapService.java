package com.nuite.service;

import java.util.Date;
import java.util.List;

public interface TryMapService {
	
	Integer getTodayTotalTryTimesByShopSeq(Integer shopSeq);
	
	Integer getTotalTryTimesByShopSeqListAfterDate(List<Integer> shopSeqList, Date beginDate);

	Integer getTotalTryTimesByShopSeqList(List<Integer> shopSeqList, Integer dateDiff);
	
	Boolean shopHasTryDataInSeconds(Integer shopSeq, Integer seconds);

}

