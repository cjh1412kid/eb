package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.ColorEntity;

import java.util.Map;


public interface ColorService extends IService<ColorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ColorEntity selectLocalColor(String colorCode, String colorName);
}

