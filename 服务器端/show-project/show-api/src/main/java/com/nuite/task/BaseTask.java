package com.nuite.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuite.controller.BaseController;
import com.nuite.entity.AreaEntity;
import com.nuite.service.AreaService;
import com.nuite.service.PeriodService;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.utils.DateUtil;

@Component("baseTask")
public class BaseTask extends BaseController {

    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private SaleShoesDetailService saleShoesDetailService;
    
    
    
    
    //获取品牌所有波次
	public List<Integer> getAllPeriodSeqsByBrandSeq(Integer brandSeq) {
		return periodService.getPeriodSeqsByBrandSeq(brandSeq);
	}

	
	//获取指定区域类型，所有的区域序号
	public List<Integer> getAreaSeqsByTypeAndBrandSeq(Integer brandSeq, int type) {
		List<Integer> areaSeqList = new ArrayList<Integer>();
		
		if(type == 1) {
			areaSeqList.add(0);
		} else if (type == 2) {
			//获取品牌所有的大区
			List<AreaEntity> areaList = areaService.getFirstAreasByBrandSeq(brandSeq);
			for(AreaEntity areaEntity : areaList) {
				areaSeqList.add(areaEntity.getSeq());
			}
		} else if (type == 3) {
			//获取品牌所有的分公司
			List<AreaEntity> areaList = areaService.getAllSecondAreasByBrandSeq(brandSeq);
			for(AreaEntity areaEntity : areaList) {
				areaSeqList.add(areaEntity.getSeq());
			}
		}
		return areaSeqList;
	}
	
	
	
	//获取区域内（一个，目前只缓存单选）所有门店序号
	public List<Integer> getShopSeqListByAreaSeq(int type, Integer areaSeq, Integer brandSeq) {
		List<Integer> areaSeqList = new ArrayList<Integer>();
		areaSeqList.add(areaSeq);
		return getShopSeqListByAreaSeqs(type, areaSeqList,  brandSeq);
	}


	//获取开始日期（本日、本周、本月）
	public List<Date> getStartDateList() {
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(getTodayDate());
		startDateList.add(getWeekMonDate());
		startDateList.add(getMonthFirstDate());
		return startDateList;
	}


	//获取开始日期（最近有销售数据日、本周、本月）
	public List<Date> getSaleStartDateList() {
		List<Date> startDateList = new ArrayList<Date>();
		//最近一日有销售额的时间
		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay();
		if(mostRecentDay == null) {
			return null;
		} else {
			startDateList.add(mostRecentDay);
		}
		if(getWeekMonDate().before(mostRecentDay)) {
			startDateList.add(getWeekMonDate());
		}
		if(getMonthFirstDate().before(mostRecentDay)) {
			startDateList.add(getMonthFirstDate());
		}
		return startDateList;
	}
	
	
	/**
	* 得到本周周一日期
	* @return
	*/
	public Date getWeekMonDate(){
		return DateUtil.getWeekMonDate();
	}
	
	
	/**
	* 获取当月第一天
	* @return
	*/
	public Date getMonthFirstDate() {
		return DateUtil.getMonthFirstDate();
	}

	
	/**
	 * 获取今日日期
	 * @return
	 */
	public Date getTodayDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String todayDate = sdf.format(new Date());
			Date date = sdf.parse(todayDate);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	
	//获取开始日期（最近1,7,15,30天的开始日期）
	public List<String> getProductDaysStartDateList() {
		List<String> list = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
        Date today = cal.getTime();
		String todayTime = sdf.format(today);
		list.add(todayTime);
		
        cal.add(Calendar.DAY_OF_MONTH, -6);// 最近7天： 今天-6天
        Date sevenDay = cal.getTime();
		String sevenDayTime = sdf.format(sevenDay);
		list.add(sevenDayTime);
		
        cal.add(Calendar.DAY_OF_MONTH, -8);// 最近15天：7天前再-8天
        Date fifteenDay = cal.getTime();
		String fifteenDayTime = sdf.format(fifteenDay);
		list.add(fifteenDayTime);
		
        cal.add(Calendar.DAY_OF_MONTH, -15);// 最近30天：15天前再-15天
        Date thirtyDay = cal.getTime();
		String thirtyDayTime = sdf.format(thirtyDay);
		list.add(thirtyDayTime);

		return list;
	}

}
