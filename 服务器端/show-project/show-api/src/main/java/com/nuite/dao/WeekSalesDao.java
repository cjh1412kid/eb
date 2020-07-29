package com.nuite.dao;

import com.nuite.entity.WeekSalesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-27 11:04:07
 */
@Mapper
public interface WeekSalesDao extends BaseMapper<WeekSalesEntity> {

	WeekSalesEntity getLastWeekSalesByParams(@Param("assistantSeq")Integer assistantSeq, 
											@Param("timeStr")String timeStr);
	
}
