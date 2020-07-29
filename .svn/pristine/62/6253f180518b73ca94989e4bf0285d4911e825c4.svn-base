package com.nuite.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.SaleAndStockDao;
import com.nuite.dao.ShoesDataDao;
import com.nuite.entity.SaleAndStockEntity;
import com.nuite.entity.ShoesDataEntity;
import com.nuite.service.SaleAndStockService;


@Service
public class SaleAndStockServiceImpl extends ServiceImpl<SaleAndStockDao, SaleAndStockEntity> implements SaleAndStockService {

	
    @Autowired
    private SaleAndStockDao saleAndStockDao;
    
    @Autowired
    private ShoesDataDao shoesDataDao;
    
	
	
	/**
	 * 获取要新增或修改的 门店某周某品类的鞋子销量和库存 实体
	 */
	@Override
	public SaleAndStockEntity getInsertOrUpdateWeekSaleAndStockEntity(Integer shopSeq, Integer year, Integer week, String sx3Code, String startTimeStr, String endTimeStr) {
		
//		Seq				int	0	0	0	-1	0	0		0	0	0	0			-1			
//		ShopSeq			int	0	0	0	0	0	0		0	0	0	0		门店序号		0			
//		Year			int	0	0	0	0	0	0		0	0	0	0		年		0			
//		Week			int	0	0	0	0	0	0		0	0	0	0		周		0			
//		SX3Code			varchar	3	0	0	0	0	0		0	0	0	0	品类Code	Chinese_PRC_CI_AS	0		
		
//		SaleCount		int	0	0	-1	0	0	0		0	0	0	0		品类总销售双数		0			
//		TotalPrice		decimal	18	2	-1	0	0	0		0	0	0	0	品类总销售金额		0			
//		AvgUnitPrice	decimal	18	2	-1	0	0	0		0	0	0	0	品类平均单价		0			
//		ShoeIdNum		int	0	0	-1	0	0	0		0	0	0	0		SKU数量		0			
//		Stock			int	0	0	-1	0	0	0		0	0	0	0		品类总库存		0			
//		StockPercent	varchar	10	0	-1	0	0	0		0	0	0	0	品类库存占所有品类总库存的比	Chinese_PRC_CI_AS	0			
//		CompleteRate	varchar	10	0	-1	0	0	0		0	0	0	0	齐码率：品类库存除以品类SKU数	Chinese_PRC_CI_AS	0			
//		InputTime		datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	插入时间		0			
//		UpdateTime		datetime	0	0	-1	0	0	0		0	0	0	0	修改时间		0			
//		Del				int	0	0	0	0	0	0	((0))	0	0	0	0	删除标识		0			
		
		SaleAndStockEntity saleAndStockEntity = new SaleAndStockEntity();
		saleAndStockEntity.setShopSeq(shopSeq);
		saleAndStockEntity.setYear(year);
		saleAndStockEntity.setWeek(week);
		saleAndStockEntity.setSX3Code(sx3Code);
		
		SaleAndStockEntity saleAndStockExist = saleAndStockDao.selectOne(saleAndStockEntity);
		if(saleAndStockExist != null) {
			saleAndStockEntity.setSeq(saleAndStockExist.getSeq());
			saleAndStockEntity.setUpdateTime(new Date());
		} else {
			saleAndStockEntity.setInputTime(new Date());
			saleAndStockEntity.setDel(0);
		}
		
		
		
		//查询品类总销售双数、品类总销售金额
		List<Map<String, Object>> list = saleAndStockDao.getSaleCountTotalPriceOneSX3(shopSeq, sx3Code, startTimeStr, endTimeStr);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			Map<String, Object> map = list.get(0);
			Integer saleCount = (Integer) map.get("saleCount");
			BigDecimal totalPrice = (BigDecimal) map.get("totalPrice");
			saleAndStockEntity.setSaleCount(saleCount);
			saleAndStockEntity.setTotalPrice(totalPrice);
			if(saleCount != null && saleCount != 0) {
				BigDecimal avgUnitPrice = totalPrice.divide(BigDecimal.valueOf(saleCount), 2, RoundingMode.HALF_UP);
				saleAndStockEntity.setAvgUnitPrice(avgUnitPrice);
			}
		}
		
		//查询品类有销售的SKU数量
		Integer shoeIdNum = saleAndStockDao.getShoeIdNumOneSX3(shopSeq, sx3Code, startTimeStr, endTimeStr);
		saleAndStockEntity.setShoeIdNum(shoeIdNum);
		
		
		//查询品类总库存
		Integer stock = saleAndStockDao.getStockOneSX3(shopSeq, sx3Code);
		saleAndStockEntity.setStock(stock);

		//查询门店总库存, 计算库存占比
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("SUM (Stock) AS totalStock").where("ShopSeq = {0}", shopSeq);
		List<Object> obj = shoesDataDao.selectObjs(wrapper);
		if(obj != null && obj.size() > 0 && obj.get(0) != null) {
			Integer totalStock = (Integer) obj.get(0);
			saleAndStockEntity.setTotalStock(totalStock);
			if(stock != null && totalStock != null && totalStock != 0) {
				BigDecimal stockPercent = BigDecimal.valueOf(stock).divide(BigDecimal.valueOf(totalStock), 4, RoundingMode.HALF_UP);
				saleAndStockEntity.setStockPercent(stockPercent.multiply(BigDecimal.valueOf(100)) + "%");
			}
		}
		
		
		//计算齐码率：品类库存除以品类SKU数
		if(stock != null && shoeIdNum != null && shoeIdNum != 0 ) {
			BigDecimal completeRate = BigDecimal.valueOf(stock).divide(BigDecimal.valueOf(shoeIdNum), 2, RoundingMode.HALF_UP);
			saleAndStockEntity.setCompleteRate(completeRate + "");
		}
		
		
		return saleAndStockEntity;
		
	}

	


	
	
	
	/**
	 * 查询某一周的销售库存列表
	 */
	@Override
	public List<SaleAndStockEntity> getSomeWeekSaleAndStockList(Integer shopSeq, Integer year, Integer week) {
		Wrapper<SaleAndStockEntity> wrapper = new EntityWrapper<SaleAndStockEntity>();
		wrapper.where("ShopSeq = {0} AND Year = {1} AND Week = {2}", shopSeq, year, week);
		List<SaleAndStockEntity> list = saleAndStockDao.selectList(wrapper);
		return list;
	}

	
}
