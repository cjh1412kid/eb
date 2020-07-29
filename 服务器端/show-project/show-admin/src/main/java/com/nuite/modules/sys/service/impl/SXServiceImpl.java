package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SXDao;
import com.nuite.modules.sys.entity.SXEntity;
import com.nuite.modules.sys.service.SXService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SXServiceImpl extends ServiceImpl<SXDao, SXEntity> implements SXService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SXEntity> page = this.selectPage(
                new Query<SXEntity>(params).getPage(),
                new EntityWrapper<SXEntity>()
        );

        return new PageUtils(page);
    }

}
