package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SaleShoesDetailDao;
import com.nuite.modules.sys.entity.SaleShoesDetailEntity;
import com.nuite.modules.sys.service.SaleShoesDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleShoesDetailServiceImpl extends ServiceImpl<SaleShoesDetailDao, SaleShoesDetailEntity> implements SaleShoesDetailService {

    @Resource
    private SaleShoesDetailDao saleShoesDetailDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SaleShoesDetailEntity> page = this.selectPage(
                new Query<SaleShoesDetailEntity>(params).getPage(),
                new EntityWrapper<SaleShoesDetailEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectShopTop10(Date lastWeekToday) {
        return saleShoesDetailDao.selectShopTop10(lastWeekToday);
    }

}
