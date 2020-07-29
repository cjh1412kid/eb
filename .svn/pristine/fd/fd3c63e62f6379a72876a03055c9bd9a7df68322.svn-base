package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.ShoesDataDailyDetailDao;
import com.nuite.modules.sys.entity.ShoesDataDailyDetailEntity;
import com.nuite.modules.sys.service.ShoesDataDailyDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每日库存存储详情
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-10 17:51:14
 */
@Service
public class ShoesDataDailyDetailServiceImpl extends ServiceImpl<ShoesDataDailyDetailDao, ShoesDataDailyDetailEntity> implements ShoesDataDailyDetailService {

    @Resource
    private ShoesDataDailyDetailDao shoesDataDailyDetailDao;

    @Override
    public boolean insertList(List<ShoesDataDailyDetailEntity> list) {
        return shoesDataDailyDetailDao.insertList(list);
    }
}
