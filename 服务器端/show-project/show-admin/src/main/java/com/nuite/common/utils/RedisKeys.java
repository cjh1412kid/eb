package com.nuite.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2017-07-18
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key) {
        return "sessionid:" + key;
    }

    public static final String SALE_TOP_KEY = "sale:top:key";

    public static final String TRY_TOP_KEY = "try:top:key";
    
    
	//2019产品版 试穿销量排行中“鞋子上柜日期” 缓存key
	public static final String SHOECABINETDATE = "ShoeCabinetDate";
	public static String getShoeCabinetDateRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		return SHOECABINETDATE + ":" + areaType + ":" + areaSeq + ":" + shoeSeq;
	}

	
}
