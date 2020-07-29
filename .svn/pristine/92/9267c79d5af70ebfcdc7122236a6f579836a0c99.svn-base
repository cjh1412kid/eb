package com.nuite.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.SaleQuotaEntity;

import java.util.List;


public interface SaleQuotaService extends IService<SaleQuotaEntity> {
    //查询所有大区的指标
    List<SaleQuotaEntity> selectAreaQuotaList(int year, int month, Integer brandSeq);

    //查询某大区下所有分公司的指标
    List<SaleQuotaEntity> selectOfficeQuotaList(int year, int month, Integer brandSeq, Integer areaSeq);

    //查询某分公司下所有门店的指标
    List<SaleQuotaEntity> selectShopQuotaList(int year, int month, Integer brandSeq, Integer officeSeq);

    //查询当前门店的某年某月销售指标
    SaleQuotaEntity selectShopQuota(int year, int month, Integer brandSeq, Integer userAreaSeq);
}

