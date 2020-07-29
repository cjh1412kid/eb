package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.SizeEntity;

import java.util.Map;


public interface SizeService extends IService<SizeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SizeEntity selectLocalSize(String code, String shoeId);
}

