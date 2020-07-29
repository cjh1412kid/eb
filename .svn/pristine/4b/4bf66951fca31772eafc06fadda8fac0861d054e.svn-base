package com.nuite.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.CompeteAnalysisEntity;


public interface CompeteAnalysisService extends IService<CompeteAnalysisEntity> {

	void addOrUpdateCompeteAnalysis(CompeteAnalysisEntity competeAnalysisEntity);

	List<CompeteAnalysisEntity> getAllCompeteAnalysisOneWeek(Integer storeSeq, Integer year, Integer month, Integer week);

	CompeteAnalysisEntity getLastWeekCompeteAnalysis(Integer storeBrandSeq, Integer year, Integer month, Integer week);

	BigDecimal getStoreBrandMonthTotalSale(Integer storeBrandSeq, Integer year, Integer month, Integer week);

}

