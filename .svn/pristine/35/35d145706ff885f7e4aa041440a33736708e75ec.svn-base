package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.dao.AnnouncementDao;
import com.nuite.dao.HomeCarouselDao;
import com.nuite.entity.AnnouncementEntity;
import com.nuite.entity.HomeCarouselEntity;
import com.nuite.service.ProductHomepageService;

@Service
public class ProductHomepageServiceImpl implements ProductHomepageService {
    
    @Autowired
    private HomeCarouselDao homeCarouselDao;
    
    @Autowired
    private AnnouncementDao announcementDao;
    
	
	/**
	 * 轮播图列表
	 */
	@Override
	public List<Map<String, Object>> getHomeCarouselList(Integer brandSeq){
		Wrapper<HomeCarouselEntity> wrapper = new EntityWrapper<HomeCarouselEntity>();
		wrapper.setSqlSelect("Seq AS seq, Brand_Seq AS brandSeq, Image AS image, Type AS type, Link_Seq AS linkSeq").where("Brand_Seq = {0}", brandSeq);
		return homeCarouselDao.selectMaps(wrapper);
	}
	
	
	
	/**
	 * 公告列表
	 */
	@Override
	public List<AnnouncementEntity> getAnnouncementByCompanySeq(Integer companySeq) {
		Wrapper<AnnouncementEntity> wrapper = new EntityWrapper<AnnouncementEntity>();
		wrapper.where("Company_Seq = {0} AND ExpirationTime >= GETDATE()", companySeq);
		return announcementDao.selectList(wrapper);
	}

    
}
