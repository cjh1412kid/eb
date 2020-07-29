package com.nuite.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.AssistantDao;
import com.nuite.entity.AssistantEntity;
import com.nuite.service.AssistantService;


@Service
public class AssistantServiceImpl extends ServiceImpl<AssistantDao, AssistantEntity> implements AssistantService {

	
    @Autowired
    private AssistantDao assistantDao;
	
	
	
	
	
	/**
	 * 获取店员列表
	 */
	@Override
	public List<AssistantEntity> getAssistantList(Integer shopSeq, Integer start, Integer num) {
		Wrapper<AssistantEntity> wrapper = new EntityWrapper<AssistantEntity>();
		wrapper.where("ShopSeq = {0}", shopSeq).orderBy("Seq DESC");
		return assistantDao.selectPage(new RowBounds(start - 1, num), wrapper);
	}


}
