package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.FestivalAnimateContentEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
public interface FestivalAnimateContentService extends IService<FestivalAnimateContentEntity> {

    /**
     * 根据模版seq查询相关动画设置
     *
     * @param seq
     * @return
     */
    List<FestivalAnimateContentEntity> selectByTemplateSeq(Integer seq);

    /**
     * 根据模版seq和素材seq查找指定的动画内容
     * @param templateSeq
     * @param materialSeq
     * @return
     */
    FestivalAnimateContentEntity selectOne(Integer templateSeq,Integer materialSeq);

    /**
     * 更新动画内容
     *
     * @param animateContentEntity
     */
    void updateContentById(FestivalAnimateContentEntity animateContentEntity);

    /**
     * 添加新动画记录，与模版seq绑定
     *
     * @param animateContentEntity
     */
    void addNew(FestivalAnimateContentEntity animateContentEntity);

    /**
     * 删除指定模版的动画设置内容
     *
     * @param templateSeq
     */
    void del(Integer templateSeq);
}
