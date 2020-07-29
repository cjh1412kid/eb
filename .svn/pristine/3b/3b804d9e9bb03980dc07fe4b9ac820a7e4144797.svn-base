package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SizeDao;
import com.nuite.modules.sys.entity.SizeEntity;
import com.nuite.modules.sys.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class SizeServiceImpl extends ServiceImpl<SizeDao, SizeEntity> implements SizeService {

    @Autowired
    private SizeDao sizeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SizeEntity> page = this.selectPage(
                new Query<SizeEntity>(params).getPage(),
                new EntityWrapper<SizeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SizeEntity selectLocalSize(String code, String shoeId) {
        String categoryCode = shoeId.trim().substring(0, 1);
        String category;
        if ("A".equals(categoryCode) || "B".equals(categoryCode) || "C".equals(categoryCode)) {
            category = "女";
        } else if ("X".equals(categoryCode)) {
            category = "男";
        } else {
            category = categoryCode;
        }

        SizeEntity sizeEntity = selectOne(new EntityWrapper<SizeEntity>()
                .eq("Category", category)
                .eq("Name", code.trim()));
        if (sizeEntity == null) {
            sizeEntity = new SizeEntity();
            sizeEntity.setBrandSeq(1);
            sizeEntity.setCategory(category);
            sizeEntity.setCode(code);
            sizeEntity.setName(code);
            sizeEntity.setDel(0);
            sizeEntity.setInputTime(new Date());
            sizeDao.insertWithSeq(sizeEntity);
        }
        return sizeEntity;
    }

}
