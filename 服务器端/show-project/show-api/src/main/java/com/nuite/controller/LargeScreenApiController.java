package com.nuite.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisUtils;
import com.nuite.dao.SXDao;
import com.nuite.dao.SXOptionDao;
import com.nuite.entity.SXEntity;
import com.nuite.entity.SXOptionEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.LargeScreenApiService;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.service.ShoeService;
import com.nuite.service.TopService;
import com.nuite.utils.DateUtil;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 大屏展示web页面专用接口
 * @author yy
 * @date 2019-01-15 10:27:49
 */
@RestController
@RequestMapping("/api/largeScreenApi")
@Api(tags="大屏展示web页面专用接口")
public class LargeScreenApiController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private LargeScreenApiService largeScreenApiService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    @Autowired
    private SXDao sxDao;
    
    @Autowired
    private SXOptionDao sxOptionDao;
    
    @Autowired
    private TopService topService;
    
    @Autowired
    private ShoeService shoeService;
    
    @Autowired
    private SaleShoesDetailService saleShoesDetailService;
    
    
    
	/**
     * 获取全国日、周、月、总试（有效+无效）穿量（顶部12组数字）
     */
    @Login
    @GetMapping("total")
    @ApiOperation(value = "获取全国日、周、月、总试（有效+无效）穿量")
    public R total(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
    		
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getLargeScreenTotalRedisKey(loginUser.getBrandSeq());
	        @SuppressWarnings("unchecked")
	        LinkedHashMap<String, Object> redisMap = redisUtils.get(redisKey, LinkedHashMap.class);
        	//不为空,从redis中取值并返回
	        if(redisMap != null) {
				return R.ok(redisMap);
	        }
    		
	        
	        
	        
	        //没有获取到缓存时，手动查库
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());    //今日
			String week = sdf.format(DateUtil.getWeekMonDate());     //本周
			String month = sdf.format(DateUtil.getMonthFirstDate());     //本月
    		
    		int today_valid = largeScreenApiService.getCountryValidTryTimes(loginUser.getBrandSeq(), today);
    		int today_invalid = largeScreenApiService.getCountryInvalidTryTimes(loginUser.getBrandSeq(), today);
    		
    		int week_valid = largeScreenApiService.getCountryValidTryTimes(loginUser.getBrandSeq(), week);
    		int week_invalid = largeScreenApiService.getCountryInvalidTryTimes(loginUser.getBrandSeq(), week);
    		
    		int month_valid = largeScreenApiService.getCountryValidTryTimes(loginUser.getBrandSeq(), month);
    		int month_invalid = largeScreenApiService.getCountryInvalidTryTimes(loginUser.getBrandSeq(), month);
    		
    		int total_valid = largeScreenApiService.getCountryValidTryTimes(loginUser.getBrandSeq(), null);
    		int total_invalid = largeScreenApiService.getCountryInvalidTryTimes(loginUser.getBrandSeq(), null);
    		
    		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    		resultMap.put("today_valid", today_valid);
    		resultMap.put("today_invalid", today_invalid);
    		resultMap.put("today_total", today_valid + today_invalid);
    		resultMap.put("week_valid", week_valid);
    		resultMap.put("week_invalid", week_invalid);
    		resultMap.put("week_total", week_valid + week_invalid);
    		resultMap.put("month_valid", month_valid);
    		resultMap.put("month_invalid", month_invalid);
    		resultMap.put("month_total", month_valid + month_invalid);
    		resultMap.put("total_valid", total_valid);
    		resultMap.put("total_invalid", total_invalid);
    		resultMap.put("total_total", total_valid + total_invalid);
			return R.ok(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
    
    
    
	/**
     * 最近30天全国销售额接口
     */
    @Login
    @GetMapping("thirtyDaysSale")
    @ApiOperation(value = "最近30天全国销售额接口")
    public R thirtyDaysSale(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
			
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getLargeScreenThirtyDaysSaleRedisKey(loginUser.getBrandSeq());
	        @SuppressWarnings("unchecked")
	        List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
        	//不为空,从redis中取值并返回
	        if(redisList != null) {
				return R.ok(redisList);
	        }
    		
	        
	        //没有获取到缓存时，手动查库
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nowDate = sdf.parse(sdf.format(new Date()));
			
			
    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    		for (int i = 30; i >= 1; i--) {
    			
        		Date date = DateUtils.addDateDays(nowDate, -i);
        		Date lastYearDate = DateUtils.addDateYears(date, -1);
        		
	    		//获取当天、去年同期全国总销售额
	    		Map<String, Object> map = new HashMap<String, Object>();
	    		map.put("date", sdf.format(date));
	    		BigDecimal sales = largeScreenApiService.getCountryTotalSales(date);
	    		map.put("sales", sales);
	    		BigDecimal lastyearSales = largeScreenApiService.getCountryTotalSales(lastYearDate);
	    		map.put("lastyearSales", lastyearSales);
	    		
	    		list.add(map);
    		}
    		
    		return R.ok(list);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
	/**
     * 年度品类销售情况
     */
    @Login
    @GetMapping("sX3WeekSale")
    @ApiOperation(value = "年度品类销售情况")
    public R sX3WeekSale(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
			
    		
    		//查询今年的所有波次
    		Calendar calendar = Calendar.getInstance();
    		int year = calendar.get(Calendar.YEAR);
    		List<Object> periods = largeScreenApiService.getPeriodsByYear(loginUser.getBrandSeq(), year);
    		//去年的所有波次
    		List<Object> lastYearPeriods = largeScreenApiService.getPeriodsByYear(loginUser.getBrandSeq(), year - 1);
    		
    		
    		//查询所有品类
    		SXEntity sxEntity = new SXEntity();
    		sxEntity.setBrandSeq(loginUser.getBrandSeq());
    		sxEntity.setSXID("SX3");
    		sxEntity = sxDao.selectOne(sxEntity);
    		
    		Wrapper<SXOptionEntity> wrapper = new EntityWrapper<SXOptionEntity>();
    		wrapper.where("SXSeq = {0}", sxEntity.getSeq()).orderBy("Code DESC");
    		List<SXOptionEntity> sX3OptionList = sxOptionDao.selectList(wrapper);
    		
    		
    		
    		//获取本周周一日期
    		Date startDate = DateUtil.getWeekMonDate();
    		//获取本周周日日期
	        Date endDate = DateUtils.addDateDays(startDate, 7);
    		
	        //去年同期开始时间
	        Date lastYearStartDate = DateUtils.addDateYears(startDate, -1);
	        //去年同期结束时间  //TODO 去年同期天数必定与本周天数不一致，这个比较没有意义
	        Date lastYearEndDate = DateUtils.addDateYears(endDate, -1);
	        
	        
    		
    		
    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    		
    		//查询本周，今年波次鞋子，各个品类的销售双数
    		for(SXOptionEntity sX3Option : sX3OptionList) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("yearSx3Name", year + sX3Option.getValue());
    			
    			
    			
    			//本周内，今年波次的鞋子 该品类的 销售双数
    			Integer saleNum = largeScreenApiService.getSaleNumByYearAndSx3code(startDate, endDate, periods, sX3Option.getCode());
    			//去年本周，今年波次的鞋子 该品类的 销售双数      （//TODO 逻辑错误，去年不可能销售今年波次的鞋子）
    			Integer lastYearSaleNum = largeScreenApiService.getSaleNumByYearAndSx3code(lastYearStartDate, lastYearEndDate, periods, sX3Option.getCode());
    			//销售双数同比增长
    			BigDecimal saleNumRaise = BigDecimal.ZERO;
    			if(saleNum != null && lastYearSaleNum != null && lastYearSaleNum != 0) {
    	    		saleNumRaise = (BigDecimal.valueOf(saleNum).subtract(BigDecimal.valueOf(lastYearSaleNum))).divide(BigDecimal.valueOf(lastYearSaleNum), 2, RoundingMode.HALF_UP);
    	    	}
    			map.put("saleNum", saleNum);
    			map.put("lastYearSaleNum", lastYearSaleNum);
    			map.put("saleNumRaise", saleNumRaise);
    			
    			
    			
    			
    			//本周内，今年波次的鞋子 该品类的 进货总量
    			Integer totalNum = largeScreenApiService.getTotalNumByYearAndSx3code(startDate, endDate, periods, sX3Option.getCode());
    			//计算售罄率
    			BigDecimal sellOutRate = BigDecimal.ZERO;
    			if(saleNum != null && totalNum != null && totalNum != 0) {
    				sellOutRate = BigDecimal.valueOf(saleNum).divide(BigDecimal.valueOf(totalNum), 2, RoundingMode.HALF_UP);
    			}

    			//去年本周，今年波次的鞋子 该品类的 进货总量（//TODO 逻辑错误，去年不可能销售今年波次的鞋子）
    			Integer lastYearTotalNum = largeScreenApiService.getTotalNumByYearAndSx3code(lastYearStartDate, lastYearEndDate, periods, sX3Option.getCode());
    			//计算售罄率
    			BigDecimal lastYearSellOutRate = BigDecimal.ZERO;
    			if(lastYearSaleNum != null && lastYearTotalNum != null && lastYearTotalNum != 0) {
    				lastYearSellOutRate = BigDecimal.valueOf(lastYearSaleNum).divide(BigDecimal.valueOf(lastYearTotalNum), 2, RoundingMode.HALF_UP);
    			}

    			
    			//售罄率同比增长
    			BigDecimal sellOutRateRaise = BigDecimal.ZERO;
    			if(lastYearSellOutRate.compareTo(BigDecimal.ZERO) != 0) {
    				sellOutRateRaise = (sellOutRate.subtract(lastYearSellOutRate)).divide(lastYearSellOutRate, 2, RoundingMode.HALF_UP);
    			}
    			map.put("sellOutRate", sellOutRate);
    			map.put("lastYearSellOutRate", lastYearSellOutRate);
    			map.put("sellOutRateRaise", sellOutRateRaise);
    			
    			list.add(map);
    		}
    		
    		
    		//前几条数据（几个品类）可能没有销售量，清除这些数据，并添加去年的这几个品类的数据，保证总数据条数为品类数
    		int n = 0;
    		for(int i = 0; i < list.size(); i++) {
    			Map<String, Object> map = list.get(i);
    			Integer saleNum = (Integer) map.get("saleNum");
    			if(saleNum != 0) {
    				break;
    			}
        		list.remove(i);
        		i--;
    			n++;
    		}
    		
    		
    		//查询本周，去年波次鞋子，各个品类的销售双数
    		for(int i = 0; i < n; i++) {
    			
    			SXOptionEntity sX3Option = sX3OptionList.get(i);
    			
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("yearSx3Name", (year - 1) + sX3Option.getValue());
    			
    			
    			
    			//本周内，去年波次的鞋子 该品类的 销售双数
    			Integer saleNum = largeScreenApiService.getSaleNumByYearAndSx3code(startDate, endDate, lastYearPeriods, sX3Option.getCode());
    			//去年本周，去年波次的鞋子 该品类的 销售双数
    			Integer lastYearSaleNum = largeScreenApiService.getSaleNumByYearAndSx3code(lastYearStartDate, lastYearEndDate, lastYearPeriods, sX3Option.getCode());
    			//销售双数同比增长
    			BigDecimal saleNumRaise = BigDecimal.ZERO;
    			if(saleNum != null && lastYearSaleNum != null && lastYearSaleNum != 0) {
    	    		saleNumRaise = (BigDecimal.valueOf(saleNum).subtract(BigDecimal.valueOf(lastYearSaleNum))).divide(BigDecimal.valueOf(lastYearSaleNum), 2, RoundingMode.HALF_UP);
    	    	}
    			map.put("saleNum", saleNum);
    			map.put("lastYearSaleNum", lastYearSaleNum);
    			map.put("saleNumRaise", saleNumRaise);
    			
    			
    			
    			
    			//本周内，去年波次的鞋子 该品类的 进货总量
    			Integer totalNum = largeScreenApiService.getTotalNumByYearAndSx3code(startDate, endDate, lastYearPeriods, sX3Option.getCode());
    			//计算售罄率
    			BigDecimal sellOutRate = BigDecimal.ZERO;
    			if(saleNum != null && totalNum != null && totalNum != 0) {
    				sellOutRate = BigDecimal.valueOf(saleNum).divide(BigDecimal.valueOf(totalNum), 2, RoundingMode.HALF_UP);
    			}

    			//去年本周，去年波次的鞋子 该品类的 进货总量
    			Integer lastYearTotalNum = largeScreenApiService.getTotalNumByYearAndSx3code(lastYearStartDate, lastYearEndDate, lastYearPeriods, sX3Option.getCode());
    			//计算售罄率
    			BigDecimal lastYearSellOutRate = BigDecimal.ZERO;
    			if(lastYearSaleNum != null && lastYearTotalNum != null && lastYearTotalNum != 0) {
    				lastYearSellOutRate = BigDecimal.valueOf(lastYearSaleNum).divide(BigDecimal.valueOf(lastYearTotalNum), 2, RoundingMode.HALF_UP);
    			}

    			
    			//售罄率同比增长
    			BigDecimal sellOutRateRaise = BigDecimal.ZERO;
    			if(lastYearSellOutRate.compareTo(BigDecimal.ZERO) != 0) {
    				sellOutRateRaise = (sellOutRate.subtract(lastYearSellOutRate)).divide(lastYearSellOutRate, 2, RoundingMode.HALF_UP);
    			}
    			map.put("sellOutRate", sellOutRate);
    			map.put("lastYearSellOutRate", lastYearSellOutRate);
    			map.put("sellOutRateRaise", sellOutRateRaise);
    			
    			list.add(map);
    		}
    		
    		
    		
    		return R.ok(list);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
    
    
    
	/**
     * 全国、所有波次、本月 试穿Top20
     */
    @Login
    @GetMapping("tryTop")
    @ApiOperation("全国、所有波次、本月 试穿Top20")
    public R tryTop(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
   		 	//判断用户账号权限
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
    		
			//获取本月1日日期
			Date startDate = DateUtil.getMonthFirstDate();
			
    		List<Map<String, Object>> goodIdTryCount = null;
    		
    		
    		//尝试从redis获取缓存的值
			String redisKey = RedisKeys.getLargeScreenTryTopRedisKey(loginUser.getBrandSeq());
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
			
			if (redisList != null) {
				goodIdTryCount = redisList;
			} else { //没有从redis获取到缓存，主动查库
    			/** 获取全国、所有波次、本月，试穿top20 的货号、试穿次数 **/
    			goodIdTryCount = topService.getCountryMonthTopGoodIdTryCount(startDate, 20);
			}
    		
			
			
    		for(int i = 0; i < goodIdTryCount.size(); i++) {
    			Map<String, Object> map = goodIdTryCount.get(i);
    			//添加名次
    			map.put("rank", i+1);
    			//根据货号查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeService.getShoeViewByShoeId((String)map.get("shoeId"));
    			//添加图片
    			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
    			
    			if(redisList == null) { //没有从redis获取到缓存，添加销售量
	    			/** 获取全国、本月、某 货号的销售量   ('波次序号' 仅用于确定要查询的分表) **/
	    			int saleCount = topService.getCountryMonthShoeIdSales(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID(), startDate);
	    			map.put("saleCount", saleCount);
    			}
    		}
    		
			return R.ok(goodIdTryCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 全国、所有波次、本月 销售Top20
     */
    @Login
    @GetMapping("saleTop")
    @ApiOperation("全国、所有波次、本月 销售Top20")
    public R saleTop(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
   		 	//判断用户账号权限
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
    		
			//获取本月1日日期
			Date startDate = DateUtil.getMonthFirstDate();
			
			List<Map<String, Object>> goodIdSaleCount = null;
			
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getLargeScreenSaleTopRedisKey(loginUser.getBrandSeq());
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
        	//不为空,从redis中取值
	        if(redisList != null) {
	        	goodIdSaleCount = redisList;
	        } else {    //没有从redis获取到缓存，主动查库
        		/** 获取指定区域内、指定波次List，指定起止日期，指定top的按销量排序 的货号、销售量 **/
        		goodIdSaleCount = topService.getCountryMonthTopGoodIdSaleCount(startDate, 20);
    		}

    		for(int i = 0; i < goodIdSaleCount.size(); i++) {
    			Map<String, Object> map = goodIdSaleCount.get(i);
    			//添加名次
    			map.put("rank", i+1);
    			
    			//根据货号查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeService.getShoeViewByShoeId((String)map.get("shoeId"));
    			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
    			
    			if(redisList == null) { //没有从redis获取到缓存，添加试穿次数、平均试穿时间
        			/** 获取指定 '门店序号'，指定 '起止日期' 时间段内某 '货号' 的销售量('波次序号' 仅用于确定要查询的分表) **/
        			Map<String, Object> tryCountTimes = topService.getCountryMonthShoeIdTryCountMap(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID(), startDate);
        			map.putAll(tryCountTimes);
    			}
    		}
    		
			return R.ok(goodIdSaleCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    
    
	/**
     * 昨日、本年销售额，去年同期昨日，去年销售额
     */
    @Login
    @GetMapping("saleNum")
    @ApiOperation(value = "昨日、本年销售额，去年同期昨日，去年销售额")
    public R saleNum(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
   		 	//判断用户账号权限
    		if(loginUser.getRoleSeq() != 1) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "对不起，您的权限不足");
    		}
			
    		//最近一日有销售额的时间
    		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay();
    		if(mostRecentDay == null) {
    			return R.error("对不起，暂无任何销售数据");
    		}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			mostRecentDay = sdf.parse(sdf.format(mostRecentDay));
			
    		Date lastyearDay = DateUtils.addDateYears(mostRecentDay, -1);   //去年同期时间
			
    		// 全国 最近一日 销售额
    		BigDecimal daySales = largeScreenApiService.getCountryOneDaySales(mostRecentDay);
    		// 全国 去年同一日 销售额
    		BigDecimal lastyearDaySales = largeScreenApiService.getCountryOneDaySales(lastyearDay);
    		
    		// 全国 今年 截止今日 总销售额
    		BigDecimal yearSales = largeScreenApiService.getCountryYearToDaySales(mostRecentDay);
    		// 全国 去年 截止今日 总销售额
    		BigDecimal lastyearSales = largeScreenApiService.getCountryYearToDaySales(lastyearDay);
    		// 全国 去年 总销售额
    		BigDecimal lastyearTotalSales = largeScreenApiService.getCountryYearTotalSales(lastyearDay);
    		
    		
    		//同比日增长
    		String dayGrow = "";
			if(lastyearDaySales.compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal dayGrowRate = (daySales.subtract(lastyearDaySales)).multiply(BigDecimal.valueOf(100)).divide(lastyearDaySales, 0, RoundingMode.HALF_UP);
	    		dayGrow = dayGrowRate + "%";
			}
    		//同比年增长
    		String yearGrow = "";
			if(lastyearSales.compareTo(BigDecimal.ZERO) > 0) {
	    		BigDecimal yearGrowRate = (yearSales.subtract(lastyearSales)).multiply(BigDecimal.valueOf(100)).divide(lastyearSales, 0, RoundingMode.HALF_UP);
	    		yearGrow = yearGrowRate + "%";
			}
    		
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("daySales", daySales);
    		resultMap.put("lastyearDaySales", lastyearDaySales);
    		resultMap.put("yearSales", yearSales);
    		resultMap.put("lastyearSales", lastyearSales);
    		resultMap.put("lastyearTotalSales", lastyearTotalSales);
    		resultMap.put("dayGrow", dayGrow);
    		resultMap.put("yearGrow", yearGrow);
    		return R.ok(resultMap);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    


}
