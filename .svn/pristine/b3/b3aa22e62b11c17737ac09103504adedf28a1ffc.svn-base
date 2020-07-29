package com.nuite.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.entity.SaleQuotaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-10 18:26:53
 */
@Mapper
public interface SaleQuotaDao extends BaseMapper<SaleQuotaEntity> {
    List<SaleQuotaEntity> selectListContainAreaName(@Param(value = "year") int year,
                                                    @Param(value = "month") int month,
                                                    @Param(value = "brandSeq") Integer brandSeq,
                                                    @Param(value = "areaType") Integer areaType,
                                                    @Param(value = "seqList") List<Object> seqList);

    
    
	void insertSaleQuota(@Param("saleQuota")SaleQuotaEntity saleQuotaEntity);
	
	
}
