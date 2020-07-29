package com.nuite.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.ShopService;
import com.nuite.service.TryMapService;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 实时试穿数据地图
 * @author yy
 * @date 2018-11-26 13:27:49
 */
@RestController
@RequestMapping("/api/tryMap")
@Api(tags="实时试穿数据地图")
public class TryMapController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private TryMapService tryMapService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
	/**
     * 获取所属区域内门店日、周、月、总试穿量（顶部4组数字）
     */
    @Login
    @GetMapping("total")
    @ApiOperation(value = "获取所属区域内门店日、周、月、总试穿量")
    public R total(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		//根据用户权限，获取所属区域安装设备的门店序号List
    		List<Integer> shopSeqList = getShopEntityListByLoginUser(loginUser);
    		if(shopSeqList == null) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
    		
    		
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getTryMapTotalRedisKey(loginUser.getBrandSeq());
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
        	//不为空,从redis中取值
	        if(redisList != null && redisList.size() > 0) {
	        	int todayNum = 0;
	        	int weekNum = 0;
	        	int monthNum = 0;
	        	int totalNum = 0;
	        	for(int shopSeq : shopSeqList) {
	        		for(Map<String, Object> redisMap : redisList) {
	        			if((int)redisMap.get("seq") == shopSeq) {
	    	        		todayNum += (int)redisMap.get("todayNum");
	    	        		weekNum += (int)redisMap.get("weekNum");
	    	        		monthNum += (int)redisMap.get("monthNum");
	    	        		totalNum += (int)redisMap.get("totalNum");
	    	        		break;
	        			}
	        		}

	        	}
	    		Map<String, Object> resultMap = new HashMap<String, Object>();
	    		resultMap.put("today", todayNum);
	    		resultMap.put("week", weekNum);
	    		resultMap.put("month", monthNum);
	    		resultMap.put("total", totalNum);
				return R.ok(resultMap);
	        }

    		
    		
    		
    		
    		//计算datediff
    		Integer dateDiff = 0;
			Calendar calendar = Calendar.getInstance();
    		//1.所属区域今日试穿总数
    		int todayNum = tryMapService.getTotalTryTimesByShopSeqList(shopSeqList, 0);
    		
    		//2.所属区域本周试穿总数
				//获取今天是本周第几天-2
			dateDiff = calendar.get(Calendar.DAY_OF_WEEK) - 2;
    		int weekNum = tryMapService.getTotalTryTimesByShopSeqList(shopSeqList, dateDiff);
    		
    		//3.所属区域本月试穿总数
				//获取今天是本月第几天-1
			dateDiff = calendar.get(Calendar.DAY_OF_MONTH) - 1;
    		int monthNum = tryMapService.getTotalTryTimesByShopSeqList(shopSeqList, dateDiff);
    		
    		//4.所属区域总试穿数
    		int totalNum = tryMapService.getTotalTryTimesByShopSeqList(shopSeqList, null);
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("today", todayNum);
    		resultMap.put("week", weekNum);
    		resultMap.put("month", monthNum);
    		resultMap.put("total", totalNum);
			return R.ok(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
	/**
     * 获取所有门店今日试穿总量（首页中国地图）
     */
    @Login
    @GetMapping("today")
    @ApiOperation(value = "获取所有门店今日试穿总量（门店名称、经纬度、今日试穿总量）", responseReference = "[{name: \"苏州\",  value: [120.6519, 31.3989, 30], total: 255},{...}]")
    public R today(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		//从redis获取缓存的值
    		String redisKey = RedisKeys.getTryMapTodayRediskey(loginUser.getBrandSeq());
        	@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = redisUtils.get(redisKey, List.class);
        	if(list != null) {
        		//不为空直接返回
        		return R.ok(list);
        	}
        	
    		//品牌所有门店
    		List<ShopEntity> shopEntityList = shopService.getShopsByBrandSeq(loginUser.getBrandSeq());
    		
    		//组装返回值，添加门店今日试穿数据
    		List<Map<String, Object>> shopTryTimesList = new ArrayList<Map<String, Object>>(); //[{name: "苏州",  value: [120.6519, 31.3989, 42]},{...}]
    		Map<String, Object> map; //{name: "苏州",  value: [120.6519, 31.3989, 42]}
    		
    		for(ShopEntity shopEntity : shopEntityList) {
    			//没有安装设备的门店不统计
    			if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
    					&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
	    			
	    			map = new HashMap<String, Object>();
	    			map.put("name", shopEntity.getName());
	    			
	    			BigDecimal[] lngAndLat = new BigDecimal[3];
	    			lngAndLat[0] = shopEntity.getLng();
	    			lngAndLat[1] = shopEntity.getLat();
	    			Integer tryTimes = tryMapService.getTodayTotalTryTimesByShopSeq(shopEntity.getSeq()); //查询门店今日试穿总量
	    			lngAndLat[2] = BigDecimal.valueOf(tryTimes);
	    			map.put("value", lngAndLat);
	    			
	    			shopTryTimesList.add(map);
    			}
    		}
    		
			return R.ok(shopTryTimesList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
	/**
     * 实时新试穿数据航线
     */
    @Login
    @GetMapping("dataLine")
    @ApiOperation(value = "实时新试穿数据航线（App定时调用，用于画出飞机线路）", responseReference = "[[{\"coord\":[106.55,29.57]},{\"coord\":[120.58,31.3]}],[{\"coord\":[126.53,45.8]},{\"coord\":[120.58,31.3]}]]")
    public R dataLine(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("查询间隔时间(秒) 目前写死300秒，可不传") @RequestParam(value = "seconds", required = false, defaultValue = "300" ) Integer seconds) {
    	try {
    		//从redis获取缓存的值
    		String redisKey = RedisKeys.getTryMapDataLineRediskey(loginUser.getBrandSeq());
        	@SuppressWarnings("unchecked")
        	List<List<Map<String, Object>>> redisList = redisUtils.get(redisKey, List.class);
        	if(redisList != null) {
        		//不为空直接返回
        		return R.ok(redisList);
        	}
        	
        	
    		//品牌所有门店
    		List<ShopEntity> shopEntityList = shopService.getShopsByBrandSeq(loginUser.getBrandSeq());
    		
    		//判断门店时间间隔内有没有新的试穿数据
    		List<List<Map<String, Object>>> hasTryDataShopList = new ArrayList<List<Map<String, Object>>>(); // [[{"coord":[106.55,29.57]},{"coord":[120.58,31.3]}],[{"coord":[126.53,45.8]},{"coord":[120.58,31.3]}]]
    		List<Map<String, Object>> list; // [{"coord":[106.55,29.57]},{"coord":[120.58,31.3]}]
    		Map<String, Object> map; // {"coord":[106.55,29.57]}
    		
			//苏州经纬度Map
    		Map<String, Object> suZhouLatAndLngMap = new HashMap<String, Object>();
			BigDecimal[] suZhouLngAndLat = new BigDecimal[2];
			suZhouLngAndLat[0] = BigDecimal.valueOf(120.58);
			suZhouLngAndLat[1] = BigDecimal.valueOf(31.3);
			suZhouLatAndLngMap.put("coord", suZhouLngAndLat);
			
    		for(ShopEntity shopEntity : shopEntityList) {
    			//没有安装设备的门店不查询
    			if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
    					&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
	    			
	    			Boolean hasTryData = tryMapService.shopHasTryDataInSeconds(shopEntity.getSeq(), 3600);
	    			if(hasTryData) {
	    				list = new ArrayList<Map<String, Object>>();
	    				
	    				//门店经纬度
		    			map = new HashMap<String, Object>();
		    			BigDecimal[] shopLngAndLat = new BigDecimal[2];
		    			shopLngAndLat[0] = shopEntity.getLng();
		    			shopLngAndLat[1] = shopEntity.getLat();
		    			map.put("coord", shopLngAndLat);
		    			list.add(map);
		    			
		    			list.add(suZhouLatAndLngMap);
		    			
		    			hasTryDataShopList.add(list);
	    			}

    			}
    		}
    		
			return R.ok(hasTryDataShopList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    

}
