package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.MyPagination;
import com.nuite.modules.sys.dao.FestivalMaterialDao;
import com.nuite.modules.sys.entity.FestivalMaterialEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Service
public class FestivalMaterialServiceImpl extends ServiceImpl<FestivalMaterialDao, FestivalMaterialEntity> implements FestivalMaterialService {

    @Override
    public List<FestivalMaterialEntity> selectUserMaterial(Integer userSeq) {
        List<FestivalMaterialEntity> materials = super.selectList(new EntityWrapper<FestivalMaterialEntity>()
                .eq("BelongTo", 1)
                .eq("CreatorSeq", userSeq)
                .orderBy("InputTime", false));
        return materials;
    }

    @Override
    public MyPagination<FestivalMaterialEntity> selectUserMaterial(Integer userSeq, Integer pageNumber, Integer pageSize) {
        Page<FestivalMaterialEntity> pages = new Page<>(pageNumber, pageSize, "InputTime", false);
        Page<FestivalMaterialEntity> materials = super.selectPage(pages, new EntityWrapper<FestivalMaterialEntity>()
                .eq("BelongTo", 1)
                .eq("CreatorSeq", userSeq));
        return new MyPagination<>(materials);
    }

    @Override
    public List<FestivalMaterialEntity> selectCompanyMaterial() {
        List<FestivalMaterialEntity> materials = super.selectList(new EntityWrapper<FestivalMaterialEntity>()
                .eq("BelongTo", 2)
                .orderBy("InputTime", false));
        return materials;
    }

    @Override
    public MyPagination<FestivalMaterialEntity> selectCompanyMaterial(Integer pageNumber, Integer pageSize) {
        Page<FestivalMaterialEntity> pages = new Page<>(pageNumber, pageSize);
        Page<FestivalMaterialEntity> materials = super.selectPage(pages, new EntityWrapper<FestivalMaterialEntity>()
                .eq("BelongTo", 2)
                .orderBy("InputTime", false));
        return new MyPagination<>(materials);
    }

    @Override
    public void addNewMaterial(FestivalMaterialEntity materialEntity) {
        super.insert(materialEntity);
    }

    @Override
    public void delMaterial(Integer seq) {
        super.deleteById(seq);
    }
}
