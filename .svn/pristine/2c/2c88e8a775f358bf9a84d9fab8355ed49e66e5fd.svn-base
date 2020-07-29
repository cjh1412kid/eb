package com.nuite.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Joiner;
import com.nuite.common.utils.DateUtils;
import com.nuite.dao.LargeScreenApiDao;
import com.nuite.dao.PeriodDao;
import com.nuite.dao.SaleShoesDetailDao;
import com.nuite.dao.ShoeDao;
import com.nuite.entity.PeriodEntity;
import com.nuite.entity.SaleShoesDetailEntity;
import com.nuite.entity.ShoeEntity;
import com.nuite.service.LargeScreenApiService;
import com.nuite.service.PeriodService;


@Service
public class LargeScreenApiServiceImpl implements LargeScreenApiService {

    @Autowired
    private LargeScreenApiDao largeScreenApiDao;

	@Autowired
	private PeriodService periodService;
	
    @Autowired
    private SaleShoesDetailDao saleShoesDetailDao;
    
	@Autowired
	private PeriodDao periodDao;
	
	@Autowired
	private ShoeDao shoeDao;
	
	
	
    /**
     * 获取从开始时间开始，全国的有效试穿次数
     */
	@Override
	public int getCountryValidTryTimes(Integer brandSeq, String startDate) {
		
		List<Integer> periodSeqList = periodService.getPeriodSeqsByBrandSeq(brandSeq);
		int total = 0;
		for(Integer periodSeq : periodSeqList) {
			total += largeScreenApiDao.getCountryTryTimesAfterStartDate("Valid", periodSeq, startDate);
		}
		return total;
	}

	
	
    /**
     * 获取从开始时间开始，全国的无效试穿次数
     */
	@Override
	public int getCountryInvalidTryTimes(Integer brandSeq, String startDate) {
		
		List<Integer> periodSeqList = periodService.getPeriodSeqsByBrandSeq(brandSeq);
		int total = 0;
		for(Integer periodSeq : periodSeqList) {
			total += largeScreenApiDao.getCountryTryTimesAfterStartDate("Invalid", periodSeq, startDate);
		}
		return total;
	}



	/**
	 * 查询全国某一天的销售额
	 */
	@Override
	public BigDecimal getCountryTotalSales(Date date) {
		Date startdate = date;
		Date endDate = DateUtils.addDateDays(date, 1);
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)") //注意：当计算decimal类型时，返回的null记为有结果集[null],list大小为1,而不是[]
		.where("SaleDate >= {0} AND SaleDate < {1} ", startdate, endDate);
		
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}



	/**
	 * 根据年份获取波次
	 */
	@Override
	public List<Object> getPeriodsByYear(Integer brandSeq, Integer year) {
		Wrapper<PeriodEntity> wrapper = new EntityWrapper<PeriodEntity>();
		wrapper.setSqlSelect("Seq AS seq").where("BrandSeq = {0}", brandSeq).like("Name", year + "");
		List<Object> list = periodDao.selectObjs(wrapper);
		return list;
	}



	/**
	 * 起止时间段内，指定波次和品类的鞋， 全国销售双数
	 */
	@Override
	public Integer getSaleNumByYearAndSx3code(Date startDate, Date endDate, List<Object> periods, String sx3Code) {
		
		//根据波次和品类，获取所有鞋子列表
		List<ShoeEntity> shoeEntityList = this.getShoesByPeriodsAndSX3(periods, sx3Code);
		List<String> goodsIdList = new ArrayList<String>();
		for(ShoeEntity shoeEntity : shoeEntityList) {
			goodsIdList.add(shoeEntity.getShoeID());
		}
		
		if(goodsIdList.size() > 0) {
			String goodsIds = "'" + Joiner.on("','").join(goodsIdList) + "'";
			Integer saleNum = largeScreenApiDao.getSaleNumByGoodsIdList(startDate, endDate, goodsIds);
			if(saleNum != null) {
				return saleNum;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	
	
	
	/**
	 * 起止时间段内，指定波次和品类的鞋， 全国进货双数
	 */
	@Override
	public Integer getTotalNumByYearAndSx3code(Date startDate, Date endDate, List<Object> periods, String sx3Code) {
		//根据波次和品类，获取所有鞋子列表
		List<ShoeEntity> shoeEntityList = this.getShoesByPeriodsAndSX3(periods, sx3Code);
		List<Integer> shoeSeqList = new ArrayList<Integer>();
		for(ShoeEntity shoeEntity : shoeEntityList) {
			shoeSeqList.add(shoeEntity.getSeq());
		}
		
		if(shoeSeqList.size() > 0) {
			String shoeSeqs = Joiner.on(",").join(shoeSeqList);
			Integer totalNum = largeScreenApiDao.getTotalNumByShoeSeqList(startDate, endDate, shoeSeqs);
			if(totalNum != null) {
				return totalNum;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
		
	}

	
	
	//根据波次序号和品类code,获取鞋子列表
	private List<ShoeEntity> getShoesByPeriodsAndSX3(List<Object> periods, String sx3Code) {
		Wrapper<ShoeEntity> wrapper = new EntityWrapper<ShoeEntity>();
		wrapper.in("PeriodSeq", periods).eq("SX3", sx3Code);
		return shoeDao.selectList(wrapper);
	}
	
	
	
	/**
	 * 查询全国某一天销售额
	 */
	@Override
	public BigDecimal getCountryOneDaySales(Date mostRecentDay) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)") //注意：当计算decimal类型时，返回的null记为有结果集[null],list大小为1,而不是[]
		.where("DateDiff(dd, {0}, SaleDate) = 0 ", mostRecentDay);
				
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	
	
	/**
	 * 查询全国某一年截止到某一日的销售额
	 */
	@Override
	public BigDecimal getCountryYearToDaySales(Date date) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)") //注意：当计算decimal类型时，返回的null记为有结果集[null],list大小为1,而不是[]
		.where("DateDiff(yyyy, {0}, SaleDate) = 0 AND DateDiff(dd, {1}, SaleDate) <= 0", date, date);
				
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	
	
	/**
	 * 查询全国某一年总销售额
	 */
	@Override
	public BigDecimal getCountryYearTotalSales(Date date) {
		Wrapper<SaleShoesDetailEntity> wrapper = new EntityWrapper<SaleShoesDetailEntity>();
		wrapper.setSqlSelect("SUM (SaleCount * RealPrice)") //注意：当计算decimal类型时，返回的null记为有结果集[null],list大小为1,而不是[]
		.where("DateDiff(yyyy, {0}, SaleDate) = 0 ", date);
				
		List<Object> list = saleShoesDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return (BigDecimal) list.get(0);
		} else {
			return BigDecimal.ZERO;
		}
	}

}
