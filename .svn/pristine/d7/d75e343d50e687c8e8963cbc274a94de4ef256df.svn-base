package com.nuite.modules.sys.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.RedisKeys;
import com.nuite.common.utils.RedisUtils;
import com.nuite.modules.sys.dao.PeriodDao;
import com.nuite.modules.sys.dao.SaleShoesDetailDao;
import com.nuite.modules.sys.dao.ShoeViewDao;
import com.nuite.modules.sys.dao.ShoesDataDailyDetailDao;
import com.nuite.modules.sys.dao.ShoesDataDao;
import com.nuite.modules.sys.dao.ShopDao;
import com.nuite.modules.sys.dao.TryShoesDetailDao;
import com.nuite.modules.sys.entity.GoodsInitialDataEntity;
import com.nuite.modules.sys.entity.PeriodEntity;
import com.nuite.modules.sys.entity.SaleShoesDetailEntity;
import com.nuite.modules.sys.entity.ShoeViewEntity;
import com.nuite.modules.sys.entity.ShoesDataDailyDetailEntity;
import com.nuite.modules.sys.entity.ShoesDataEntity;
import com.nuite.modules.sys.entity.ShopEntity;
import com.nuite.modules.sys.entity.TryShoesDetailEntity;
import com.nuite.modules.sys.service.DeadlineRaiseRateService;
import com.nuite.modules.sys.service.GoodsInitialDataService;
import com.nuite.modules.sys.service.PurchasePlanService;
import com.nuite.modules.sys.service.ReplenishmentService;


@Service
public class ReplenishmentServiceImpl implements ReplenishmentService {
    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;
    
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;
    
    @Autowired
    private ShoesDataDao shoesDataDao;
    
	@Autowired
    private ShoesDataDailyDetailDao shoesDataDailyDetailDao;
	
    @Autowired
    private ShopDao shopDao;
    
    @Autowired
    private PeriodDao periodDao;
    
    @Autowired
    private DeadlineRaiseRateService deadlineRaiseRateService;
    
    @Autowired
    private GoodsInitialDataService goodsInitialDataService;
    
    @Autowired
    private PurchasePlanService purchasePlanService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
	
    //获取指定波次、品类所有鞋子序号
	private List<Object> getPeriodSxShoesSeqList(Integer periodSeq, String sxValue){
		Wrapper<ShoeViewEntity> wrapper = new EntityWrapper<ShoeViewEntity>();
		wrapper.setSqlSelect("Seq AS shoeSeq");
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		//属性条件判断
//		春季(春深\春浅)售罄率考核截止到6月30日
//      夏季(半凉\全凉)售罄率考核截止到9月30日
//      秋季(秋浅\秋深)售罄率考核截止到12月31日  
//      冬季(单靴\二棉\大棉)售罄率考核截止到次年3月31日
		if(sxValue.equals("春深")) {
			wrapper.where("SX2Value = '深口' AND SX3Value = '春单'");
		} else if (sxValue.equals("春浅")) {
			wrapper.where("SX2Value = '浅口' AND SX3Value = '春单'");
		} else if (sxValue.equals("秋浅")) {
			wrapper.where("SX2Value = '浅口' AND SX3Value = '秋单'");
		} else if (sxValue.equals("秋深")) {
			wrapper.where("SX2Value = '深口' AND SX3Value = '秋单'");
		} else {
			wrapper.where("SX3Value = {0}", sxValue);
		}
		List<Object> shoesSeqList = shoeViewDao.selectObjs(wrapper);
		return shoesSeqList;
	}
	
	
	
	
	/**
	 * 试穿排行
	 */
	@Override
	public List<Map<String, Object>> getTryTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Integer dateType, Date startDate,
			Date endDate, int topNum) {
		//满足指定波次、品类条件的所有鞋子序号
		List<Object> shoesSeqList = getPeriodSxShoesSeqList(periodSeq, sxValue);
		
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " RANK() OVER(ORDER BY COUNT (1) DESC) AS tryRank,  ShoeSeq AS shoeSeq, COUNT (1) AS tryCount");
		wrapper.in("ShoeSeq", shoesSeqList);
		
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if(dateType == 1) { // 某段时间
			wrapper.where("DataTime >= {0} AND DataTime < {1}", startDate, endDate);
		} else if (dateType == 2) { // 累计
			wrapper.where("DataTime < {0}", endDate);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		wrapper.groupBy("ShoeSeq")
		.orderBy("tryCount DESC");
		return tryShoesDetailDao.selectMaps(wrapper);
		
	}




	/**
	 * 销售排行
	 */
	@Override
	public List<Map<String, Object>> getSaleTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Integer dateType,
			Date startDate, Date endDate, int topNum) {
		//满足指定波次、品类条件的所有鞋子序号
		List<Object> shoesSeqList = getPeriodSxShoesSeqList(periodSeq, sxValue);
		
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " RANK() OVER(ORDER BY SUM (SaleCount) DESC) AS saleRank, ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount");
		wrapper.in("ShoeSeq", shoesSeqList);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//时间段条件判断
		if (dateType == 1) { // 某一段时间
			wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		} else if (dateType == 2) { // 累计
			wrapper.where("SaleDate < {0}", endDate);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		wrapper.groupBy("ShoeSeq");
		wrapper.orderBy("saleCount DESC");
		
		return saleShoesDetailDao.selectMaps(wrapper);
		
	}




	/**
	 * 售罄率排行
	 */
	@Override
	public List<Map<String, Object>> getSaleOutRateTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue,
			int topNum) {
		//满足指定波次、品类条件的所有鞋子序号
		List<Object> shoesSeqList = getPeriodSxShoesSeqList(periodSeq, sxValue);
		
		//售罄率计算方式： 总销量/总进货量 = 总进货量-总库存/总进货量 
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("TOP " + topNum + "RANK() OVER(ORDER BY CAST((SUM (Num) - SUM (Stock)) AS Float)/NULLIF(SUM (Num), 0) DESC) AS saleoutRank,"
				+ " ShoeSeq AS shoeSeq, ROUND(CAST((SUM (Num) - SUM (Stock)) AS Float)/NULLIF(SUM (Num), 0), 2) AS selloutRate");
		wrapper.in("ShoeSeq", shoesSeqList);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		//波次条件判断
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		wrapper.groupBy("ShoeSeq");
		wrapper.orderBy("selloutRate DESC");
		
		return shoesDataDao.selectMaps(wrapper);
	}



	
	/**
	 * 销售强度排行
	 */
	@Override
	public List<Map<String, Object>> getSaleStrengthTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue,
			Date startDate, Date endDate, int topNum) {
		
		//1.取出波次品类的所有货品
		List<Object> shoesSeqList = getPeriodSxShoesSeqList(periodSeq, sxValue);
		
		//2.取出其中区域内一周内有销量的鞋子seq、销量
		List<Map<String, Object>> hasSaleShoesList = getHasSaleShoesList(areaType, areaSeq, periodSeq, shoesSeqList, startDate, endDate);
		
		//3.计算所有有销量鞋子的销售强度
		for(Map<String, Object> map : hasSaleShoesList) {
			Integer shoeSeq = (Integer) map.get("shoeSeq");
			//这一周的销售双数
			Integer saleCount = (Integer) map.get("saleCount");
			map.put("saleCount", saleCount);
			
			//计算区域内鞋子上柜门店数量
	    	Integer cabinetShopNum = getCabinetShopNumBetweenDate(areaType, areaSeq, shoeSeq, startDate, endDate);
	    	map.put("cabinetShopNum", cabinetShopNum);
	    	
	    	// 计算销售强度: 自然周销售双数/7天/上货门店数 
	    	BigDecimal saleStrength = BigDecimal.ZERO;
	    	if(saleCount != null && cabinetShopNum != null && cabinetShopNum != 0) {
	    		saleStrength = BigDecimal.valueOf(saleCount).divide(BigDecimal.valueOf(cabinetShopNum * 7), 2, RoundingMode.HALF_UP);
	    	}
	    	map.put("saleStrength", saleStrength);
		}
		
		
		//4. 手工排序, 取销售强度最大的top20
		if(hasSaleShoesList != null && hasSaleShoesList.size() > 0) {
		    List<Map<String, Object>> shoesSaleStrengthDescList = hasSaleShoesList.stream().sorted(
		    		 Comparator.comparing(this::comparingBySaleStrength).reversed())
		             .collect(Collectors.toList());
		    
    		if(shoesSaleStrengthDescList.size() > topNum) {
    			shoesSaleStrengthDescList = shoesSaleStrengthDescList.subList(0, topNum);
    		}
    		int i = 1;
    		for(Map<String, Object> map : shoesSaleStrengthDescList) {
    			map.put("saleStrengthRank", i++);
    		}
		    return shoesSaleStrengthDescList;
		    
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}
	
	
	 private BigDecimal comparingBySaleStrength(Map<String, Object> map){
		 return (BigDecimal) map.get("saleStrength");
	 }
	 
	
	
	//销售强度：获取区域内所有有销量的鞋子的seq，销量List
	public List<Map<String, Object>> getHasSaleShoesList(Integer areaType, Integer areaSeq, Integer periodSeq, List<Object> shoesSeqList, Date startDate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount");
		wrapper.in("ShoeSeq", shoesSeqList);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		
		//波次条件判断
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		wrapper.groupBy("ShoeSeq");
		wrapper.orderBy("saleCount DESC");
		
		List<Map<String, Object>> hasSaleShoesList = saleShoesDetailDao.selectMaps(wrapper);
		return hasSaleShoesList;
	}
	
	
	
	//销售强度：获取区域内某鞋子指定起止时间段的上柜的门店数量
	public Integer getCabinetShopNumBetweenDate(Integer areaType, Integer areaSeq, Integer shoeSeq, Date startDate, Date endDate) {
		Wrapper<ShoesDataDailyDetailEntity> wrapper = new EntityWrapper<ShoesDataDailyDetailEntity>();
		wrapper.setSqlSelect("COUNT (DISTINCT (Shop_Seq))");
		wrapper.where("Shoes_Seq = {0} AND UpdateTime < {1}", shoeSeq, endDate);
		
		//区域条件判断
		if(areaType == 1) { //全国
			wrapper.where("Shop_Seq != -1");  //全国范围筛选时，不存在的门店-1剔除
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
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
	
	
	
	
	
	
	/**
	 * 区域内鞋子的库存
	 */
	@Override
	public Integer getShoeStock(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("SUM (Stock) AS stock").where("shoeSeq = {0}", shoeSeq);
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return 0;
		}
		
		List<Object> list = shoesDataDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return 0;
		}
	}




	/**
	 * 获取分公司内的正价门店总数量
	 */
	@Override
	public Integer getBranchofficeNonDiscountShopNum(Integer areaSeq) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.setSqlSelect("COUNT (1)").where("AreaSeq = {0}", areaSeq)
		.notLike("Name", "T").notLike("Name", "M").notLike("Name", "LS") ;
		List<Object> list = shopDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return 0;
		}
	}




	/**
	 * 添加同比变化、预测销量、建议补单量
	 */
	@Override
	public List<Map<String, Object>> addYearOnyearData(List<Map<String, Object>> branchofficeReplenishmentSuggestList,
			Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate, Integer branchofficeRestPlanPurchaseNum) {
		
		//今年，本波次，本品类，所有鞋子，上周销售排名List
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> sxShoeWeekSaleRankList = getSXShoeWeekSaleRankList(areaType, areaSeq, periodSeq, sxValue, sdf.format(startDate), sdf.format(endDate));
		
		//去年，去年同季节波次，本品类，所有鞋子，去年周销售排名List
		Integer lastyearPeriodSeq = getLastyearPeriodSeq(periodSeq);
		String lastyearStartDate = DateUtils.getWeekDiffMonDateFromDate(-52, startDate); //去年同期开始周一0点
		String lastyearEndDate = DateUtils.getWeekDiffMonDateFromDate(-52, endDate); //去年同期结束周一0点
		List<Map<String, Object>> lastYearSXShoeWeekSaleRankList = getSXShoeWeekSaleRankList(areaType, areaSeq, lastyearPeriodSeq, sxValue, lastyearStartDate, lastyearEndDate);
		
		//获取波次配置的"销售对比最少销售天数"
		Integer minSalesDays = deadlineRaiseRateService.getPeriodMinSalesDaysSetting(periodSeq);
		//获取波次品类配置的"考核截止日期"
		Date sxValueCheckDate = deadlineRaiseRateService.getSxValueCheckedDateSetting(periodSeq, sxValue);
		Date lastyearSxValueCheckDate = deadlineRaiseRateService.getSxValueCheckedDateSetting(lastyearPeriodSeq, sxValue);
		if(lastyearSxValueCheckDate == null) {  //如果没有配置去年的截止日期，直接用今年的年份-1
			lastyearSxValueCheckDate = DateUtils.addDateYears(sxValueCheckDate, -1);
		}
		//获取波次品类配置的"售罄率考核值"
		Integer sxValueSaleOutRate = deadlineRaiseRateService.getSxValueSaleOutRateSetting(periodSeq, sxValue);
		BigDecimal saleOutRate = BigDecimal.valueOf(sxValueSaleOutRate).divide(BigDecimal.valueOf(100));
		//获取波次品类配置的"默认同比增长率"
		Integer sxValueYearRaise = deadlineRaiseRateService.getSxValueYearRaiseSetting(periodSeq, sxValue);
		BigDecimal yearRaise = BigDecimal.valueOf(sxValueYearRaise).divide(BigDecimal.valueOf(100));
		
		//判断区域波次品类有没有上传过采购计划，没上传 直接提示"未上传采购计划"，上传过 计算当前所在计划在今后的占比
		boolean hasUploadPurchasePlan = purchasePlanService.hasUploadPurchasePlan(areaSeq, periodSeq, sxValue);
		BigDecimal purchasePlanPercent = null;
		if(hasUploadPurchasePlan) {
			//获取当前时间所在的补单计划数量在今后所有计划总量的占比
			purchasePlanPercent = purchasePlanService.getThisTimePurchasePlanPercent(new Date(), areaSeq, periodSeq, sxValue);
			if(purchasePlanPercent == null) { //说明计划已经结束，今后已经没有采购计划了
				if(branchofficeRestPlanPurchaseNum != null && branchofficeRestPlanPurchaseNum > 0) {  //如果还有剩余计划采购量，按100%计算占比
					purchasePlanPercent = BigDecimal.valueOf(1);
				} else { //没有剩余计划采购量，则提示"今后已无采购计划，且无剩余数量"
					purchasePlanPercent = null;
				}
			}
		}

		
		
		for(Map<String, Object> map : branchofficeReplenishmentSuggestList) {
			Integer shoeSeq = (Integer)map.get("shoeSeq");
			
			//获取货品的今年上周销售名次
			Integer shoeWeekSaleRank = getShoeSeqInListRank(shoeSeq, sxShoeWeekSaleRankList);
			
			//预测今年总订单量
			BigDecimal thisyearShoeTotalSaleCount = null;
			
			//去年存在同名次的货品
			if(shoeWeekSaleRank != null && lastYearSXShoeWeekSaleRankList.size() >= shoeWeekSaleRank+1) {
				//获取去年同一名次的鞋子seq
				Integer lastyearSameRankShoeSeq = (Integer)lastYearSXShoeWeekSaleRankList.get(shoeWeekSaleRank).get("shoeSeq");
				
				//计算同比增长率
				BigDecimal saleCountRaise = BigDecimal.ZERO;
				if(hasCabinetMoreThanOneMonth(areaType, areaSeq, shoeSeq, minSalesDays)) { //货品已经上柜1个月以上
					//去年此名次鞋子，到目前时间销售的总数量
					Integer lastyearShoeBeforeSaleCount = getShoeBeforeSaleCount(areaType, areaSeq, lastyearSameRankShoeSeq, lastyearEndDate);
					
					//今年此次名次鞋子，到目前时间销售的总数量
					Integer thisyearShoeBeforeSaleCount = getShoeBeforeSaleCount(areaType, areaSeq, shoeSeq, sdf.format(endDate));
					
					if(lastyearShoeBeforeSaleCount != null && thisyearShoeBeforeSaleCount != null && lastyearShoeBeforeSaleCount != 0) {
						saleCountRaise = (BigDecimal.valueOf(thisyearShoeBeforeSaleCount).subtract(BigDecimal.valueOf(lastyearShoeBeforeSaleCount))).divide(BigDecimal.valueOf(lastyearShoeBeforeSaleCount), 2, RoundingMode.HALF_UP);
					}
					String saleCountRaiseStr = saleCountRaise.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "%";
					map.put("yearOnyearSaleCountRaise", saleCountRaiseStr);
				} else {
					map.put("yearOnyearSaleCountRaise", "未满上柜天数，使用配置的默认值");
				}
				
				//去年此名次鞋子，到季末（考核截止日期）的总销量
				Integer lastyearShoeTotalSaleCount = getShoeBeforeSaleCount(areaType, areaSeq, lastyearSameRankShoeSeq, sdf.format(lastyearSxValueCheckDate));
				
				//预测今年总订单量 = 去年同排名到季末总销售量×（1+同比累计增长率）/当前品类售罄率考核值   注：如果没有上柜超过一个月，使用默认同比增长率
				if(hasCabinetMoreThanOneMonth(areaType, areaSeq, shoeSeq, minSalesDays)) { //货品已经上柜1个月以上
					thisyearShoeTotalSaleCount = BigDecimal.valueOf(lastyearShoeTotalSaleCount).multiply(saleCountRaise.add(BigDecimal.valueOf(1))).divide(saleOutRate, 0, RoundingMode.HALF_UP);
				} else { //不足1个月使用配置的默认同比增长率
					thisyearShoeTotalSaleCount = BigDecimal.valueOf(lastyearShoeTotalSaleCount).multiply(yearRaise.add(BigDecimal.valueOf(1))).divide(saleOutRate, 0, RoundingMode.HALF_UP);
				}
				map.put("thisyearShoeTotalSaleCount", thisyearShoeTotalSaleCount);
				
				
			} else {  // 去年名次不足，或上周无销售
				map.put("remark", "上周无销售，或去年名次不足");
				map.put("yearOnyearSaleCountRaise", "上周无销售，或去年名次不足，使用配置的默认值");
				
				map.put("thisyearShoeTotalSaleCount", thisyearShoeTotalSaleCount);
			}
			
			
			//获取首单、补单数据
			GoodsInitialDataEntity goodsInitialDataEntity = goodsInitialDataService.getShoeInitialData(areaType, areaSeq, shoeSeq);
			if(goodsInitialDataEntity != null && goodsInitialDataEntity.getFirstOrderNum() != null && goodsInitialDataEntity.getPatchedNum() != null && goodsInitialDataEntity.getReserveNum() != null) {
				map.put("firstOrderNum", BigDecimal.valueOf(goodsInitialDataEntity.getFirstOrderNum())); //首单
				map.put("patchedNum", BigDecimal.valueOf(goodsInitialDataEntity.getPatchedNum())); //补单
				map.put("reserveNum", BigDecimal.valueOf(goodsInitialDataEntity.getReserveNum())); //预留
				
				//建议今后总下单量
				BigDecimal suggestAfterTotalReplenishNum = null;
				if(thisyearShoeTotalSaleCount != null) { // 名次充足，建议今后总下单量 = 预测总订单量-首单-已经补单
					suggestAfterTotalReplenishNum = thisyearShoeTotalSaleCount.subtract(BigDecimal.valueOf(goodsInitialDataEntity.getFirstOrderNum())).subtract(BigDecimal.valueOf(goodsInitialDataEntity.getPatchedNum()));
					map.put("suggestAfterTotalReplenishNum", suggestAfterTotalReplenishNum);
					
					// 如果结果是负数，建议今后总下单量 = 上周销量 × 剩余销售周数 ×（1 + 默认销售增长比）/ 售罄率考核值”；
					if(suggestAfterTotalReplenishNum.compareTo(BigDecimal.ZERO) < 0) {
						String oldNum = "(原值:" + suggestAfterTotalReplenishNum + ")";
						map.put("remark", "建议量为负数");
						Integer weekSale = (Integer) map.get("weekSale");
						Integer restSaleDays = DateUtils.daysBetween(new Date(), sxValueCheckDate);
						suggestAfterTotalReplenishNum = BigDecimal.valueOf(weekSale).multiply(BigDecimal.valueOf(restSaleDays).divide(BigDecimal.valueOf(7), 2, RoundingMode.HALF_UP)).multiply(yearRaise.add(BigDecimal.valueOf(1))).divide(saleOutRate, 0, RoundingMode.HALF_UP);
						map.put("suggestAfterTotalReplenishNum", suggestAfterTotalReplenishNum + oldNum);
					}
				} else { // 缺少名次，预计今年总订单量 = 上周销量 × 剩余销售周数 ×（1 + 默认销售增长比）/ 售罄率考核值”；
					Integer weekSale = (Integer) map.get("weekSale");
					Integer restSaleDays = DateUtils.daysBetween(new Date(), sxValueCheckDate);
					suggestAfterTotalReplenishNum = BigDecimal.valueOf(weekSale).multiply(BigDecimal.valueOf(restSaleDays).divide(BigDecimal.valueOf(7), 2, RoundingMode.HALF_UP)).multiply(yearRaise.add(BigDecimal.valueOf(1))).divide(saleOutRate, 0, RoundingMode.HALF_UP);
					map.put("suggestAfterTotalReplenishNum", suggestAfterTotalReplenishNum);
				}
				
				// 上传过采购计划 , 使用占比计算建议补单量
				if(hasUploadPurchasePlan) {
					if(purchasePlanPercent != null) {
						// 本次建议下单量 = 建议今后总下单量 ×（当前在采购计划第几次补单时计划的量的占比）
						BigDecimal suggestReplenishNum = suggestAfterTotalReplenishNum.multiply(purchasePlanPercent);
						map.put("suggestReplenishNum", suggestReplenishNum.setScale(0, BigDecimal.ROUND_HALF_UP));
					} else {
						map.put("suggestReplenishNum", "采购计划已结束");
					}
				} else {
					map.put("suggestReplenishNum", "未上传采购计划");
				}
				
			} else {
				map.put("suggestReplenishNum", "缺少首单补单数据");
			}
				
		}
		return branchofficeReplenishmentSuggestList;
	}
	
	
	
	
	
	//获取鞋子到某一时间前的销量
	private Integer getShoeBeforeSaleCount(Integer areaType, Integer areaSeq, Integer shoeSeq, String endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount");

		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return null;
		}
		
		wrapper.where("SaleDate < {0}", endDate);
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return null;
		}
	}

	

	//判断货品是否已经销售过minSalesDays天以上
	private boolean hasSaleMoreThanOneMonth(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer minSalesDays) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("Min (SaleDate)");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return false;
		}
		
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			Date firstSaleDate =  (Date) list.get(0);
			if(DateUtils.daysBetween(firstSaleDate, new Date()) > minSalesDays ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	//判断货品是否已经上柜minSalesDays天以上
	private boolean hasCabinetMoreThanOneMonth(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer minSalesDays) {
		//获取鞋子上柜日期
		Date date = getShoeCabinetDate(areaType, areaSeq, shoeSeq);
		if(date != null) {
			if(DateUtils.daysBetween(date, new Date()) > minSalesDays ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}




	//获取去年同一个季节的波次序号
	private Integer getLastyearPeriodSeq(Integer periodSeq) {
		PeriodEntity periodEntity = periodDao.selectById(periodSeq);
		String periodName = periodEntity.getName();
		Integer year = Integer.parseInt(periodName.substring(0, 4));
		String lastYearPeriodName = (year - 1) + periodName.substring(4);
		
		PeriodEntity lastYearPeriodEntity = new PeriodEntity();
		lastYearPeriodEntity.setName(lastYearPeriodName);
		lastYearPeriodEntity.setIsValid(1);
		lastYearPeriodEntity = periodDao.selectOne(lastYearPeriodEntity);
		if(lastYearPeriodEntity != null) {
			return lastYearPeriodEntity.getSeq();
		} else {
			return null;
		}
	}
	
	
	
	// 获取鞋子序号在排名List中位数
	private Integer getShoeSeqInListRank(Integer shoeSeq, List<Map<String, Object>> sxShoeWeekSaleRankList) {
		for(int i = 0; i < sxShoeWeekSaleRankList.size(); i++) {
			Map<String, Object> map = sxShoeWeekSaleRankList.get(i);
			if(((Integer)map.get("shoeSeq")).equals(shoeSeq)) {
				return i;
			}
		}
		return null;
	}
	


	private List<Map<String, Object>> getSXShoeWeekSaleRankList(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, String startDate, String endDate) {
		//满足指定波次、品类条件的所有鞋子序号
		List<Object> shoesSeqList = getPeriodSxShoesSeqList(periodSeq, sxValue);
		
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("RANK() OVER(ORDER BY SUM (SaleCount) DESC) AS saleRank, ShoeSeq AS shoeSeq, SUM (SaleCount) AS saleCount");
		
		wrapper.in("ShoeSeq", shoesSeqList);

		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return null;
		}
		
		wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		wrapper.where("PeriodSeq = {0}", periodSeq);
		
		wrapper.groupBy("ShoeSeq");
		wrapper.orderBy("saleCount DESC");
		
		return saleShoesDetailDao.selectMaps(wrapper);
	}





	/**
	 * 添加环比数据
	 */
	@Override
	public List<Map<String, Object>> addLinkRelativeRatioData(List<Map<String, Object>> branchofficeReplenishmentSuggestList, 
			Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate) {
		
		for(Map<String, Object> map : branchofficeReplenishmentSuggestList) {
			Integer shoeSeq = (Integer)map.get("shoeSeq");
			
			//获取上周销量
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Integer lastWeekSaleCount = getShoeWeekSaleCount(areaType, areaSeq, shoeSeq, sdf.format(startDate), sdf.format(endDate));
			
			//获取上上周销量
			String beforeLastWeekStartDate = DateUtils.getWeekDiffMonDateFromDate(-1, startDate); //上上周一0点
			String beforeLastWeekEndDate = DateUtils.getWeekDiffMonDateFromDate(-1, endDate); //上周一0点
			Integer beforeLastWeekSaleCount = getShoeWeekSaleCount(areaType, areaSeq, shoeSeq, beforeLastWeekStartDate, beforeLastWeekEndDate);
			
			//计算环比变化值
	    	BigDecimal saleCountRaise = BigDecimal.ZERO;
	    	if(beforeLastWeekSaleCount != null && lastWeekSaleCount != null && beforeLastWeekSaleCount != 0) {
	    		saleCountRaise = (BigDecimal.valueOf(lastWeekSaleCount).subtract(BigDecimal.valueOf(beforeLastWeekSaleCount))).divide(BigDecimal.valueOf(beforeLastWeekSaleCount), 2, RoundingMode.HALF_UP);
	    	}
			String saleCountRaiseStr = saleCountRaise.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "%";
			map.put("linkRelativeRatioSaleCountRaise", saleCountRaiseStr);
					
		}
		return branchofficeReplenishmentSuggestList;
	}
	
	
	
	//获取鞋子某一时间段的销量
	private Integer getShoeWeekSaleCount(Integer areaType, Integer areaSeq, Integer shoeSeq, String startDate, String endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return null;
		}
		
		wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return null;
		}
	}




	/**
	 * 获取鞋子时间段内销量
	 */
	@Override
	public Integer getShoeSalecount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date startDate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount) AS saleCount");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return null;
		}
		
		wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (Integer) list.get(0);
		} else {
			return null;
		}
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
	 * 时间段内鞋子平均折扣
	 */
	@Override
	public BigDecimal getShoeAvgDiscount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date startDate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM(RealPrice)/SUM(TagPrice)");
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else {
			return null;
		}
		
		wrapper.where("SaleDate >= {0} AND SaleDate < {1}", startDate, endDate);
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			BigDecimal avgDiscount = (BigDecimal) list.get(0);
			return avgDiscount.multiply(BigDecimal.valueOf(10)).setScale(1, BigDecimal.ROUND_HALF_UP);
		} else {
			return null;
		}
	}
	
	
	
}
