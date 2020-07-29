package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.WeekSalesEntity;


public interface WeekSalesService extends IService<WeekSalesEntity> {

	List<WeekSalesEntity> getAssistantWeekSalesOneMonth(Integer year, Integer month, Integer assistantSeq);

	WeekSalesEntity getWeekSalesByEntity(WeekSalesEntity weekSalesEntity);

	void addOrUpdateWeeksales(WeekSalesEntity weekSalesEntity);

	List<WeekSalesEntity> getAllWeekSalesOneWeek(Integer shopSeq, Integer year, Integer month, Integer week);

	WeekSalesEntity getLastWeekSalesByParams(Integer assistantSeq, Integer year, Integer month, Integer week);

	List<Map<String, Object>> getAssistantMonthTotalAmount(Integer shopSeq, Integer year, Integer month, Integer week);
	
}

