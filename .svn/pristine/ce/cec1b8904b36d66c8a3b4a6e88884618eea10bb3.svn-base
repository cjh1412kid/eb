package com.nuite.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.SizeAllotTemplateDetailEntity;

/**
 * 补单配码模板详情
 */
@Mapper
public interface SizeAllotTemplateDetailDao extends BaseMapper<SizeAllotTemplateDetailEntity> {
	
	List<String> getAllSizes();
	
	SizeAllotTemplateDetailEntity getSizeAllotTemplateDetailBySeqAndSize(@Param("sizeAllotTemplateSeq")Integer sizeAllotTemplateSeq,@Param("size")String size);
}
