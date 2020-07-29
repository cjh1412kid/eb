package com.nuite.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.PatchDetailDao;
import com.nuite.modules.sys.entity.PatchDetailEntity;
import com.nuite.modules.sys.entity.PatchEntity;
import com.nuite.modules.sys.service.PatchDetailService;

@Service
public class PatchDetailServiceImpl extends ServiceImpl<PatchDetailDao, PatchDetailEntity> implements PatchDetailService {

	@Autowired
	private PatchDetailDao patchDetailDao;

	@Override
	public List<Map<String, Object>> getAllSizeAllotTemplate(Integer periodSeq, String sxValue, Integer week,Integer branchOfficeSeq) {
		return patchDetailDao.getAllSizeAllotTemplate(periodSeq, sxValue, week,branchOfficeSeq);
	}

	@Override
	public Integer getAllBoxCount(Integer shoesSeq) {
		return patchDetailDao.getAllBoxCount(shoesSeq);
	}

	@Override
	public Integer getAllData(Integer shoesSeq,Integer sizeAllotTemplateSeq,Integer branchOfficeSeq) {
		return patchDetailDao.getAllData(shoesSeq,sizeAllotTemplateSeq,branchOfficeSeq);
	}

	@Override
	public List<Map<String, Object>> getSizeAllotTemplates(Integer periodSeq, String sxValue, Integer week) {
		return patchDetailDao.getSizeAllotTemplates(periodSeq,sxValue,week);
	}

	@Override
	public List<Map<String, Object>> getAreasByWeek(Integer periodSeq, Integer week) {
		return patchDetailDao.getAreasByWeek(periodSeq,week);
	}

	@Override
	public List<Map<String, Object>> getAreasByWeekAndArea(Integer periodSeq, Integer week, Integer areaSeq) {
		return patchDetailDao.getAreasByWeekAndArea(periodSeq,week,areaSeq);
	}

	@Override
	public List<Map<String, Object>> getsXvalueByWeek(Integer periodSeq, Integer week) {
		return  patchDetailDao.getsXvalueByWeek(periodSeq,week);
	}

	@Override
	public List<Map<String, Object>> getAllShoeSeq(Integer periodSeq, String sxValue, Integer week) {
		return patchDetailDao.getAllShoeSeq(periodSeq,sxValue,week);
	}

	
}
