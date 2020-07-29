package com.nuite.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Joiner;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.dao.TopDao;
import com.nuite.dao.TryShoesDetailDao;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.entity.TryShoesDetailEntity;
import com.nuite.service.TopService;


@Service
public class TopServiceImpl implements TopService {

    @Autowired
    private TopDao topDao;
    
    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;
    
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;

	
	
    
	/**
	 * 试穿Top20:
	 * 获取指定门店序号、指定波次，指定起止日期，指定top的按试穿量从大到小 的货号、试穿次数、平均试穿时间
	 * @param shopSeqList  门店序号
	 * @param periodSeq  波次序号（为空时查询总表，不为空查询对应的波次有效表）
	 * @param startDate 开始日期
	 * @param endDate  结束日期
	 * @param topNum  top数量
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTopGoodIdTryCount(List<Integer> shopSeqList, List<Integer> periodSeqList, Date startDate, Date endDate,
			Integer topNum) {
		if(periodSeqList == null || periodSeqList.size() == 0) {  //波次序号为空，所有波次， 查询总表
			Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
			wrapper.setSqlSelect("TOP " + topNum + " ShoeID AS shoeId, COUNT (1) AS tryCount, SUM (TryTimes) AS tryTimes")
			.in("ShopSeq", shopSeqList)
			.where("TryTimes >= 2 AND TryTimes <= 180 AND DateDiff(dd, {0}, DataTime) >= 0 AND DateDiff(dd, DataTime, {1}) >= 0 ", startDate, endDate)
			.groupBy("ShoeID")
			.orderBy("tryCount DESC");
			return tryShoesDetailDao.selectMaps(wrapper);
		} else { //波次序号不为空，查询对应波次的有效表
			String shopSeqs = Joiner.on(",").join(shopSeqList);
			List<Map<String, Object>> goodIdTryCountMap = topDao.getTopGoodIdTryCount("Valid", periodSeqList, shopSeqs, startDate, endDate, topNum);
			return goodIdTryCountMap;
		}

	}




	
	/**
	 * 试穿top20中销量:
	 * 获取指定 '门店序号'，指定 '起止日期' 时间段内某 '货号' 的销售量
	 * @param shopSeqList 门店序号
	 * @param periodSeq 波次序号 （仅用于确定要查询的分表）
	 * @param shoeID 货号
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public int getShoeIdSalesFromShopsInSomeDate(List<Integer> shopSeqList, Integer periodSeq, String shoeId, Date startDate, Date endDate) {
		String shopSeqs = Joiner.on(",").join(shopSeqList);
		Integer saleCount = topDao.getShoeIdSalesFromShopsInSomeDate(periodSeq, shopSeqs, shoeId, startDate, endDate);
		if(saleCount != null) {
			return saleCount;
		} else {
			return 0;
		}

	}
	


	
	/**
	 * 销售、滞销Top20:
	 * 获取指定区域、波次、起止日期、销量或滞销top20
	 * @param type  区域类型
	 * @param areaSeqs  区域Seqs
	 * @param periodSeqList  波次序号
	 * @param startDate  开始日期
	 * @param endDate  结束日期
	 * @param topNum  
	 * @param topType
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTopGoodIdSaleCount(Integer type, List<Integer> areaSeqList, List<Integer> periodSeqList,
			Date startDate, Date endDate, Integer topNum, Integer topType) {
		
		if(periodSeqList == null || periodSeqList.size() == 0) {  //波次序号为空，所有波次， 查询总表
			Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
			wrapper.setSqlSelect("TOP " + topNum + " ShoeID AS shoeId, SUM (SaleCount) AS saleCount");
			
			if(type == 2) {
				wrapper.in("AreaSeq", areaSeqList);
			} else if (type == 3) {
				wrapper.in("BranchOfficeSeq", areaSeqList);
			} else if (type == 4) {
				wrapper.in("ShopSeq", areaSeqList);
			}
			
			wrapper.where("DateDiff(dd, {0}, SaleDate) >= 0 AND DateDiff(dd, SaleDate, {1}) >= 0 ", startDate, endDate)
			.groupBy("ShoeID");
			if(topType == 1) {
				wrapper.orderBy("saleCount DESC");
			} else if (topType == 2) {
				wrapper.orderBy("saleCount ASC");
			}

			return saleShoesDetailDao.selectMaps(wrapper);
		} else { //波次序号不为空，查询对应波次的有效表
			String areaSeqs = Joiner.on(",").join(areaSeqList);
			List<Map<String, Object>> goodIdSaleCountMap = topDao.getTopGoodIdSaleCount(type, areaSeqs, periodSeqList, startDate, endDate, topNum, topType);
			return goodIdSaleCountMap;
		}
	}
	
	

	/**
	 * 销售、滞销Top20中试穿量和时间:
	 * 获取某货号试穿次数、试穿时间
	 */
	@Override
	public Map<String, Object> getShoeIdTryCountTimesMap(List<Integer> shopSeqList, Integer periodSeq, String shoeID, Date startDate,
			Date endDate) {
		String shopSeqs = Joiner.on(",").join(shopSeqList);
		List<Map<String, Object>> tryCountTimesList = topDao.getShoeIdTryCountTimesMap(periodSeq, shopSeqs, shoeID, startDate, endDate);
		if(tryCountTimesList != null && tryCountTimesList.size() > 0) {
			return tryCountTimesList.get(0);
		} else {
			return null;
		}

	}



	/**
	 * 大屏试穿top20:  获取全国、所有波次、本月，试穿topNum 的货号、试穿次数
	 */
	@Override
	public List<Map<String, Object>> getCountryMonthTopGoodIdTryCount(Date startDate, Integer topNum) {
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " ShoeID AS shoeId, COUNT (1) AS tryCount")
				.where("TryTimes >= 2 AND TryTimes <= 180 AND DataTime >= {0}", startDate).groupBy("ShoeID")
				.orderBy("tryCount DESC");
		return tryShoesDetailDao.selectMaps(wrapper);
	}




	/**
	 * 大屏试穿top20中销量:  获取全国、本月、某 货号的销售量   ('波次序号' 仅用于确定要查询的分表)
	 */
	@Override
	public int getCountryMonthShoeIdSales(Integer periodSeq, String shoeId, Date startDate) {
		Integer saleCount = topDao.getCountryMonthShoeIdSales(periodSeq, shoeId, startDate);
		if(saleCount != null) {
			return saleCount;
		} else {
			return 0;
		}

	}




	/**
	 * 大屏销售top20:  获取全国、所有波次、本月，销售topNum 的货号、销售双数
	 */
	@Override
	public List<Map<String, Object>> getCountryMonthTopGoodIdSaleCount(Date startDate, Integer topNum) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("TOP " + topNum + " ShoeID AS shoeId, SUM (SaleCount) AS saleCount");
		wrapper.where("SaleDate >= {0}", startDate).groupBy("ShoeID").orderBy("saleCount DESC");
		return saleShoesDetailDao.selectMaps(wrapper);
	}





	/**
	 * 大屏销售top20中试穿量和时间:  获取某货号试穿次数、试穿时间
	 */
	@Override
	public Map<String, Object> getCountryMonthShoeIdTryCountMap(Integer periodSeq, String shoeID, Date startDate) {
		List<Map<String, Object>> tryCountTimesList = topDao.getCountryMonthShoeIdTryCountMap(periodSeq, shoeID, startDate);
		if(tryCountTimesList != null && tryCountTimesList.size() > 0) {
			return tryCountTimesList.get(0);
		} else {
			return null;
		}
	}
	

}
