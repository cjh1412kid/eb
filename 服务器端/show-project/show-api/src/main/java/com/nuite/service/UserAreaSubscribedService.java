package com.nuite.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.UserAreaSubscribedEntity;


public interface UserAreaSubscribedService extends IService<UserAreaSubscribedEntity> {

	List<UserAreaSubscribedEntity> getUserAreaSubscribed(Integer userSeq);

}

