package com.nuite.modules.sys.controller;

import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.FestivalTemplateEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.FestivalTemplateService;
import com.nuite.modules.sys.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 节日动画模版控制器
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Slf4j
@RestController
@RequestMapping("/sys/festivalTemplate")
public class FestivalTemplateController {

    @Autowired
    private FestivalTemplateService festivalTemplateService;

    @RequestMapping("addNew")
    public R addTemplate(FestivalTemplateEntity templateEntity) {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        templateEntity.setCreatorSeq(userEntity.getSeq());
        festivalTemplateService.addNew(templateEntity);
        return R.ok();
    }

    @RequestMapping("update")
    public R updateTemplateInfo(FestivalTemplateEntity templateEntity) {
        templateEntity.setUpdateTime(new Date());
        festivalTemplateService.updateInfo(templateEntity);
        return R.ok();
    }

    @RequestMapping("delAll")
    public R deleteLogicTemplate(@RequestBody List<Integer> ids) {
        festivalTemplateService.deleteBatchIds(ids);
        return R.ok();
    }

    @RequestMapping("del")
    public R deleteTemplate(Integer seq) {
        festivalTemplateService.deleteById(seq);
        return R.ok();
    }

    @RequestMapping("selectUserTemplates")
    public R selectUserTemplates() {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        List<FestivalTemplateEntity> templates = festivalTemplateService.selectTemplatesOfUser(userEntity.getSeq());
        return R.ok().result(templates);
    }

    @RequestMapping("selectCompanyTemplates")
    public R selectCompanyTemplates() {
        List<FestivalTemplateEntity> templates = festivalTemplateService.selectTemplatesOfCompany();
        return R.ok().result(templates);
    }

}

