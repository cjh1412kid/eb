package com.nuite.modules.sys.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReplenishmentService {

	/**
	 * 试穿排行
	 * @param areaType 区域类型（1:全国 3:分公司）
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param dateType  时间类型（1:某一段时间 2:累计）
	 * @param startDate
	 * @param endDate
	 * @param topNum
	 * @return
	 */
	List<Map<String, Object>> getTryTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Integer dateType, Date startDate,
			Date endDate, int topNum);

	/**
	 * 销售排行
	 * @param areaType 区域类型（1:全国 3:分公司）
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param dateType  时间类型（1:某一段时间 2:累计）
	 * @param startDate
	 * @param endDate
	 * @param topNum
	 * @return
	 */
	List<Map<String, Object>> getSaleTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Integer dateType, Date startDate, 
			Date endDate, int topNum);

	
	/**
	 * 售罄率排行
	 * @param areaType 区域类型（1:全国 3:分公司）
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param topNum
	 * @return
	 */
	List<Map<String, Object>> getSaleOutRateTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, int topNum);
	
	
	/**
	 * 销售强度排行
	 * @param areaType
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param startDate
	 * @param endDate
	 * @param topNum
	 * @return
	 */
	List<Map<String, Object>> getSaleStrengthTop(Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate, int topNum);
	
	
	/**
	 * 区域内鞋子的库存
	 * @param areaType
	 * @param areaSeq
	 * @param shoeSeq
	 * @return
	 */
	Integer getShoeStock(Integer areaType, Integer areaSeq, Integer shoeSeq);
	
	
	/**
	 * 获取分公司内的正价门店总数量
	 * @param areaSeq
	 * @return
	 */
	Integer getBranchofficeNonDiscountShopNum(Integer areaSeq);
	
	
	/**
	 * 添加同比数据
	 * @param branchofficeReplenishmentSuggestList
	 * @param areaType
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param startDate
	 * @param endDate
	 * @param branchofficeRestPlanPurchaseNum 
	 * @return
	 */
	List<Map<String, Object>> addYearOnyearData(List<Map<String, Object>> branchofficeReplenishmentSuggestList, 
			Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate, Integer branchofficeRestPlanPurchaseNum);

	
	/**
	 * 添加环比数据
	 * @param branchofficeReplenishmentSuggestList
	 * @param areaType
	 * @param areaSeq
	 * @param periodSeq
	 * @param sxValue
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> addLinkRelativeRatioData(List<Map<String, Object>> branchofficeReplenishmentSuggestList, 
			Integer areaType, Integer areaSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate);


	/**
	 * 获取鞋子时间段内销量
	 * @param areaType
	 * @param areaSeq
	 * @param shoeSeq
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer getShoeSalecount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date startDate, Date endDate);

	
	/**
	 * 获取鞋子上柜日期
	 * @param areaType
	 * @param areaSeq
	 * @param shoeSeq
	 * @return
	 */
	Date getShoeCabinetDate(Integer areaType, Integer areaSeq, Integer shoeSeq);

	
	/**
	 * 时间段内鞋子平均折扣
	 * @param areaType
	 * @param areaSeq
	 * @param shoeSeq
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	BigDecimal getShoeAvgDiscount(Integer areaType, Integer areaSeq, Integer shoeSeq, Date startDate, Date endDate);
	
}

