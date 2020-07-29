package com.nuite.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.UserModuleSubscribedEntity;


public interface UserModuleSubscribedService extends IService<UserModuleSubscribedEntity> {

	List<Object> getUserAreaSubscribedModuleSeqs(Integer userSeq, Integer userAreaSubscribedSeq);

	void deleteUserAreaSubscribedModules(Integer userSeq, Integer userAreaSubscribedSeq, Integer moduleSeq);

	List<Map<String, Object>> getModuleSubscribedAreaByModuleSeq(Integer moduleSeq);

}

