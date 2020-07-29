package com.nuite.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.PatchDetailEntity;

/**
 * 补单配码详情
 */
@Mapper
public interface PatchDetailDao extends BaseMapper<PatchDetailEntity> {
	
	List<Map<String, Object>> getAllSizeAllotTemplate(@Param("periodSeq")Integer periodSeq,@Param("sxValue") String sxValue,@Param("week") Integer week,@Param("branchOfficeSeq")Integer branchOfficeSeq);

	Integer getAllBoxCount(@Param("shoesSeq")Integer shoesSeq);
	
	Integer getAllData(@Param("shoesSeq")Integer shoesSeq,@Param("sizeAllotTemplateSeq")Integer sizeAllotTemplateSeq,@Param("branchOfficeSeq")Integer branchOfficeSeq) ;

	List<Map<String, Object>> getSizeAllotTemplates(@Param("periodSeq")Integer periodSeq,@Param("sxValue") String sxValue,@Param("week") Integer week);

	List<Map<String, Object>> getAreasByWeek(@Param("periodSeq")Integer periodSeq,@Param("week")Integer week);
	
	List<Map<String, Object>> getAreasByWeekAndArea(@Param("periodSeq")Integer periodSeq,@Param("week")Integer week,@Param("areaSeq")Integer areaSeq);

	List<Map<String, Object>> getsXvalueByWeek(@Param("periodSeq")Integer periodSeq,@Param("week")Integer week);

	List<Map<String, Object>> getAllShoeSeq(@Param("periodSeq")Integer periodSeq,@Param("sxValue") String sxValue,@Param("week") Integer week);
}
