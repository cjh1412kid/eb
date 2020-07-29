package com.nuite.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.AssistantEntity;
import com.nuite.entity.CompeteAnalysisEntity;
import com.nuite.entity.StoreBrandEntity;
import com.nuite.entity.UserEntity;
import com.nuite.entity.WeekSalesEntity;
import com.nuite.service.AssistantService;
import com.nuite.service.CompeteAnalysisService;
import com.nuite.service.StoreBrandService;
import com.nuite.service.StoreService;
import com.nuite.service.WeekSalesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 商场竞品销售跟进表
 * @author yy
 * @date 2018-12-27 11:04:07
 */
@RestController
@RequestMapping("/api/report/competeAndSalesFollow")
@Api(tags="报表 - 商场竞品销售跟进表")
public class CompeteAndSalesFollowController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private WeekSalesService weekSalesService;
    
    @Autowired
    private AssistantService assistantService;
    
    @Autowired
    private CompeteAnalysisService competeAnalysisService;
    
    @Autowired
    private StoreBrandService storeBrandService;
    
    @Autowired
    private StoreService storeService;
    
    
    
	/**
     * 查询区域内 商场竞品销售跟进报表
     */
    @Login
    @GetMapping("getList")
    @ApiOperation("查询区域内 商场竞品销售跟进报表")
    public R getList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week) {
    	try {
   		 	//判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
    		//根据区域筛选条件，获取分组格式的全部的门店详细List
    		List<Map<String, Object>> shopDetailList = getAllGroupShopListByAreaSeqs(type, areaSeqs, loginUser.getBrandSeq());
    		if(shopDetailList == null || shopDetailList.size() == 0) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "没有满足要求的数据");
    		}
    		
    		
    		
    		List<Map<String, Object>> saleAndCompeteFollowList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> saleAndCompeteFollowMap;
    	
    		
    /*循环所有门店，获取每个门店的 销售，竞争力，组装结果*/
	for(Map<String, Object> map : shopDetailList) {
		saleAndCompeteFollowMap = new HashMap<String, Object>();
				
		//获取 大区、分公司、门店名称
		saleAndCompeteFollowMap.put("firstAreaName", map.get("firstAreaName"));
		saleAndCompeteFollowMap.put("secondAreaName", map.get("secondAreaName"));
		saleAndCompeteFollowMap.put("shopName", map.get("shopName"));
    	//门店序号
		Integer shopSeq = (Integer) map.get("shopSeq");
		
    		
			/***********     导购销售报表查询接口 原封代码   start  ****************/
    		//查询门店某一周所有店员的报表
    		List<WeekSalesEntity> weekSalesList = weekSalesService.getAllWeekSalesOneWeek(shopSeq, year, month, week);

    		
			//门店所有店员，某月某周前销售额总额（降序排序）
			List<Map<String, Object>> allAssistantMonthTotalSalesList = weekSalesService.getAssistantMonthTotalAmount(shopSeq, year, month, week);
			
			
			
			BigDecimal total_monthGoal = BigDecimal.ZERO;  		//月销售目标所有店员合计
			BigDecimal total_monthAmount = BigDecimal.ZERO;  	//月销售额所有店员合计
			BigDecimal total_weekGoal = BigDecimal.ZERO;  		//周销售目标所有店员合计
			BigDecimal total_lastWeekAmount = BigDecimal.ZERO;  //上周销售额所有店员合计
			BigDecimal total_weekAmount = BigDecimal.ZERO;  	//周销售额所有店员合计
			Integer total_weekPairs = 0;  		//周销售双数所有店员合计
			
			
			for(WeekSalesEntity weekSalesEntity : weekSalesList) {
				Integer assistantSeq = weekSalesEntity.getAssistantSeq();
				AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
    			//添加店员姓名、头像、等级
				weekSalesEntity.setAssistantName(assistantEntity.getName());
				weekSalesEntity.setHeadImg(getAssistantPictureUrl(assistantEntity.getSeq().toString()) + assistantEntity.getHeadImg());
				weekSalesEntity.setAssistantLevel(assistantEntity.getAssistantLevel());
				
				//查询上周销售额
				WeekSalesEntity lastWeekSalesEntity = weekSalesService.getLastWeekSalesByParams(assistantSeq, year, month, week);
				if(lastWeekSalesEntity != null) {
					weekSalesEntity.setLastWeekAmount(lastWeekSalesEntity.getWeekAmount());
				}
				
	    		//添加月累计、完成率、排名
				for(int i = 0; i < allAssistantMonthTotalSalesList.size(); i++) {
					Map<String, Object> monthTotalMap = allAssistantMonthTotalSalesList.get(i);
					if(((Integer)monthTotalMap.get("assistantSeq")).equals(assistantSeq)) {
			    		BigDecimal monthTotalAmount = (BigDecimal)monthTotalMap.get("monthTotalAmount");
			    		weekSalesEntity.setMonthTotalAmount(monthTotalAmount);
			    		weekSalesEntity.setMonthTotalRank(i + 1);
			    		if(weekSalesEntity.getMonthGoal().compareTo(BigDecimal.ZERO) > 0) {
				    		BigDecimal rate = monthTotalAmount.divide(weekSalesEntity.getMonthGoal(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
				    		weekSalesEntity.setMonthTotalRate(rate);
				    		weekSalesEntity.setMonthTotalRateStr(rate + "%");
			    		}
			    		break;
					}
				}
				
				
				if(weekSalesEntity.getMonthGoal() != null) {
					total_monthGoal = total_monthGoal.add(weekSalesEntity.getMonthGoal());
				}
				if(weekSalesEntity.getMonthTotalAmount() != null) {
					total_monthAmount = total_monthAmount.add(weekSalesEntity.getMonthTotalAmount());
				}
				if(weekSalesEntity.getWeekGoal() != null) {
					total_weekGoal = total_weekGoal.add(weekSalesEntity.getWeekGoal());
				}
				if(weekSalesEntity.getLastWeekAmount() != null) {
					total_lastWeekAmount = total_lastWeekAmount.add(weekSalesEntity.getLastWeekAmount());
				}
				if(weekSalesEntity.getWeekAmount() != null) {
					total_weekAmount = total_weekAmount.add(weekSalesEntity.getWeekAmount());
				}
				if(weekSalesEntity.getWeekPairs() != null) {
					total_weekPairs = total_weekPairs + (weekSalesEntity.getWeekPairs());
				}
				
			}
    		
			
			//合计
			WeekSalesEntity hejiEntity = new WeekSalesEntity();
			hejiEntity.setAssistantName("店铺 合计");
			hejiEntity.setMonthGoal(total_monthGoal);
			hejiEntity.setMonthTotalAmount(total_monthAmount);
			if(total_monthGoal.compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal total_rate = total_monthAmount.divide(total_monthGoal, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
	    		hejiEntity.setMonthTotalRate(total_rate);
	    		hejiEntity.setMonthTotalRateStr(total_rate + "%");
			}
			hejiEntity.setWeekGoal(total_weekGoal);
			hejiEntity.setLastWeekAmount(total_lastWeekAmount);
			hejiEntity.setWeekAmount(total_weekAmount);
			hejiEntity.setWeekPairs(total_weekPairs);
			
			
			
			/******计算同期合计，同期对比 （代码复制了一份，年份-1）******/
			
			
    		//查询门店某一周所有店员的报表
    		List<WeekSalesEntity> lastYearWeekSalesList = weekSalesService.getAllWeekSalesOneWeek(shopSeq, year - 1, month, week);

			
			//门店所有店员，某月某周前销售额总额（降序排序）
			List<Map<String, Object>> lastYearAllAssistantMonthTotalSalesList = weekSalesService.getAssistantMonthTotalAmount(shopSeq, year - 1, month, week);
			
			
			
			BigDecimal lastYear_total_monthGoal = BigDecimal.ZERO;  		//月销售目标所有店员合计
			BigDecimal lastYear_total_monthAmount = BigDecimal.ZERO;  		//月销售额所有店员合计
			BigDecimal lastYear_total_weekGoal = BigDecimal.ZERO;  			//周销售目标所有店员合计
			BigDecimal lastYear_total_lastWeekAmount = BigDecimal.ZERO;  	//上周销售额所有店员合计
			BigDecimal lastYear_total_weekAmount = BigDecimal.ZERO;  		//周销售额所有店员合计
			Integer lastYear_total_weekPairs = 0;  							//周销售双数所有店员合计
			
			
			for(WeekSalesEntity weekSalesEntity : lastYearWeekSalesList) {
				Integer assistantSeq = weekSalesEntity.getAssistantSeq();
				
				//查询上周销售额
				WeekSalesEntity lastWeekSalesEntity = weekSalesService.getLastWeekSalesByParams(assistantSeq, year - 1, month, week);
				if(lastWeekSalesEntity != null) {
					weekSalesEntity.setLastWeekAmount(lastWeekSalesEntity.getWeekAmount());
				}
				
	    		//添加月累计
				for(int i = 0; i < lastYearAllAssistantMonthTotalSalesList.size(); i++) {
					Map<String, Object> monthTotalMap = lastYearAllAssistantMonthTotalSalesList.get(i);
					if(((Integer)monthTotalMap.get("assistantSeq")).equals(assistantSeq)) {
			    		BigDecimal monthTotalAmount = (BigDecimal)monthTotalMap.get("monthTotalAmount");
			    		weekSalesEntity.setMonthTotalAmount(monthTotalAmount);
			    		break;
					}
				}
				
				
				if(weekSalesEntity.getMonthGoal() != null) {
					lastYear_total_monthGoal = lastYear_total_monthGoal.add(weekSalesEntity.getMonthGoal());
				}
				if(weekSalesEntity.getMonthTotalAmount() != null) {
					lastYear_total_monthAmount = lastYear_total_monthAmount.add(weekSalesEntity.getMonthTotalAmount());
				}
				if(weekSalesEntity.getWeekGoal() != null) {
					lastYear_total_weekGoal = lastYear_total_weekGoal.add(weekSalesEntity.getWeekGoal());
				}
				if(weekSalesEntity.getLastWeekAmount() != null) {
					lastYear_total_lastWeekAmount = lastYear_total_lastWeekAmount.add(weekSalesEntity.getLastWeekAmount());
				}
				if(weekSalesEntity.getWeekAmount() != null) {
					lastYear_total_weekAmount = lastYear_total_weekAmount.add(weekSalesEntity.getWeekAmount());
				}
				if(weekSalesEntity.getWeekPairs() != null) {
					lastYear_total_weekPairs = lastYear_total_weekPairs + (weekSalesEntity.getWeekPairs());
				}
				
			}
    		
			
			//同期合计
			WeekSalesEntity lastYearHejiEntity = new WeekSalesEntity();
			lastYearHejiEntity.setAssistantName("同期 合计");
			lastYearHejiEntity.setMonthGoal(lastYear_total_monthGoal);
			lastYearHejiEntity.setMonthTotalAmount(lastYear_total_monthAmount);
			if(lastYear_total_monthGoal.compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal total_rate = lastYear_total_monthAmount.divide(lastYear_total_monthGoal, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
	    		lastYearHejiEntity.setMonthTotalRate(total_rate);
	    		lastYearHejiEntity.setMonthTotalRateStr(total_rate + "%");
			}
			lastYearHejiEntity.setWeekGoal(lastYear_total_weekGoal);
			lastYearHejiEntity.setLastWeekAmount(lastYear_total_lastWeekAmount);
			lastYearHejiEntity.setWeekAmount(lastYear_total_weekAmount);
			lastYearHejiEntity.setWeekPairs(lastYear_total_weekPairs);
			
			

			
			
			
			
			/******计算同期合计，同期对比******/
			
			
			/******同期对比******/
			WeekSalesEntity duibiEntity = new WeekSalesEntity();
			
			duibiEntity.setAssistantName("同期 对比");
			duibiEntity.setMonthGoal(hejiEntity.getMonthGoal().subtract(lastYearHejiEntity.getMonthGoal()));
			duibiEntity.setMonthTotalAmount(hejiEntity.getMonthTotalAmount().subtract(lastYearHejiEntity.getMonthTotalAmount()));
			if(lastYearHejiEntity.getMonthTotalRate() != null) {
				BigDecimal duibi_rate = hejiEntity.getMonthTotalRate().subtract(lastYearHejiEntity.getMonthTotalRate());
		    	duibiEntity.setMonthTotalRate(duibi_rate);
		    	duibiEntity.setMonthTotalRateStr(duibi_rate + "%");
			}
			
			duibiEntity.setWeekGoal(hejiEntity.getWeekGoal().subtract(lastYearHejiEntity.getWeekGoal()));
			duibiEntity.setLastWeekAmount(hejiEntity.getLastWeekAmount().subtract(lastYearHejiEntity.getLastWeekAmount()));
			duibiEntity.setWeekAmount(hejiEntity.getWeekAmount().subtract(lastYearHejiEntity.getWeekAmount()));
			duibiEntity.setWeekPairs(hejiEntity.getWeekPairs() - lastYearHejiEntity.getWeekPairs());
			
			/******同期对比******/
			
			
			weekSalesList.add(hejiEntity);
			weekSalesList.add(lastYearHejiEntity);
			weekSalesList.add(duibiEntity);
//			return R.ok(weekSalesList);
			
			
			
			/***********     导购销售报表查询接口 原封代码   end  ****************/
			
			
			/* 从原封代码中获取数据 */
			//周数据
				//去年本周销售额
			saleAndCompeteFollowMap.put("lastYearWeekAmount", lastYearHejiEntity.getWeekAmount());
				//本周目标
			saleAndCompeteFollowMap.put("weekGoal", hejiEntity.getWeekGoal());
				//本周实际销售
			saleAndCompeteFollowMap.put("weekAmount", hejiEntity.getWeekAmount());
				//本周完成率， 之前只有月总完成率，需要计算
			if(hejiEntity.getWeekGoal().compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal weekCompleteRate = hejiEntity.getWeekAmount().divide(hejiEntity.getWeekGoal(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
				saleAndCompeteFollowMap.put("weekCompleteRate", weekCompleteRate + "%");
			} else {
				saleAndCompeteFollowMap.put("weekCompleteRate", null);
			}
				//增长率，之前不是比率，需要计算
			if(lastYearHejiEntity.getWeekAmount().compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal weekGrowRate = (hejiEntity.getWeekAmount().subtract(lastYearHejiEntity.getWeekAmount())).divide(lastYearHejiEntity.getWeekAmount(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
				saleAndCompeteFollowMap.put("weekGrowRate", weekGrowRate + "%");
			} else {
				saleAndCompeteFollowMap.put("weekGrowRate", null);
			}

			//月数据
				//去年同月销售额
			saleAndCompeteFollowMap.put("lastYearMonthAmount", lastYearHejiEntity.getMonthTotalAmount());
				//本月目标
			saleAndCompeteFollowMap.put("monthGoal", hejiEntity.getMonthGoal());
				//本月实际销售   （放入 线下销售额 和 月累计两个字段)
			saleAndCompeteFollowMap.put("monthAmount", hejiEntity.getMonthTotalAmount());
			saleAndCompeteFollowMap.put("offlineAmount", hejiEntity.getMonthTotalAmount());
			saleAndCompeteFollowMap.put("onlineAmount", null);
				//完成率
			saleAndCompeteFollowMap.put("monthCompleteRate", hejiEntity.getMonthTotalRateStr());
				//增长率，之前不是比率，需要计算
			if(lastYearHejiEntity.getMonthTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal monthGrowRate = (hejiEntity.getMonthTotalAmount().subtract(lastYearHejiEntity.getMonthTotalAmount())).divide(lastYearHejiEntity.getMonthTotalAmount(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
				saleAndCompeteFollowMap.put("monthGrowRate", monthGrowRate + "%");
			} else {
				saleAndCompeteFollowMap.put("monthGrowRate", null);
			}
			

			
			
			
			
			
			
			
			/***********     竞争力分析报表查询接口 原封代码  start   ****************/
			

    		//根据门店序号，查询门店所处的商场序号
    		StoreBrandEntity storeBrand = storeService.getStoreBrandByShopSeq(shopSeq);
    		if(storeBrand == null) {
    			//没有维护商场信息，则没有竞争力相关数据
    			saleAndCompeteFollowList.add(saleAndCompeteFollowMap);
    			continue;
    		}
	    	Integer storeSeq = storeBrand.getStoreSeq();  //商场序号
	    	Integer currentBrandStoreBrandSeq = storeBrand.getSeq();  //当前品牌商场品牌序号
	    	BigDecimal currentBrandMonthSale = BigDecimal.ZERO;  //当前品牌月销售额
    		
	    	
    		//查询门店某一周所有商场品牌竞争力的报表
    		List<CompeteAnalysisEntity> competeAnalysisList = competeAnalysisService.getAllCompeteAnalysisOneWeek(storeSeq, year, month, week);
			Integer brandsNum = null;
			Integer monthTaskNo = null;
			Integer monthRealNo = null;
			String describe = null;
    		for(CompeteAnalysisEntity competeAnalysis : competeAnalysisList) {
    			//添加商场品牌名称
    			StoreBrandEntity storeBrandEntity = storeBrandService.selectById(competeAnalysis.getStoreBrandSeq());
    			competeAnalysis.setStoreBrandName(storeBrandEntity.getBrandName());
    			
				//查询上周销售额
				CompeteAnalysisEntity lastWeekSalesEntity = competeAnalysisService.getLastWeekCompeteAnalysis(competeAnalysis.getStoreBrandSeq(), year, month, week);
				if(lastWeekSalesEntity != null) {
					competeAnalysis.setLastWeekSale(lastWeekSalesEntity.getWeekSale());
					//计算周增长
					competeAnalysis.setWeekChange(competeAnalysis.getWeekSale().subtract(lastWeekSalesEntity.getWeekSale()));
				}
				
	    		//添加月销售额
			    BigDecimal monthSale = competeAnalysisService.getStoreBrandMonthTotalSale(competeAnalysis.getStoreBrandSeq(), year, month, week);  //品牌某月某周前销售额总额
			    competeAnalysis.setMonthSale(monthSale);
			    
	    		//获取总品牌数,伊伴月任务排名,实际排名,截止时间
			    if(competeAnalysis.getStoreBrandSeq().equals(currentBrandStoreBrandSeq)) {
			    	brandsNum = competeAnalysis.getBrandsNum();
			    	monthTaskNo = competeAnalysis.getMonthTaskNo();
			    	monthRealNo = competeAnalysis.getMonthRealNo();
			    	describe = competeAnalysis.getDescribe();
			    	currentBrandMonthSale = competeAnalysis.getMonthSale();
			    }
			}
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("competeAnalysisList", competeAnalysisList);
    		resultMap.put("brandsNum", brandsNum);
    		resultMap.put("monthTaskNo", monthTaskNo);
    		resultMap.put("monthRealNo", monthRealNo);
    		resultMap.put("describe", describe);
    		
//			return R.ok(resultMap);
			
			
			
			
			/***********     竞争力分析报表查询接口 原封代码  end  ****************/
			
			
    		
    		/* 从原封代码中获取数据 */
			//全馆品牌数量、排名目标、 实际排名
			saleAndCompeteFollowMap.put("brandsNum", brandsNum);
			saleAndCompeteFollowMap.put("monthTaskNo", monthTaskNo);
			saleAndCompeteFollowMap.put("monthRealNo", monthRealNo);
    		
			//当前品牌的月销售额
			
			
			//第1名	第2名	第3名	第4名	第5名	第6名	第7名	第8名	第9名	第10名	第11名	第12名	第13名	第14名	第15名
    		for(int i = 1; i <= competeAnalysisList.size(); i++) {
    			CompeteAnalysisEntity competeAnalysis = competeAnalysisList.get(i-1);
    			//品牌名
    			String storeBrandName = competeAnalysis.getStoreBrandName();
    			//品牌月销售额
    			BigDecimal monthSale = competeAnalysis.getMonthSale();
    			//当前品牌的月销售占它的比例
    			String currentBrandPercent = "";
    			if(monthSale != null && monthSale.compareTo(BigDecimal.ZERO) > 0) {
    	    		BigDecimal monthGrowRate = currentBrandMonthSale.divide(monthSale, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    	    		currentBrandPercent = monthGrowRate + "%";
    			}
    			
				saleAndCompeteFollowMap.put("No" + i, storeBrandName + "\n"  + monthSale + "\n" + currentBrandPercent);
    		}
    		
    		
			saleAndCompeteFollowList.add(saleAndCompeteFollowMap);
	}
	
	
	
		return R.ok(saleAndCompeteFollowList);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    

}
