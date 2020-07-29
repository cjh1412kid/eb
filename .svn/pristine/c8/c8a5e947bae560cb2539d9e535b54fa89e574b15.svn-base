package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.SaleQuotaDao;
import com.nuite.entity.SaleQuotaEntity;
import com.nuite.service.SaleQuotaDistributeService;


@Service
public class SaleQuotaDistributeServiceImpl extends ServiceImpl<SaleQuotaDao, SaleQuotaEntity> implements SaleQuotaDistributeService {

	
    @Autowired
    private SaleQuotaDao saleQuotaDao;
	
    
    
    
    /**
     * 根据实体查询
     */
	@Override
	public SaleQuotaEntity getSaleQuotaByEntity(SaleQuotaEntity saleQuotaEntity) {
		return saleQuotaDao.selectOne(saleQuotaEntity);
	}




	/**
	 * 批量新增销售指标
	 */
	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void distributeSaleQuota(List<SaleQuotaEntity> saleQuotaList) {
		
		for(SaleQuotaEntity saleQuotaEntity : saleQuotaList){
			
			//判断是否已存在
			SaleQuotaEntity saleQuotaExist = new SaleQuotaEntity();
			saleQuotaExist.setBrandSeq(saleQuotaEntity.getBrandSeq());
			saleQuotaExist.setYear(saleQuotaEntity.getYear());
			saleQuotaExist.setMonth(saleQuotaEntity.getMonth());
			saleQuotaExist.setAreaType(saleQuotaEntity.getAreaType());
			saleQuotaExist.setAreaSeq(saleQuotaEntity.getAreaSeq());
			saleQuotaExist = saleQuotaDao.selectOne(saleQuotaExist);
			
			//不存在，新增
			if(saleQuotaExist == null) {
				saleQuotaDao.insertSaleQuota(saleQuotaEntity);
			} else { //存在，修改
				saleQuotaEntity.setSeq(saleQuotaExist.getSeq());
				saleQuotaDao.updateById(saleQuotaEntity);
			}
			
		}
		
	}


	
	/**
	 * 查询本品牌有销售指标的所有年份、月份
	 */
	@Override
	public List<Map<String, Object>> getHistoryYearAndMonthByBrandSeq(Integer brandSeq) {
		Wrapper<SaleQuotaEntity> wrapper = new EntityWrapper<SaleQuotaEntity>();
		wrapper.setSqlSelect("DISTINCT Year AS year, Month AS month").where("BrandSeq = {0}", brandSeq)
		.orderBy("Year DESC, Month DESC");
		List<Map<String, Object>> list = saleQuotaDao.selectMaps(wrapper);
		return list;
	}
	
	
	
}
