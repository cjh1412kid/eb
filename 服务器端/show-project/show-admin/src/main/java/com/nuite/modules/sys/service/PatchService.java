package com.nuite.modules.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.PatchEntity;


public interface PatchService extends IService<PatchEntity> {

    PageUtils queryPage(Map<String, Object> params);

	Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq);

	void submit(Integer year, Integer week, Date startDate, Date endDate, Integer areaSeq, Integer branchOfficeSeq, Integer userSeq,
			Integer periodSeq, String sxValue, String shoeAndNum);
	
	List<Map<String, Object>> getAreas(Integer periodSeq,String sxValue,Integer week);

	List<Object> getWeeks(Integer areaSeq);
	
	List<Object> getPeriods(Integer userSeq,Integer week);
	
	List<PatchEntity> getPatchs(Integer periodSeq,String sxValue,Integer week,Integer shoeSeq,Integer branchOfficeSeq);
	
	List<Object> getShoeSeqs(Integer periodSeq,String sxValue,Integer week,Integer areaSeq);
	
	Integer allPatchNum(Integer shoesSeq,Integer branchOfficeSeq);

	
	PatchEntity getOnByParam(Integer periodSeq,String sxValue,Integer week,Integer shoesSeq,Integer branchOfficeSeq);


	List<PatchEntity> getBranchofficeAlreadyPatchList(Integer branchOfficeSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate);

}

