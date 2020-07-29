package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.PeriodEntity;

import java.util.List;
import java.util.Map;


public interface PeriodService extends IService<PeriodEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void insertWithSeq(PeriodEntity periodEntity);
	
	List<Map<String, Object>> getPeriodsByBrandSeq(Integer brandSeq,List<String> season);
	
	PeriodEntity getPeriodEntityByName(String name);
}

