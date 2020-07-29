package com.nuite.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.GoodsInitialDataEntity;


public interface GoodsInitialDataService extends IService<GoodsInitialDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
	Integer getFirstOrderAndPatchedNum(Integer areaSeq, Integer periodSeq, String sxValue);

	GoodsInitialDataEntity getShoeInitialData(Integer areaType, Integer areaSeq, Integer shoeSeq);
	
	void insertOrUpdateOne(GoodsInitialDataEntity goodsInitialDataEntity);
	
	List<Map<String, Object>> getAllPeriods(Integer year);
	
	List<String> getAllSXValue(Integer periodSeq);
	
	List<GoodsInitialDataEntity> getGoodsInitialDataEntityByPeriodSeqANDSXValue(Integer PeriodSeq,String SXValue,Integer type);

}

