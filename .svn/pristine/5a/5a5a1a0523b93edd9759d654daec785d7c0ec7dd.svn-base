package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ShoesMainpushDao;
import com.nuite.entity.ShoesMainpushEntity;
import com.nuite.service.ShoesMainpushService;


@Service
public class ShoesMainpushServiceImpl extends ServiceImpl<ShoesMainpushDao, ShoesMainpushEntity> implements ShoesMainpushService {
	
	@Autowired
	private ShoesMainpushDao shoesMainpushDao;
	
	
	/**
	 * 查询主推鞋子列表
	 */
	@Override
	public List<Map<String, Object>> getMainpushShoesList(Integer type, Integer areaSeq, Integer periodSeq, Integer start, Integer num) {
		Wrapper<ShoesMainpushEntity> wrapper = new EntityWrapper<ShoesMainpushEntity>();
		wrapper.setSqlSelect("Shoes_Seq AS shoesSeq").where("AreaType = {0} AND AreaSeq = {1}", type, areaSeq);
		if(periodSeq != null && periodSeq != 0) {
			wrapper.where("Period_Seq = {0}", periodSeq);
		}
		List<Map<String, Object>> list = shoesMainpushDao.selectMapsPage(new RowBounds(start - 1, num), wrapper);
		return list;
	}


	/**
	 * 查询鞋子是否已经主推过
	 */
	@Override
	public boolean isMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		ShoesMainpushEntity shoesMainpushEntity = new ShoesMainpushEntity();
		shoesMainpushEntity.setAreaSeq(areaSeq);
		shoesMainpushEntity.setAreaType(areaType);
		shoesMainpushEntity.setShoesSeq(shoeSeq);
		shoesMainpushEntity = shoesMainpushDao.selectOne(shoesMainpushEntity);
		if(shoesMainpushEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * 设置主推
	 */
	@Override
	public void setMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer periodSeq) {
		ShoesMainpushEntity shoesMainpushEntity = new ShoesMainpushEntity();
		shoesMainpushEntity.setAreaSeq(areaSeq);
		shoesMainpushEntity.setAreaType(areaType);
		shoesMainpushEntity.setShoesSeq(shoeSeq);
		shoesMainpushEntity.setPeriodSeq(periodSeq);
		shoesMainpushDao.insert(shoesMainpushEntity);
	}


	/**
	 * 取消主推
	 */
	@Override
	public void cancelMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq) {
		ShoesMainpushEntity shoesMainpushEntity = new ShoesMainpushEntity();
		shoesMainpushEntity.setAreaSeq(areaSeq);
		shoesMainpushEntity.setAreaType(areaType);
		shoesMainpushEntity.setShoesSeq(shoeSeq);
		shoesMainpushEntity = shoesMainpushDao.selectOne(shoesMainpushEntity);
		if(shoesMainpushEntity != null) {
			shoesMainpushDao.deleteById(shoesMainpushEntity.getSeq());
		}
	}

}
