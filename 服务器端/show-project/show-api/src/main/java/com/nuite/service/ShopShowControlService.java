package com.nuite.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.AnimationEntity;
import com.nuite.entity.ShopAnimationControlEntity;
import com.nuite.entity.ShopShowControlEntity;


public interface ShopShowControlService extends IService<ShopShowControlEntity> {

	List<AnimationEntity> getAllAnimations();

	void addShopAnimationControl(ShopAnimationControlEntity shopAnimationControl);

	void addShopShowControl(ShopShowControlEntity shopShowControl);

	ShopAnimationControlEntity getShopAnimationControl(Integer shopSeq);

	AnimationEntity getAnimationBySeq(Integer animationSeq);

	ShopShowControlEntity getShopShowControlEntity(Integer seq);

}

