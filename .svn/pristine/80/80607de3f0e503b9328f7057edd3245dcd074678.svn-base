package com.nuite.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.TryShoesDetailDao;
import com.nuite.entity.TryShoesDetailEntity;
import com.nuite.service.TryShoesDetailService;


@Service
public class TryShoesDetailServiceImpl extends ServiceImpl<TryShoesDetailDao, TryShoesDetailEntity> implements TryShoesDetailService {

    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;

	
	
	/**
	 * 获取一个门店多个波次某时间段内有效试穿次数
	 */
	@Override
	public int getShopTryValidNum(Integer shopSeq, List<Integer> periodSeqs, Date startDate, Date endDate) {
		int total = 0;
		for(Integer periodSeq : periodSeqs) {
			//查询 Valid_波次表 某个门店 某时间段 内试穿次数
			total += tryShoesDetailDao.getShopTryNum("Valid", periodSeq, shopSeq, startDate, endDate);
		}
		return total;
	}

	
	
	
	/**
	 * 获取一个门店多个波次某时间段内无效试穿次数
	 */
	@Override
	public int getShopTryInvalidNum(Integer shopSeq, List<Integer> periodSeqs, Date startDate, Date endDate) {
		int total = 0;
		for(Integer periodSeq : periodSeqs) {
			//查询 Valid_波次表 某个门店 某时间段 内试穿次数
			total += tryShoesDetailDao.getShopTryNum("Invalid", periodSeq, shopSeq, startDate, endDate);
		}
		return total;
	}



	
	

	/**
	 * 获取一个门店最后一次上传试穿数据的时间
	 */
	@Override
	public Date getShopLastTryTime(Integer shopSeq) {
		Wrapper<TryShoesDetailEntity> wrapper = new EntityWrapper<TryShoesDetailEntity>();
		wrapper.setSqlSelect("Top 1 DataTime").where("ShopSeq = {0}", shopSeq).orderBy("DataTime DESC");
		List<Object> list = tryShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0) {
			return (Date) list.get(0);
		} else {
			return null;
		}
		
	}




}
