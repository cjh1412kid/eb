package com.nuite.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.PurchasePlanEntity;

/**
 * 采购计划管理
 */
@Mapper
public interface PurchasePlanDao extends BaseMapper<PurchasePlanEntity> {
	
	 List<Map<String, Object>> getAllSXValueByYear(@Param("year")Integer year);
	 
	 List<PurchasePlanEntity> getALLPurchasePlansByYearAndSXValue(@Param("year")Integer year,@Param("periodSeq")Integer periodSeq,@Param("SXValue") String SXValue); 
}
