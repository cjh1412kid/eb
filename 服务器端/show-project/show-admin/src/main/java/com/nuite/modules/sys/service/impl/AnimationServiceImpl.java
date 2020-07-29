package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.AnimationDao;
import com.nuite.modules.sys.entity.AnimationEntity;
import com.nuite.modules.sys.service.AnimationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AnimationServiceImpl extends ServiceImpl<AnimationDao, AnimationEntity> implements AnimationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AnimationEntity> page = this.selectPage(
                new Query<AnimationEntity>(params).getPage(),
                new EntityWrapper<AnimationEntity>()
        );

        return new PageUtils(page);
    }

}
