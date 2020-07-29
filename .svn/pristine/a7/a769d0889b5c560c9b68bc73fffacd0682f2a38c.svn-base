package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.ShoesMainpushEntity;


public interface ShoesMainpushService extends IService<ShoesMainpushEntity> {

	List<Map<String, Object>> getMainpushShoesList(Integer type, Integer areaSeq, Integer periodSeq, Integer start, Integer num);

	boolean isMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq);

	void setMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq, Integer periodSeq);

	void cancelMainpush(Integer areaType, Integer areaSeq, Integer shoeSeq);

}

