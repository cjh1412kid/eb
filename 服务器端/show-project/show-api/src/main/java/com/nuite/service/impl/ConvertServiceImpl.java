package com.nuite.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.nuite.dao.ConvertDao;
import com.nuite.dao.ShoeDao;
import com.nuite.entity.ShoeEntity;
import com.nuite.service.ConvertService;


@Service
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private ConvertDao convertDao;
    
    @Autowired
    private ShoeDao shoeDao;
    
    
    
	/**
	 * 获取 多个门店、 多个波次或某货号、 某一天内 有效试穿次数
	 * @param shopSeqList   门店序号
	 * @param periodSeqs  波次序号
	 * @param shoeIds  货号 （如果货号不为空，则波次序号无效，为货号对应波次）
	 * @param date 某一天
	 * @return
	 */
	@Override
	public int getShopsTryValidNumOneDay(List<Integer> shopSeqList, List<Integer> periodSeqs, List<String> shoeIds, Date date) {
		String shopSeqs = Joiner.on(",").join(shopSeqList);
		int total = 0;
		
		// 货号不为空，查询多个门店  某一天 某一货号 试穿次数
		if(shoeIds != null && shoeIds.size() > 0) {
			
			for(String shoeId : shoeIds) {
				//1.查询货号对应的波次，确定要查询的分表
				ShoeEntity shoeEntity = new ShoeEntity();
				shoeEntity.setShoeID(shoeId);
				Integer periodSeq = shoeDao.selectOne(shoeEntity).getPeriodSeq();
				//2.查询对应 _Valid_periodSeq分表 多个门店 某一天 某一货号 试穿次数
				total += convertDao.getShopsShoeIdTryNumOneDay("Valid", periodSeq, shopSeqs, shoeId, date);
			}
			
		} else {  // 否则按波次查询各分表 多个门店 某一天 总试穿次数
			
			for(Integer periodSeq : periodSeqs) {
				// 查询对应 _Valid_periodSeq分表 多个门店 某一天 试穿次数
				total += convertDao.getShopsTryNumOneDay("Valid", periodSeq, shopSeqs, date);
			}
			
		}
		return total;
	}
	
	
	
	
	
	/**
	 * 获取 某一区域、 多个波次或某货号、 某一天内 销量
	 * @param type  区域类型
	 * @param areaSeqList  区域seq
	 * @param periodSeqs  波次序号
	 * @param shoeIds  号 （如果货号不为空，则波次序号无效，为货号对应波次）
	 * @param date  某一天
	 * @return
	 */
	@Override
	public int getAreasSaleNumOneDay(Integer type, List<Integer> areaSeqList, List<Integer> periodSeqs, List<String> shoeIds, Date date) {
		String areaSeqs = Joiner.on(",").join(areaSeqList);
		int saleCount = 0;

		// 货号不为空，查询 区域内、 某一天 、某一货号 销量
		if(shoeIds != null && shoeIds.size() > 0) {
			
			for(String shoeId : shoeIds) {
				//1.查询货号对应的波次，确定要查询的分表
				ShoeEntity shoeEntity = new ShoeEntity();
				shoeEntity.setShoeID(shoeId);
				Integer periodSeq = shoeDao.selectOne(shoeEntity).getPeriodSeq();
				//2.查询对应 _Valid_periodSeq分表 区域内 某一天 某一货号 销量
				Integer count = convertDao.getAreasShoeIdSaleNumOneDay(periodSeq, type, areaSeqs, shoeId, date);
				if(count != null) {
					saleCount += count;
				}
			}
			
		} else {  // 否则按波次查询各分表 区域内、 某一天 总销量
			
			for(Integer periodSeq : periodSeqs) {
				//查询 _波次分表 区域内 某一天 销量
				Integer count = convertDao.getAreasSaleNumOneDay(periodSeq, type, areaSeqs, date);
				if(count != null) {
					saleCount += count;
				}
			}
			
		}
		
		return saleCount;
		
	}
	
}
