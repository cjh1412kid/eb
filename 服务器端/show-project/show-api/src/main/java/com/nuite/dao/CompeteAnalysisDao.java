package com.nuite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.entity.CompeteAnalysisEntity;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-03 10:45:55
 */
@Mapper
public interface CompeteAnalysisDao extends BaseMapper<CompeteAnalysisEntity> {

	CompeteAnalysisEntity getLastWeekCompeteAnalysis(@Param("storeBrandSeq")Integer storeBrandSeq, 
			@Param("timeStr")String timeStr);
}
