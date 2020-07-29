package com.nuite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ShopDao;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.service.AreaService;
import com.nuite.service.ShopService;


@Service
public class ShopServiceImpl extends ServiceImpl<ShopDao, ShopEntity> implements ShopService {

    @Autowired
    private ShopDao shopDao;
	
    @Autowired
    private AreaService areaService;
    
    
	
	/**
     * 根据分公司序号，查询门店实体
     * @return
	 */
	@Override
	public List<ShopEntity> getShopsByAreaSeq(Integer areaSeq) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.where("AreaSeq = {0}", areaSeq);
		return shopDao.selectList(wrapper);
	}
	
	
	/**
     * 根据分公司序号，查询门店Map
     * @return [{seq: 19, name:"上海汇金虹桥店"},{seq: 28, name:"大连百盛西安路店"}]
	 */
	@Override
	public List<Map<String, Object>> getShopsMapByAreaSeq(Integer areaSeq) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.setSqlSelect("Seq AS seq, Name AS name").where("AreaSeq = {0}", areaSeq);
		return shopDao.selectMaps(wrapper);
	}

	
	
	/**
     * 根据分公司序号List，查询多个分公司内所有门店实体
     * @return
	 */
	@Override
	public List<ShopEntity> getShopsByAreaSeqList(List<Integer> areaSeqs) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.in("AreaSeq", areaSeqs);
		return shopDao.selectList(wrapper);
	}
	
	
	
	/**
     * 根据分公司序号List，查询多个分公司内所有门店Map
     * @return [{seq: 19, name:"上海汇金虹桥店"},{seq: 28, name:"大连百盛西安路店"}]
	 */
	@Override
	public List<Map<String, Object>> getShopsMapByAreaSeqList(List<Integer> areaSeqs) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.setSqlSelect("Seq AS seq, Name AS name").in("AreaSeq", areaSeqs);
		return shopDao.selectMaps(wrapper);
	}


	
    /**
     * 根据品牌序号，查询品牌的所有门店实体
     */
    @Override
    public List<ShopEntity> getShopsByBrandSeq(Integer brandSeq) {
		//1.根据品牌序号查询所有分公司
		List<AreaEntity> areaEntityList = areaService.getAllSecondAreasByBrandSeq(brandSeq);
		  //分公司序号列表
		List<Integer> areaSeqList = new ArrayList<Integer>();
		for(AreaEntity areaEntity : areaEntityList) {
			areaSeqList.add(areaEntity.getSeq());
		}
		
		//2.根据分公司序号列表查询门店
		List<ShopEntity> shopEntityList = this.getShopsByAreaSeqList(areaSeqList);
		
		return shopEntityList;
    }
    
}
