package com.nuite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.UserAreaSubscribedDao;
import com.nuite.dao.UserModuleSubscribedDao;
import com.nuite.entity.UserAreaSubscribedEntity;
import com.nuite.entity.UserModuleSubscribedEntity;
import com.nuite.service.UserModuleSubscribedService;


@Service
public class UserModuleSubscribedServiceImpl extends ServiceImpl<UserModuleSubscribedDao, UserModuleSubscribedEntity> implements UserModuleSubscribedService {

	
    @Autowired
    private UserModuleSubscribedDao userModuleSubscribedDao;
    
    @Autowired
    private UserAreaSubscribedDao userAreaSubscribedDao;
    
	
    /**
     * 获取用户区域已订阅模块seqs
     */
	@Override
	public List<Object> getUserAreaSubscribedModuleSeqs(Integer userSeq, Integer userAreaSubscribedSeq) {
		Wrapper<UserModuleSubscribedEntity> wrapper = new EntityWrapper<UserModuleSubscribedEntity>();
		wrapper.setSqlSelect("Module_Seq").where("User_Seq = {0} AND UserAreaSubscribed_Seq = {1}", userSeq, userAreaSubscribedSeq);
		return userModuleSubscribedDao.selectObjs(wrapper);
	}


	/**
	 * 删除用户区域已订阅模块
	 */
	@Override
	public void deleteUserAreaSubscribedModules(Integer userSeq, Integer userAreaSubscribedSeq, Integer moduleSeq) {
		Wrapper<UserModuleSubscribedEntity> wrapper = new EntityWrapper<UserModuleSubscribedEntity>();
		wrapper.where("User_Seq = {0} AND UserAreaSubscribed_Seq = {1} AND Module_Seq = {2}", userSeq, userAreaSubscribedSeq, moduleSeq);
		userModuleSubscribedDao.delete(wrapper);
	}


	/**
	 * 获取订阅了某个模块的用户模块订阅实体
	 */
	@Override
	public List<Map<String, Object>> getModuleSubscribedAreaByModuleSeq(Integer moduleSeq) {
		Wrapper<UserModuleSubscribedEntity> wrapper1 = new EntityWrapper<UserModuleSubscribedEntity>();
		wrapper1.setSqlSelect("UserAreaSubscribed_Seq").where("Module_Seq = {0}", moduleSeq);
		List<Object> userAreaSubscribedSeqs = userModuleSubscribedDao.selectObjs(wrapper1);
		if(userAreaSubscribedSeqs == null || userAreaSubscribedSeqs.size() == 0) {
			return new ArrayList<Map<String, Object>>();
		}
		Wrapper<UserAreaSubscribedEntity> wrapper2 = new EntityWrapper<UserAreaSubscribedEntity>();
		wrapper2.setSqlSelect("DISTINCT AreaType, AreaSeq").in("Seq", userAreaSubscribedSeqs);
		return userAreaSubscribedDao.selectMaps(wrapper2);
	}

}
