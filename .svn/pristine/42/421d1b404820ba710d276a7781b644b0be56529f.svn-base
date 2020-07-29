package com.nuite.modules.erp.dao;

import com.nuite.modules.erp.entity.ShoeDetail;
import com.nuite.modules.erp.entity.ShoeProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ShoesPropertyDao {
    List<ShoeProperty> selectShoesProperties(@Param("shoesId") String shoesId);

    List<ShoeDetail> selectList(@Param("startTime") Date startTime);
}
