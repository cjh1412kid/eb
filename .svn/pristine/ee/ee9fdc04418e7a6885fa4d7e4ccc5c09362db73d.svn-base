package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.model.ShopAnimationTemplateModel;
import com.nuite.modules.sys.dao.ShopAnimationControlDao;
import com.nuite.modules.sys.entity.FestivalAnimateContentEntity;
import com.nuite.modules.sys.entity.FestivalBackgroundEntity;
import com.nuite.modules.sys.entity.FestivalStaticContentEntity;
import com.nuite.modules.sys.entity.ShopAnimationControlEntity;
import com.nuite.modules.sys.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopAnimationControlServiceImpl extends ServiceImpl<ShopAnimationControlDao, ShopAnimationControlEntity> implements ShopAnimationControlService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AnimationService animationService;

    @Autowired
    private ShopAnimationControlDao shopAnimationControlDao;

    @Autowired
    private FestivalStaticContentService staticContentService;

    @Autowired
    private FestivalAnimateContentService animateContentService;

    @Autowired
    FestivalTemplateBackgroundService templateBackgroundService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShopAnimationControlEntity> page = this.selectPage(
                new Query<ShopAnimationControlEntity>(params).getPage(),
                new EntityWrapper<ShopAnimationControlEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public String queryShopAnimate(Integer shopSeq) {
        if (shopSeq == null) return null;
        try {
            List<ShopAnimationControlEntity> controlList = this.selectList(new EntityWrapper<ShopAnimationControlEntity>()
                    .where("ShopSeqs like '%," + shopSeq + ",%' or ShopSeqs like '" + shopSeq + ",%' or ShopSeqs like '%," + shopSeq + "'")
                    .eq("Del", 0)
                    .orderBy("InputTime", false));

            if (controlList.size() > 0) {
                Integer animateSeq = controlList.get(0).getAnimationSeq();
                if (animateSeq != null) {
                    return animationService.selectById(animateSeq).getComponentName();
                }
            }
        } catch (Exception e) {
            logger.error("query error", e);
        }
        return null;
    }

    @Override
    public ShopAnimationTemplateModel queryShopAnimateTemplate(Integer shopSeq) {
        ShopAnimationControlEntity controlEntity = queryAnimationByShopSeq(shopSeq);
        if (controlEntity == null || controlEntity.getAnimationSeq() == null) return null;
        ShopAnimationTemplateModel shopAnimationTemplateModel = new ShopAnimationTemplateModel();
        //获取静态素材配置
        List<FestivalStaticContentEntity> festivalStaticContentEntities = staticContentService.selectByTemplateSeq(controlEntity.getAnimationSeq());
        shopAnimationTemplateModel.setStaticContentEntities(festivalStaticContentEntities);
        //获取动态素材配置
        List<FestivalAnimateContentEntity> festivalAnimateContentEntities = animateContentService.selectByTemplateSeq(controlEntity.getAnimationSeq());
        shopAnimationTemplateModel.setAnimateContentEntities(festivalAnimateContentEntities);
        //获取背景图信息
        FestivalBackgroundEntity backgroundEntity = templateBackgroundService.selectByTemplateSeq(controlEntity.getAnimationSeq());
        shopAnimationTemplateModel.setBackgroundEntity(backgroundEntity);

        return shopAnimationTemplateModel;
    }

    @Override
    public Integer findMaxSeq() {
        return shopAnimationControlDao.findMaxSeq();
    }

    @Override
    public ShopAnimationControlEntity queryAnimationByShopSeq(Integer shopSeq) {
        if (shopSeq == null) return null;
        try {
            List<ShopAnimationControlEntity> controlList = super.selectList(new EntityWrapper<ShopAnimationControlEntity>()
                    .where("GETDATE() between SValidDate and EValidDate")
                    .andNew("ShopSeqs ='" + shopSeq + "' or ShopSeqs like '%," + shopSeq + ",%' or ShopSeqs like '%," + shopSeq + "' or ShopSeqs like '" + shopSeq + ",%'")
                    .orderBy("InputTime", false));

            if (controlList.size() > 0) {
                return controlList.get(0);
            }
        } catch (Exception e) {
            logger.error("query error", e);
            return null;
        }
        return null;
    }

    @Override
    public ShopAnimationControlEntity queryByTemplateSeqBeforeValidTime(Integer templateSeq) {
        List<ShopAnimationControlEntity> controlList = super.selectList(new EntityWrapper<ShopAnimationControlEntity>()
                .eq("AnimationSeq", templateSeq)
                .andNew("GETDATE()<= EValidDate")
                .orderBy("InputTime", false));

        if (controlList.size() > 0) {
            return controlList.get(0);
        }
        return null;
    }

    @Override
    public void deleteLogicAllByTemplateSeq(Integer templateSeq) {
        super.delete(new EntityWrapper<ShopAnimationControlEntity>()
                .eq("AnimationSeq", templateSeq));
    }
}
