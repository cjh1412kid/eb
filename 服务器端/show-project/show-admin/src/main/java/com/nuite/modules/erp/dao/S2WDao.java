package com.nuite.modules.erp.dao;

import com.nuite.modules.erp.entity.ErpStockEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface S2WDao {
    List<ErpStockEntity> selectList(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
