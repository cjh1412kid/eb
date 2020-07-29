package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.AreaEntity;

import java.util.Map;


public interface AreaService extends IService<AreaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    AreaEntity getAreaByName(String name);
}

