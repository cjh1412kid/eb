package com.nuite.modules.sys.controller;

import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.FestivalAnimateContentEntity;
import com.nuite.modules.sys.service.FestivalAnimateContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店展示动画动画素材设置
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */

@Slf4j
@RestController
@RequestMapping("/sys/animateContent")
public class FestivalAnimateContentController {

    @Autowired
    FestivalAnimateContentService animateContentService;

    @RequestMapping("selectByTemplateSeq/{seq}")
    public R selectByTemplateSeq(@PathVariable Integer seq) {
        List<FestivalAnimateContentEntity> animates = animateContentService.selectByTemplateSeq(seq);
        return R.ok().result(animates);
    }

    @RequestMapping("add")
    public R addNew(@RequestBody List<FestivalAnimateContentEntity> animates) {

        for (FestivalAnimateContentEntity animateContentEntity : animates) {
            if (animateContentEntity.getSeq() == null) {
                animateContentService.addNew(animateContentEntity);
            } else {
                //更新或删除
                if (animateContentEntity.getMaterialSeq() == null) {
                    animateContentService.deleteById(animateContentEntity.getSeq());
                } else {
                    animateContentEntity.setInputTime(new Date());
                    animateContentService.updateContentById(animateContentEntity);
                }
            }
        }
        return R.ok();
    }

    @RequestMapping("delAll")
    public R del(Integer templateSeq) {
        animateContentService.del(templateSeq);
        return R.ok();
    }

}

