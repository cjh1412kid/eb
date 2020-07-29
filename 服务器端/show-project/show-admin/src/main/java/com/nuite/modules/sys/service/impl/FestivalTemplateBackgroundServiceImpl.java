package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.FestivalTemplateBackgroundDao;
import com.nuite.modules.sys.entity.FestivalBackgroundEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import com.nuite.modules.sys.service.FestivalTemplateBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangchuang
 * @since 2019-02-19
 */
@Service
public class FestivalTemplateBackgroundServiceImpl
        extends ServiceImpl<FestivalTemplateBackgroundDao, FestivalBackgroundEntity>
        implements FestivalTemplateBackgroundService {

    @Autowired
    FestivalMaterialService materialService;

    @Override
    public FestivalBackgroundEntity selectByTemplateSeq(Integer seq) {

        FestivalBackgroundEntity backgroundEntity = super.selectOne(
                new EntityWrapper<FestivalBackgroundEntity>()
                        .eq("TemplateSeq", seq));
        if (backgroundEntity != null) {
            backgroundEntity.setMaterial(materialService.selectById(backgroundEntity.getMaterialSeq()));
        }
        return backgroundEntity;
    }
}
