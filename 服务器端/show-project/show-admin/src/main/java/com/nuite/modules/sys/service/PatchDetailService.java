package com.nuite.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.PatchDetailEntity;
import com.nuite.modules.sys.entity.PatchEntity;

public interface PatchDetailService extends IService<PatchDetailEntity> {

	List<Map<String, Object>> getAllSizeAllotTemplate(Integer periodSeq,String sxValue,Integer week,Integer branchOfficeSeq);
	
	Integer getAllBoxCount(Integer shoesSeq);
	
	Integer getAllData(Integer shoesSeq,Integer sizeAllotTemplateSeq,Integer branchOfficeSeq);
	
	List<Map<String, Object>> getSizeAllotTemplates(Integer periodSeq,String sxValue,Integer week);
	
	List<Map<String, Object>> getAreasByWeek(Integer periodSeq,Integer week);
	
	List<Map<String, Object>> getAreasByWeekAndArea(Integer periodSeq,Integer week,Integer areaSeq);
	
	List<Map<String, Object>> getsXvalueByWeek(Integer periodSeq,Integer week);
	
	List<Map<String, Object>> getAllShoeSeq(Integer periodSeq,String sxValue,Integer week);
	
	
}
