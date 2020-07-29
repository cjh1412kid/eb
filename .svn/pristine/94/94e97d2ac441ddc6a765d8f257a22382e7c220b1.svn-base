package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.AreaDao;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.service.AreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AreaEntity> page = this.selectPage(
                new Query<AreaEntity>(params).getPage(),
                new EntityWrapper<AreaEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public AreaEntity getAreaByName(String name) {
		AreaEntity areaEntity=new AreaEntity();
		areaEntity.setAreaName(name);
		return areaDao.selectOne(areaEntity);
	}

}
