package com.nuite.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.SizeAllotTemplateDetailDao;
import com.nuite.modules.sys.entity.SizeAllotTemplateDetailEntity;
import com.nuite.modules.sys.service.SizeAllotTemplateDetailService;

@Service
public class SizeAllotTemplateDetailServiceImpl extends ServiceImpl<SizeAllotTemplateDetailDao, SizeAllotTemplateDetailEntity> implements SizeAllotTemplateDetailService {

	@Autowired
	private SizeAllotTemplateDetailDao sizeAllotTemplateDetailDao;
	
	
	@Override
	public SizeAllotTemplateDetailEntity insertOrUpdateOne(
			SizeAllotTemplateDetailEntity sizeAllotTemplateDetailEntity) {
		SizeAllotTemplateDetailEntity sizeAllotTemplateDetailEntity2=new SizeAllotTemplateDetailEntity();
		sizeAllotTemplateDetailEntity2.setSizeAllotTemplateSeq(sizeAllotTemplateDetailEntity.getSizeAllotTemplateSeq());
		sizeAllotTemplateDetailEntity2.setSize(sizeAllotTemplateDetailEntity.getSize());
		sizeAllotTemplateDetailEntity2=	sizeAllotTemplateDetailDao.selectOne(sizeAllotTemplateDetailEntity2);
		if(sizeAllotTemplateDetailEntity2==null) {
			sizeAllotTemplateDetailDao.insert(sizeAllotTemplateDetailEntity);
			return sizeAllotTemplateDetailEntity;
		}else {
			sizeAllotTemplateDetailEntity2.setPer(sizeAllotTemplateDetailEntity.getPer());
			sizeAllotTemplateDetailDao.updateById(sizeAllotTemplateDetailEntity2);
			return sizeAllotTemplateDetailEntity2;
		}
	}


	@Override
	public List<String> getAllSizes() {
		return sizeAllotTemplateDetailDao.getAllSizes();
	}


	@Override
	public SizeAllotTemplateDetailEntity getSizeAllotTemplateDetailBySeqAndSize(Integer sizeAllotTemplateSeq,String size) {
		return sizeAllotTemplateDetailDao.getSizeAllotTemplateDetailBySeqAndSize(sizeAllotTemplateSeq, size);
	}

}
