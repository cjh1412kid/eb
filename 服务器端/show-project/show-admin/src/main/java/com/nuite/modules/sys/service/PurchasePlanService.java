package com.nuite.modules.sys.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.PurchasePlanEntity;


public interface PurchasePlanService extends IService<PurchasePlanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
	Integer getTotalPlanPurchaseNum(Integer areaSeq, Integer periodSeq, String sxValue);
	
	Integer getSXPlanTotalNum(Integer areaSeq, Integer periodSeq, String sxValue);

	Boolean hasUploadPurchasePlan(Integer areaSeq, Integer periodSeq, String sxValue);
	
	BigDecimal getThisTimePurchasePlanPercent(Date date, Integer areaSeq, Integer periodSeq, String sxValue);
	
	void insertOrUpdateOne(PurchasePlanEntity purchasePlanEntity);
	
	List<Map<String, Object>> getAllSXValueByYear(Integer year);
	
	List<PurchasePlanEntity> getALLPurchasePlansByYearAndSXValue(Integer year,Integer periodSeq,String SXValue);

	void update(Integer year,Integer branchOfficeSeq,String sXValue,Integer sXPlanTotalNum);

}

