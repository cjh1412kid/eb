package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.AreaEntity;


public interface AreaService extends IService<AreaEntity> {

	
	List<AreaEntity> getFirstAreasByBrandSeq(Integer brandSeq);
	
	List<Map<String, Object>> getFirstAreasMapByBrandSeq(Integer brandSeq);
	


	List<AreaEntity> getSecondAreasByParentSeq(Integer parentSeq);
	
	List<Map<String, Object>> getSecondAreasMapByParentSeq(Integer parentSeq);
	

	
	List<AreaEntity> getSecondAreasByParentSeqList(List<Integer> parentSeqs);
	
	List<Map<String, Object>> getSecondAreasMapByParentSeqList(List<Integer> parentSeqs);
	
	
	

	List<AreaEntity> getAllSecondAreasByBrandSeq(Integer brandSeq);

}

