package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.ShoeEntity;

import java.util.Map;


public interface ShoeService extends IService<ShoeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ShoeEntity selectByGoodId(String goodId);

    void updateLocalShoe(String shoeId, ShoeEntity shoeEntity);
}

