package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.TryShoesDetailEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface TryShoesDetailService extends IService<TryShoesDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String, Object>> selectShopTop10(Date lastWeekToday);
}

