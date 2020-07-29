package com.nuite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ModuleDao;
import com.nuite.entity.ModuleEntity;
import com.nuite.service.ModuleService;


@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleDao, ModuleEntity> implements ModuleService {
	
	@Autowired
    private ModuleDao moduleDao;
	
	
	
	
	/**
	 * 获取所有专题
	 */
	@Override
	public List<Object> getAllTopics() {
		Wrapper<ModuleEntity> wrapper = new EntityWrapper<ModuleEntity>();
		wrapper.setSqlSelect("TopicName").groupBy("TopicName,TopicSort").orderBy("TopicSort ASC");
		return moduleDao.selectObjs(wrapper);
	}
	
	
	
	/**
	 * 获取专题所有模块
	 */
	@Override
	public List<Map<String, Object>> getTopicAllModules(String topicName) {
		Wrapper<ModuleEntity> wrapper = new EntityWrapper<ModuleEntity>();
		wrapper.setSqlSelect("Seq AS moduleSeq, ModuleName AS moduleName").where("TopicName = {0}", topicName)
		.orderBy("ModuleSort ASC");
		return moduleDao.selectMaps(wrapper);
	}



	/**
	 * 根据seqs获取模块
	 */
	@Override
	public List<ModuleEntity> getModulesBySeqs(List<Object> moduleSeqList) {
		if(moduleSeqList == null || moduleSeqList.size() == 0) {
			return new ArrayList<ModuleEntity>();
		}
		Wrapper<ModuleEntity> wrapper = new EntityWrapper<ModuleEntity>();
		wrapper.in("Seq", moduleSeqList);
		return moduleDao.selectList(wrapper);
	}


}
