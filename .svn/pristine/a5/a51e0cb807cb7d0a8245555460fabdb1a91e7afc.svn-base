package com.nuite.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.AssistantEntity;
import com.nuite.entity.UserEntity;
import com.nuite.entity.WeekSalesEntity;
import com.nuite.service.AssistantService;
import com.nuite.service.WeekSalesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 导购销售周报表
 * @author yy
 * @date 2018-12-27 11:04:07
 */
@RestController
@RequestMapping("/api/report/weeksales")
@Api(tags="报表 - 导购销售周报表")
public class WeekSalesController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private WeekSalesService weekSalesService;
    
    @Autowired
    private AssistantService assistantService;
    
    
    
	/**
     * 查询某一周全部店员报表列表
     */
    @Login
    @GetMapping("getWeeksalesList")
    @ApiOperation("查询某一周全部店员报表列表")
    public R getWeeksalesList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("门店序号") @RequestParam("shopSeq") Integer shopSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week) {
    	try {
    		//TODO 判断该门店是否为用户下级区域
    		

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
			return R.ok(weekSalesList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }

	
    
    
	/**
     * 查询月销售目标
     */
    @Login
    @GetMapping("getMonthGoal")
    @ApiOperation("查询月销售目标")
    public R getMonthGoal(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			HttpServletRequest request) {
    	try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
    		//查询店员该月所有周销售报表
			List<WeekSalesEntity> weekSalesList = weekSalesService.getAssistantWeekSalesOneMonth(year, month, assistantSeq);
			Map<String, Object> map = new HashMap<String, Object>();
    		if(weekSalesList != null && weekSalesList.size() > 0) {
    			map.put("monthGoal", weekSalesList.get(0).getMonthGoal());
    		} else {
    			map.put("monthGoal", null);
    		}
    		return R.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
    }
    
    
	/**
     * 修改月销售目标
     */
    @Login
    @PostMapping("changeMonthGoal")
    @ApiOperation("修改月销售目标")
    public R changeMonthGoal(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("月销售目标") @RequestParam("monthGoal") BigDecimal monthGoal,
			HttpServletRequest request) {
    	try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
    		//查询店员该月所有周销售报表
			List<WeekSalesEntity> weekSalesList = weekSalesService.getAssistantWeekSalesOneMonth(year, month, assistantSeq);
    		for(WeekSalesEntity weekSales : weekSalesList) {
    			weekSales.setMonthGoal(monthGoal);
    		}
			weekSalesService.updateBatchById(weekSalesList);
			
    		return R.ok("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
    }
    
    
	
	
	/**
	 * 新增或修改一条报表记录
	 */
	@Login
	@PostMapping("addOrUpdateWeeksales")
	@ApiOperation("新增或修改一条报表记录")
	public R addOrUpdateWeeksales(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week,
			@ApiParam("数据截止时间") @RequestParam("cutoffTime") Date cutoffTime,
			@ApiParam("月销售目标（修改时，需要再单独调用changeMonthGoal接口，完成对本月所有周记录的修改）") @RequestParam("monthGoal") BigDecimal monthGoal,
			
			@ApiParam("周销售目标") @RequestParam("weekGoal") BigDecimal weekGoal,
			@ApiParam("本周销售额") @RequestParam("weekAmount") BigDecimal weekAmount,
			@ApiParam("本周销售双数") @RequestParam("weekPairs") Integer weekPairs,
			@ApiParam("连单数(百分比)") @RequestParam("continuityPercent") String continuityPercent,
			@ApiParam("店员周工作分析") @RequestParam("workerDescribe") String workerDescribe,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
//    		Seq					int			0	0	0	0	0	0		0	0	0	0	序号(主键)		-1			
//    		ShopSeq				int			0	0	-1	0	0	0		0	0	0	0	店铺序号(NWBase_Shop表外键)		0	
    		
//    		AssistantSeq		int			0	0	-1	0	0	0		0	0	0	0	店员序号(NWUser_Assistant表外键)		0			
//    		Year				int			0	0	-1	0	0	0		0	0	0	0	年		0			
//    		Month				int			0	0	-1	0	0	0		0	0	0	0	月		0			
//    		Week				int			0	0	-1	0	0	0		0	0	0	0	第几周		0			
//    		CutoffTime			datetime	0	0	-1	0	0	0		0	0	0	0	数据截止时间		0	
    		
//    		MonthGoal			decimal		18	2	-1	0	0	0		0	0	0	0	月销售目标		0	
//    		WeekGoal			decimal		18	2	-1	0	0	0		0	0	0	0	周销售目标		0			
//    		WeekAmount			decimal		18	2	-1	0	0	0		0	0	0	0	本周销售额		0			
//    		WeekPairs			int			0	0	-1	0	0	0		0	0	0	0	本周销售双数		0			
//    		ContinuityPercent	varchar		10	0	-1	0	0	0		0	0	0	0	连单数(百分比)	Chinese_PRC_CI_AS	0			
//    		WorkerDescribe		varchar		500	0	-1	0	0	0		0	0	0	0	店员周工作分析	Chinese_PRC_CI_AS	0	
    		
//    		InputTime			datetime	0	0	-1	0	0	0		0	0	0	0	插入时间		0			
//    		UpdateTime			datetime	0	0	-1	0	0	0		0	0	0	0	更新时间		0			
//    		Del					int			0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
    		
    		
			WeekSalesEntity weekSalesEntity = new WeekSalesEntity();
			
			weekSalesEntity.setShopSeq(loginUser.getUserAreaSeq());
			weekSalesEntity.setAssistantSeq(assistantSeq);
			weekSalesEntity.setYear(year);
			weekSalesEntity.setMonth(month);
			weekSalesEntity.setWeek(week);
			weekSalesEntity.setCutoffTime(cutoffTime);
			weekSalesEntity.setMonthGoal(monthGoal);
			
			weekSalesEntity.setWeekGoal(weekGoal);
			weekSalesEntity.setWeekAmount(weekAmount);
			weekSalesEntity.setWeekPairs(weekPairs);
			weekSalesEntity.setContinuityPercent(continuityPercent);
			weekSalesEntity.setWorkerDescribe(workerDescribe);
			weekSalesEntity.setDel(0);
			
			weekSalesService.addOrUpdateWeeksales(weekSalesEntity);
			
			return R.ok("录入成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("录入失败");
		}
	}
	
	
	
	
	/**
     *  获取一条报表记录（用于编辑）
     */
	@Login
	@GetMapping("getWeeksalesForEdit")
	@ApiOperation("获取一条报表记录（用于编辑）")
	public R getWeeksalesForEdit(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
			WeekSalesEntity weekSalesEntity = new WeekSalesEntity();
			
			weekSalesEntity.setShopSeq(loginUser.getUserAreaSeq());
			weekSalesEntity.setAssistantSeq(assistantSeq);
			weekSalesEntity.setYear(year);
			weekSalesEntity.setMonth(month);
			weekSalesEntity.setWeek(week);
			
			weekSalesEntity = weekSalesService.getWeekSalesByEntity(weekSalesEntity);
			
			List<WeekSalesEntity> list = new ArrayList<WeekSalesEntity>();
			list.add(weekSalesEntity);
			
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("录入失败");
		}
	}
    
    
    

}
