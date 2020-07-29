package com.nuite.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.common.utils.DateUtils;
import com.nuite.dao.TryShoesDetailDao;
import com.nuite.entity.TryShoesDetailEntity;
import com.nuite.service.TryMapService;


@Service
public class TryMapServiceImpl implements TryMapService {

    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;
    
    
    
	/**
	 * 查询门店今日试穿总量
	 */
	@Override
	public Integer getTodayTotalTryTimesByShopSeq(Integer shopSeq) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String todayDate = sdf.format(new Date());
		
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.where("ShopSeq = {0} AND DataTime >= {1}", shopSeq, todayDate);
		return tryShoesDetailDao.selectCount(wrapper);
	}

	
	/**
	 * 查询多个门店今日本周本月总试穿总量
	 */
	@Override
	public Integer getTotalTryTimesByShopSeqList(List<Integer> shopSeqList, Integer dateDiff) {
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.in("ShopSeq", shopSeqList);
		if(dateDiff != null) {
			wrapper.where("DateDiff(dd, DataTime, GETDATE()) <= {0}", dateDiff);
		}
		return tryShoesDetailDao.selectCount(wrapper);
	}
	
	
	/**
	 * 查询多个门店今日本周本月总试穿总量(通过比较时间，提高查询速度)
	 */
	@Override
	public Integer getTotalTryTimesByShopSeqListAfterDate(List<Integer> shopSeqList, Date beginDate) {
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.in("ShopSeq", shopSeqList);
		if(beginDate != null) {
			wrapper.where("DataTime >= {0}", beginDate);
		}
		return tryShoesDetailDao.selectCount(wrapper);
	}

	
	
	
	/**
	 * 判断门店时间段内是否有试穿数据
	 */
	@Override
	public Boolean shopHasTryDataInSeconds(Integer shopSeq, Integer seconds) {
        Date date = new Date();
        date = DateUtils.addDateSeconds(date, 0 - seconds);
		
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("Top 1 Seq").where("ShopSeq = {0} AND DataTime >= {1}", shopSeq, date);
		List<Object> list = tryShoesDetailDao.selectObjs(wrapper);
		if(list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}


}
