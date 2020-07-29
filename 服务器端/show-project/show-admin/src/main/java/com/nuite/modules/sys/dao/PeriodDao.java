package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.PeriodEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-12 13:46:50
 */
@Mapper
public interface PeriodDao extends BaseMapper<PeriodEntity> {

	void insertWithSeq(PeriodEntity periodEntity);

	List<Map<String, Object>> getPeriodsByBrandSeq(@Param("brandSeq")Integer brandSeq,@Param("seasons")List<String> seasons);
}
