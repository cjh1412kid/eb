package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.PeriodDao;
import com.nuite.entity.PeriodEntity;
import com.nuite.service.PeriodService;


@Service
public class PeriodServiceImpl extends ServiceImpl<PeriodDao, PeriodEntity> implements PeriodService {

    @Autowired
    private PeriodDao periodDao;
    
    
	
	/**
	 * 根据品牌序号查询所有有效波次Map
	 */
	@Override
	public List<Map<String, Object>> getPeriodsByBrandSeq(Integer brandSeq) {
		Wrapper<PeriodEntity> wrapper = new EntityWrapper<PeriodEntity>();
		wrapper.setSqlSelect("Seq AS seq, Name AS name").where("BrandSeq = {0}", brandSeq).orderBy("Seq DESC");
		return periodDao.selectMaps(wrapper);
	}


	/**
	 * 根据品牌序号查询所有有效波次序号Seq
	 */
	@Override
	public List<Integer> getPeriodSeqsByBrandSeq(Integer brandSeq) {
		Wrapper<PeriodEntity> wrapper = new EntityWrapper<PeriodEntity>();
		wrapper.setSqlSelect("Seq AS seq").where("BrandSeq = {0}", brandSeq);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Integer> list =  (List<Integer>)(List)periodDao.selectObjs(wrapper);
		return list;
	}


	/**
	 * 2019产品版：获取xxxx年后的波次 
	 */
	@Override
	public List<PeriodEntity> getPeriodsByBrandSeqAfterYear(Integer brandSeq, Integer year) {
		Wrapper<PeriodEntity> wrapper = new EntityWrapper<PeriodEntity>();
		wrapper.where("BrandSeq = {0} AND Name >= {1}", brandSeq, year.toString()).orderBy("Seq DESC");
		return periodDao.selectList(wrapper);
	}

}
