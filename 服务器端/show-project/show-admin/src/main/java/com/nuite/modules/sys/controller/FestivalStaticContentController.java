package com.nuite.modules.sys.controller;

import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.FestivalStaticContentEntity;
import com.nuite.modules.sys.service.FestivalStaticContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 门店展示动画静态状态素材内容
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@RestController
@RequestMapping("/sys/festivalStaticContent")
public class FestivalStaticContentController {

    @Autowired
    FestivalStaticContentService staticContentService;

    @RequestMapping("selectByTemplateSeq/{seq}")
    public R selectByTemplateSeq(@PathVariable Integer seq) {
        List<FestivalStaticContentEntity> staticContentEntities = staticContentService.selectByTemplateSeq(seq);
        return R.ok().result(staticContentEntities);
    }

    @RequestMapping("add")
    public R addNew(@RequestBody List<FestivalStaticContentEntity> staticContentEntities) {

        if (staticContentEntities == null || staticContentEntities.size() == 0) {
            return R.error("上传静态素材设置异常，无数据");
        }
        //删除此模版之前的所有静态设置
        staticContentService.deleteBySeq(staticContentEntities.get(0).getTemplateSeq());
        //保存新的静态设置
        for (FestivalStaticContentEntity staticContentEntiy : staticContentEntities) {
            staticContentService.addNew(staticContentEntiy);
        }
        return R.ok();
    }

    @RequestMapping("delAll")
    public R del(Integer templateSeq) {
        staticContentService.deleteBySeq(templateSeq);
        return R.ok();
    }

}

