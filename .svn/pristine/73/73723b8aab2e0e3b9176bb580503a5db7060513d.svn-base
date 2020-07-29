package com.nuite.modules.sys.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.DeadlineRaiseRateEntity;
import com.nuite.modules.sys.service.DeadlineRaiseRateService;
import com.nuite.modules.sys.service.PeriodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * 基础信息查询（区域、门店、波次）
 * @author yy
 * @date 2018-11-27 15:12:43
 */
@RestController
@RequestMapping("/sys/baseInfo")
@Api(tags="基础信息查询（区域、门店、波次）")
public class AreaShopPeriodController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private DeadlineRaiseRateService deadlineRaiseRateService;
    
    
    
    /**
     * 获取补单可选择的波次
     */
    @GetMapping("periods")
    @ApiOperation("获取补单可选择的波次")
    public R periods() {
    	try {
    		//获取今年的补单配置
    		Calendar cal = Calendar.getInstance();
    		Integer thisYear = cal.get(Calendar.YEAR);
    		Date nowDate = cal.getTime();
    		
    		DeadlineRaiseRateEntity thisYearSetting = deadlineRaiseRateService.getSomeYearSetting(thisYear);
    		if(thisYearSetting == null){
    			return R.error("请先完成今年的补单配置！");
    		}
    		//补单提前考核截止日的天数
    		Integer thisYearPatchAdvanceDays = thisYearSetting.getPatchAdvanceDays();
    		
    		
    		List<String> season = new ArrayList<String>();
			if (DateUtils.daysBetween(nowDate, thisYearSetting.getSpringDeep()) >= thisYearPatchAdvanceDays
					|| DateUtils.daysBetween(nowDate, thisYearSetting.getSpringShallow()) >= thisYearPatchAdvanceDays) {
				season.add(thisYear + "春季");
			}
			if (DateUtils.daysBetween(nowDate, thisYearSetting.getHalfCold()) >= thisYearPatchAdvanceDays
					|| DateUtils.daysBetween(nowDate, thisYearSetting.getFullCold()) >= thisYearPatchAdvanceDays) {
				season.add(thisYear + "夏季");
			}
			if (DateUtils.daysBetween(nowDate, thisYearSetting.getAutumnShallow()) >= thisYearPatchAdvanceDays
					|| DateUtils.daysBetween(nowDate, thisYearSetting.getAutumnDeep()) >= thisYearPatchAdvanceDays) {
				season.add(thisYear + "秋季");
			}
			if (DateUtils.daysBetween(nowDate, thisYearSetting.getSingleBoot()) >= thisYearPatchAdvanceDays
					|| DateUtils.daysBetween(nowDate, thisYearSetting.getTwoCotton()) >= thisYearPatchAdvanceDays
					|| DateUtils.daysBetween(nowDate, thisYearSetting.getBigCotton()) >= thisYearPatchAdvanceDays) {
				season.add(thisYear + "冬季");
			}
			
			
			
    		//获取明年的补单配置
    		Integer nextYear = thisYear + 1;
    		
    		DeadlineRaiseRateEntity nextYearSetting = deadlineRaiseRateService.getSomeYearSetting(nextYear);
    		if(nextYearSetting != null){
	    		//补单提前考核截止日的天数
	    		Integer nextYearPatchAdvanceDays = nextYearSetting.getPatchAdvanceDays();
	    		
				if (DateUtils.daysBetween(nowDate, nextYearSetting.getSpringDeep()) >= nextYearPatchAdvanceDays
						|| DateUtils.daysBetween(nowDate, nextYearSetting.getSpringShallow()) >= nextYearPatchAdvanceDays) {
					season.add(nextYear + "春季");
				}
				if (DateUtils.daysBetween(nowDate, nextYearSetting.getHalfCold()) >= nextYearPatchAdvanceDays
						|| DateUtils.daysBetween(nowDate, nextYearSetting.getFullCold()) >= nextYearPatchAdvanceDays) {
					season.add(nextYear + "夏季");
				}
				if (DateUtils.daysBetween(nowDate, nextYearSetting.getAutumnShallow()) >= nextYearPatchAdvanceDays
						|| DateUtils.daysBetween(nowDate, nextYearSetting.getAutumnDeep()) >= nextYearPatchAdvanceDays) {
					season.add(nextYear + "秋季");
				}
				if (DateUtils.daysBetween(nowDate, nextYearSetting.getSingleBoot()) >= nextYearPatchAdvanceDays
						|| DateUtils.daysBetween(nowDate, nextYearSetting.getTwoCotton()) >= nextYearPatchAdvanceDays
						|| DateUtils.daysBetween(nowDate, nextYearSetting.getBigCotton()) >= nextYearPatchAdvanceDays) {
					season.add(nextYear + "冬季");
				}
    		}
			
    		List<Map<String, Object>> periodList = new ArrayList<Map<String, Object>>();
    		if(season.size() > 0) {
    			periodList = periodService.getPeriodsByBrandSeq(1, season);
    		}
    		return R.ok(periodList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    /**
     * 获取补单可选择的品类（根据已选择的波次名称）
     */
    @GetMapping("categorys")
    @ApiOperation("获取补单可选择的品类")
    public R categorys(@ApiParam("波次名称") @RequestParam("periodName") String periodName) {
    	try {
    		//获取今年的补单配置
    		Calendar cal = Calendar.getInstance();
    		Integer thisYear = cal.get(Calendar.YEAR);
    		Date nowDate = cal.getTime();
    		
    		DeadlineRaiseRateEntity thisYearSetting = deadlineRaiseRateService.getSomeYearSetting(thisYear);
    		if(thisYearSetting == null){
    			return R.error("请先完成今年的补单配置！");
    		}
    		//补单提前考核截止日的天数
    		Integer thisYearPatchAdvanceDays = thisYearSetting.getPatchAdvanceDays();
    		
    		Set<String> categorys = new LinkedHashSet<String>();
    		if(periodName.indexOf("春季") != -1){
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getSpringDeep()) >= thisYearPatchAdvanceDays) {
					categorys.add("春深");
				}
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getSpringShallow()) >= thisYearPatchAdvanceDays) {
					categorys.add("春浅");
				}
    		} else if (periodName.indexOf("夏季") != -1) {
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getHalfCold()) >= thisYearPatchAdvanceDays) {
					categorys.add("半凉");
				}
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getFullCold()) >= thisYearPatchAdvanceDays) {
					categorys.add("全凉");
				}
    		} else if (periodName.indexOf("秋季") != -1) {
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getAutumnShallow()) >= thisYearPatchAdvanceDays) {
					categorys.add("秋浅");
				}
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getAutumnDeep()) >= thisYearPatchAdvanceDays) {
					categorys.add("秋深");
				}
    		} else if (periodName.indexOf("冬季") != -1) {
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getSingleBoot()) >= thisYearPatchAdvanceDays) {
					categorys.add("单靴");
				}
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getTwoCotton()) >= thisYearPatchAdvanceDays) {
					categorys.add("二棉");
				}
				if (DateUtils.daysBetween(nowDate, thisYearSetting.getBigCotton()) >= thisYearPatchAdvanceDays) {
					categorys.add("大棉");
				}
    		}
    		
    		
    		//获取明年的补单配置
    		Integer nextYear = thisYear + 1;
    		
    		DeadlineRaiseRateEntity nextYearSetting = deadlineRaiseRateService.getSomeYearSetting(nextYear);
    		if(nextYearSetting != null){
	    		//补单提前考核截止日的天数
	    		Integer nextYearPatchAdvanceDays = nextYearSetting.getPatchAdvanceDays();
	    		
	    		if(periodName.indexOf("春季") != -1){
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getSpringDeep()) >= nextYearPatchAdvanceDays) {
						categorys.add("春深");
					}
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getSpringShallow()) >= nextYearPatchAdvanceDays) {
						categorys.add("春浅");
					}
	    		} else if (periodName.indexOf("夏季") != -1) {
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getHalfCold()) >= nextYearPatchAdvanceDays) {
						categorys.add("半凉");
					}
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getFullCold()) >= nextYearPatchAdvanceDays) {
						categorys.add("全凉");
					}
	    		} else if (periodName.indexOf("秋季") != -1) {
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getAutumnShallow()) >= nextYearPatchAdvanceDays) {
						categorys.add("秋浅");
					}
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getAutumnDeep()) >= nextYearPatchAdvanceDays) {
						categorys.add("秋深");
					}
	    		} else if (periodName.indexOf("冬季") != -1) {
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getSingleBoot()) >= nextYearPatchAdvanceDays) {
						categorys.add("单靴");
					}
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getTwoCotton()) >= nextYearPatchAdvanceDays) {
						categorys.add("二棉");
					}
					if (DateUtils.daysBetween(nowDate, nextYearSetting.getBigCotton()) >= nextYearPatchAdvanceDays) {
						categorys.add("大棉");
					}
	    		}
    		}
    		
    		return R.ok(categorys);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
}
