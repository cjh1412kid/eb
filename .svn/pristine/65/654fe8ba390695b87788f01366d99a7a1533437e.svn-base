package com.nuite.modules.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuite.modules.sys.dao.ShoeViewDao;
import com.nuite.modules.sys.entity.ShoeViewEntity;
import com.nuite.modules.sys.service.ExcelGoodsService;

@Service
public class QiangrenExcelGoodsServiceImpl implements ExcelGoodsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShoeViewDao shoeViewDao;
	
	@Override
	public ShoeViewEntity getShoeViewEntity(String shoeId) {
		ShoeViewEntity shoeViewEntity=new ShoeViewEntity();
		shoeViewEntity.setShoeID(shoeId);
		shoeViewEntity=	shoeViewDao.selectOne(shoeViewEntity);
		return shoeViewEntity;
	}
}
