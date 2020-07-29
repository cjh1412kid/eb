package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.AreaEntity;


public interface ProductAreaShopPeriodService extends IService<AreaEntity> {

	List<Map<String, Object>> getAreasByBrandSeq(Integer brandSeq);
	
	List<Map<String, Object>> getBranchOfficesByAreaSeq(Integer parentSeq);
	
	List<Map<String, Object>> getShopsByAreaSeq(Integer areaSeq);
	
}

