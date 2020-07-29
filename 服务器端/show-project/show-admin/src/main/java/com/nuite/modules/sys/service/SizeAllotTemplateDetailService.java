package com.nuite.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.SizeAllotTemplateDetailEntity;

public interface SizeAllotTemplateDetailService extends IService<SizeAllotTemplateDetailEntity>{

	SizeAllotTemplateDetailEntity insertOrUpdateOne(SizeAllotTemplateDetailEntity sizeAllotTemplateDetailEntity);

	List<String> getAllSizes();
	
	SizeAllotTemplateDetailEntity getSizeAllotTemplateDetailBySeqAndSize(Integer sizeAllotTemplateSeq,String size);
}
