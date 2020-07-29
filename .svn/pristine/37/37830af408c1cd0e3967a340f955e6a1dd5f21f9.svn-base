package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SeriesDao;
import com.nuite.modules.sys.entity.SeriesEntity;
import com.nuite.modules.sys.service.SeriesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SeriesServiceImpl extends ServiceImpl<SeriesDao, SeriesEntity> implements SeriesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SeriesEntity> page = this.selectPage(
                new Query<SeriesEntity>(params).getPage(),
                new EntityWrapper<SeriesEntity>()
        );

        return new PageUtils(page);
    }

}
