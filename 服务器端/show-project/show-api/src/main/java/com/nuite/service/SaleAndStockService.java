package com.nuite.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.SaleAndStockEntity;


public interface SaleAndStockService extends IService<SaleAndStockEntity> {

	SaleAndStockEntity getInsertOrUpdateWeekSaleAndStockEntity(Integer shopSeq, Integer year, Integer week, String sx3Code, String startTimeStr, String endTimeStr);

	List<SaleAndStockEntity> getSomeWeekSaleAndStockList(Integer shopSeq, Integer year, Integer week);

}

