package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.TryShoesDetailDao;
import com.nuite.modules.sys.entity.TryShoesDetailEntity;
import com.nuite.modules.sys.service.TryShoesDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class TryShoesDetailServiceImpl extends ServiceImpl<TryShoesDetailDao, TryShoesDetailEntity> implements TryShoesDetailService {

    @Autowired
    private TryShoesDetailDao tryShoesDetailDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TryShoesDetailEntity> page = this.selectPage(
                new Query<TryShoesDetailEntity>(params).getPage(),
                new EntityWrapper<TryShoesDetailEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectShopTop10(Date lastWeekToday) {
        return tryShoesDetailDao.selectShopTop10(lastWeekToday);
    }

}
