package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.FestivalStaticContentEntity;

import java.util.List;

/**
 * <p>
 * 1个模版对应8个位置的静态图
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
public interface FestivalStaticContentService extends IService<FestivalStaticContentEntity> {

    /**
     * 根据模版seq查询所有静态图数据
     *
     * @param seq
     * @return
     */
    List<FestivalStaticContentEntity> selectByTemplateSeq(Integer seq);

    /**
     * 根据seq更新静态图数据
     *
     * @param staticContent
     */
    void updateBySeq(FestivalStaticContentEntity staticContent);

    /**
     * 删除模板相关联的静态图记录
     *
     * @param templateSeq
     */
    void deleteBySeq(Integer templateSeq);

    /**
     * 添加新的静态图记录
     *
     * @param staticContentEntity
     */
    void addNew(FestivalStaticContentEntity staticContentEntity);
}
