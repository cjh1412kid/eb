package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.FestivalTemplateEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
public interface FestivalTemplateService extends IService<FestivalTemplateEntity> {

    /**
     * 查询总部的所有模板
     * @return
     */
    List<FestivalTemplateEntity> selectTemplatesOfCompany();

    /**
     * 查询个人创建的所有模板
     * @return
     */
    List<FestivalTemplateEntity> selectTemplatesOfUser(Integer userSeq);

    /**
     * 添加新的模版记录
     * @param templateEntity
     */
    void addNew(FestivalTemplateEntity templateEntity);

    /**
     * 更新指定模版的信息
     * @param templateEntity
     */
    void updateInfo(FestivalTemplateEntity templateEntity);

}
