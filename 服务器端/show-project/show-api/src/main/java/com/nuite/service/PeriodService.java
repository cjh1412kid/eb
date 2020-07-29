package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.PeriodEntity;


public interface PeriodService extends IService<PeriodEntity> {

	List<Map<String, Object>> getPeriodsByBrandSeq(Integer brandSeq);

	List<Integer> getPeriodSeqsByBrandSeq(Integer brandSeq);

	List<PeriodEntity> getPeriodsByBrandSeqAfterYear(Integer brandSeq, Integer year);

}

