package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.model.ShopAnimationTemplateModel;
import com.nuite.modules.sys.entity.ShopAnimationControlEntity;

import java.util.Map;

public interface ShopAnimationControlService extends IService<ShopAnimationControlEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String queryShopAnimate(Integer shopSeq);

    /**
     * 门店展示端接口，获取展示动画
     *
     * @param shopSeq
     * @return
     */
    ShopAnimationTemplateModel queryShopAnimateTemplate(Integer shopSeq);

    /**
     * 查询店铺相关联动画模版，当前日期在有效时间范围内，取最近一个
     *
     * @param shopSeq
     * @return
     */
    ShopAnimationControlEntity queryAnimationByShopSeq(Integer shopSeq);

    /**
     * 查询结束时间前的指定模版控制信息
     *
     * @param templateSeq
     * @return
     */
    ShopAnimationControlEntity queryByTemplateSeqBeforeValidTime(Integer templateSeq);

    /**
     * 逻辑删除所有指定模版控制记录
     * @param templateSeq
     */
    void deleteLogicAllByTemplateSeq(Integer templateSeq);

    Integer findMaxSeq();
}

