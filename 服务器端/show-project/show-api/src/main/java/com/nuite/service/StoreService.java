package com.nuite.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.StoreBrandEntity;
import com.nuite.entity.StoreEntity;
import com.nuite.entity.UserEntity;


public interface StoreService extends IService<StoreEntity> {

	StoreBrandEntity getStoreBrandByShopSeq(Integer shopSeq);

	void addStore(StoreEntity storeEntity, UserEntity loginUser);

	List<StoreBrandEntity> getStoreBrandListByStoreSeq(Integer storeSeq);

}

