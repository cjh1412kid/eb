package com.nuite.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nuite.common.utils.RedisUtils;
import com.nuite.dao.BrandDao;
import com.nuite.entity.BrandEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.service.ShopService;
import com.nuite.service.TryMapService;
import com.nuite.utils.RedisKeys;


@Component("tryMapTask")
public class TryMapTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseTask baseTask;
    
    @Autowired
    private TryMapService tryMapService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private BrandDao brandDao;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
	/**
     * 获取所属区域内门店日、周、月、总试穿量（顶部4组数字）
     */
	//@Scheduled(cron = "0 3/20 * * * ?" )
    public void total() {
		Date startTaskTime = new Date();
		logger.info("###tryMapTask total方法，开始执行: " + startTaskTime);
    	try {
    		
    		Date today = baseTask.getTodayDate();
    		Date weekMonDate = baseTask.getWeekMonDate();
    		Date monthFirstDate = baseTask.getMonthFirstDate();
			
			
    		//获取所有品牌
    		List<BrandEntity> brandList = brandDao.selectList(null);
    		
    		for(BrandEntity brandEntity : brandList) {
    		
    		/** controller方法 **/
    		//品牌所有门店
    		List<ShopEntity> shopEntityList = shopService.getShopsByBrandSeq(brandEntity.getSeq());
    		
    		//组装返回值，添加门店今日试穿数据
    		List<Map<String, Object>> shopTryTimesList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> shopMap;
		
    		for(ShopEntity shopEntity : shopEntityList) {
    			//没有安装设备的门店不统计
    			if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
    					&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
    				
    	    		List<Integer> shopSeqList = new ArrayList<Integer>();
    	    		shopSeqList.add(shopEntity.getSeq());
    	    		
    	    		//1.门店今日试穿总数
    	    		int todayNum = tryMapService.getTotalTryTimesByShopSeqListAfterDate(shopSeqList, today);
    	    		
    	    		//2.门店本周试穿总数
    	    		int weekNum = tryMapService.getTotalTryTimesByShopSeqListAfterDate(shopSeqList, weekMonDate);
    	    		
    	    		//3.门店本月试穿总数
    	    		int monthNum = tryMapService.getTotalTryTimesByShopSeqListAfterDate(shopSeqList, monthFirstDate);
    	    		
    	    		//4.门店总试穿数
    	    		int totalNum = tryMapService.getTotalTryTimesByShopSeqListAfterDate(shopSeqList, null);
    	    		
    	    		shopMap = new HashMap<String, Object>();
    	    		shopMap.put("seq", shopEntity.getSeq());
    	    		shopMap.put("todayNum", todayNum);
    	    		shopMap.put("weekNum", weekNum);
    	    		shopMap.put("monthNum", monthNum);
    	    		shopMap.put("totalNum", totalNum);
    	    		shopTryTimesList.add(shopMap);
    	    		
    			}
    		}
    		/** 获取品牌所有门店今日试穿controller方法 **/
    		
    		
    		/** 存入Redis **/
    		String redisKey = RedisKeys.getTryMapTotalRedisKey(brandEntity.getSeq());
        	redisUtils.set(redisKey, shopTryTimesList, 60 * 60);
			/** 存入Redis **/
			
    		
    		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		Date endTaskTime = new Date();
		logger.info("###tryMapTask total方法，执行完毕: " + endTaskTime + "本次总计耗时:" + (endTaskTime.getTime() - startTaskTime.getTime()));
    }
	
	
    
    
	/**
     * 获取所有门店今日试穿总量（首页中国地图）
     */
	//@Scheduled(cron = "0 30 * * * ?" )
    public void today() {
		Date startTaskTime = new Date();
		logger.info("###tryMapTask today方法，开始执行: " + startTaskTime);
    	try {
    		
    		//获取所有品牌
    		List<BrandEntity> brandList = brandDao.selectList(null);
    		
    		for(BrandEntity brandEntity : brandList) {
    			
    		
    		/** 获取品牌所有门店今日试穿controller方法 **/
    		//品牌所有门店
    		List<ShopEntity> shopEntityList = shopService.getShopsByBrandSeq(brandEntity.getSeq());
    		
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
//			return R.ok(shopTryTimesList);
    		/** 获取品牌所有门店今日试穿controller方法 **/
    		
    		
    		/** 存入Redis **/
    		String redisKey = RedisKeys.getTryMapTodayRediskey(brandEntity.getSeq());
        	redisUtils.set(redisKey, shopTryTimesList, 60 * 60 * 3);
			/** 存入Redis **/
			
    		}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		Date endTaskTime = new Date();
		logger.info("###tryMapTask today方法，执行完毕: " + endTaskTime + "本次总计耗时:" + (endTaskTime.getTime() - startTaskTime.getTime()));
    }
    
	
	
	
	/**
     * 实时新试穿数据航线（地图飞机线）
     */
	//@Scheduled(cron = "0 30 * * * ?" )
    public void dataLine() {
		Date startTaskTime = new Date();
		logger.info("###tryMapTask dataLine方法，开始执行: " + startTaskTime);
    	try {
    		
    		//获取所有品牌
    		List<BrandEntity> brandList = brandDao.selectList(null);
    		
    		for(BrandEntity brandEntity : brandList) {
    			
    		
    		/** 实时新试穿数据航线controller方法 **/
	        //品牌所有门店
	        List<ShopEntity> shopEntityList = shopService.getShopsByBrandSeq(brandEntity.getSeq());
	        
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
	        
	//    	return R.ok(hasTryDataShopList);
	    		
    		/** 实时新试穿数据航线controller方法 **/
    		
    		
    		/** 存入Redis **/
    		String redisKey = RedisKeys.getTryMapDataLineRediskey(brandEntity.getSeq());
        	redisUtils.set(redisKey, hasTryDataShopList, 60 * 60 * 3);
			/** 存入Redis **/
			
    		}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		Date endTaskTime = new Date();
		logger.info("###tryMapTask dataLine方法，执行完毕: " + endTaskTime + "本次总计耗时:" + (endTaskTime.getTime() - startTaskTime.getTime()));
    }
	
	
	
    
}
