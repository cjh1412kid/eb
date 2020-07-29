package com.nuite.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.CompeteAnalysisDao;
import com.nuite.entity.CompeteAnalysisEntity;
import com.nuite.service.CompeteAnalysisService;


@Service
public class CompeteAnalysisServiceImpl extends ServiceImpl<CompeteAnalysisDao, CompeteAnalysisEntity> implements CompeteAnalysisService {
	
	@Autowired
	private CompeteAnalysisDao competeAnalysisDao;
	
	
	/**
	 * 新增或修改一条报表记录
	 */
	@Override
	public void addOrUpdateCompeteAnalysis(CompeteAnalysisEntity competeAnalysisEntity) {
		//判断是否存在
		CompeteAnalysisEntity competeAnalysisExist = new CompeteAnalysisEntity();
		competeAnalysisExist.setStoreBrandSeq(competeAnalysisEntity.getStoreBrandSeq());
		competeAnalysisExist.setYear(competeAnalysisEntity.getYear());
		competeAnalysisExist.setMonth(competeAnalysisEntity.getMonth());
		competeAnalysisExist.setWeek(competeAnalysisEntity.getWeek());
		competeAnalysisExist = competeAnalysisDao.selectOne(competeAnalysisExist);

		// 不存在，新增
		if (competeAnalysisExist == null) {
			competeAnalysisDao.insert(competeAnalysisEntity);
		} else { // 存在，修改
			competeAnalysisEntity.setSeq(competeAnalysisExist.getSeq());
			competeAnalysisDao.updateAllColumnById(competeAnalysisEntity);
		}
	}


	/**
	 * 查询门店某一周所有商场品牌竞争力的报表
	 */
	@Override
	public List<CompeteAnalysisEntity> getAllCompeteAnalysisOneWeek(Integer storeSeq, Integer year, Integer month, Integer week) {
		Wrapper<CompeteAnalysisEntity> wrapper = new EntityWrapper<CompeteAnalysisEntity>();
		wrapper.where("StoreSeq = {0} AND Year = {1} AND Month = {2} AND Week = {3}", storeSeq, year, month, week).orderBy("StoreNo ASC");
		return competeAnalysisDao.selectList(wrapper);
	}


	
	/**
	 * 获取某商场品牌上周竞争力分析
	 */
	@Override
	public CompeteAnalysisEntity getLastWeekCompeteAnalysis(Integer storeBrandSeq, Integer year, Integer month, Integer week) {
		String timeStr = year + month + week + "";
		CompeteAnalysisEntity competeAnalysisEntity = competeAnalysisDao.getLastWeekCompeteAnalysis(storeBrandSeq, timeStr);
		return competeAnalysisEntity;
	}


	
	/**
	 * 品牌某月某周前销售额总额
	 */
	@Override
	public BigDecimal getStoreBrandMonthTotalSale(Integer storeBrandSeq, Integer year, Integer month, Integer week) {
		Wrapper<CompeteAnalysisEntity> wrapper = new EntityWrapper<CompeteAnalysisEntity>();
		wrapper.setSqlSelect("SUM (WeekSale) AS monthSale")
		.where("StoreBrandSeq = {0} AND Year = {1} AND Month = {2} AND Week <= {3}", storeBrandSeq, year, month, week);
		List<Object> list = competeAnalysisDao.selectObjs(wrapper);
		if(list != null && list.size() > 0) {
			return (BigDecimal) list.get(0);
		} else {
			return null;
		}
		
	}
	
	

}
