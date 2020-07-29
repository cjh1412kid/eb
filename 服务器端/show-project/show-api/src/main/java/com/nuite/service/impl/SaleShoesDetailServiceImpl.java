package com.nuite.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.service.SaleShoesDetailService;


@Service
public class SaleShoesDetailServiceImpl extends ServiceImpl<SaleShoesDetailDao, SaleShoesDetailEntity> implements SaleShoesDetailService {

    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;
	
    
    
    /**
     * 获取最新一条销售数据的时间
     */
	@Override
	public Date getSaleDataMostRecentDay() {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("MAX (SaleDate)");
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0) {
			return (Date) list.get(0);
		} else {
			return null;
		}
	}
	
	
	
	/**
	 * 获取某大区时间段内的销售额
	 */
	@Override
	public BigDecimal getFirstAreaTotalSales(Integer firstAreaSeq, Date startdate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)") //注意：当计算decimal类型时，返回的null记为有结果集[null],list大小为1,而不是[]
		.where("AreaSeq = {0} AND DateDiff(dd, {1}, SaleDate) >= 0 AND DateDiff(dd, SaleDate, {2}) >= 0 ", firstAreaSeq, startdate, endDate);
				
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}

	
	/**
	 * 获取某分公司时间段内的销售额
	 */
	@Override
	public BigDecimal getSecondAreaTotalSales(Integer secondAreaSeq, Date startdate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)")
		.where("BranchOfficeSeq = {0} AND DateDiff(dd, {1}, SaleDate) >= 0 AND DateDiff(dd, SaleDate, {2}) >= 0 ", secondAreaSeq, startdate, endDate);
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}

	
	/**
	 * 获取某门店时间段内的销售额
	 */
	@Override
	public BigDecimal getShopTotalSales(Integer shopSeq, Date startdate, Date endDate) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)")
		.where("ShopSeq = {0} AND DateDiff(dd, {1}, SaleDate) >= 0 AND DateDiff(dd, SaleDate, {2}) >= 0 ", shopSeq, startdate, endDate);
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}

	
    /**
     * 获取某时间段内各大区、分公司、门店、日期的销量
     */
    @Override
    public List<Map<String, Object>> getDetailSales(Date startDate, Date endDate) {
        return saleShoesDetailDao.getDetailSales(startDate, endDate);
    }

}
