package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.FestivalStaticcontentDao;
import com.nuite.modules.sys.entity.FestivalStaticContentEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import com.nuite.modules.sys.service.FestivalStaticContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Service
public class FestivalStaticContentServiceImpl
        extends ServiceImpl<FestivalStaticcontentDao, FestivalStaticContentEntity>
        implements FestivalStaticContentService {

    @Autowired
    FestivalMaterialService materialService;

    @Override
    public List<FestivalStaticContentEntity> selectByTemplateSeq(Integer seq) {
        List<FestivalStaticContentEntity> staticContentEntities = super.selectList(new EntityWrapper<FestivalStaticContentEntity>()
                .eq("TemplateSeq", seq));
        for (FestivalStaticContentEntity staticContentEntity : staticContentEntities) {
            staticContentEntity.setMaterial(materialService.selectById(staticContentEntity.getMaterialSeq()));
        }
        return staticContentEntities;
    }

    @Override
    public void updateBySeq(FestivalStaticContentEntity staticContent) {
        super.updateById(staticContent);
    }

    @Override
    public void deleteBySeq(Integer templateSeq) {
        super.delete(new EntityWrapper<FestivalStaticContentEntity>()
                .eq("TemplateSeq", templateSeq));
    }

    @Override
    public void addNew(FestivalStaticContentEntity staticContentEntity) {
        super.insert(staticContentEntity);
    }
}
