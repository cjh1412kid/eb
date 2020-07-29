package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nuite.modules.sys.entity.FestivalAnimateContentEntity;
import com.nuite.modules.sys.dao.FestivalAnimatecontentDao;
import com.nuite.modules.sys.service.FestivalAnimateContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.service.FestivalMaterialService;
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
public class FestivalAnimateContentServiceImpl
        extends ServiceImpl<FestivalAnimatecontentDao, FestivalAnimateContentEntity>
        implements FestivalAnimateContentService {

    @Autowired
    FestivalMaterialService materialService;

    @Override
    public List<FestivalAnimateContentEntity> selectByTemplateSeq(Integer seq) {
        List<FestivalAnimateContentEntity> animates = super.selectList(new EntityWrapper<FestivalAnimateContentEntity>()
                .eq("TemplateSeq", seq));
        for (FestivalAnimateContentEntity animate : animates) {
            animate.setMaterial(materialService.selectById(animate.getMaterialSeq()));
        }
        return animates;
    }

    @Override
    public FestivalAnimateContentEntity selectOne(Integer templateSeq, Integer materialSeq) {
        return super.selectOne(new EntityWrapper<FestivalAnimateContentEntity>()
                .eq("TemplateSeq", templateSeq)
                .eq("MaterialSeq", materialSeq));
    }

    @Override
    public void updateContentById(FestivalAnimateContentEntity animateContentEntity) {
        super.updateById(animateContentEntity);
    }

    @Override
    public void addNew(FestivalAnimateContentEntity animateContentEntity) {
        super.insert(animateContentEntity);
    }

    @Override
    public void del(Integer templateSeq) {
        super.delete(new EntityWrapper<FestivalAnimateContentEntity>()
                .eq("TemplateSeq", templateSeq));
    }
}
