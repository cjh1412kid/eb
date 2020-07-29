package com.nuite.modules.sys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.AnnouncementDao;
import com.nuite.modules.sys.entity.AnnouncementEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementDao, AnnouncementEntity>
        implements AnnouncementService {

    @Override
    public PageUtils list(UserEntity userEntity, Map<String, Object> params) {
        Wrapper<AnnouncementEntity> wrapper = new EntityWrapper<AnnouncementEntity>();
        wrapper.where("Company_Seq = {0} AND Del = 0", 1).orderBy("InputTime DESC");
        Page<AnnouncementEntity> shoesPage = this.selectPage(new Query<AnnouncementEntity>(params).getPage(), wrapper);
        return new PageUtils(shoesPage);
    }
}
