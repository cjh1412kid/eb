package com.nuite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ShoesDataDao;
import com.nuite.entity.ShoesDataEntity;
import com.nuite.service.ShoesDataService;


@Service
public class ShoesDataServiceImpl extends ServiceImpl<ShoesDataDao, ShoesDataEntity> implements ShoesDataService {

	
    @Autowired
    private ShoesDataDao shoesDataDao;
    
    
	
	/**
	 * 根据鞋子序号查询所有ShoesData实体
	 */
	@Override
	public List<ShoesDataEntity> getShoesDataListByShoeSeq(Integer shoeSeq) {
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.where("ShoeSeq = {0}", shoeSeq);
		return shoesDataDao.selectList(wrapper);
	}

}
