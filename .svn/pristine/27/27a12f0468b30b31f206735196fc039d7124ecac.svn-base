package com.nuite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.AnimationDao;
import com.nuite.dao.ShopAnimationControlDao;
import com.nuite.dao.ShopShowControlDao;
import com.nuite.entity.AnimationEntity;
import com.nuite.entity.ShopAnimationControlEntity;
import com.nuite.entity.ShopShowControlEntity;
import com.nuite.service.ShopShowControlService;


@Service
public class ShopShowControlServiceImpl extends ServiceImpl<ShopShowControlDao, ShopShowControlEntity> implements ShopShowControlService {

	
    @Autowired
    private AnimationDao animationDao;
	
    @Autowired
    private ShopAnimationControlDao shopAnimationControlDao;
    
    @Autowired
    private ShopShowControlDao shopShowControlDao;
    
    
	
	/**
	 * 获取所有动画
	 */
	@Override
	public List<AnimationEntity> getAllAnimations() {
		return animationDao.selectList(null);
	}




	/**
	 * 新增一条门店动画控制记录
	 */
	@Override
	public void addShopAnimationControl(ShopAnimationControlEntity shopAnimationControl) {
		shopAnimationControlDao.insertShopAnimationControl(shopAnimationControl);
	}




	/**
	 * 新增一条门店展示控制记录
	 */
	@Override
	public void addShopShowControl(ShopShowControlEntity shopShowControl) {
		shopShowControlDao.insertShopShowControl(shopShowControl);
	}




	/**
	 * 获取门店当前的动画控制
	 */
	@Override
	public ShopAnimationControlEntity getShopAnimationControl(Integer shopSeq) {
		Wrapper<ShopAnimationControlEntity> wrapper = new EntityWrapper<ShopAnimationControlEntity>();
		wrapper.setSqlSelect("Top 1 *").where("(',' + ShopSeqs + ',') LIKE '%,' + {0} + ',%' ", shopSeq.toString())
		.orderBy("InputTime DESC");
		List<ShopAnimationControlEntity> list = shopAnimationControlDao.selectList(wrapper);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}



	/**
	 * 根据seq获取动画
	 */
	@Override
	public AnimationEntity getAnimationBySeq(Integer animationSeq) {
		return animationDao.selectById(animationSeq);
	}




	/**
	 * 获取门店当前的展示控制
	 */
	@Override
	public ShopShowControlEntity getShopShowControlEntity(Integer shopSeq) {
		Wrapper<ShopShowControlEntity> wrapper = new EntityWrapper<ShopShowControlEntity>();
		wrapper.setSqlSelect("Top 1 *").where("(',' + ShopSeqs + ',') LIKE '%,' + {0} + ',%' ", shopSeq.toString())
		.orderBy("InputTime DESC");
		List<ShopShowControlEntity> list = shopShowControlDao.selectList(wrapper);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
