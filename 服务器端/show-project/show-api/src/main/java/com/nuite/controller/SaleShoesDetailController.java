package com.nuite.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.service.ShopService;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 销售数据接口
 * @author yy
 * @date 2018-11-29 18:26:05
 */
@RestController
@RequestMapping("/api/saleShoes")
@Api(tags="销售数据接口")
public class SaleShoesDetailController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private SaleShoesDetailService saleShoesDetailService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
//    result：[{
//        "total": 300万,
//        "lastyearTotal": 300万,
//        "detail": [{
//				     "seq": 1,
//				     "areaName": "全国",
//				     "sales": 50万,
//				     "lastyearSales": 50万,
//				     "type": 1
//				     },{
//						
//				     }]
//    }]
    
	/**
     * 销售额统计接口
     */
    @Login
    @GetMapping("statistics")
    @ApiOperation(value = "销售额统计接口")
    public R statistics(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
    		@ApiParam("起始日期（如果不传则是最近一日）") @RequestParam(value = "startDate", required = false) Date startDate,
    		@ApiParam("结束日期（如果不传则是最近一日）") @RequestParam(value = "endDate", required = false) Date endDate,
    		@ApiParam("排序方式0:降序 1:升序") @RequestParam("orderDir") Integer orderDir
    		) {
    	try {
    		
   		    //判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
    		//判断起止时间正确性，组装去年同期时间
    			//最近一日有销售额的时间
    		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay();
    		if(mostRecentDay == null) {
    			return R.error("对不起，暂无任何销售数据");
    		}
    		
    		if(startDate == null || endDate == null) {  //没有起止日期查询最近一日
    			startDate = mostRecentDay;
    			endDate = mostRecentDay;
    		} else {
    			if (startDate.after(mostRecentDay)) {
    				return R.error("对不起，所选日期没有销售数据");
    			}
    			if (endDate.after(mostRecentDay)) {
    				endDate = mostRecentDay;
    			}
    		}

    		Date lastyearStartDate = DateUtils.addDateYears(startDate, -1); //去年同期开始时间
    		Date lastyearEndDate = DateUtils.addDateYears(endDate, -1); //去年同期结束时间
    		
    		
    		
    		//尝试从redis获取缓存的值
			if(areaSeqs.size() == 1) {  //目前只做了单选区域时的缓存
	    		String redisKey = RedisKeys.getSaleShoesStatisticsRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), startDate, endDate);
	        	@SuppressWarnings("unchecked")
	        	Map<String, Object> redisMap = redisUtils.get(redisKey, Map.class);
        		//不为空,从redis中取值
	        	if(redisMap != null) {
	        		return R.ok(redisMap);
	        	}
			}
			
			
    		
    		//计算区域内指定时间段的销售额
    		BigDecimal total = BigDecimal.ZERO;    //总销售额
    		BigDecimal lastyearTotal = BigDecimal.ZERO;   //去年同期总销售额
    		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
    		
    		//全国
    		if(type == 1) {
    			//查询品牌所有大区
    			List<AreaEntity> firstAreaList = areaService.getFirstAreasByBrandSeq(loginUser.getBrandSeq());
    			Map<String, Object> map;
    			for(AreaEntity areaEntity : firstAreaList) {
    				map = new HashMap<String, Object>();
    				map.put("seq", areaEntity.getSeq());
    				map.put("areaName", areaEntity.getAreaName());
    				BigDecimal sales = saleShoesDetailService.getFirstAreaTotalSales(areaEntity.getSeq(), startDate, endDate); //获取大区时间段销售额
    				map.put("sales", sales);
    				total = total.add(sales);
    				BigDecimal lastyearSales = saleShoesDetailService.getFirstAreaTotalSales(areaEntity.getSeq(), lastyearStartDate, lastyearEndDate); //获取大区去年时间段销售额
    				map.put("lastyearSales", lastyearSales);
    				lastyearTotal = lastyearTotal.add(lastyearSales);
    				map.put("type", 2);
    				detailList.add(map);
    			}
    			
    		} else if(type == 2) { //大区
    			//获取大区内所有分公司
    			List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeqList(areaSeqs);
    			Map<String, Object> map;
    			for(AreaEntity areaEntity : secondAreaList) {
    				map = new HashMap<String, Object>();
    				map.put("seq", areaEntity.getSeq());
    				map.put("areaName", areaEntity.getAreaName());
    				BigDecimal sales = saleShoesDetailService.getSecondAreaTotalSales(areaEntity.getSeq(), startDate, endDate); //获取分公司时间段销售额
    				map.put("sales", sales);
    				total = total.add(sales);
    				BigDecimal lastyearSales = saleShoesDetailService.getSecondAreaTotalSales(areaEntity.getSeq(), lastyearStartDate, lastyearEndDate); //获取分公司去年时间段销售额
    				map.put("lastyearSales", lastyearSales);
    				lastyearTotal = lastyearTotal.add(lastyearSales);
    				map.put("type", 3);
    				detailList.add(map);
    			}

    		} else if(type == 3) { //分公司
    			//根据分公司序号查询门店
    			List<ShopEntity> shopList = shopService.getShopsByAreaSeqList(areaSeqs);
    			Map<String, Object> map;
    			for(ShopEntity shopEntity : shopList) {
    				map = new HashMap<String, Object>();
    				map.put("seq", shopEntity.getSeq());
    				map.put("areaName", shopEntity.getName());
    				BigDecimal sales = saleShoesDetailService.getShopTotalSales(shopEntity.getSeq(),startDate, endDate); //获取门店时间段销售额
    				map.put("sales", sales);
    				total = total.add(sales);
    				BigDecimal lastyearSales = saleShoesDetailService.getShopTotalSales(shopEntity.getSeq(), lastyearStartDate, lastyearEndDate); //获取门店去年时间段销售额
    				map.put("lastyearSales", lastyearSales);
    				lastyearTotal = lastyearTotal.add(lastyearSales);
    				map.put("type", 4);
    				detailList.add(map);
    			}
    			
    		} else if(type == 4) { //门店
    			for(Integer shopSeq : areaSeqs) {
	    			ShopEntity shopEntity = shopService.selectById(shopSeq);
	    			
	    			Map<String, Object> map = new HashMap<String, Object>();
	    			map.put("seq", shopEntity.getSeq());
	    			map.put("areaName", shopEntity.getName());
	    			BigDecimal sales = saleShoesDetailService.getShopTotalSales(shopSeq, startDate, endDate); //获取门店时间段销售额
	    			map.put("sales", sales);
	    			total = total.add(sales);
	    			BigDecimal lastyearSales = saleShoesDetailService.getShopTotalSales(shopSeq, lastyearStartDate, lastyearEndDate); //获取门店去年时间段销售额
					map.put("lastyearSales", lastyearSales);
					lastyearTotal = lastyearTotal.add(lastyearSales);
	    			map.put("type", 4);
	    			detailList.add(map);
    			}
    		}
    		
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("total", total);
    		resultMap.put("lastyearTotal", lastyearTotal);
    		resultMap.put("mostRecentDay", mostRecentDay);
    		//按照要求对List中数据排序
    		if(orderDir == 1) {
    			Collections.sort(detailList, new CompareSales(true));
    		} else {
    			Collections.sort(detailList, new CompareSales(false));
    		}
    		resultMap.put("detail", detailList);
    		return R.ok(resultMap);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
	/**
     * 最近一日销售额点击时，下级数据查询
     */
    @Login
    @GetMapping("child")
    @ApiOperation(value = "最近一日销售额点击时，下级数据查询")
    @Deprecated
    public R child(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("父级seq") @RequestParam("seq") Integer seq,
    		@ApiParam("父级type") @RequestParam("type") Integer type,
    		@ApiParam("起始日期（如果不传则是最近一日）") @RequestParam(value = "startDate", required = false) Date startDate,
    		@ApiParam("结束日期（如果不传则是最近一日）") @RequestParam(value = "endDate", required = false) Date endDate,
    		@ApiParam("排序方式0:降序 1:升序") @RequestParam("orderDir") Integer orderDir) {
    	try {

    		BigDecimal total = BigDecimal.ZERO;
    		BigDecimal lastyearTotal = BigDecimal.ZERO;
    		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
    		
    		
    		//判断最近一日销售额时间
    		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay();
    		if(mostRecentDay == null) {
    			return R.error("对不起，暂无任何销售数据");
    		}
    		
    		if(startDate == null || endDate == null) {  //没有起止日期查询最近一日
    			startDate = mostRecentDay;
    			endDate = mostRecentDay;
    		} else {
    			if (startDate.after(mostRecentDay)) {
    				return R.error("对不起，所选日期没有销售数据");
    			}
    			if (endDate.after(mostRecentDay)) {
    				endDate = mostRecentDay;
    			}
    		}

    		Date lastyearStartDate = DateUtils.addDateYears(startDate, -1);
    		Date lastyearEndDate = DateUtils.addDateYears(endDate, -1);
    		
    		
    		//大区
    		if(type == 2) {
    			//获取大区内所有分公司
    			List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(seq);
    			Map<String, Object> map;
    			for(AreaEntity areaEntity : secondAreaList) {
    				map = new HashMap<String, Object>();
    				map.put("seq", areaEntity.getSeq());
    				map.put("areaName", areaEntity.getAreaName());
    				BigDecimal sales = saleShoesDetailService.getSecondAreaTotalSales(areaEntity.getSeq(), startDate, endDate); //获取分公司时间段销售额
    				map.put("sales", sales);
    				total = total.add(sales);
    				BigDecimal lastyearSales = saleShoesDetailService.getSecondAreaTotalSales(areaEntity.getSeq(), lastyearStartDate, lastyearEndDate); //获取分公司去年时间段销售额
    				map.put("lastyearSales", lastyearSales);
    				lastyearTotal = lastyearTotal.add(lastyearSales);
    				map.put("type", 3);
    				detailList.add(map);
    			}

    		} else if(type == 3) { //分公司
    			//根据分公司序号查询门店
    			List<ShopEntity> shopList = shopService.getShopsByAreaSeq(seq);
    			Map<String, Object> map;
    			for(ShopEntity shopEntity : shopList) {
    				map = new HashMap<String, Object>();
    				map.put("seq", shopEntity.getSeq());
    				map.put("areaName", shopEntity.getName());
    				BigDecimal sales = saleShoesDetailService.getShopTotalSales(shopEntity.getSeq(), startDate, endDate); //获取门店时间段销售额
    				map.put("sales", sales);
    				total = total.add(sales);
    				BigDecimal lastyearSales = saleShoesDetailService.getShopTotalSales(shopEntity.getSeq(), lastyearStartDate, lastyearEndDate); //获取门店去年时间段销售额
    				map.put("lastyearSales", lastyearSales);
    				lastyearTotal = lastyearTotal.add(lastyearSales);
    				map.put("type", 4);
    				detailList.add(map);
    			}
    			
    		} else {
    			return R.error(HttpStatus.FORBIDDEN.value(), "没有更多数据了");
    		}
    		
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("total", total);
    		resultMap.put("lastyearTotal", lastyearTotal);
    		//按照要求对List中数据排序
    		if(orderDir == 1) {
    			Collections.sort(detailList, new CompareSales(true));
    		} else {
    			Collections.sort(detailList, new CompareSales(false));
    		}
    		resultMap.put("detail", detailList);
    		return R.ok(resultMap);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
    
    
    //比较销售数据
	private static final class CompareSales implements Comparator<Map<String, Object>> {
	     boolean isAsc;

	     public CompareSales(boolean isAsc) {
	    	 this.isAsc = isAsc;
	     }

	     /*
	      * int compare(Person p1, Person p2) 返回一个基本类型的整型，
	      * 返回负数表示：p1 小于p2，
	      * 返回0 表示：p1和p2相等，
	      * 返回正数表示：p1大于p2
	      */
	     @Override
	     public int compare(Map<String, Object> map1, Map<String, Object> map2) {
	         //按照Sales进行排列
	         if(((BigDecimal)map1.get("sales")).compareTo((BigDecimal)map2.get("sales")) == 1){
	        	 if(isAsc) {
		             return 1;
	        	 } else {
	        		 return -1;  //Comparator默认按照升序排列，通过返回相反的值，实现降序
	        	 }
	         } else if(((BigDecimal)map1.get("sales")).compareTo((BigDecimal)map2.get("sales")) == 0){
	             return 0;
	         } else {
	        	 if(isAsc) {
		             return -1;
	        	 } else {
	        		 return 1;
	        	 }
	         }
	     }
	}
	

}
