package com.nuite.modules.sys.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.dao.ShoeViewDao;
import com.nuite.modules.sys.entity.DeadlineRaiseRateEntity;
import com.nuite.modules.sys.entity.PatchEntity;
import com.nuite.modules.sys.entity.ShoeViewEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.DeadlineRaiseRateService;
import com.nuite.modules.sys.service.GoodsInitialDataService;
import com.nuite.modules.sys.service.PatchService;
import com.nuite.modules.sys.service.PurchasePlanService;
import com.nuite.modules.sys.service.ReplenishmentService;

import io.swagger.annotations.ApiParam;


/**
 * 分公司补单建议接口
 * @author yy
 */
@RestController
@RequestMapping("sys/replenishment")
public class ReplenishmentController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ReplenishmentService replenishmentService;
    
    @Autowired
    private PatchService patchService;
    
    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private DeadlineRaiseRateService deadlineRaiseRateService;
    
    @Autowired
    private PurchasePlanService purchasePlanService;
    
    @Autowired
    private GoodsInitialDataService goodsInitialDataService;
    
    
    
	/**
     * 排行
     */
    @GetMapping("top")
    public R top(@ApiParam("波次") @RequestParam("periodSeq") Integer periodSeq,
			@ApiParam("品类（中文）") @RequestParam("sxValue") String sxValue,
			@ApiParam("开始时间") @RequestParam("startDate") Date startDate,
			@ApiParam("结束时间") @RequestParam("endDate") Date endDate,
			HttpServletRequest request) {
    	try {
    		UserEntity loginUser = getUser();
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		
    		//TODO 判断配置是否完善
    		
    		//获取今年的补单配置
    		Calendar cal = Calendar.getInstance();
    		Integer thisYear = cal.get(Calendar.YEAR);
    		DeadlineRaiseRateEntity thisYearSetting = deadlineRaiseRateService.getSomeYearSetting(thisYear);
    		if(thisYearSetting == null){
    			return R.error("请先完成今年的补单配置！");
    		}
    		//黑马允许最大上柜天数
    		Integer darkHorseCabinetDays = thisYearSetting.getDarkHorseCabinetDays();
    		
    		
    		/**全国数据**/
    		// 全国本品类的  采购计划总量
    		Integer countryTotalPlanPurchaseNum = purchasePlanService.getSXPlanTotalNum(0, periodSeq, sxValue);
    		resultMap.put("countryTotalPlanPurchaseNum", countryTotalPlanPurchaseNum);
    		// 全国本品类的  已经采购量（首单+已经补单）
    		Integer countryFirstOrderAndPatchedNum = goodsInitialDataService.getFirstOrderAndPatchedNum(0, periodSeq, sxValue);
    		resultMap.put("countryFirstOrderAndPatchedNum", countryFirstOrderAndPatchedNum);
    		// 全国本品类的  剩余采购量
    		Integer countryRestPlanPurchaseNum = null;
    		if(countryTotalPlanPurchaseNum != null && countryFirstOrderAndPatchedNum != null) {
    			countryRestPlanPurchaseNum = countryTotalPlanPurchaseNum - countryFirstOrderAndPatchedNum;
    		}
    		resultMap.put("countryRestPlanPurchaseNum", countryRestPlanPurchaseNum);
    		
    		
			// 1.全国上周试穿排名Top20
			List<Map<String, Object>> countryWeekTryTopList = replenishmentService.getTryTop(1, 0, periodSeq, sxValue, 1, startDate, endDate, 20);
			addImg(countryWeekTryTopList);
    		resultMap.put("countryWeekTry", countryWeekTryTopList);
    		
			// 2.全国累计试穿排名Top20
			List<Map<String, Object>> countryTotalTryTopList = replenishmentService.getTryTop(1, 0, periodSeq, sxValue, 2, startDate, endDate, 20);
			addImg(countryTotalTryTopList);
    		resultMap.put("countryTotalTry", countryTotalTryTopList);
    		
			// 3.全国上周销售排名Top20
			List<Map<String, Object>> countryWeekSaleTopList = replenishmentService.getSaleTop(1, 0, periodSeq, sxValue, 1, startDate, endDate, 20);
    		addImg(countryWeekSaleTopList);
    		resultMap.put("countryWeekSale", countryWeekSaleTopList);
    		
			// 4.全国累计销售排名Top20
			List<Map<String, Object>> countryTotalSaleTopList = replenishmentService.getSaleTop(1, 0, periodSeq, sxValue, 2, startDate, endDate, 20);
    		addImg(countryTotalSaleTopList);
    		resultMap.put("countryTotalSale", countryTotalSaleTopList);
    		
			// 5.全国累计售罄率排名Top20
			List<Map<String, Object>> countryTotalSaleOutRateTopList = replenishmentService.getSaleOutRateTop(1, 0, periodSeq, sxValue, 20);
    		addImg(countryTotalSaleOutRateTopList);
    		addPercentSign(countryTotalSaleOutRateTopList);
    		resultMap.put("countryTotalSaleOutRate", countryTotalSaleOutRateTopList);
    		
			// 6.全国上周销售强度排名Top20
			List<Map<String, Object>> countryWeekSaleStrengthTopList = replenishmentService.getSaleStrengthTop(1, 0, periodSeq, sxValue, startDate, endDate, 20);
    		addImg(countryWeekSaleStrengthTopList);
    		resultMap.put("countryWeekSaleStrength", countryWeekSaleStrengthTopList);
    		
    		// 7.汇总上周销售、累计销售2个排行，按上周销售排序
    		Map<Integer, Integer> shoeWeekSaleDescMap = getShoeWeekSaleDescMap(1, 0, startDate, endDate, countryWeekSaleTopList, countryTotalSaleTopList);
    		
    		//生成黑马shoesSeq的Set（上周销售排名Top20内的，上柜2周内的货品）
    		Set<Integer> countryDarkHorseShoeSeqSet = getDarkHorseShoeSeqSet(1, 0, startDate, endDate, countryWeekSaleTopList, darkHorseCabinetDays);
    		
    		
    		//如果是分公司，查询分公司时间段本波次品类已提交的补单申请记录，添加到全国补单推荐List和分公司补单推荐List中（其中，如果全国的推荐货号在分公司推荐中已存在，则不加入全国推荐List）
    		List<PatchEntity> alreadyPatchList = new ArrayList<PatchEntity>();
    		if(loginUser.getRoleSeq() == 3) {
    			Integer areaSeq = loginUser.getUserAreaSeq();
	    		alreadyPatchList = patchService.getBranchofficeAlreadyPatchList(areaSeq, periodSeq, sxValue, startDate, endDate);
    		}
    		
    		//组装汇总推荐补货列表
    		List<Map<String, Object>> countryReplenishmentSuggestList = new ArrayList<Map<String, Object>>();
    		int i = 1;
    	    for (Integer shoeSeq : shoeWeekSaleDescMap.keySet()) {
    	    	Integer weekSale = shoeWeekSaleDescMap.get(shoeSeq);
    	    	Map<String, Object> map = new HashMap<String, Object>();
    	    	map.put("rank", i++);
    	    	map.put("shoeSeq", shoeSeq);
    	    	map.put("weekSale", weekSale);
    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById(shoeSeq);
    			map.put("shoeId", shoeViewEntity.getShoeID());
    			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
    			//库存（即全国库存）
    			Integer stock = replenishmentService.getShoeStock(1, 0, shoeSeq);
    	    	map.put("stock", stock);
    	    	//上周平均折扣（实际销售价格/吊牌价）
    	    	BigDecimal avgDiscount = replenishmentService.getShoeAvgDiscount(1, 0, shoeSeq, startDate, endDate);
    	    	map.put("avgDiscount", avgDiscount);
    	    	//上柜天数
    			Date date = replenishmentService.getShoeCabinetDate(1, 0, shoeSeq);
    			if(date != null) {
    				map.put("cabinetDays", DateUtils.daysBetween(date, new Date()));
    			} else {
    				map.put("cabinetDays", "");
    			}
    	    	//判断是否是黑马
    	    	map.put("isDarkHorse", countryDarkHorseShoeSeqSet.contains(shoeSeq) ? 1:0);
    	    	map.put("existInBranchofficeList", 0);
    	    	
    	    	//添加已补单数据（添加三个空的初始值，因为页面vue加载时没这个字段会出错）
    	    	map.put("patchNum", "");
    	    	map.put("patchState", "");
    	    	map.put("patchDetail", "{}");
    	    	for(PatchEntity patchEntity : alreadyPatchList) {
    	    		if(shoeSeq.equals(patchEntity.getShoeSeq())) {
    	    	    	map.put("patchNum", patchEntity.getPatchNum());
    	    	    	map.put("patchState", patchEntity.getState());
    	    	    	map.put("patchDetail", new JSONObject(patchEntity.getPatchDetailMap()).toString());
    	    			break;
    	    		}
    	    	}
    	    	
    	    	countryReplenishmentSuggestList.add(map);
            }
    	    
    	    //添加同比变化、预测销量、建议补单量
    	    countryReplenishmentSuggestList = replenishmentService.addYearOnyearData(countryReplenishmentSuggestList, 1, 0, periodSeq, sxValue, startDate, endDate, countryRestPlanPurchaseNum);
    	    //添加环比变化
    	    countryReplenishmentSuggestList = replenishmentService.addLinkRelativeRatioData(countryReplenishmentSuggestList, 1, 0, periodSeq, sxValue, startDate, endDate);
    	    
    	    resultMap.put("countryReplenishmentSuggest", countryReplenishmentSuggestList);
    		
    		
    	    
    	    
    		/**分公司数据**/
	    	if(loginUser.getRoleSeq() == 3) {
		    	Integer areaSeq = loginUser.getUserAreaSeq();
		    	// 本分公司内正价门店数量
		    	Integer branchofficeNonDiscountShopNum = replenishmentService.getBranchofficeNonDiscountShopNum(areaSeq);
		    	
	    		// 分公司本品类的 采购计划总量
	    		Integer branchofficeTotalPlanPurchaseNum = purchasePlanService.getSXPlanTotalNum(areaSeq, periodSeq, sxValue);
	    		resultMap.put("branchofficeTotalPlanPurchaseNum", branchofficeTotalPlanPurchaseNum);
	    		// 分公司本品类的 已经采购量（首单+已经补单）
	    		Integer branchofficeFirstOrderAndPatchedNum = goodsInitialDataService.getFirstOrderAndPatchedNum(areaSeq, periodSeq, sxValue);
	    		resultMap.put("branchofficeFirstOrderAndPatchedNum", branchofficeFirstOrderAndPatchedNum);
	    		// 分公司本品类的 剩余采购量
	    		Integer branchofficeRestPlanPurchaseNum = null;
	    		if(branchofficeTotalPlanPurchaseNum != null && branchofficeFirstOrderAndPatchedNum != null) {
	    			branchofficeRestPlanPurchaseNum = branchofficeTotalPlanPurchaseNum - branchofficeFirstOrderAndPatchedNum;
	    		}
	    		resultMap.put("branchofficeRestPlanPurchaseNum", branchofficeRestPlanPurchaseNum);
		    	
	    		
				// 1.分公司上周试穿排名Top20
				List<Map<String, Object>> branchofficeWeekTryTopList = replenishmentService.getTryTop(3, areaSeq, periodSeq, sxValue, 1, startDate, endDate, 20);
				addImg(branchofficeWeekTryTopList);
	    		resultMap.put("branchofficeWeekTry", branchofficeWeekTryTopList);
	    		
				// 2.分公司累计试穿排名Top20
				List<Map<String, Object>> branchofficeTotalTryTopList = replenishmentService.getTryTop(3, areaSeq, periodSeq, sxValue, 2, startDate, endDate, 20);
				addImg(branchofficeTotalTryTopList);
	    		resultMap.put("branchofficeTotalTry", branchofficeTotalTryTopList);
	    		
				// 3.分公司上周销售排名Top20
				List<Map<String, Object>> branchofficeWeekSaleTopList = replenishmentService.getSaleTop(3, areaSeq, periodSeq, sxValue, 1, startDate, endDate, 20);
	    		addImg(branchofficeWeekSaleTopList);
	    		resultMap.put("branchofficeWeekSale", branchofficeWeekSaleTopList);
	    		
				// 4.分公司累计销售排名Top20
				List<Map<String, Object>> branchofficeTotalSaleTopList = replenishmentService.getSaleTop(3, areaSeq, periodSeq, sxValue, 2, startDate, endDate, 20);
	    		addImg(branchofficeTotalSaleTopList);
	    		resultMap.put("branchofficeTotalSale", branchofficeTotalSaleTopList);
	    		
				// 5.分公司累计售罄率排名Top20
				List<Map<String, Object>> branchofficeTotalSaleOutRateTopList = replenishmentService.getSaleOutRateTop(3, areaSeq, periodSeq, sxValue, 20);
	    		addImg(branchofficeTotalSaleOutRateTopList);
	    		addPercentSign(branchofficeTotalSaleOutRateTopList);
	    		resultMap.put("branchofficeTotalSaleOutRate", branchofficeTotalSaleOutRateTopList);
	    		
				// 6.分公司上周销售强度排名Top20
				List<Map<String, Object>> branchofficeWeekSaleStrengthTopList = replenishmentService.getSaleStrengthTop(3, areaSeq, periodSeq, sxValue, startDate, endDate, 20);
	    		addImg(branchofficeWeekSaleStrengthTopList);
	    		resultMap.put("branchofficeWeekSaleStrength", branchofficeWeekSaleStrengthTopList);
	    		
	    		// 7.汇总上周销售、累计销售2个排行，按上周销售排序
	    		Map<Integer, Integer> branchofficeShoeWeekSaleDescMap = getShoeWeekSaleDescMap(3, areaSeq, startDate, endDate, branchofficeWeekSaleTopList, branchofficeTotalSaleTopList);
	    		
	    		//生成黑马shoesSeq的Set（上周销售排名Top20内的，上柜2周内的货品）
	    		Set<Integer> branchofficeDarkHorseShoeSeqSet = getDarkHorseShoeSeqSet(3, areaSeq, startDate, endDate, branchofficeWeekSaleTopList, darkHorseCabinetDays);
	    		
	    		
	    		//组装汇总推荐补货列表
	    		List<Map<String, Object>> branchofficeReplenishmentSuggestList = new ArrayList<Map<String, Object>>();
	    		int j = 1;
	    	    for (Integer shoeSeq : branchofficeShoeWeekSaleDescMap.keySet()) {
	    	    	Integer weekSale = branchofficeShoeWeekSaleDescMap.get(shoeSeq);
	    	    	Map<String, Object> map = new HashMap<String, Object>();
	    	    	map.put("rank", j++);
	    	    	map.put("shoeSeq", shoeSeq);
	    	    	map.put("weekSale", weekSale);
	    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById(shoeSeq);
	    			map.put("shoeId", shoeViewEntity.getShoeID());
	    			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
	    			//库存
	    			Integer stock = replenishmentService.getShoeStock(3, areaSeq, shoeSeq);
	    	    	map.put("stock", stock);
	    	    	//平均每个门店分到的库存（单店齐码率）
	    	    	BigDecimal perShopStock = null;
	    	    	if(branchofficeNonDiscountShopNum != 0) {
		    	    	perShopStock = BigDecimal.valueOf(stock).divide(BigDecimal.valueOf(branchofficeNonDiscountShopNum), 1, RoundingMode.HALF_UP);
	    	    	}
	    	    	map.put("perShopStock", perShopStock);
	    	    	//全国的库存
	    	    	Integer countryStock = replenishmentService.getShoeStock(1, 0, shoeSeq);
	    	    	map.put("countryStock", countryStock);
	    	    	//上周平均折扣（采用实际销售价格和吊牌价格进行计算）
	    	    	BigDecimal avgDiscount = replenishmentService.getShoeAvgDiscount(3, areaSeq, shoeSeq, startDate, endDate);
	    	    	map.put("avgDiscount", avgDiscount);
	    	    	//上柜天数
	    			Date date = replenishmentService.getShoeCabinetDate(3, areaSeq, shoeSeq);
	    			if(date != null) {
	    				map.put("cabinetDays", DateUtils.daysBetween(date, new Date()));
	    			} else {
	    				map.put("cabinetDays", "");
	    			}
	    	    	//判断是否是黑马
	    	    	map.put("isDarkHorse", branchofficeDarkHorseShoeSeqSet.contains(shoeSeq) ? 1:0);
	    	    	
	    	    	//添加已补单数据（添加三个空的初始值，因为页面vue加载时没这个字段会出错）
	    	    	map.put("patchNum", "");
	    	    	map.put("patchState", "");
	    	    	map.put("patchDetail", "{}");
	    	    	for(PatchEntity patchEntity : alreadyPatchList) {
	    	    		if(shoeSeq.equals(patchEntity.getShoeSeq())) {
	    	    	    	map.put("patchNum", patchEntity.getPatchNum());
	    	    	    	map.put("patchState", patchEntity.getState());
	    	    	    	map.put("patchDetail", new JSONObject(patchEntity.getPatchDetailMap()).toString());
	    	    			break;
	    	    		}
	    	    	}
	    	    	
    	    		branchofficeReplenishmentSuggestList.add(map);
	    	    	
	    	    	//循环全国的推荐补单List，判断分公司是否已存在
	    	    	for(Map<String, Object> countryReplenishmentSuggestMap : countryReplenishmentSuggestList) {
	        	    	Integer countryShoeSeq = (Integer) countryReplenishmentSuggestMap.get("shoeSeq");
	    	    		if(shoeSeq.equals(countryShoeSeq)) {
	    	    			countryReplenishmentSuggestMap.put("existInBranchofficeList", 1);
	    	    			//设置三个初始值为空，使全国页面不重复展示已补单信息
	    	    			countryReplenishmentSuggestMap.put("patchNum", "");
	    	    			countryReplenishmentSuggestMap.put("patchState", "");
	    	    			countryReplenishmentSuggestMap.put("patchDetail", "{}");
	    	    			break;
	    	    		}
	    	    	}
	    	    	
	            }
	    	    
	    	    //添加同比变化、预测销量、建议补单量
	    	    branchofficeReplenishmentSuggestList = replenishmentService.addYearOnyearData(branchofficeReplenishmentSuggestList, 3, areaSeq, periodSeq, sxValue, startDate, endDate, branchofficeRestPlanPurchaseNum);
	    	    //添加环比变化
	    	    branchofficeReplenishmentSuggestList = replenishmentService.addLinkRelativeRatioData(branchofficeReplenishmentSuggestList, 3, areaSeq, periodSeq, sxValue, startDate, endDate);
	    	    
	    	    resultMap.put("countryReplenishmentSuggest", countryReplenishmentSuggestList);
	    	    resultMap.put("branchofficeReplenishmentSuggest", branchofficeReplenishmentSuggestList);
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    /* **** 新需求start：如果是分公司，在全国推荐界面只展示不存在的货号，且推荐值计算使用分公司的数据 ****  */
	    	    
	    	    //1.取出全国在分公司不存在的鞋子序号
	    	    List<Integer> notExistInBranchofficeListShoeSeqList = new ArrayList<Integer>();
    	    	for(Map<String, Object> map : countryReplenishmentSuggestList) {
        	    	Integer existInBranchofficeList = (Integer) map.get("existInBranchofficeList");
        	    	if(existInBranchofficeList == 0) {
        	    		Integer shoeSeq = (Integer) map.get("shoeSeq");
        	    		notExistInBranchofficeListShoeSeqList.add(shoeSeq);
        	    	}
    	    	}
	    		//2.重新组装汇 属于分公司的 全国总推荐补货列表
        		List<Map<String, Object>> branchoffice_CountryReplenishmentSuggestList = new ArrayList<Map<String, Object>>();
	    		int k = 1;
	    	    for (Integer shoeSeq : notExistInBranchofficeListShoeSeqList) {
	    	    	Map<String, Object> map = new HashMap<String, Object>();
	    	    	map.put("existInBranchofficeList", 0);
	    	    	
	    	    	map.put("rank", k++);
	    	    	map.put("shoeSeq", shoeSeq);
					//鞋子上周销量
					Integer weekSale = replenishmentService.getShoeSalecount(3, areaSeq, shoeSeq, startDate, endDate);
					if(weekSale == null) {
						weekSale = 0;
					}
	    	    	map.put("weekSale", weekSale);
	    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById(shoeSeq);
	    			map.put("shoeId", shoeViewEntity.getShoeID());
	    			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
	    			//库存
	    			Integer stock = replenishmentService.getShoeStock(3, areaSeq, shoeSeq);
	    	    	map.put("stock", stock);
	    	    	//平均每个门店分到的库存（单店齐码率）
	    	    	BigDecimal perShopStock = null;
	    	    	if(branchofficeNonDiscountShopNum != 0) {
		    	    	perShopStock = BigDecimal.valueOf(stock).divide(BigDecimal.valueOf(branchofficeNonDiscountShopNum), 1, RoundingMode.HALF_UP);
	    	    	}
	    	    	map.put("perShopStock", perShopStock);
	    	    	//全国的库存
	    	    	Integer countryStock = replenishmentService.getShoeStock(1, 0, shoeSeq);
	    	    	map.put("countryStock", countryStock);
	    	    	//上周平均折扣（采用实际销售价格和吊牌价格进行计算）
	    	    	BigDecimal avgDiscount = replenishmentService.getShoeAvgDiscount(3, areaSeq, shoeSeq, startDate, endDate);
	    	    	map.put("avgDiscount", avgDiscount);
	    	    	//上柜天数
	    			Date date = replenishmentService.getShoeCabinetDate(3, areaSeq, shoeSeq);
	    			if(date != null) {
	    				map.put("cabinetDays", DateUtils.daysBetween(date, new Date()));
	    			} else {
	    				map.put("cabinetDays", "");
	    			}
	    	    	//判断是否是黑马
	    	    	map.put("isDarkHorse", branchofficeDarkHorseShoeSeqSet.contains(shoeSeq) ? 1:0);
	    	    	
	    	    	//添加已补单数据（添加三个空的初始值，因为页面vue加载时没这个字段会出错）
	    	    	map.put("patchNum", "");
	    	    	map.put("patchState", "");
	    	    	map.put("patchDetail", "{}");
	    	    	for(PatchEntity patchEntity : alreadyPatchList) {
	    	    		if(shoeSeq.equals(patchEntity.getShoeSeq())) {
	    	    	    	map.put("patchNum", patchEntity.getPatchNum());
	    	    	    	map.put("patchState", patchEntity.getState());
	    	    	    	map.put("patchDetail", new JSONObject(patchEntity.getPatchDetailMap()).toString());
	    	    			break;
	    	    		}
	    	    	}
	    	    	
	    	    	branchoffice_CountryReplenishmentSuggestList.add(map);
	            }
	    	    
	    	    //添加同比变化、预测销量、建议补单量
	    	    branchoffice_CountryReplenishmentSuggestList = replenishmentService.addYearOnyearData(branchoffice_CountryReplenishmentSuggestList, 3, areaSeq, periodSeq, sxValue, startDate, endDate, branchofficeRestPlanPurchaseNum);
	    	    //添加环比变化
	    	    branchoffice_CountryReplenishmentSuggestList = replenishmentService.addLinkRelativeRatioData(branchoffice_CountryReplenishmentSuggestList, 3, areaSeq, periodSeq, sxValue, startDate, endDate);
	    	    
	    	    resultMap.put("countryReplenishmentSuggest", branchoffice_CountryReplenishmentSuggestList);
	    	    
	    	    /* **** 新需求end：如果是分公司，在全国推荐界面只展示不存在的货号，且推荐值计算使用分公司的数据 ****  */
	    	    
	    	    
	    	}
	    	
			return R.ok(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器异常");
		}
    }
	
	

    //汇总上周销售、累计销售2个排行，按上周销售排序
	private Map<Integer, Integer> getShoeWeekSaleDescMap(Integer areaType, Integer areaSeq, Date startDate, Date endDate, List<Map<String, Object>> weekSaleTopList, List<Map<String, Object>> totalSaleTopList) {
		Map<Integer, Integer> shoeWeekSaleMap = new HashMap<Integer, Integer>();
		//取出上周销量Top20的鞋子seq:销量
		for(Map<String, Object> map : weekSaleTopList) {
			Integer shoeSeq = (Integer) map.get("shoeSeq");
			Integer weekSale = (Integer) map.get("saleCount");
			shoeWeekSaleMap.put(shoeSeq, weekSale);
		}
		//取出累计销量Top20中不存在于上周销量Top20的鞋子，计算上周销量，并加入到鞋子销量Map
		for(Map<String, Object> map : totalSaleTopList) {
			Integer shoeSeq = (Integer) map.get("shoeSeq");
			if(!shoeWeekSaleMap.containsKey(shoeSeq)) {
				//鞋子上周销量
				Integer weekSale = replenishmentService.getShoeSalecount(areaType, areaSeq, shoeSeq, startDate, endDate);
				if(weekSale == null) {
					weekSale = 0;
				}
				shoeWeekSaleMap.put(shoeSeq, weekSale);
			}
		}
		//按上周销售排序
		Map<Integer, Integer> shoeWeekSaleDescMap = sortByValue(shoeWeekSaleMap, true);
		return shoeWeekSaleDescMap;
	}
    




    //黑马鞋子序号set，上周销售排名Top20内的，上柜2周内的货品
	private Set<Integer> getDarkHorseShoeSeqSet(Integer areaType, Integer areaSeq, Date startDate, Date endDate, List<Map<String, Object>> weekSaleTopList, Integer darkHorseCabinetDays) {
		Set<Integer> darkHorseShoeSeqSet = new TreeSet<Integer>();
		for(Map<String, Object> map : weekSaleTopList) {
			Integer shoeSeq = (Integer) map.get("shoeSeq");
			Date shoeCabinetDate = replenishmentService.getShoeCabinetDate(areaType, areaSeq, shoeSeq);
			if(shoeCabinetDate != null && DateUtils.daysBetween(shoeCabinetDate, startDate) <= darkHorseCabinetDays) {
				darkHorseShoeSeqSet.add(shoeSeq);
			}
		}
		return darkHorseShoeSeqSet;
	}
	
	

	//给列表的鞋子添加图片
    private List<Map<String, Object>> addImg(List<Map<String, Object>> shoesList) {
		for(int i = 0; i < shoesList.size(); i++) {
			Map<String, Object> map = shoesList.get(i);
			//根据Seq查询鞋子视图
			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoeSeq"));
			map.put("shoeId", shoeViewEntity.getShoeID());
			
			//添加 鞋子图片
			map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
		}
		return shoesList;
    }
    
    
    //给售罄率列表添加百分号
    private List<Map<String, Object>> addPercentSign(List<Map<String, Object>> shoesList) {
		for(int i = 0; i < shoesList.size(); i++) {
			Map<String, Object> map = shoesList.get(i);
			if(map.get("selloutRate") != null) {
				map.put("selloutRate", (BigDecimal.valueOf((Double)map.get("selloutRate"))).multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "%");
			}
		}
		return shoesList;
    }
    
    
    
	
    /**
     * 根据map的value排序
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {            
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
            .forEach(e -> result.put(e.getKey(), e.getValue()));
        } else {            
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
            .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
    
    
}
