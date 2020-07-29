package com.nuite.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.BrandDao;
import com.nuite.dao.StoreBrandDao;
import com.nuite.dao.StoreDao;
import com.nuite.dao.UserBrandDao;
import com.nuite.entity.BrandEntity;
import com.nuite.entity.StoreBrandEntity;
import com.nuite.entity.StoreEntity;
import com.nuite.entity.UserBrandEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.StoreService;


@Service
public class StoreServiceImpl extends ServiceImpl<StoreDao, StoreEntity> implements StoreService {

	@Autowired
	private StoreDao storeDao;
	
    @Autowired
    private StoreBrandDao storeBrandDao;
    
    @Autowired
    private UserBrandDao userBrandDao;
    
    @Autowired
    private BrandDao brandDao;
	
	
	
	/**
	 * 根据门店序号获取商场品牌实体
	 */
	@Override
	public StoreBrandEntity getStoreBrandByShopSeq(Integer shopSeq) {
		StoreBrandEntity storeBrandEntity = new StoreBrandEntity();
		storeBrandEntity.setShopSeq(shopSeq);
		storeBrandEntity.setDel(0);
		return storeBrandDao.selectOne(storeBrandEntity);
	}



	/**
	 * 新增商场、商场品牌关系
	 */
	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void addStore(StoreEntity storeEntity, UserEntity loginUser) {
		Date nowDate = new Date();
		storeEntity.setInputTime(nowDate);
		storeDao.insert(storeEntity);
		
		//要新增的商场品牌实体
		StoreBrandEntity storeBrandEntity = new StoreBrandEntity();
		storeBrandEntity.setStoreSeq(storeEntity.getSeq());
		
			//用户所属的品牌名称
		UserBrandEntity userBrandEntity = new UserBrandEntity();
		userBrandEntity.setUserSeq(loginUser.getSeq());
		userBrandEntity = userBrandDao.selectOne(userBrandEntity);
		BrandEntity brandEntity = brandDao.selectById(userBrandEntity.getBrandSeq());
		storeBrandEntity.setBrandName(brandEntity.getBrandName());
		
		storeBrandEntity.setShopSeq(loginUser.getUserAreaSeq());
		storeBrandEntity.setInputTime(nowDate);
		storeBrandEntity.setDel(0);
		storeBrandDao.insert(storeBrandEntity);
	
	}



	/**
	 * 根据商场序号获取商场所有品牌
	 */
	@Override
	public List<StoreBrandEntity> getStoreBrandListByStoreSeq(Integer storeSeq) {
		Wrapper<StoreBrandEntity> wrapper = new EntityWrapper<StoreBrandEntity>();
		wrapper.where("StoreSeq = {0}", storeSeq);
		return storeBrandDao.selectList(wrapper);
	}


}
