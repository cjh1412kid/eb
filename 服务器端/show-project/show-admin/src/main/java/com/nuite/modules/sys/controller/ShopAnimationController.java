package com.nuite.modules.sys.controller;

import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.ShopAnimationControlEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.ShopAnimationControlService;
import com.nuite.modules.sys.service.ShopService;
import com.nuite.modules.sys.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 门店动画设置应用控制器
 * 用于设置模版应用门店，应用生效时间
 *
 * @Author: yangchuang
 * @Date: 2019/1/15 13:31
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("sys/shopAnimationControl")
public class ShopAnimationController {

    @Autowired
    ShopAnimationControlService shopAnimationControlService;

    @Autowired
    ShopService shopService;

    @RequestMapping("query/{templateSeq}")
    public R queryByTemplateSeqAtValidTime(@PathVariable(value = "templateSeq", required = true) Integer templateSeq) {
        ShopAnimationControlEntity controlEntity = shopAnimationControlService.queryByTemplateSeqBeforeValidTime(templateSeq);
        return R.ok().result(controlEntity);
    }

    @RequestMapping("del/{templateSeq}")
    public R deleteLogicAllByTemplateSeq(@PathVariable(value = "templateSeq", required = true) Integer templateSeq) {
        shopAnimationControlService.deleteLogicAllByTemplateSeq(templateSeq);
        return R.ok();
    }

    @RequestMapping("addNew")
    public R addNewShopAnimationControl(ShopAnimationControlEntity shopAnimationControlEntity) {

        Integer maxSeq = shopAnimationControlService.findMaxSeq();
        if (maxSeq == null) {
            return R.error("查询异常");
        }

        UserEntity userEntity = ShiroUtils.getUserEntity();
        shopAnimationControlEntity.setUserSeq(userEntity.getSeq());
        shopAnimationControlEntity.setDel(0);
        shopAnimationControlEntity.setInputTime(new Date());
        shopAnimationControlEntity.setSeq(maxSeq + 1);

        shopAnimationControlService.insert(shopAnimationControlEntity);

        return R.ok();
    }
}
