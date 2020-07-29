package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.SaleQuotaEntity;


public interface SaleQuotaDistributeService extends IService<SaleQuotaEntity> {

	SaleQuotaEntity getSaleQuotaByEntity(SaleQuotaEntity saleQuotaEntity);

	void distributeSaleQuota(List<SaleQuotaEntity> saleQuotaList);

	List<Map<String, Object>> getHistoryYearAndMonthByBrandSeq(Integer brandSeq);
}

