package com.nuite.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.RedisUtils;
import com.nuite.dao.AreaDao;
import com.nuite.dao.ProductTopDao;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.dao.ShoesDataDailyDetailDao;
import com.nuite.dao.ShoesDataDao;
import com.nuite.dao.ShopDao;
import com.nuite.dao.SizeDao;
import com.nuite.dao.TryShoesDetailDao;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.entity.ShoesDataDailyDetailEntity;
import com.nuite.entity.ShoesDataEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.SizeEntity;
import com.nuite.entity.TryShoesDetailEntity;
import com.nuite.service.ProductTopService;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.utils.DateUtil;
import com.nuite.utils.ProductModuleEnum;
import com.nuite.utils.RedisKeys;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Service
public class ProductTopServiceImpl implements ProductTopService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;
    
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;

    @Autowired
    private ShoesDataDao shoesDataDao;
    
    @Autowired
    private SizeDao sizeDao;
    
    @Autowired
    private ProductTopDao productTopDao;
    
    @Autowired
    private SaleShoesDetailService saleShoesDetailService;
    
    @Autowired
    private AreaDao areaDao;
    
    @Autowired
    private ShopDao shopDao;
    
	@Autowired
    private ShoesDataDailyDetailDao shoesDataDailyDetailDao;
	
    @Autowired
    private RedisUtils redisUtils;
    
    
    
	
	/**
	 * 2019产品版：试穿排行
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTryTop(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum) {
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " ShoeSeq AS shoeSeq, COUNT (1) AS tryCount, SUM (TryTimes) AS tryTimes");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			String startDate = DateUtil.getDateDiffBeginDate(-1); //昨天0点
			String endDate = DateUtil.getDateDiffBeginDate(0); //今天0点
			wrapper.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper.groupBy("ShoeSeq")
		.orderBy("tryCount DESC");
		return tryShoesDetailDao.selectMaps(wrapper);
	}
	

	
	/**
	 * 2019产品版：销售排行 （包括了畅销、销售额两种排行）
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getSaleTop(int rankType, Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount, SUM (SaleCount * RealPrice) AS salePriceSum");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper.groupBy("ShoeSeq");
		
		if(rankType == ProductModuleEnum.SALE_SALETOP.getModuleSeq()) {	 //畅销
			wrapper.orderBy("saleCount DESC");
		} else if (rankType == ProductModuleEnum.SALE_SALEPRICETOP.getModuleSeq()) {   //销售额
			wrapper.orderBy("salePriceSum DESC");
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		return saleShoesDetailDao.selectMaps(wrapper);
	}
	
	
	
	/**
	 * 2019产品版：滞销排行
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getSaleLast(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff, int topNum) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		/* 1.查询没有销量的鞋子 */
		
		//区域内、时间段前、 本波次，上过柜的所有鞋子
		Wrapper<ShoesDataDailyDetailEntity> wrapper0 = new EntityWrapper<ShoesDataDailyDetailEntity>();
		wrapper0.setSqlSelect("DISTINCT Shoes_Seq");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper0.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper0.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper0.where("Shop_Seq = {0}", areaSeq);
		} else {
			return null;
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper0.where("UpdateTime < {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			wrapper0.where("UpdateTime < {0}", startDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper0.where("PeriodSeq = {0}", periodSeq);
		}
		
		// 所有上柜的鞋子Seqs
		List<Object> cabinShoesSeqList = shoesDataDailyDetailDao.selectObjs(wrapper0);
		
		
		
		
		
		//查询有销量的鞋子的Seq
		Wrapper<SaleShoesDetailEntity> wrapper1 = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper1.setSqlSelect("DISTINCT ShoeSeq");
		
		//区域条件判断
		if(areaType == 1) { //全国	
			
		} else if (areaType == 2) {  //大区
			wrapper1.where("AreaSeq = {0}", areaSeq);	
		} else if (areaType == 3) {  //分公司
			wrapper1.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper1.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper1.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper1.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper1.where("PeriodSeq = {0}", periodSeq);
		}
		
		//有销量的鞋子Seqs
		List<Object> hasSaleShoesSeqList = saleShoesDetailDao.selectObjs(wrapper1);
		
		//无销量的鞋子
		cabinShoesSeqList.removeAll(hasSaleShoesSeqList);
		for(int i = 0; i < cabinShoesSeqList.size() && i < 30; i++) {
			Object object = cabinShoesSeqList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shoeSeq", ((Long)object).intValue());
			map.put("saleCount", 0);
			map.put("salePriceSum", BigDecimal.ZERO);
			resultList.add(map);
		}
		
		
		if(resultList.size() < 30) {

		
		
		/* 2.如果没有销量的鞋子总数不足topNum个，查询有销量，销量最少的鞋子，补足topNum个 */
		
		Wrapper<SaleShoesDetailEntity> wrapper3 = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper3.setSqlSelect("TOP " + (topNum - resultList.size()) + " ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount, SUM (SaleCount * RealPrice) AS salePriceSum");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper3.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper3.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper3.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper3.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper3.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper3.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper3.groupBy("ShoeSeq");
		wrapper3.orderBy("saleCount ASC");
		
		
		List<Map<String, Object>> list = saleShoesDetailDao.selectMaps(wrapper3);
		
		resultList.addAll(list);
		}
		
		return resultList;
	}
	
	
	
	/**
	 * ^销量数据^： 试穿排行中销量数据，本波次（0即为所有波次）所有货品的 销量、排名、平均售价 List
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getSaleCountAndRankList(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("RANK() OVER(ORDER BY SUM (SaleCount) DESC) AS saleRank, ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount, SUM (SaleCount * RealPrice) AS salePriceSum");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper.groupBy("ShoeSeq");
		return saleShoesDetailDao.selectMaps(wrapper);
	}
	
	

	/**
	 * ^库存数据^：本波次所有货品的 累计入库量、库存、库存排名、每个尺码对应的库存、尺码区域下一级的库存 List
	 */
	@Override
	public List<Map<String, Object>> getInNumAndStockRankList(Integer areaType, Integer areaSeq, Integer periodSeq) {
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("RANK() OVER(ORDER BY SUM (Stock) DESC) AS stockRank, ShoeSeq AS shoeSeq, SUM (Num) AS totalInNum, SUM (Stock) AS stock");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper.groupBy("ShoeSeq")
		.orderBy("stock DESC");
		
		List<Map<String, Object>> list = shoesDataDao.selectMaps(wrapper);
		if(list != null) {
			//添加每个尺码对应的库存、尺码区域下一级的库存
			for(Map<String, Object> map : list) {
    			Integer shoeSeq = (Integer)map.get("shoeSeq");
    			List<Map<String, Object>> everySizeStock = getEverySizeStockList(areaType, areaSeq, shoeSeq);
    			map.put("everySizeStock", everySizeStock);
			}
		}
		return list;
	}
	
	
	
	/**
	 * ^库存数据^中区域内某货号每个尺码的库存、尺码区域下一级的库存
	 */
	private List<Map<String, Object>> getEverySizeStockList(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		//区域内某货号每个尺码的库存
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("SizeSeq AS sizeSeq, SUM (Stock) AS stock");
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		wrapper.groupBy("SizeSeq").orderBy("SizeSeq ASC");
		
		List<Map<String, Object>> list = shoesDataDao.selectMaps(wrapper);
		if(list != null) {
			//添加每个尺码的名称、尺码区域下一级的库存
			Integer sizeSeq;
			SizeEntity sizeEntity;
			List<Map<String, Object>> nextAreaStock;
			for(Map<String, Object> map : list) {
    			sizeSeq = (Integer)map.get("sizeSeq");
    			sizeEntity = sizeDao.selectById(sizeSeq);
    			map.put("sizeName", sizeEntity.getName());
    			
    			//尺码区域下一级的库存
    			nextAreaStock = getNextAreaStockList(areaType, areaSeq, shoeSeq, sizeSeq);
    			map.put("nextAreaStock", nextAreaStock);
			}
		}
		return list;
	}
	
	
	
	/**
	 * ^库存数据^中区域内某货号每个尺码区域下一级的库存
	 */
	private List<Map<String, Object>> getNextAreaStockList(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer sizeSeq) {
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.where("ShoeSeq = {0} AND SizeSeq = {1}", shoeSeq, sizeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国，取下一级 各个大区的库存
			wrapper.setSqlSelect("AreaSeq AS seq, SUM (Stock) AS stock");
			wrapper.groupBy("AreaSeq");
		} else if (areaType == 2) {  //大区，取下一级 各个分公司的库存
			wrapper.setSqlSelect("BranchOfficeSeq AS seq, SUM (Stock) AS stock");
			wrapper.where("AreaSeq = {0}", areaSeq);
			wrapper.groupBy("BranchOfficeSeq");
		} else if (areaType == 3) {  //分公司，取下一级 各个门店的库存
			wrapper.setSqlSelect("ShopSeq AS seq, SUM (Stock) AS stock");
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
			wrapper.groupBy("ShopSeq");
		} else if (areaType == 4) {  //门店
			return new ArrayList<Map<String, Object>>();
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		List<Map<String, Object>> list = shoesDataDao.selectMaps(wrapper);
		
		if(list != null) {
			//添加每个区域的名称
			AreaEntity areaEntity;
			ShopEntity shopEntity;
			for(Map<String, Object> map : list) {
    			Integer seq = (Integer)map.get("seq");
				if(areaType == 1 || areaType == 2) {
	    			areaEntity = areaDao.selectById(seq);
	    			if(areaEntity == null) {
	    				logger.error("*****areaType= " + areaType + ", seq=" + seq);
	    			}
	    			map.put("name", areaEntity.getAreaName());
				} else if (areaType == 3) {
	    			shopEntity = shopDao.selectById(seq);
	    			map.put("name", shopEntity.getName());
				}
			}
		}
		return list;
	}
	
	
	
	
	/**
	 * 近30天销量与试穿图表： 获取区域内货号某一天的销量、平均售价
	 */
	@Override
	public Map<String, Object> getDaySaleCountAndAvgPrice(Integer areaType, Integer areaSeq, Integer shoeSeq, Date date) {
		
		Date startdate = date;
		Date endDate = DateUtils.addDateDays(date, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startdateStr = sdf.format(startdate);
		String endDateStr = sdf.format(endDate);
		
		/* 尝试从redis获取缓存的值 */
    	String daySaleCountAndAvgPriceRedisKey = RedisKeys.getDaySaleCountAndAvgPriceRedisKey(areaType, areaSeq, shoeSeq, startdateStr);
    	@SuppressWarnings("unchecked")
		Map<String, Object> daySaleCountAndAvgPriceMap = redisUtils.get(daySaleCountAndAvgPriceRedisKey, Map.class);
    	//不为空,从redis中取值并返回
        if(daySaleCountAndAvgPriceMap != null) {
        	return daySaleCountAndAvgPriceMap;
        }
		
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount, CAST(SUM (SaleCount * RealPrice)/NULLIF(SUM (SaleCount), 0) AS DECIMAL(18,2)) AS avgSalePrice");
		wrapper.where("ShoeSeq = {0} AND SaleDate >= {1} AND SaleDate < {2}", shoeSeq, startdateStr, endDateStr);
		
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new HashMap<String, Object>();
		}
		
		List<Map<String, Object>> list = saleShoesDetailDao.selectMaps(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			daySaleCountAndAvgPriceMap = list.get(0);
			//存入redis 
        	redisUtils.set(daySaleCountAndAvgPriceRedisKey, JSONObject.fromObject(daySaleCountAndAvgPriceMap), 60 * 60 * 24 * 31);
			return daySaleCountAndAvgPriceMap;
		} else {
			return new HashMap<String, Object>();
		}
	}


	/**
	 * 近30天销量与试穿图表： 获取区域内货号某一天的试穿量
	 */
	@Override
	public Integer getDayTryCount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date date) {
		
		Date startdate1 = date;
		Date endDate1 = DateUtils.addDateDays(date, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startdateStr = sdf.format(startdate1);
		String endDateStr = sdf.format(endDate1);
		
		/* 尝试从redis获取缓存的值 */
    	String dayTryCountRedisKey = RedisKeys.getDayTryCountRedisKey(areaType, areaSeq, shoeSeq, startdateStr);
		Integer dayTryCount = redisUtils.get(dayTryCountRedisKey, Integer.class);
    	//不为空,从redis中取值并返回
        if(dayTryCount != null) {
        	return dayTryCount;
        }
		
		
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("COUNT (1) AS tryCount");
		wrapper.where("ShoeSeq = {0} AND DataTime >= {1} AND DataTime < {2}", shoeSeq, startdateStr, endDateStr);
		
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0} ", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0} ", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		List<Object> list = tryShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0) {
			dayTryCount = (Integer) list.get(0);
			//存入redis 
        	redisUtils.set(dayTryCountRedisKey, dayTryCount, 60 * 60 * 24 * 31);
        	
			return dayTryCount;
		} else {
			return 0;
		}
	}



	
	/**
	 * ^试穿数据^： 销量排行中本波次所有货品的 试穿量、试穿排名、总试穿时间 List
	 */
	@Override
	public List<Map<String, Object>> getTryCountAndRankList(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff) {
		
		 /* 已存入redis，试穿排行那边直接从列表中获取，但销售额排行可能有重复货品，会用到 */
		/* 尝试从redis获取缓存的值 */
    	String tryCountAndRankListRedisKey = RedisKeys.getTryCountAndRankListRedisKey(areaType, areaSeq, periodSeq, datediff);
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> tryCountAndRankList = redisUtils.get(tryCountAndRankListRedisKey, List.class);
    	//不为空,从redis中取值并返回
        if(tryCountAndRankList != null) {
        	return tryCountAndRankList;
        }
		 
		 
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("RANK() OVER(ORDER BY COUNT (1) DESC) AS tryRank, ShoeSeq AS shoeSeq, COUNT (1) AS tryCount, SUM (TryTimes) AS tryTimes");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日
			String startDate = DateUtil.getDateDiffBeginDate(-1); //昨天0点
			String endDate = DateUtil.getDateDiffBeginDate(0); //今天0点
			wrapper.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 7) { // 上一周
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计
			
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		if(periodSeq == 0) {
			
		} else {
			wrapper.where("PeriodSeq = {0}", periodSeq);
		}
		
		wrapper.groupBy("ShoeSeq");
		
		tryCountAndRankList = tryShoesDetailDao.selectMaps(wrapper);
		
		//存入redis 
    	redisUtils.set(tryCountAndRankListRedisKey, JSONArray.fromObject(tryCountAndRankList), 60 * 60 * 23);
    	
		return tryCountAndRankList;
	}



	/**
	 * 历史日平均销量三根参考线（已取消展示此块功能，改为展示销售强度）
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> getHistoryDayAvgLineMap(Integer areaType, Integer areaSeq, Integer shoeSeq) throws ParseException {
		Map<String, Object> historyDayAvgLineMap = new HashMap<String, Object>();  //{countryDayAvgLine:100, areaDayAvgLine:80, branchOfficeDayAvgLine:60]}
		
		//获取计算平均值的开始日期  （区域内一个月没有销量后的第一天，或者第一次开始有销量的那天）
		Date countryDayAvgStartDate = getSaleCountDayAvgStartDate(1, 0, shoeSeq);
		//获取计算平均值的结束日期  （最近有销售数据那天）
		Date dayAvgEndDate = saleShoesDetailService.getSaleDataMostRecentDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dayAvgEndDate = sdf.parse(sdf.format(dayAvgEndDate));
		
		//计算全国日平均销量
		Integer countryDayAvgLine = getSaleCountDayAvgBetweenDate(1, 0, shoeSeq, countryDayAvgStartDate, dayAvgEndDate);
		historyDayAvgLineMap.put("countryDayAvgLine", countryDayAvgLine);
		
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			//计算大区日平均销量
			Date areaDayAvgStartDate = getSaleCountDayAvgStartDate(areaType, areaSeq, shoeSeq);
			Integer areaDayAvgLine = getSaleCountDayAvgBetweenDate(areaType, areaSeq, shoeSeq, areaDayAvgStartDate, dayAvgEndDate);
			historyDayAvgLineMap.put("areaDayAvgLine", areaDayAvgLine);
		} else if (areaType == 3) {  //分公司
			//获取分公司所属大区
			Integer bigAreaSeq = getAreaSeqByBranchOfficeSeq(areaSeq);
			
			//计算大区日平均销量
			Date areaDayAvgStartDate = getSaleCountDayAvgStartDate(2, bigAreaSeq, shoeSeq);
			Integer areaDayAvgLine = getSaleCountDayAvgBetweenDate(2, bigAreaSeq, shoeSeq, areaDayAvgStartDate, dayAvgEndDate);
			historyDayAvgLineMap.put("areaDayAvgLine", areaDayAvgLine);
			
			//计算分公司日平均销量
			Date branchOfficeDayAvgStartDate = getSaleCountDayAvgStartDate(areaType, areaSeq, shoeSeq);
			Integer branchOfficeDayAvgLine = getSaleCountDayAvgBetweenDate(areaType, areaSeq, shoeSeq, branchOfficeDayAvgStartDate, dayAvgEndDate);
			historyDayAvgLineMap.put("branchOfficeDayAvgLine", branchOfficeDayAvgLine);
		} else if (areaType == 4) {  //门店
			//获取门店所属分公司
			Integer branchOfficeSeq = getBranchOfficeSeqByShopSeq(areaSeq);
			//获取分公司所属大区
			Integer bigAreaSeq = getAreaSeqByBranchOfficeSeq(branchOfficeSeq);
			
			//计算大区日平均销量
			Date areaDayAvgStartDate = getSaleCountDayAvgStartDate(2, bigAreaSeq, shoeSeq);
			Integer areaDayAvgLine = getSaleCountDayAvgBetweenDate(2, bigAreaSeq, shoeSeq, areaDayAvgStartDate, dayAvgEndDate);
			historyDayAvgLineMap.put("areaDayAvgLine", areaDayAvgLine);
			
			//计算分公司日平均销量
			Date branchOfficeDayAvgStartDate = getSaleCountDayAvgStartDate(3, branchOfficeSeq, shoeSeq);
			Integer branchOfficeDayAvgLine = getSaleCountDayAvgBetweenDate(3, branchOfficeSeq, shoeSeq, branchOfficeDayAvgStartDate, dayAvgEndDate);
			historyDayAvgLineMap.put("branchOfficeDayAvgLine", branchOfficeDayAvgLine);
		}
		return historyDayAvgLineMap;
	}


	//获取计算平均值的开始日期  （区域内一个月没有销量后的第一天，或者第一次开始有销量的那天）
	private Date getSaleCountDayAvgStartDate(Integer areaType, Integer areaSeq, Integer shoeSeq) throws ParseException {
		Date date = productTopDao.getSaleCountDayAvgStartDate(areaType, areaSeq, shoeSeq);
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(sdf.format(date));
			return date;
		} else {
			return null;
		}
	}
	
	
	//计算日平均销量
	private Integer getSaleCountDayAvgBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, Date dayAvgStartDate, Date dayAvgEndDate) {
		if(dayAvgStartDate == null || dayAvgStartDate.after(dayAvgEndDate)) {
			return null;
		}
		Date startdate = dayAvgStartDate;
		Date endDate = DateUtils.addDateDays(dayAvgEndDate, 1);
		//计算两个日期间的天数
		Integer days = DateUtils.daysBetween(startdate, endDate);
		
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount");
		if(areaType == 1) { //全国
			wrapper.where("ShoeSeq = {0} AND SaleDate >= {1} AND SaleDate < {2}", shoeSeq, startdate, endDate);
		} else if (areaType == 2) {  //大区
			wrapper.where("ShoeSeq = {0} AND AreaSeq = {1} AND SaleDate >= {2} AND SaleDate < {3}", shoeSeq, areaSeq, startdate, endDate);
		} else if (areaType == 3) {  //分公司
			wrapper.where("ShoeSeq = {0} AND BranchOfficeSeq = {1} AND SaleDate >= {2} AND SaleDate < {3}", shoeSeq, areaSeq, startdate, endDate);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShoeSeq = {0} AND ShopSeq = {1} AND SaleDate >= {2} AND SaleDate < {3}", shoeSeq, areaSeq, startdate, endDate);
		}
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			Integer saleCount = (Integer) list.get(0);
			return saleCount/days;
		} else {
			return null;
		}
		
	}
	
	
	
	//根据分公司seq获取大区seq
	@Override
	public Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq) {
		AreaEntity areaEntity = areaDao.selectById(branchOfficeSeq);
		return areaEntity.getParentSeq();
	}
	
	
	//根据门店seq获取分公司seq
	@Override
	public Integer getBranchOfficeSeqByShopSeq(Integer shopSeq) {
		ShopEntity shopEntity = shopDao.selectById(shopSeq);
		return shopEntity.getAreaSeq();
	}

	
	
	/**
	 * 获取鞋子上柜日期
	 */
	@Override
	public Date getShoeCabinetDate(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		/* 尝试从redis获取缓存的值 */
    	String shoeCabinetDateRedisKey = RedisKeys.getShoeCabinetDateRedisKey(areaType, areaSeq, shoeSeq);
        Date shoeCabinetDate = redisUtils.get(shoeCabinetDateRedisKey, Date.class);
    	//不为空,从redis中取值并返回
        if(shoeCabinetDate != null) {
        	return shoeCabinetDate;
        }
        
        
        /* 没有获取到缓存时，手动查库 */
		Wrapper<ShoesDataDailyDetailEntity> wrapper = new EntityWrapper<ShoesDataDailyDetailEntity>();
		wrapper.setSqlSelect("TOP 1 UpdateTime");
		wrapper.where("Shoes_Seq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("Shop_Seq = {0}", areaSeq);
		} else {
			return null;
		}
		
		List<Object> list = shoesDataDailyDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			shoeCabinetDate = (Date) list.get(0);
			//存入redis 
        	redisUtils.setNotExpire(shoeCabinetDateRedisKey, shoeCabinetDate);
			return shoeCabinetDate;
		} else {
			return null;
		}
	}

	
	
	
	/**
	 * 试穿趋势
	 */
	@Override
	public Integer getTryCountTrend(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer datediff) {
		/* 尝试从redis获取缓存的值 */
    	String shoeTryCountTrendRedisKey = RedisKeys.getShoeTryCountTrendRedisKey(areaType, areaSeq, shoeSeq, datediff);
    	Integer shoeTryCountTrend = redisUtils.get(shoeTryCountTrendRedisKey, Integer.class);
    	//不为空,从redis中取值并返回
        if(shoeTryCountTrend != null) {
        	return shoeTryCountTrend;
        }
		
		
		/* 后一个时间段试穿量 */
		Wrapper<TryShoesDetailEntity> wrapper1 = new EntityWrapper<TryShoesDetailEntity>();
		wrapper1.setSqlSelect("COUNT (1) AS tryCount");
		wrapper1.where("ShoeSeq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper1.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper1.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper1.where("ShopSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日, 以最近一日跟最近第二日对比
			String startDate = DateUtil.getDateDiffBeginDate(-1); //昨天0点
			String endDate = DateUtil.getDateDiffBeginDate(0); //今天0点
			wrapper1.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 7) { // 上一周		//上周以上周与上上周做对比
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper1.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计 	//累计以7天数据做对比
			String startDate = DateUtil.getDateDiffBeginDate(-7); //7天前0点
			String endDate = DateUtil.getDateDiffBeginDate(0); //今天0点
			wrapper1.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else {
			return 0;
		}
		
		List<Object> list1 = tryShoesDetailDao.selectObjs(wrapper1);
		Integer tryCount1 = 0;
		if (list1 != null && list1.size() > 0 && list1.get(0) != null) {
			tryCount1 = (Integer)list1.get(0);
		}
		
		
		
		
		/* 前一个时间段试穿量 */
		Wrapper<TryShoesDetailEntity> wrapper2 = new EntityWrapper<TryShoesDetailEntity>();
		wrapper2.setSqlSelect("COUNT (1) AS tryCount");
		wrapper2.where("ShoeSeq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper2.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper2.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper2.where("ShopSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日, 以最近一日跟最近第二日对比
			String startDate = DateUtil.getDateDiffBeginDate(-2); //前天0点
			String endDate = DateUtil.getDateDiffBeginDate(-1); //昨天0点
			wrapper2.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 7) { // 上一周		//上周以上周与上上周做对比
			String startDate = DateUtil.getWeekDiffMonDate(-2); //上上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			wrapper2.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计 	//累计以7天数据做对比
			String startDate = DateUtil.getDateDiffBeginDate(-14); //14天前0点
			String endDate = DateUtil.getDateDiffBeginDate(-7); //7天前0点
			wrapper2.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else {
			return 0;
		}
		
		List<Object> list2 = tryShoesDetailDao.selectObjs(wrapper2);
		Integer tryCount2 = 0;
		if (list2 != null && list2.size() > 0 && list2.get(0) != null) {
			tryCount2 = (Integer)list2.get(0);
		}
		
		shoeTryCountTrend = tryCount1 - tryCount2;
		
		//存入redis 
    	redisUtils.set(shoeTryCountTrendRedisKey, shoeTryCountTrend, 60 * 60 * 23);
    	
		return shoeTryCountTrend;
		
	}
	
	
	
	/**
	 * 销售趋势（销量趋势 + 销售额趋势）
	 */
	@Override
	public Map<String, Object> getSaleTrendMap(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer datediff) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("saleCountTrend", 0);
		map.put("salePriceTrend", 0);
		
		/* 后一个时间段销量、销售额 */
		Wrapper<SaleShoesDetailEntity> wrapper1 = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper1.setSqlSelect("SUM (SaleCount) AS saleCount, SUM (SaleCount * RealPrice) AS salePriceSum");
		wrapper1.where("ShoeSeq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper1.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper1.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper1.where("ShopSeq = {0}", areaSeq);
		} else {
			return map;
		}
		
		//时间段条件判断
		Date mostRecentDay = saleShoesDetailService.getSaleDataMostRecentDay(); //最近一日有销售额的时间
		if(datediff == 1) { // 最近一日, 以最近一日跟最近第二日对比
			String startDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper1.where("SaleDate >= {0}", startDate);
		} else if (datediff == 7) { // 上一周		//上周以上周与上上周做对比
			String startDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(0); //本周周一0点
			wrapper1.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计 	//累计以7天数据做对比
			String startDate = DateUtil.getDateDiffBeginDateFromDate(-7, mostRecentDay); //销售日期7天前0点
			wrapper1.where("SaleDate >= {0}", startDate);
		} else {
			return map;
		}
		
		List<Map<String, Object>> list1 = saleShoesDetailDao.selectMaps(wrapper1);
		Integer saleCount1 = 0;
		BigDecimal salePriceSum1 = BigDecimal.ZERO;
		if (list1 != null && list1.size() > 0 && list1.get(0) != null) {
			Map<String, Object> map1 = list1.get(0);
			if(map1.get("saleCount") != null) {
				saleCount1 = (Integer) map1.get("saleCount");
			}
			if(map1.get("salePriceSum") != null) {
				salePriceSum1 = (BigDecimal) map1.get("salePriceSum");
			}
		}
		
		
		/* 前一个时间段销量、销售额 */
		Wrapper<SaleShoesDetailEntity> wrapper2 = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper2.setSqlSelect("SUM (SaleCount) AS saleCount, SUM (SaleCount * RealPrice) AS salePriceSum");
		wrapper2.where("ShoeSeq = {0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper2.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper2.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper2.where("ShopSeq = {0}", areaSeq);
		} else {
			return map;
		}
		
		//时间段条件判断
		if(datediff == 1) { // 最近一日, 以最近一日跟最近第二日对比
			String startDate = DateUtil.getDateDiffBeginDateFromDate(-1, mostRecentDay); //销售日期前一天0点
			String endDate = DateUtil.getDateDiffBeginDateFromDate(0, mostRecentDay); //销售日期当天0点
			wrapper2.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 7) { // 上一周		//上周以上周与上上周做对比
			String startDate = DateUtil.getWeekDiffMonDate(-2); //上上周周一0点
			String endDate = DateUtil.getWeekDiffMonDate(-1); //上周周一0点
			wrapper2.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (datediff == 9) { // 累计 	//累计以7天数据做对比
			String startDate = DateUtil.getDateDiffBeginDateFromDate(-14, mostRecentDay); //销售日期14天前0点
			String endDate = DateUtil.getDateDiffBeginDateFromDate(-7, mostRecentDay); //销售日期7天前0点
			wrapper2.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else {
			return map;
		}
		
		List<Map<String, Object>> list2 = saleShoesDetailDao.selectMaps(wrapper2);
		Integer saleCount2 = 0;
		BigDecimal salePriceSum2 = BigDecimal.ZERO;
		if (list2 != null && list2.size() > 0 && list2.get(0) != null) {
			Map<String, Object> map2 = list2.get(0);
			if(map2.get("saleCount") != null) {
				saleCount2 = (Integer) map2.get("saleCount");
			}
			if(map2.get("salePriceSum") != null) {
				salePriceSum2 = (BigDecimal) map2.get("salePriceSum");
			}
		}
		
		map.put("saleCountTrend", saleCount1 - saleCount2);
		map.put("salePriceTrend", salePriceSum1.subtract(salePriceSum2));
		return map;
	}



	/**
	 * 销售强度图表：获取区域内某鞋子指定起止时间段的销量
	 */
	@Override
	public Integer getSaleCountBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, String startDate, String endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount");
		wrapper.where("ShoeSeq = {0} AND SaleDate >= {1} AND SaleDate < {2}", shoeSeq, startDate, endDate);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer)list.get(0);
		} else {
			return 0;
		}
	}



	/**
	 * 销售强度图表：获取区域内某鞋子指定起止时间段的上货的门店数量
	 */
	@Override
	public Integer getCabinetShopNumBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, String startDate, String endDate) {
		Wrapper<ShoesDataDailyDetailEntity> wrapper = new EntityWrapper<ShoesDataDailyDetailEntity>();
		wrapper.setSqlSelect("COUNT (DISTINCT (Shop_Seq))");
		wrapper.where("Shoes_Seq = {0} AND UpdateTime >= {1} AND UpdateTime < {2}", shoeSeq, startDate, endDate);
		
		//区域条件判断
		if(areaType == 1) { //全国
			wrapper.where("Shop_Seq != -1");  //全国范围筛选时，不存在的门店-1剔除
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		List<Object> list = shoesDataDailyDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return 0;
		}
	}

}
