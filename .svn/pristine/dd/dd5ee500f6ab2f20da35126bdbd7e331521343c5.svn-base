package com.nuite.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.entity.SaleShoesDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-11-29 18:26:05
 */
@Mapper
public interface SaleShoesDetailDao extends BaseMapper<SaleShoesDetailEntity> {
    List<Map<String, Object>> getDetailSales(@Param("start") Date startDate, @Param("end") Date endDate);
}
