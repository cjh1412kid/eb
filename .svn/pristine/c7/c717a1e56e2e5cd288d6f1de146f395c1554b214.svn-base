package com.nuite.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.ShoesDataEntity;


public interface ShoesDataService extends IService<ShoesDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    ShoesDataEntity getOneByShoeSeq(Integer shoeSeq);

	List<Map<String, Object>> getShoeSizeNumStockSale(Integer areaType, Integer areaSeq, Integer shoeSeq);

}

