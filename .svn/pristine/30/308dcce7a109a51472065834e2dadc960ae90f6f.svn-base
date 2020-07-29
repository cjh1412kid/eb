package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.MyPagination;
import com.nuite.modules.sys.entity.FestivalMaterialEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
public interface FestivalMaterialService extends IService<FestivalMaterialEntity> {

    /**
     * 查询门店用户上传的素材
     *
     * @return
     */
    List<FestivalMaterialEntity> selectUserMaterial(Integer userSeq);

    /**
     * 分页查询门店用户上传的材料
     *
     * @param userSeq
     * @param pageNumber 第几页
     * @param pageSize   一页几条
     * @return
     */
    MyPagination<FestivalMaterialEntity> selectUserMaterial(Integer userSeq, Integer pageNumber, Integer pageSize);

    /**
     * 查询总部上传的素材
     *
     * @return
     */
    List<FestivalMaterialEntity> selectCompanyMaterial();

    /**
     * 分页查询总部上传的素材
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    MyPagination<FestivalMaterialEntity> selectCompanyMaterial(Integer pageNumber, Integer pageSize);

    /**
     * 添加一条新的素材记录
     *
     * @param materialEntity
     */
    void addNewMaterial(FestivalMaterialEntity materialEntity);

    /**
     * 彻底删除指定素材记录
     *
     * @param seq
     */
    void delMaterial(Integer seq);
}
