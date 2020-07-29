package com.nuite.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.SaleQuotaDao;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.SaleQuotaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.service.AreaService;
import com.nuite.service.SaleQuotaService;
import com.nuite.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SaleQuotaServiceImpl extends ServiceImpl<SaleQuotaDao, SaleQuotaEntity> implements SaleQuotaService {

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private SaleQuotaDao saleQuotaDao;

    @Override
    public List<SaleQuotaEntity> selectAreaQuotaList(int year, int month, Integer brandSeq) {
        return saleQuotaDao.selectListContainAreaName(year, month, brandSeq, 2, null);
//        return this.selectList(new EntityWrapper<SaleQuotaEntity>()
//                .eq("Year", year)
//                .eq("Month", month)
//                .eq("BrandSeq", brandSeq)
//                .eq("AreaType", 1));
    }

    @Override
    public List<SaleQuotaEntity> selectOfficeQuotaList(int year, int month, Integer brandSeq, Integer areaSeq) {
        List<Object> seqList = areaService.selectObjs(new EntityWrapper<AreaEntity>()
                .setSqlSelect("Seq")
                .eq("ParentSeq", areaSeq)
                .eq("BrandSeq", brandSeq));

        return saleQuotaDao.selectListContainAreaName(year, month, brandSeq, 3, seqList);
//        return this.selectList(new EntityWrapper<SaleQuotaEntity>()
//                .eq("Year", year)
//                .eq("Month", month)
//                .eq("BrandSeq", brandSeq)
//                .eq("AreaType", 2)
//                .in("AreaSeq", seqList));
    }

    @Override
    public List<SaleQuotaEntity> selectShopQuotaList(int year, int month, Integer brandSeq, Integer officeSeq) {
        List<Object> seqList = shopService.selectObjs(new EntityWrapper<ShopEntity>()
                .setSqlSelect("Seq")
                .eq("AreaSeq", officeSeq));
        return saleQuotaDao.selectListContainAreaName(year, month, brandSeq, 4, seqList);
//        return this.selectList(new EntityWrapper<SaleQuotaEntity>()
//                .eq("Year", year)
//                .eq("Month", month)
//                .eq("BrandSeq", brandSeq)
//                .eq("AreaType", 3)
//                .in("AreaSeq", seqList));
    }

    @Override
    public SaleQuotaEntity selectShopQuota(int year, int month, Integer brandSeq, Integer userAreaSeq) {
        return this.selectOne(new EntityWrapper<SaleQuotaEntity>()
                .eq("Year", year)
                .eq("Month", month)
                .eq("BrandSeq", brandSeq)
                .eq("AreaSeq", userAreaSeq)
                .eq("AreaType", 4));
    }
}
