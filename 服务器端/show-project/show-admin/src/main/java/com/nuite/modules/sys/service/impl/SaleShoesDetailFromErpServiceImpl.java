package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SaleShoesDetailFromErpDao;
import com.nuite.modules.sys.entity.SaleShoesDetailFromErpEntity;
import com.nuite.modules.sys.service.SaleShoesDetailFromErpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleShoesDetailFromErpServiceImpl extends ServiceImpl<SaleShoesDetailFromErpDao, SaleShoesDetailFromErpEntity> implements SaleShoesDetailFromErpService {
    @Resource
    private SaleShoesDetailFromErpDao saleShoesDetailFromErpDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SaleShoesDetailFromErpEntity> page = this.selectPage(
                new Query<SaleShoesDetailFromErpEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectShopTop10(Date lastWeekToday) {
        return saleShoesDetailFromErpDao.selectShopTop10(lastWeekToday);
    }

    @Override
    public void insertList(List<SaleShoesDetailFromErpEntity> newSaleShoesList) {
        for (SaleShoesDetailFromErpEntity shoesDetailFromErpEntity : newSaleShoesList) {
            saleShoesDetailFromErpDao.insertSaleDetail(shoesDetailFromErpEntity);
        }
    }
}
