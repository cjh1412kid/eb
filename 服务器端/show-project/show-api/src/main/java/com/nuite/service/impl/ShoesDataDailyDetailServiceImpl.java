package com.nuite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ShoesDataDailyDetailDao;
import com.nuite.entity.ShoesDataDailyDetailEntity;
import com.nuite.service.ShoesDataDailyDetailService;

/**
 * 每日库存存储详情
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-10 17:51:14
 */
@Service
public class ShoesDataDailyDetailServiceImpl extends ServiceImpl<ShoesDataDailyDetailDao, ShoesDataDailyDetailEntity> implements ShoesDataDailyDetailService {

	@Autowired
    private ShoesDataDailyDetailDao shoesDataDailyDetailDao;

}
