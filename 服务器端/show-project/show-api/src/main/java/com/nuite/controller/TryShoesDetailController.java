package com.nuite.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.PeriodService;
import com.nuite.service.ShopService;
import com.nuite.service.TryMapService;
import com.nuite.service.TryShoesDetailService;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 试穿数据接口
 * @author yy
 * @date 2018-11-26 14:07:06
 */
@RestController
@RequestMapping("/api/tryShoes")
@Api(tags="试穿数据接口")
public class TryShoesDetailController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private TryShoesDetailService tryShoesDetailService;
    
    @Autowired
    private TryMapService tryMapService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
    
	/**
     * 今日门店统计（黄色饼图）：无论哪一级都查看所属最后一级的（门店）数据
     */
    @Login
    @GetMapping("today")
    @ApiOperation(value = "今日门店统计（黄色饼图）", responseReference = "[{name:\"未上传\", value:19},{name:\"1~100\", value:52},{name:\"101~200\", value:15},{name:\"200条以上\", value:45}]")
    public R today(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("分段范围(两个数逗号隔开如:100,200返回0,1~100,101~200,>200四段)") @RequestParam("ranges") List<Integer> ranges) {
    	try {
    		
    		List<ShopEntity> shopList;
    		//全国
    		if(loginUser.getRoleSeq() == 1) {
    			//获取品牌所有门店
    			shopList = shopService.getShopsByBrandSeq(loginUser.getBrandSeq());
    			
    		} else if(loginUser.getRoleSeq() == 2) { //大区
    			//1.获取大区内所有分公司
    			List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(loginUser.getUserAreaSeq());
    			  //分公司序号列表
    			List<Integer> secondAreaSeqList = new ArrayList<Integer>();
    			for(AreaEntity area : secondAreaList) {
    				secondAreaSeqList.add(area.getSeq());
    			}
    			
    			//2.根据分公司序号列表查询门店
    			shopList = shopService.getShopsByAreaSeqList(secondAreaSeqList);
    			
    		} else if(loginUser.getRoleSeq() == 3) { //分公司
    			//根据分公司序号查询门店
    			shopList = shopService.getShopsByAreaSeq(loginUser.getUserAreaSeq());
    			
    		} else if(loginUser.getRoleSeq() == 4) { //门店
    			shopList = new ArrayList<ShopEntity>();
    			shopList.add(shopService.selectById(loginUser.getUserAreaSeq()));
    			
    		} else {
    			return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
    		}
    		
    		
    		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    		
//    		{
//			"name": "未上传",
//			"value": 9,
//			"detail": {
//				"浦东川沙一店": "2018-11-15 09:52",
//				"浦东华夏东路一店": "2018-11-14 13:56",
//				"上海人民广场一店": "2018-11-13 11:45",
//				"上海闵行区总二店": "2018-11-12 09:52",
//				"上海人民广场二店": "2018-11-11 13:56",
//				"上海闵行区总店": "2018-11-10 11:45",
//				"上海人民广场三店": "2018-11-09 09:52",
//				"上海闵行区三店": "2018-11-08 13:56",
//				"浦东川沙三店": "2018-11-07 11:45"
//			}
//		},
    		Map<String, Object> map0 = new HashMap<String, Object>();
    		map0.put("name", "未上传");
    		map0.put("value", 0);
    		Map<String, Object> map0detail = new HashMap<String, Object>();
    		map0.put("detail", map0detail);
    		result.add(map0);

//   		 {
//			"name": "1~100条",
//			"value": 5,
//			"detail": {
//				"浦东川沙店": "56",
//				"上海人民广场店": "52",
//				"上海闵行区总店": "22",
//				"浦东川沙二店": "12",
//				"浦东华夏路店": "10"
//			}
//		},
    		Map<String, Object> map1 = new HashMap<String, Object>();
    		map1.put("name", "1~" + ranges.get(0) + "条");
    		map1.put("value", 0);
    		Map<String, Object> map1detail = new HashMap<String, Object>();
    		map1.put("detail", map1detail);
    		result.add(map1);
    		
    		Map<String, Object> map2 = new HashMap<String, Object>();
    		map2.put("name", (ranges.get(0)+1) + "~" + ranges.get(1) + "条");
    		map2.put("value", 0);
    		Map<String, Object> map2detail = new HashMap<String, Object>();
    		map2.put("detail", map2detail);
    		result.add(map2);
    		
    		Map<String, Object> map3 = new HashMap<String, Object>();
    		map3.put("name", ranges.get(1) + "条以上");
    		map3.put("value", 0);
    		Map<String, Object> map3detail = new HashMap<String, Object>();
    		map3.put("detail", map3detail);
    		result.add(map3);

    		
    		//获取每个门店今日试穿量，添加进对应map
    		for(ShopEntity shopEntity : shopList) {
				//没有安装设备的门店不统计
				if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
						&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
    			
					
					Integer total = null;
					boolean redisFlag = false;
		    		//尝试从redis获取缓存的值
			    	String redisKey = RedisKeys.getTryShoesTodayRedisKey(loginUser.getBrandSeq(), shopEntity.getSeq());
					Integer redisTotal = redisUtils.get(redisKey, Integer.class);
		        	//不为空,从redis中取值
			        if(redisTotal != null) {
			        	redisFlag = true;
			        	total = redisTotal;
			        }

			        //没有从redis获取到缓存，主动查库
			        if(!redisFlag) {
			        	total = tryMapService.getTodayTotalTryTimesByShopSeq(shopEntity.getSeq());
			        }
			        
	    			if(total == 0) { //未上传
	    				map0.put("value", (int)map0.get("value") + 1);
	    				//获取最后上传时间
	    				
			    		//尝试从redis获取缓存的值
				    	String lastTryTimeRedisKey = RedisKeys.getTryShoesLastTryTimeRedisKey(loginUser.getBrandSeq(), shopEntity.getSeq());
				    	Date lastTryTime;
			        	//为空,手动查库
				        if(redisUtils.hasKey(lastTryTimeRedisKey)) {
				        	lastTryTime = redisUtils.get(lastTryTimeRedisKey, Date.class);
				        } else {
				        	lastTryTime = tryShoesDetailService.getShopLastTryTime(shopEntity.getSeq());
				        }
				        
	    				map0detail.put(shopEntity.getName(), lastTryTime);
	    			} else if (total > 0 && total <= ranges.get(0)) { //第一段
	    				map1.put("value", (int)map1.get("value") + 1);
	    				map1detail.put(shopEntity.getName(), total);
	    			} else if (total > ranges.get(0) && total <= ranges.get(1)) { //第二段
	    				map2.put("value", (int)map2.get("value") + 1);
	    				map2detail.put(shopEntity.getName(), total);
	    			} else if (total > ranges.get(1)) { //第三段
	    				map3.put("value", (int)map3.get("value") + 1);
	    				map3detail.put(shopEntity.getName(), total);
	    			}
				}
    		}
    		
			return R.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    




    
    
    
	/**
     * 总试穿统计（蓝色饼图） :按区域、波次、起止时间统计
     */
    @Login
    @GetMapping("statistics")
    @ApiOperation("总试穿统计（蓝色饼图）")
    public R statistics(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
    		@ApiParam("波次序号（逗号分隔）") @RequestParam(value = "periodSeqs", required = false) List<Integer> periodSeqs,
    		@ApiParam("起始日期") @RequestParam("startDate") Date startDate,
    		@ApiParam("结束日期") @RequestParam("endDate") Date endDate) {
    	try {
    		 //判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
    		
    		//尝试从redis获取缓存的值
			if(areaSeqs.size() == 1 && (periodSeqs == null || periodSeqs.size() == 0) ) {  //目前只做了单选区域、不选波次时的缓存
				String redisKey;
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				if(cal.get(Calendar.YEAR) == 2010) {   //起始时间为2010年，查询全部试穿数据的缓存
					redisKey = RedisKeys.getTryShoesTotalStatisticsRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0));
				} else {
					redisKey = RedisKeys.getTryShoesStatisticsRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), startDate, endDate);
				}
	    		
	        	@SuppressWarnings("unchecked")
	        	Map<String, Object> redisMap = redisUtils.get(redisKey, Map.class);
        		//不为空,从redis中取值
	        	if(redisMap != null) {
	        		return R.ok(redisMap);
	        	}
			}
    		
    		
    		
			
    		//如果波次序号为空，查询品牌的所有波次
    		if(periodSeqs == null || periodSeqs.size() == 0) {
    			periodSeqs = periodService.getPeriodSeqsByBrandSeq(loginUser.getBrandSeq());
    		}
    		
    		//pie饼图统计数据
    		int pieValidNum = 0;
    		int pieInvalidNum = 0;
    		//详细试穿数据
    		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
    		
    		//全国
    		if(type == 1) {
    			Map<String, Object> countryMap = new HashMap<String, Object>();
    			countryMap.put("seq", 0);
    			countryMap.put("areaName", "全国");
        		int countryValid = 0;
        		int countryInvalid = 0;
    			List<Map<String, Object>> countryChildList = new ArrayList<Map<String, Object>>();
    			
        		//根据品牌查询大区
        		List<AreaEntity> firstAreaList = areaService.getFirstAreasByBrandSeq(loginUser.getBrandSeq());
    			for(AreaEntity firstArea : firstAreaList) {
    				//获取大区试穿统计
    				Map<String, Object> firstAreaMap = getFirstAreaStatistics(firstArea, periodSeqs, startDate, endDate);
    				countryValid += (int)firstAreaMap.get("valid");
    				countryInvalid += (int)firstAreaMap.get("invalid");
    				countryChildList.add(firstAreaMap);
    			}
    			countryMap.put("valid", countryValid);
    			countryMap.put("invalid", countryInvalid);
    			Collections.sort(countryChildList, new CompareValid(false)); //按有效试穿量降序
    			countryMap.put("child", countryChildList);
    			detailList.add(countryMap);
    			
    			pieValidNum = countryValid;
    			pieInvalidNum = countryInvalid;
    		} else if (type == 2){ //大区
    			
    			for(Integer areaSeq : areaSeqs) {
    				AreaEntity firstArea = areaService.selectById(areaSeq);
    				//获取大区试穿统计
    				Map<String, Object> firstAreaMap = getFirstAreaStatistics(firstArea, periodSeqs, startDate, endDate);
    				
    				detailList.add(firstAreaMap);
        			pieValidNum += (int)firstAreaMap.get("valid");
        			pieInvalidNum += (int)firstAreaMap.get("invalid");
    			}
    			
    		} else if (type == 3){ //分公司
    			
    			for(Integer areaSeq : areaSeqs) {
    				AreaEntity secondArea = areaService.selectById(areaSeq);
    				//获取分公司试穿统计
    				Map<String, Object> secondAreaMap = getSecondAreaStatistics(secondArea, periodSeqs, startDate, endDate);
    				
    				detailList.add(secondAreaMap);
        			pieValidNum += (int)secondAreaMap.get("valid");
        			pieInvalidNum += (int)secondAreaMap.get("invalid");
    			}
    			
    		} else if (type == 4){ //门店
    			
    			for(Integer shopSeq : areaSeqs) {
    				ShopEntity shop = shopService.selectById(shopSeq);
    				
    				//没有安装设备的门店不统计
    				if(shop.getInstallDate() != null && shop.getLat() != null && shop.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
    						&& shop.getLng() != null && shop.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
    					
	    				//获取门店试穿统计
	    				Map<String, Object> shopMap = getShopStatistics(shop, periodSeqs, startDate, endDate);
	    				
	    				detailList.add(shopMap);
	        			pieValidNum += (int)shopMap.get("valid");
	        			pieInvalidNum += (int)shopMap.get("invalid");
    				}
    			}
    		}
    		
    		
    		
    		/** 返回结果组装 **/
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		//pie图数据
    		List<Map<String, Object>> pieList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> pieMap = new HashMap<String, Object>();
    		pieMap.put("name", "有效数据");
    		pieMap.put("value", pieValidNum);
    		pieList.add(pieMap);
    		pieMap = new HashMap<String, Object>();
    		pieMap.put("name", "无效数据");
    		pieMap.put("value", pieInvalidNum);
    		pieList.add(pieMap);
    		
    		resultMap.put("pie", pieList);
    		//详细试穿数据
    		resultMap.put("detail", detailList);
    		
    		return R.ok(resultMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }


//    {
//        "seq": 45,
//        "areaName": "大区1",
//        "totalCount": 200,
//        "validCount": 160,
//        "child": []
//    },
    
    
    /**
     * 获取某个大区试穿统计
     * @param firstArea 要获取试穿统计的大区实体
     * @param periodSeqs 波次
     * @param dateDiff	时间段
     * @return
     */
	private Map<String, Object> getFirstAreaStatistics(AreaEntity firstArea, List<Integer> periodSeqs, Date startDate, Date endDate) {
		
		Map<String, Object> firstAreaMap = new HashMap<String, Object>();
		firstAreaMap.put("seq", firstArea.getSeq());
		firstAreaMap.put("areaName", firstArea.getAreaName());
		int firstAreaValid = 0;
		int firstAreaInvalid = 0;
		List<Map<String, Object>> firstAreaChildList = new ArrayList<Map<String, Object>>();
		
		
		//根据大区序号查询分公司
		List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(firstArea.getSeq());
		for(AreaEntity secondArea : secondAreaList) {
			//获取分公司试穿统计
			Map<String, Object> secondAreaMap = getSecondAreaStatistics(secondArea, periodSeqs, startDate, endDate);
			firstAreaValid += (int)secondAreaMap.get("valid");
			firstAreaInvalid += (int)secondAreaMap.get("invalid");
			firstAreaChildList.add(secondAreaMap);
		}
		
		firstAreaMap.put("valid", firstAreaValid);
		firstAreaMap.put("invalid", firstAreaInvalid);
		Collections.sort(firstAreaChildList, new CompareValid(false)); //按有效试穿量降序
		firstAreaMap.put("child", firstAreaChildList);
		return firstAreaMap;
	}


	
//	{
//        "seq": 123,
//        "areaName": "分公司1",
//        "totalCount": 120,
//        "validCount": 160,
//        "child": [
//            {
//                "seq": 1234,
//                "areaName": "门店1",
//                "totalCount": 60,
//                "validCount": 160
//            },
//            {
//                "seq": 5678,
//                "areaName": "门店2",
//                "totalCount": 60,
//                "validCount": 160
//            }
//        ]
//    },
	
	/**
	 * 获取某个分公司试穿统计
	 * @param secondArea 要获取试穿统计的分公司实体
	 * @param periodSeqs 波次
	 * @param dateDiff 时间段
	 * @return
	 */
	private Map<String, Object> getSecondAreaStatistics(AreaEntity secondArea, List<Integer> periodSeqs, Date startDate, Date endDate) {
		Map<String, Object> secondAreaMap = new HashMap<String, Object>();
		secondAreaMap.put("seq", secondArea.getSeq());
		secondAreaMap.put("areaName", secondArea.getAreaName());
		int secondAreaValid = 0;
		int secondAreainvalid = 0;
		List<Map<String, Object>> secondAreaChildList = new ArrayList<Map<String, Object>>();
		
		
		//根据分公司序号查询门店
		List<ShopEntity> shopList = shopService.getShopsByAreaSeq(secondArea.getSeq());
		for(ShopEntity shop : shopList) {
			
			//没有安装设备的门店不统计
			if(shop.getInstallDate() != null && shop.getLat() != null && shop.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
					&& shop.getLng() != null && shop.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
				//获取门店试穿统计
				Map<String, Object> shopMap = getShopStatistics(shop, periodSeqs, startDate, endDate);
				secondAreaValid += (int)shopMap.get("valid");
				secondAreainvalid += (int)shopMap.get("invalid");
				secondAreaChildList.add(shopMap);
			}
		}
		
		secondAreaMap.put("valid", secondAreaValid);
		secondAreaMap.put("invalid", secondAreainvalid);
		Collections.sort(secondAreaChildList, new CompareValid(false)); //按有效试穿量降序
		secondAreaMap.put("child", secondAreaChildList);
		return secondAreaMap;
	}

	
	
//	{
//        "seq": 1234,
//        "areaName": "门店1",
//        "totalCount": 60,
//        "validCount": 160
//    },

	/**
	 * 获取某个门店试穿统计
	 * @param secondArea 要获取试穿统计的门店实体
	 * @param periodSeqs 波次
	 * @param dateDiff 时间段
	 * @return
	 */
	private Map<String, Object> getShopStatistics(ShopEntity shop, List<Integer> periodSeqs, Date startDate, Date endDate) {
		Map<String, Object> shopMap = new HashMap<String, Object>();
		shopMap.put("seq", shop.getSeq());
		shopMap.put("areaName", shop.getName());
		int shopValid = tryShoesDetailService.getShopTryValidNum(shop.getSeq(), periodSeqs, startDate, endDate);
		int shopInvalid = tryShoesDetailService.getShopTryInvalidNum(shop.getSeq(), periodSeqs, startDate, endDate);;
		shopMap.put("valid", shopValid);
		shopMap.put("invalid", shopInvalid);
		return shopMap;
	}
	
	
	
	
	
	
    //比较有效试穿数据
	private static final class CompareValid implements Comparator<Map<String, Object>> {
	     boolean isAsc;

	     public CompareValid(boolean isAsc) {
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
	         //按照有效试穿量进行排列
	         if((int)map1.get("valid") > (int)map2.get("valid")){
	        	 if(isAsc) {
		             return 1;
	        	 } else {
	        		 return -1;  //Comparator默认按照升序排列，通过返回相反的值，实现降序
	        	 }
	         } else if((int)map1.get("valid") == (int)map2.get("valid")){
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
