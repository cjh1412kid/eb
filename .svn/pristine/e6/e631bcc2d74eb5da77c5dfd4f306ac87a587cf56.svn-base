package com.nuite.service;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.SaleShoesDetailEntity;


public interface SaleShoesDetailService extends IService<SaleShoesDetailEntity> {

	//获取最近的有销售数据的一天
	Date getSaleDataMostRecentDay();
	
	BigDecimal getFirstAreaTotalSales(Integer firstAreaSeq, Date startdate, Date endDate);

	BigDecimal getSecondAreaTotalSales(Integer secondAreaSeq, Date startdate, Date endDate);

	BigDecimal getShopTotalSales(Integer shopSeq, Date startdate, Date endDate);

    //获取某时间段内各大区、分公司、门店、日期的销量
    List<Map<String, Object>> getDetailSales(Date startDate, Date endDate);
    
}
