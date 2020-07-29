package com.nuite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.dao.ShoeDao;
import com.nuite.dao.ShoeViewDao;
import com.nuite.dao.TryShoesDetailDao;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.entity.ShoeEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.TryShoesDetailEntity;
import com.nuite.service.ShoeService;


@Service
public class ShoeServiceImpl extends ServiceImpl<ShoeDao, ShoeEntity> implements ShoeService {

    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;
    
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;
	
    
    
	/**
	 * 根据货号查询鞋子视图实体
	 */
	@Override
	public ShoeViewEntity getShoeViewByShoeId(String shoeId) {
		ShoeViewEntity shoeViewEntity = new ShoeViewEntity();
		shoeViewEntity.setShoeID(shoeId);
		return shoeViewDao.selectOne(shoeViewEntity);
	}



	
	/**
	 * 获取品牌最近1个月试穿Top50+销售Top50+滞销Top50鞋子的货号
	 */
	@Override
	public List<String> getOneMonthTop20ShoeIds(Integer brandSeq) {
		
		Set<String> shoeIdSet = new TreeSet<String>();
		
		
		//试穿Top50
		Wrapper<TryShoesDetailEntity> tryWrapper = new EntityWrapper<TryShoesDetailEntity>();
		tryWrapper.setSqlSelect("TOP 20 ShoeID AS shoeId, COUNT (1) AS tryCount")
		.where("TryTimes >= 2 AND TryTimes <= 180 AND DateDiff(dd, DataTime, GETDATE()) <= 30 ")
		.groupBy("ShoeID")
		.orderBy("tryCount DESC");
		List<Map<String, Object>> tryShoIdList = tryShoesDetailDao.selectMaps(tryWrapper);
		for(Map<String, Object> map : tryShoIdList) {
			shoeIdSet.add(map.get("shoeId").toString());
		}
		
		
		//销售Top50
		Wrapper<SaleShoesDetailEntity> saleWrapper = new EntityWrapper<SaleShoesDetailEntity>();
		saleWrapper.setSqlSelect("TOP 20 ShoeID AS shoeId, SUM (SaleCount) AS saleCount")
		.where("DateDiff(dd, SaleDate, GETDATE()) <= 30 ")
		.groupBy("ShoeID")
		.orderBy("saleCount DESC");
		List<Map<String, Object>> saleShoIdList = saleShoesDetailDao.selectMaps(saleWrapper);
		for(Map<String, Object> map : saleShoIdList) {
			shoeIdSet.add(map.get("shoeId").toString());
		}
		
		//滞销Top50
		Wrapper<SaleShoesDetailEntity> notSaleWrapper = new EntityWrapper<SaleShoesDetailEntity>();
		notSaleWrapper.setSqlSelect("TOP 20 ShoeID AS shoeId, SUM (SaleCount) AS saleCount")
		.where("DateDiff(dd, SaleDate, GETDATE()) <= 30 ")
		.groupBy("ShoeID")
		.orderBy("saleCount ASC");
		List<Map<String, Object>> notSaleShoIdList = saleShoesDetailDao.selectMaps(notSaleWrapper);
		for(Map<String, Object> map : notSaleShoIdList) {
			shoeIdSet.add(map.get("shoeId").toString());
		}
		
		//Set转List
		List<String> list = new ArrayList<String>(shoeIdSet);
		return list;
		
	}
	

}
