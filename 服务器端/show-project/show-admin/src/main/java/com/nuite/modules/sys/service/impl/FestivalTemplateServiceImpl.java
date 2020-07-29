package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.modules.sys.dao.FestivalTemplateDao;
import com.nuite.modules.sys.entity.FestivalTemplateEntity;
import com.nuite.modules.sys.entity.ShopAnimationControlEntity;
import com.nuite.modules.sys.service.FestivalTemplateService;
import com.nuite.modules.sys.service.ShopAnimationControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class FestivalTemplateServiceImpl extends ServiceImpl<FestivalTemplateDao, FestivalTemplateEntity> implements FestivalTemplateService {

    @Autowired
    ShopAnimationControlService shopAnimationControlService;

    @Override
    public List<FestivalTemplateEntity> selectTemplatesOfCompany() {
        List<FestivalTemplateEntity> templates = super.selectList(new EntityWrapper<FestivalTemplateEntity>().eq("BelongTo", 2));
        List<FestivalTemplateEntity> festivalTemplateEntities = setTemplateValidFlag(templates);
        return festivalTemplateEntities;
    }

    @Override
    public List<FestivalTemplateEntity> selectTemplatesOfUser(Integer userSeq) {
        List<FestivalTemplateEntity> templates = super.selectList(new EntityWrapper<FestivalTemplateEntity>()
                .eq("BelongTo", 1).eq("CreatorSeq", userSeq));
        List<FestivalTemplateEntity> festivalTemplateEntities = setTemplateValidFlag(templates);
        return festivalTemplateEntities;
    }

    /**
     * 给查询到的模版集合设置模版有效标记
     *
     * @param templates
     * @return
     */
    private List<FestivalTemplateEntity> setTemplateValidFlag(List<FestivalTemplateEntity> templates) {

        if (templates.isEmpty()) return templates;

        //获取所有模版的seq集合
        List<Integer> ids = new ArrayList<>();
        for (FestivalTemplateEntity template : templates) {
            if (template.getNodeType() == 1) {
                ids.add(template.getSeq());
            }
        }

        //有效期范围内
        List<ShopAnimationControlEntity> shopAnimationControlEntities = shopAnimationControlService.selectList(new EntityWrapper<ShopAnimationControlEntity>()
                .setSqlSelect("AnimationSeq")
                .where("GETDATE() between SValidDate and EValidDate")
                .in("AnimationSeq", ids)
                .orderBy("InputTime", false));
        if (!shopAnimationControlEntities.isEmpty()) {

            //设置时间最近的模版标记为生效中
            /*Integer validSeq = shopAnimationControlEntities.get(0).getAnimationSeq();
            for (FestivalTemplateEntity template : templates) {
                if (template.getSeq() == validSeq) {
                    template.setValidFlag(1);
                    System.out.println("template = " + template);
                    break;
                }
            }*/

            //设置当前时间在有效期内的所有模版 标记为生效中
            for (ShopAnimationControlEntity controlEntity : shopAnimationControlEntities) {
                for (FestivalTemplateEntity template : templates) {
                    if (template.getSeq() == controlEntity.getAnimationSeq()) {
                        template.setValidFlag(1);
                        break;
                    }
                }
            }

        }

        //待生效的
        List<ShopAnimationControlEntity> shopAnimationControlEntities2 = shopAnimationControlService.selectList(new EntityWrapper<ShopAnimationControlEntity>()
                .setSqlSelect("AnimationSeq")
                .where("GETDATE() < SValidDate")
                .in("AnimationSeq", ids)
                .orderBy("InputTime", false));
        if (!shopAnimationControlEntities2.isEmpty()) {
            //当前时间还未到生效时间的所有模版标记待生效
            for (ShopAnimationControlEntity shopAnimationControlEntity : shopAnimationControlEntities2) {
                for (FestivalTemplateEntity template : templates) {
                    if (shopAnimationControlEntity.getAnimationSeq() == template.getSeq()) {
                        template.setValidFlag(2);
                        break;
                    }
                }
            }
        }

        return templates;
    }

    @Override
    public void addNew(FestivalTemplateEntity templateEntity) {
        super.insertOrUpdate(templateEntity);
    }

    @Override
    public void updateInfo(FestivalTemplateEntity templateEntity) {
        super.updateById(templateEntity);
    }

}
