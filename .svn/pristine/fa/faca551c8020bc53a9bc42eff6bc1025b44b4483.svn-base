package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.SXDao;
import com.nuite.modules.sys.dao.SXOptionDao;
import com.nuite.modules.sys.entity.SXEntity;
import com.nuite.modules.sys.entity.SXOptionEntity;
import com.nuite.modules.sys.service.SXOptionService;
import com.nuite.modules.sys.service.SXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class SXOptionServiceImpl extends ServiceImpl<SXOptionDao, SXOptionEntity> implements SXOptionService {

    @Autowired
    private SXService sxService;

    @Autowired
    private SXDao sxDao;

    @Autowired
    private SXOptionDao sxOptionDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SXOptionEntity> page = this.selectPage(
                new Query<SXOptionEntity>(params).getPage(),
                new EntityWrapper<SXOptionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SXOptionEntity selectLocalSXOption(String name, String code, int i) {
        SXEntity sxEntity = sxService.selectOne(new EntityWrapper<SXEntity>()
                .eq("BrandSeq", 1)
                .eq("SXID", "SX" + i)
                .eq("Del", 0));
        if (sxEntity == null) {
        	//目前只手工维护了10个用到的属性，此处的插入新属性其实无效，所以也没有解析SXName
            sxEntity = new SXEntity();
            sxEntity.setBrandSeq(1);
            sxEntity.setSXID("SX" + i);
            sxEntity.setInputTime(new Date());
            sxEntity.setVisible(1);
            sxDao.insertWithSeq(sxEntity);
        }

        Integer sxSeq = sxEntity.getSeq();

        SXOptionEntity sxOptionEntity = this.selectOne(new EntityWrapper<SXOptionEntity>()
                .eq("SXSeq", sxSeq)
                .eq("Code", code)
                .eq("Del", 0));
        if (sxOptionEntity == null) {
            sxOptionEntity = new SXOptionEntity();
            sxOptionEntity.setCode(code);
            sxOptionEntity.setValue(name);
            sxOptionEntity.setSXSeq(sxSeq);
            sxOptionEntity.setInputTime(new Date());
            sxOptionEntity.setDel(0);
            sxOptionDao.insertWithSeq(sxOptionEntity);
        }
        return sxOptionEntity;
    }

}
