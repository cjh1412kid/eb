package com.nuite.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.TryShoesDetailEntity;


public interface TryShoesDetailService extends IService<TryShoesDetailEntity> {

	int getShopTryValidNum(Integer shopSeq, List<Integer> periodSeqs, Date startDate, Date endDate);

	int getShopTryInvalidNum(Integer shopSeq, List<Integer> periodSeqs, Date startDate, Date endDate);
	
	Date getShopLastTryTime(Integer shopSeq);

}

