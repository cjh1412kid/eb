package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.CompanyDao;
import com.nuite.modules.sys.entity.CompanyEntity;
import com.nuite.modules.sys.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, CompanyEntity> implements CompanyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CompanyEntity> page = this.selectPage(
                new Query<CompanyEntity>(params).getPage(),
                new EntityWrapper<CompanyEntity>()
        );

        return new PageUtils(page);
    }

}
