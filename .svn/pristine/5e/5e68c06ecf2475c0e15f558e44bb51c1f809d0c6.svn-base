package com.nuite.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.SizeAllotTemplateDetailEntity;
import com.nuite.modules.sys.entity.SizeAllotTemplateEntity;


public interface SizeAllotTemplateService extends IService<SizeAllotTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    SizeAllotTemplateEntity insertOrUpdateOne(SizeAllotTemplateEntity sizeAllotTemplateEntity);


	List<SizeAllotTemplateEntity> getTemplates(Integer nbCode, Integer perNum);
	
	List<SizeAllotTemplateDetailEntity> getSizeAllotTemplateDetailList(Integer sizeAllotTemplateSeq);
    
    List<Map<String, Object>> getAllSizeAllotTemplate();

	List<SizeAllotTemplateEntity> getAllTemplates();

}

