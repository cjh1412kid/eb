package com.nuite.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.FestivalBackgroundEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import com.nuite.modules.sys.service.FestivalTemplateBackgroundService;
import com.nuite.modules.sys.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangchuang
 * @since 2019-02-19
 */
@RestController
@RequestMapping("/sys/ScreenBackground")
public class FestivalTemplateBackgroundController {

    @Autowired
    FestivalTemplateBackgroundService templateBackgroundService;
    @Autowired
    FestivalMaterialService materialService;

    @RequestMapping("/del/{seq}")
    public R delBackground(@PathVariable Integer seq) {
        templateBackgroundService.delete(new EntityWrapper<FestivalBackgroundEntity>()
                .eq("TemplateSeq", seq));
        return R.ok();
    }

    @PostMapping("/add")
    public R addNew(FestivalBackgroundEntity backgroundEntity) {
        FestivalBackgroundEntity bgEntity = templateBackgroundService.selectOne(
                new EntityWrapper<FestivalBackgroundEntity>()
                        .eq("TemplateSeq", backgroundEntity.getTemplateSeq()));

        if (bgEntity == null) {
            backgroundEntity.setCreatorSeq(ShiroUtils.getUserSeq());
            templateBackgroundService.insert(backgroundEntity);
        }else{
            //存在则判断是否相同
            if (backgroundEntity.getMaterialSeq() != bgEntity.getMaterialSeq()) {
                backgroundEntity.setCreatorSeq(ShiroUtils.getUserSeq());
                backgroundEntity.setSeq(bgEntity.getSeq());
                backgroundEntity.setInputTime(new Date());
                templateBackgroundService.updateById(backgroundEntity);
            }
        }
        return R.ok();
    }

    @GetMapping("/get/{seq}")
    public R select(@PathVariable Integer seq) {
        FestivalBackgroundEntity backgroundEntity = templateBackgroundService.selectByTemplateSeq(seq);
        return R.ok().result(backgroundEntity);
    }
}

