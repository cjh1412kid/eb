package com.nuite.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2017-07-18
 */
public class RedisKeys {

	//TryMap 日、周、月、总试穿量
    public static final String TRYMAP_TOTAL = "trymap:total";
	public static String getTryMapTotalRedisKey(Integer brandSeq) {
		return brandSeq + ":" + TRYMAP_TOTAL;
	}
	
	
    //TryMap 试穿中国地图
    public static final String TRYMAP_TODAY = "tryMap:today";
    public static String getTryMapTodayRediskey(Integer brandSeq) {
        return brandSeq + ":" + TRYMAP_TODAY;
    }

    
    //TryMap 试穿飞机线
    public static final String TRYMAP_DATALINE = "tryMap:dataLine";
    public static String getTryMapDataLineRediskey(Integer brandSeq) {
        return brandSeq + ":" + TRYMAP_DATALINE;
    }


	

    //Top 试穿Top20
    public static final String TOP_TRYTOP = "top:trytop";

    public static String getTopTryTopRedisKey(Integer brandSeq, Integer type, Integer areaSeq, Integer periodSeq, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + TOP_TRYTOP + ":" + type + ":" + areaSeq + ":" + periodSeq + ":" + startDateStr + ":" + endDateStr;
    }


    //Top 销售、滞销Top20
    public static final String TOP_SALETOP = "top:saletop";

    public static String getTopSaleTopRedisKey(Integer brandSeq, Integer type, Integer areaSeq, Integer periodSeq, Date startDate, Date endDate, Integer topType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + TOP_SALETOP + ":" + type + ":" + areaSeq + ":" + periodSeq + ":" + startDateStr + ":" + endDateStr + ":" + topType;
    }


    //TryShoes  今日门店统计（黄色饼图）
    public static final String TRYSHOES_TODAY = "tryshoes:today";

    public static String getTryShoesTodayRedisKey(Integer brandSeq, Integer shopSeq) {
        return brandSeq + ":" + TRYSHOES_TODAY + ":" + shopSeq;
    }

    
    //TryShoes  今日门店统计（黄色饼图）最后一次上传数据时间
    public static final String TRYSHOES_LASTTRYTIME = "tryshoes:lasttrytime";
    
	public static String getTryShoesLastTryTimeRedisKey(Integer brandSeq, Integer shopSeq) {
        return brandSeq + ":" + TRYSHOES_LASTTRYTIME + ":" + shopSeq;
	}
	

    //TryShoes  总试穿统计（蓝色饼图）
    public static final String TRYSHOES_STATISTICS = "tryshoes:statistics";

    public static String getTryShoesStatisticsRedisKey(Integer brandSeq, Integer type, Integer areaSeq, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + TRYSHOES_STATISTICS + ":" + type + ":" + areaSeq + ":" + startDateStr + ":" + endDateStr;
    }
    
	public static String getTryShoesTotalStatisticsRedisKey(Integer brandSeq, Integer type, Integer areaSeq) {
        return brandSeq + ":" + TRYSHOES_STATISTICS + ":" + type + ":" + areaSeq + ":" + "2010/01/01";
	}
	


    //convert  总转化率
    public static final String CONVERT_ALLCONVERT = "convert:allconvert";

    public static String getConvertAllConvertRedisKey(Integer brandSeq, Integer type, Integer areaSeq, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + CONVERT_ALLCONVERT + ":" + type + ":" + areaSeq + ":" + startDateStr + ":" + endDateStr;
    }


    //convert  单个货品转化率
    public static final String CONVERT_SHOEIDCONVERT = "convert:shoeidconvert";

    public static String getConvertShoeIdConvertRedisKey(Integer brandSeq, Integer type, Integer areaSeq, String shoeId, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + CONVERT_SHOEIDCONVERT + ":" + type + ":" + areaSeq + ":" + shoeId + ":" + startDateStr + ":" + endDateStr;
    }

    
    
    //saleShoes 销售额统计
    public static final String SALESHOES_STATISTICS = "saleshoes:statistics";
	public static String getSaleShoesStatisticsRedisKey(Integer brandSeq, Integer type, Integer areaSeq, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);
        return brandSeq + ":" + SALESHOES_STATISTICS + ":" + type + ":" + areaSeq + ":" + startDateStr + ":" + endDateStr;
	}
	
	
	
    //销售指标，当前月的销售数据
    public static final String QUOTA_SALE_CURRENT_MONTH = "quota:sale:current:month";


    
    
	//largeScreen 日、周、月、总有效、无效试穿量
    public static final String LARGESCREEN_TOTAL = "largescreen:total";
	public static String getLargeScreenTotalRedisKey(Integer brandSeq) {
		return brandSeq + ":" + LARGESCREEN_TOTAL;
	}

	//largeScreen 月试穿Top20
    public static final String LARGESCREEN_TRYTOP = "largescreen:trytop";
	public static String getLargeScreenTryTopRedisKey(Integer brandSeq) {
		return brandSeq + ":" + LARGESCREEN_TRYTOP;
	}
	
	//largeScreen 月销售Top20
    public static final String LARGESCREEN_SALETOP = "largescreen:saletop";
	public static String getLargeScreenSaleTopRedisKey(Integer brandSeq) {
		return brandSeq + ":" + LARGESCREEN_SALETOP;
	}

	//largeScreen 最近30天全国销售额接口
	public static final String LARGESCREEN_THIRTYDAYSSALE = "largescreen:thirtyDaysSale";
	public static String getLargeScreenThirtyDaysSaleRedisKey(Integer brandSeq) {
		return brandSeq + ":" + LARGESCREEN_THIRTYDAYSSALE;
	}


	
	
	
    //2019产品版 各个 "可订阅模块接口数据" 缓存时的redisKey
	public static String getProductModuleRedisKey(Integer moduleSeq, Integer areaType, Integer areaSeq, Integer days, Integer periodSeq) {
        return moduleSeq + ":" + areaType + ":" + areaSeq + ":" + days + ":" + periodSeq;
	}
	
	
	//2019产品版 试穿销量排行中“库存数据” 缓存key
	public static final String INNUMANDSTOCKRANKLIST = "InNumAndStockRankList";
	public static String getTopInNumAndStockRankListRedisKey(Integer areaType, Integer areaSeq, Integer periodSeq) {
		 return INNUMANDSTOCKRANKLIST + ":" + areaType + ":" + areaSeq + ":" + periodSeq;
	}
	
	
    //2019产品版 试穿销量排行中“近30天销量与试穿 图表” 缓存key
    public static final String TOPDAY30SALEANDTRYGRAPH = "TopDay30saleAndTryGraph";
	public static String getTopDay30saleAndTryMapRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq) {
        return TOPDAY30SALEANDTRYGRAPH + ":" + areaType + ":" + areaSeq + ":" + shoeSeq;
	}
	
	//2019产品版 试穿销量详情页“近30天销量与试穿 图表” 中 “区域内货号某一天的销量、平均售价” 缓存key
	public static final String TOPDAY30DAYSALECOUNTANDAVGPRICE = "TopDay30OneDaySaleCountAndAvgPrice";
	public static String getDaySaleCountAndAvgPriceRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq, String startdateStr) {
        return TOPDAY30DAYSALECOUNTANDAVGPRICE + ":" + areaType + ":" + areaSeq + ":" + shoeSeq + ":" + startdateStr;
	}
	
	//2019产品版 试穿销量详情页“近30天销量与试穿 图表” 中 “区域内货号某一天的试穿量” 缓存key
	public static final String TOPDAY30ONEDAYTRYCOUNT = "TopDay30OneDayTryCount";
	public static String getDayTryCountRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq, String startdateStr) {
        return TOPDAY30ONEDAYTRYCOUNT + ":" + areaType + ":" + areaSeq + ":" + shoeSeq + ":" + startdateStr;
	}
	
	
	//2019产品版 试穿销量排行中“销售强度 图表” 缓存key
	public static final String TOPSALESTRENGTHLIST = "TopSaleStrengthList";
	public static String getTopSaleStrengthListRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq) {
        return TOPSALESTRENGTHLIST + ":" + areaType + ":" + areaSeq + ":" + shoeSeq;
	}

	//2019产品版 试穿销量排行中“鞋子上柜日期” 缓存key
	public static final String SHOECABINETDATE = "ShoeCabinetDate";
	public static String getShoeCabinetDateRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		return SHOECABINETDATE + ":" + areaType + ":" + areaSeq + ":" + shoeSeq;
	}
	
	//2019产品版 试穿销量排行中“鞋子试穿趋势” 缓存key
	public static final String SHOETRYCOUNTTREND = "ShoeTryCountTrend";
	public static String getShoeTryCountTrendRedisKey(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer datediff) {
		return SHOETRYCOUNTTREND + ":" + areaType + ":" + areaSeq + ":" + shoeSeq + ":" + datediff;
	}

	//2019产品版 销量排行中“试穿数据” 缓存key
	public static final String TRYCOUNTANDRANKLIST = "TryCountAndRankList";
	public static String getTryCountAndRankListRedisKey(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff) {
		return TRYCOUNTANDRANKLIST + ":" + areaType + ":" + areaSeq + ":" + periodSeq + ":" + datediff;
	}

}
