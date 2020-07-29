package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.SeriesEntity;

import java.util.Map;


public interface SeriesService extends IService<SeriesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

