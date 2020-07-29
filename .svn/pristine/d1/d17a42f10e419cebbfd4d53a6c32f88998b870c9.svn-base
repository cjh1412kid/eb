package com.nuite.controller;

import java.math.BigDecimal;
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
import com.nuite.entity.CompeteAnalysisEntity;
import com.nuite.entity.StoreBrandEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CompeteAnalysisService;
import com.nuite.service.StoreBrandService;
import com.nuite.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 竞争力分析
 * @author yy
 * @date 2019-01-03 10:45:55
 */
@RestController
@RequestMapping("/api/report/competeAnalysis")
@Api(tags="报表 - 竞争力分析接口")
public class CompeteAnalysisController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private CompeteAnalysisService competeAnalysisService;
    
    @Autowired
    private StoreBrandService storeBrandService;
    
    @Autowired
    private StoreService storeService;
    
    
    
    
	/**
     * 查询某一周 商场全部品牌 竞争力报表 列表
     */
    @Login
    @GetMapping("getCompeteAnalysisList")
    @ApiOperation("查询某一周 商场全部品牌 竞争力报表 列表")
    public R getCompeteAnalysisList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("门店序号") @RequestParam("shopSeq") Integer shopSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week) {
    	try {
    		//TODO 判断该门店是否为用户下级区域
    		
    		
    		//根据门店序号，查询门店所处的商场序号
    		StoreBrandEntity storeBrand = storeService.getStoreBrandByShopSeq(shopSeq);
    		if(storeBrand == null) {  //没有新增过
    			return R.error(HttpStatus.FORBIDDEN.value(), "请先维护商场信息");
    		}
	    	Integer storeSeq = storeBrand.getStoreSeq();  //商场序号
	    	Integer storeBrandSeq = storeBrand.getSeq();  //当前品牌商场品牌序号
    		
	    	
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
			    if(competeAnalysis.getStoreBrandSeq().equals(storeBrandSeq)) {
			    	brandsNum = competeAnalysis.getBrandsNum();
			    	monthTaskNo = competeAnalysis.getMonthTaskNo();
			    	monthRealNo = competeAnalysis.getMonthRealNo();
			    	describe = competeAnalysis.getDescribe();
			    }
			}
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("competeAnalysisList", competeAnalysisList);
    		resultMap.put("brandsNum", brandsNum);
    		resultMap.put("monthTaskNo", monthTaskNo);
    		resultMap.put("monthRealNo", monthRealNo);
    		resultMap.put("describe", describe);
    		
			return R.ok(resultMap);
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
	@PostMapping("addOrUpdateCompeteAnalysis")
	@ApiOperation("新增或修改一条报表记录")
	public R addOrUpdateCompeteAnalysis(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("商场品牌序号") @RequestParam("storeBrandSeq") Integer storeBrandSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("月") @RequestParam("month") Integer month,
			@ApiParam("第几周") @RequestParam("week") Integer week,
			@ApiParam("数据截止时间") @RequestParam(value="cutoffTime", required=false) Date cutoffTime,
			
			@ApiParam("商场排名") @RequestParam("storeNo") Integer storeNo,
			@ApiParam("本周销售额") @RequestParam("weekSale") BigDecimal weekSale,
			@ApiParam("商场总品牌数（只有伊伴品牌有）") @RequestParam(value="brandsNum", required=false) Integer brandsNum,
			@ApiParam("月任务排名（只有伊伴品牌有）") @RequestParam(value="monthTaskNo", required=false) Integer monthTaskNo,
			@ApiParam("月任务实际排名（只有伊伴品牌有）") @RequestParam(value="monthRealNo", required=false) Integer monthRealNo,
			@ApiParam("排名分析（只有伊伴品牌有）") @RequestParam(value="describe", required=false) String describe,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		//判断商场品牌序号是否为门店所属商场
    			//根据门店序号获取商场品牌实体
    		StoreBrandEntity shopStoreBrandEntity = storeService.getStoreBrandByShopSeq(loginUser.getUserAreaSeq());
				//根据商场品牌序号获取商场品牌实体
    		StoreBrandEntity storeBrandEntity = storeBrandService.selectById(storeBrandSeq);
    		if(!storeBrandEntity.getStoreSeq().equals(shopStoreBrandEntity.getStoreSeq())) {
    			return R.error(HttpStatus.FORBIDDEN.value(), "对不起，你的权限不足");
    		}
    		
//			Seq				int		0	0	0	-1	0	0		0	0	0	0			0			
//			StoreSeq		int		0	0	0	0	0	0		0	0	0	0	商场序号		0			
//			StoreBrandSeq	int		0	0	0	0	0	0		0	0	0	0	商场品牌序号		0			
//			Year			int		0	0	-1	0	0	0		0	0	0	0	年		0			
//			Month			int		0	0	-1	0	0	0		0	0	0	0	月		0			
//			Week			int		0	0	-1	0	0	0		0	0	0	0	日历第几周		0	
//			CutoffTime		datetime0	0	-1	0	0	0		0	0	0	0	数据截止时间		0			
			
//			StoreNo			int		0	0	-1	0	0	0		0	0	0	0	商场排名		0			
//			WeekSale		decimal	18	2	-1	0	0	0		0	0	0	0	本周销售		0			
//			Describe		varchar	500	0	-1	0	0	0		0	0	0	0	排名分析（只在伊伴品牌有数据）	Chinese_PRC_CI_AS	0			
//			MonthTaskNo		int		0	0	-1	0	0	0		0	0	0	0	月任务排名（只有伊伴品牌有）		0			
//			MonthRealNo		int		0	0	-1	0	0	0		0	0	0	0	月任务实际排名（只有伊伴品牌有）		0		
			
			
//			InputTime		datetime0	0	-1	0	0	0	(getdate())	0	0	0	0	插入时间		0			
//			Del				int		0	0	0	0	0	0	((0))	0	0	0	0			0		
    		
    		CompeteAnalysisEntity competeAnalysisEntity = new CompeteAnalysisEntity();
    		competeAnalysisEntity.setStoreSeq(storeBrandEntity.getStoreSeq());
    		competeAnalysisEntity.setStoreBrandSeq(storeBrandSeq);
    		competeAnalysisEntity.setYear(year);
    		competeAnalysisEntity.setMonth(month);
    		competeAnalysisEntity.setWeek(week);
    		competeAnalysisEntity.setCutoffTime(cutoffTime);
    		competeAnalysisEntity.setStoreNo(storeNo);
    		competeAnalysisEntity.setWeekSale(weekSale);
    		competeAnalysisEntity.setBrandsNum(brandsNum);
    		competeAnalysisEntity.setMonthTaskNo(monthTaskNo);
    		competeAnalysisEntity.setMonthRealNo(monthRealNo);
    		competeAnalysisEntity.setDescribe(describe);
    		competeAnalysisEntity.setInputTime(new Date());
    		competeAnalysisEntity.setDel(0);
			
    		competeAnalysisService.addOrUpdateCompeteAnalysis(competeAnalysisEntity);
    		
			return R.ok("录入成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("录入失败");
		}
	}
	
}
