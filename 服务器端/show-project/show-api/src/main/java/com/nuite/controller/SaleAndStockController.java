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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.dao.PromoteExplainDao;
import com.nuite.dao.SXDao;
import com.nuite.dao.SXOptionDao;
import com.nuite.entity.PromoteExplainEntity;
import com.nuite.entity.SXEntity;
import com.nuite.entity.SXOptionEntity;
import com.nuite.entity.SaleAndStockEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.SaleAndStockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 品类销售及库存周环比、同比分析
 * @author yy
 * @date 2019-01-07 17:15:58
 */
@RestController
@RequestMapping("/api/report/saleAndStock")
@Api(tags="报表 - 品类销售及库存周环比、同比分析")
public class SaleAndStockController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private SaleAndStockService saleAndStockService;
    
    @Autowired
    private SXDao sxDao;
    
    @Autowired
    private SXOptionDao sxOptionDao;
    
	@Autowired
	private PromoteExplainDao promoteExplainDao;
    
    
	/**
     * 查询品类销售及库存周环比、同比分析
     */
    @Login
    @GetMapping("getSaleAndStock")
    @ApiOperation("查询品类销售及库存周环比、同比分析")
    public R getSaleAndStock(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("门店序号") @RequestParam("shopSeq") Integer shopSeq,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("第几周") @RequestParam("week") Integer week,
			@ApiParam("类型: 0 环比 1 同比") @RequestParam("type") Integer type) {
    	try {
    		//TODO 判断该门店是否为用户下级区域
    		
    		
    		SXEntity sxEntity = new SXEntity();
    		sxEntity.setBrandSeq(loginUser.getBrandSeq());
    		sxEntity.setSXID("SX3");
    		sxEntity = sxDao.selectOne(sxEntity);
    		
    		
    		
    		/** 本周数据 **/
    		//查询本周的销售库存列表
    		List<SaleAndStockEntity> saleAndStockEntityList = saleAndStockService.getSomeWeekSaleAndStockList(shopSeq, year, week);
    		//查询本周促销说明
    		String promoteExplain1 = "";
    		String promoteExplain2 = "";
    		PromoteExplainEntity promoteExplainEntity = new PromoteExplainEntity();
    		promoteExplainEntity.setShopSeq(shopSeq);
    		promoteExplainEntity.setYear(year);
    		promoteExplainEntity.setWeek(week);
    		promoteExplainEntity = promoteExplainDao.selectOne(promoteExplainEntity);
    		if(promoteExplainEntity != null) {
    			promoteExplain1 = promoteExplainEntity.getExplain1();
    			promoteExplain2 = promoteExplainEntity.getExplain2();
    		}
    		/** 本周数据 **/
    		
    		
    		/** 环比或同比数据 **/
    		//获取环比，同比的周
    		if(type == 0) { //环比
    			if(week == 1) {
    				year = year - 1;
    				week = 53;
    			} else {
    				week = week - 1;
    			}
    		} else if (type == 1) { //同比
    			year = year - 1;
    		}
    		//查询该周的销售库存列表
    		List<SaleAndStockEntity> lastSaleAndStockEntityList = saleAndStockService.getSomeWeekSaleAndStockList(shopSeq, year, week);
    		//查询该周促销说明
    		String last_promoteExplain1 = "";
    		String last_promoteExplain2 = "";
    		PromoteExplainEntity lastPromoteExplainEntity = new PromoteExplainEntity();
    		lastPromoteExplainEntity.setShopSeq(shopSeq);
    		lastPromoteExplainEntity.setYear(year);
    		lastPromoteExplainEntity.setWeek(week);
    		lastPromoteExplainEntity = promoteExplainDao.selectOne(lastPromoteExplainEntity);
    		if(lastPromoteExplainEntity != null) {
    			last_promoteExplain1 = lastPromoteExplainEntity.getExplain1();
    			last_promoteExplain2 = lastPromoteExplainEntity.getExplain2();
    		}
    		/** 环比或同比数据 **/
    		
    		

    		
    		
    		
    		/** 组装结果集 **/
    		List<Map<String, Object>> saleAndStockList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> saleAndStockMap;
    		
    		//本周合计
    		Integer heji_saleCount = 0;
    		BigDecimal heji_totalPrice = BigDecimal.ZERO;
    		BigDecimal heji_avgUnitPrice = BigDecimal.ZERO;
    		Integer heji_shoeIdNum = 0;
    		Integer heji_stock = 0;
    		Integer heji_totalStock = 0;
    		//环比同比合计
    		Integer heji_last_saleCount = 0;
    		BigDecimal heji_last_totalPrice = BigDecimal.ZERO;
    		BigDecimal heji_last_avgUnitPrice = BigDecimal.ZERO;
    		Integer heji_last_shoeIdNum = 0;
    		Integer heji_last_stock = 0;
    		Integer heji_last_totalStock = 0;
    		
    		
    		for(SaleAndStockEntity saleAndStockEntity : saleAndStockEntityList) {
    			saleAndStockMap = new HashMap<String, Object>();
    			
    			//添加品类名称
    			SXOptionEntity sxOptionEntity = new SXOptionEntity();
    			sxOptionEntity.setSXSeq(sxEntity.getSeq());
    			sxOptionEntity.setCode(saleAndStockEntity.getSX3Code());
    			sxOptionEntity = sxOptionDao.selectOne(sxOptionEntity);
    			saleAndStockMap.put("SX3Name", sxOptionEntity.getValue());
    			
    			//添加本周的   双数   金额    单价   SKU  库存    库存占比   齐码率
    			Integer saleCount = saleAndStockEntity.getSaleCount();
    			saleAndStockMap.put("saleCount", saleCount);
    			if(saleCount != null) {
    				heji_saleCount = heji_saleCount + saleCount;
    			}
    			
    			BigDecimal totalPrice = saleAndStockEntity.getTotalPrice();
    			saleAndStockMap.put("totalPrice", totalPrice);
    			if(totalPrice != null) {
    				heji_totalPrice = heji_totalPrice.add(totalPrice);
    			}
    			
    			BigDecimal avgUnitPrice = saleAndStockEntity.getAvgUnitPrice();
    			saleAndStockMap.put("avgUnitPrice", avgUnitPrice);
    			if(avgUnitPrice != null) {
    				heji_avgUnitPrice = heji_avgUnitPrice.add(avgUnitPrice);
    			}
    			
    			Integer shoeIdNum = saleAndStockEntity.getShoeIdNum();
    			saleAndStockMap.put("shoeIdNum", shoeIdNum);
    			if(shoeIdNum != null) {
    				heji_shoeIdNum = heji_shoeIdNum + shoeIdNum;
    			}
    			
    			Integer stock = saleAndStockEntity.getStock();
    			saleAndStockMap.put("stock", stock);
    			if(stock != null) {
    				heji_stock = heji_stock + stock;
    			}
    			
    			Integer totalStock = saleAndStockEntity.getTotalStock();
    			if(totalStock != null) {
    				heji_totalStock = heji_totalStock + totalStock;
    			}
    			
    			saleAndStockMap.put("stockPercent", saleAndStockEntity.getStockPercent());
    			saleAndStockMap.put("completeRate", saleAndStockEntity.getCompleteRate());
    			
    			
    			for(SaleAndStockEntity lastSaleAndStockEntity : lastSaleAndStockEntityList) {
    				if(lastSaleAndStockEntity.getSX3Code().equals(saleAndStockEntity.getSX3Code())) {
    					
    					//添加同比或环比的  双数   金额    单价   SKU  库存    库存占比   齐码率
    	    			Integer last_saleCount = lastSaleAndStockEntity.getSaleCount();
    	    			saleAndStockMap.put("last_saleCount", last_saleCount);
    	    			if(last_saleCount != null) {
    	    				heji_last_saleCount = heji_last_saleCount + last_saleCount;
    	    			}
    	    			
    	    			BigDecimal last_totalPrice = lastSaleAndStockEntity.getTotalPrice();
    	    			saleAndStockMap.put("last_totalPrice", last_totalPrice);
    	    			if(last_totalPrice != null) {
    	    				heji_last_totalPrice = heji_last_totalPrice.add(last_totalPrice);
    	    			}
    	    			
    	    			BigDecimal last_avgUnitPrice = lastSaleAndStockEntity.getAvgUnitPrice();
    	    			saleAndStockMap.put("last_avgUnitPrice", last_avgUnitPrice);
    	    			if(last_avgUnitPrice != null) {
    	    				heji_last_avgUnitPrice = heji_last_avgUnitPrice.add(last_avgUnitPrice);
    	    			}
    	    			
    	    			Integer last_shoeIdNum = lastSaleAndStockEntity.getShoeIdNum();
    	    			saleAndStockMap.put("last_shoeIdNum", last_shoeIdNum);
    	    			if(last_shoeIdNum != null) {
    	    				heji_last_shoeIdNum = heji_last_shoeIdNum + last_shoeIdNum;
    	    			}
    	    			
    	    			Integer last_stock = lastSaleAndStockEntity.getStock();
    	    			saleAndStockMap.put("last_stock", last_stock);
    	    			if(last_stock != null) {
    	    				heji_last_stock = heji_last_stock + last_stock;
    	    			}
    	    			
    	    			Integer last_totalStock = lastSaleAndStockEntity.getTotalStock();
    	    			if(last_totalStock != null) {
    	    				heji_last_totalStock = heji_last_totalStock + last_totalStock;
    	    			}
    	    			
    	    			saleAndStockMap.put("last_stockPercent", lastSaleAndStockEntity.getStockPercent());
    	    			saleAndStockMap.put("last_completeRate", lastSaleAndStockEntity.getCompleteRate());
    					
    	    			
    	    			//添加 增长比  双数   金额    单价   SKU  库存    库存占比
    	    			if(saleCount != null && last_saleCount != null) {
    	    				saleAndStockMap.put("grow_saleCount", saleCount - last_saleCount);
    	    			} else {
    	    				saleAndStockMap.put("grow_saleCount", null);
    	    			}
    	    			if(totalPrice != null && last_totalPrice != null) {
    	    				saleAndStockMap.put("grow_totalPrice", totalPrice.subtract(last_totalPrice));
    	    			} else {
    	    				saleAndStockMap.put("grow_totalPrice", null);
    	    			}
    	    			if(avgUnitPrice != null && last_avgUnitPrice != null) {
    	    				saleAndStockMap.put("grow_avgUnitPrice", avgUnitPrice.subtract(last_avgUnitPrice));
    	    			} else {
    	    				saleAndStockMap.put("grow_avgUnitPrice", null);
    	    			}
    	    			if(shoeIdNum != null && last_shoeIdNum != null) {
    	    				saleAndStockMap.put("grow_shoeIdNum", shoeIdNum - last_shoeIdNum);
    	    			} else {
    	    				saleAndStockMap.put("grow_shoeIdNum", null);
    	    			}
    	    			if(stock != null && last_stock != null) {
    	    				saleAndStockMap.put("grow_stock", stock - last_stock);
    	    			} else {
    	    				saleAndStockMap.put("grow_stock", null);
    	    			}
    	    			if(!StringUtils.isEmpty(saleAndStockEntity.getStockPercent()) && !StringUtils.isEmpty(lastSaleAndStockEntity.getStockPercent())) {
    	    				Integer stockPercent = Integer.parseInt(saleAndStockEntity.getStockPercent().substring(0, saleAndStockEntity.getStockPercent().length() - 1));
    	    				Integer lastStockPercent = Integer.parseInt(lastSaleAndStockEntity.getStockPercent().substring(0, lastSaleAndStockEntity.getStockPercent().length() - 1));
    	    				saleAndStockMap.put("grow_stockPercent", (stockPercent - lastStockPercent) + "%");
    	    			} else {
    	    				saleAndStockMap.put("grow_stockPercent", null);
    	    			}
    	    			
    					break;
    				}
    			}
    			
    			saleAndStockList.add(saleAndStockMap);
    		}
    		
    		
    		//添加合计
    		Map<String, Object> hejiMap = new HashMap<String, Object>();
    		
			//品类名称 :合计
    		hejiMap.put("SX3Name", "合 计");
			
			//本周的   双数   金额    单价   SKU  库存    库存占比   齐码率 的合计
    		hejiMap.put("saleCount", heji_saleCount);
    		hejiMap.put("totalPrice", heji_totalPrice);
    		hejiMap.put("avgUnitPrice", heji_avgUnitPrice);
    		hejiMap.put("shoeIdNum", heji_shoeIdNum);
    		hejiMap.put("stock", heji_stock);
    		BigDecimal heji_stockPercent = null;
    		if(heji_stock != null && heji_totalStock != null && heji_totalStock != 0) {
    			heji_stockPercent = BigDecimal.valueOf(heji_stock).divide(BigDecimal.valueOf(heji_totalStock), 4, RoundingMode.HALF_UP);
    			hejiMap.put("stockPercent", heji_stockPercent.multiply(BigDecimal.valueOf(100)) + "%");
    		} else {
    			hejiMap.put("stockPercent", null);
    		}
    		BigDecimal heji_completeRate = null;
    		if(heji_stock != null && heji_shoeIdNum != null && heji_shoeIdNum != 0 ) {
    			heji_completeRate = BigDecimal.valueOf(heji_stock).divide(BigDecimal.valueOf(heji_shoeIdNum), 2, RoundingMode.HALF_UP);
        		hejiMap.put("completeRate", heji_completeRate + "");
    		} else {
    			hejiMap.put("completeRate", null);
    		}
    		
			//同比或环比的   双数   金额    单价   SKU  库存    库存占比   齐码率 的合计
    		hejiMap.put("last_saleCount", heji_last_saleCount);
    		hejiMap.put("last_totalPrice", heji_last_totalPrice);
    		hejiMap.put("last_avgUnitPrice", heji_last_avgUnitPrice);
    		hejiMap.put("last_shoeIdNum", heji_last_shoeIdNum);
			hejiMap.put("last_stock", heji_last_stock);
			BigDecimal heji_last_stockPercent = null;
    		if(heji_last_stock != null && heji_last_totalStock != null && heji_last_totalStock != 0) {
    			heji_last_stockPercent = BigDecimal.valueOf(heji_last_stock).divide(BigDecimal.valueOf(heji_last_totalStock), 4, RoundingMode.HALF_UP);
    			hejiMap.put("last_stockPercent", heji_last_stockPercent.multiply(BigDecimal.valueOf(100)) + "%");
    		} else {
    			hejiMap.put("last_stockPercent", null);
    		}
    		BigDecimal heji_last_completeRate = null;
    		if(heji_last_stock != null && heji_last_shoeIdNum != null && heji_last_shoeIdNum != 0 ) {
    			heji_last_completeRate = BigDecimal.valueOf(heji_last_stock).divide(BigDecimal.valueOf(heji_last_shoeIdNum), 2, RoundingMode.HALF_UP);
        		hejiMap.put("last_completeRate", heji_last_completeRate + "");
    		} else {
    			hejiMap.put("last_completeRate", null);
    		}
    		
			//增长比  双数   金额    单价   SKU  库存    库存占比 的合计
			if(heji_saleCount != null && heji_last_saleCount != null) {
				hejiMap.put("grow_saleCount", heji_saleCount - heji_last_saleCount);
			} else {
				hejiMap.put("grow_saleCount", null);
			}
			if(heji_totalPrice != null && heji_last_totalPrice != null) {
				hejiMap.put("grow_totalPrice", heji_totalPrice.subtract(heji_last_totalPrice));
			} else {
				hejiMap.put("grow_totalPrice", null);
			}
			if(heji_avgUnitPrice != null && heji_last_avgUnitPrice != null) {
				hejiMap.put("grow_avgUnitPrice", heji_avgUnitPrice.subtract(heji_last_avgUnitPrice));
			} else {
				hejiMap.put("grow_avgUnitPrice", null);
			}
			if(heji_shoeIdNum != null && heji_last_shoeIdNum != null) {
				hejiMap.put("grow_shoeIdNum", heji_shoeIdNum - heji_last_shoeIdNum);
			} else {
				hejiMap.put("grow_shoeIdNum", null);
			}
			if(heji_stock != null && heji_last_stock != null) {
				hejiMap.put("grow_stock", heji_stock - heji_last_stock);
			} else {
				hejiMap.put("grow_stock", null);
			}
			if(heji_stockPercent != null && heji_last_stockPercent != null) {
				hejiMap.put("grow_stockPercent", (heji_stockPercent.subtract(heji_last_stockPercent)).multiply(BigDecimal.valueOf(100)) + "%");
			} else {
				hejiMap.put("grow_stockPercent", null);
			}
			
    		saleAndStockList.add(hejiMap);
    		
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("saleAndStockList", saleAndStockList);
    		resultMap.put("promoteExplain1", promoteExplain1);
    		resultMap.put("promoteExplain2", promoteExplain2);
    		resultMap.put("last_promoteExplain1", last_promoteExplain1);
    		resultMap.put("last_promoteExplain2", last_promoteExplain2);
    		
			return R.ok(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    

}
