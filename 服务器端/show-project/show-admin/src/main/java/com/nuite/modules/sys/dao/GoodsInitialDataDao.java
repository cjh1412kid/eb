package com.nuite.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.GoodsInitialDataEntity;

/**
 * 首单、补单、预留数据管理
 */
@Mapper
public interface GoodsInitialDataDao extends BaseMapper<GoodsInitialDataEntity> {
	
	 List<Map<String, Object>> getAllPeriods(@Param("year")String year);
	 
	 List<String> getAllSXValue(@Param("periodSeq")Integer periodSeq);
	 
	 List<GoodsInitialDataEntity> getGoodsInitialDataEntityByPeriodSeqANDSXValue(@Param("periodSeq")Integer PeriodSeq,
			 @Param("SXValue")String SXValue, @Param("type")Integer type);
}
