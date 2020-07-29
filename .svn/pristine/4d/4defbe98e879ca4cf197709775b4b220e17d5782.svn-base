package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.ModuleEntity;


public interface ModuleService extends IService<ModuleEntity> {

	List<Object> getAllTopics();
	
	List<Map<String, Object>> getTopicAllModules(String topicName);

	List<ModuleEntity> getModulesBySeqs(List<Object> moduleSeqList);

}

