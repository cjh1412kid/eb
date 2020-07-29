package com.nuite.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.common.utils.DateUtils;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.service.ProductSaleShoesService;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.utils.DateUtil;


@Service
public class ProductSaleShoesServiceImpl implements ProductSaleShoesService {
    
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;
    
    @Autowired
    private SaleShoesDetailService saleShoesDetailService;
    

	

	/**
	 * 区域内 时间段内的销售额
	 * @return
	 */
	@Override
	public BigDecimal getAreaSale(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice) AS salePriceSum");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return BigDecimal.ZERO;
		}
		
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 30) { // 当月
			String startDate = DateUtil.getMonthFirstDateStr(); //本月1日
			wrapper.where("SaleDate >= {0}", startDate);
		} else if (datediff == 365) { // 当年
			String startDate = DateUtil.getYearFirstDateStr(); //今年1月1日
			wrapper.where("SaleDate >= {0}", startDate);
		} else {
			return BigDecimal.ZERO;
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
		
	}
	
	
	
	/**
	 * 区域内 去年同期的销售额
	 * @return
	 */
	@Override
	public BigDecimal getAreaSaleLastYear(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice) AS salePriceSum");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return BigDecimal.ZERO;
		}
		
		
		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			String endDate = DateUtil.getDateDiffBeginDateFromDate(1, mostRecentDay); //销售日期后一天0点
			
			String lastyearStartDate = DateUtils.addDateYears(startDate, -1); //去年同期开始时间
			String lastyearEndDate = DateUtils.addDateYears(endDate, -1); //去年同期开始时间
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", lastyearStartDate, lastyearEndDate);
		} else if (datediff == 7) { // 上一周
			String lastyearStartDate = DateUtil.getWeekDiffMonDate(-53); //去年同期上周周一0点
			String lastyearEndDate = DateUtil.getWeekDiffMonDate(-52); //去年同期本周周一0点
			
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", lastyearStartDate, lastyearEndDate);
		} else if (datediff == 30) { // 当月
			String startDate = DateUtil.getMonthFirstDateStr(); //本月1日
			String endDate = DateUtil.getDateDiffBeginDateFromDate(1, mostRecentDay); //销售日期后一天0点
			
			String lastyearStartDate = DateUtils.addDateYears(startDate, -1); //去年同期开始时间
			String lastyearEndDate = DateUtils.addDateYears(endDate, -1); //去年同期开始时间
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", lastyearStartDate, lastyearEndDate);
		} else if (datediff == 365) { // 当年
			String startDate = DateUtil.getYearFirstDateStr(); //今年1月1日
			String endDate = DateUtil.getDateDiffBeginDateFromDate(1, mostRecentDay); //销售日期后一天0点
			
			String lastyearStartDate = DateUtils.addDateYears(startDate, -1); //去年同期开始时间
			String lastyearEndDate = DateUtils.addDateYears(endDate, -1); //去年同期开始时间
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", lastyearStartDate, lastyearEndDate);
		} else {
			return BigDecimal.ZERO;
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		

		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
		
	}
	
	
}
