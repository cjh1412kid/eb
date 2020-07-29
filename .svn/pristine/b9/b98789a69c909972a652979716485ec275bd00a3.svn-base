package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.AreaDao;
import com.nuite.dao.ShopDao;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.service.ProductAreaShopPeriodService;


@Service
public class ProductAreaShopPeriodServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements ProductAreaShopPeriodService {
    
    @Autowired
    private AreaDao areaDao;
    
    @Autowired
    private ShopDao shopDao;
    
    
    
    /**
     * 根据品牌序号，查询所有大区Map
     * @return
     */
	@Override
	public List<Map<String, Object>> getAreasByBrandSeq(Integer brandSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.setSqlSelect("Seq AS areaSeq, AreaName AS areaName, 2 as areaType").where("BrandSeq = {0} AND ParentSeq = 0", brandSeq);
		List<Map<String, Object>> list = areaDao.selectMaps(wrapper);
		// 删除 '测试大区'
		for(Map<String, Object> map : list) {
			if(map.get("areaName").equals("测试大区")) {
				list.remove(map);
				break;
			}
		}
		
		return list;
	}
	
	
	
	/**
     * 根据大区序号，查询所有分公司Map
     * @return
	 */
	@Override
	public List<Map<String, Object>> getBranchOfficesByAreaSeq(Integer parentSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.setSqlSelect("Seq AS areaSeq, AreaName AS areaName, 3 as areaType").where("ParentSeq = {0}", parentSeq);
		List<Map<String, Object>> list = areaDao.selectMaps(wrapper);
		// 将'办事处'替换为'分公司'
		for(Map<String, Object> map : list) {
			if(map.get("areaName") != null) {
				map.put("areaName", map.get("areaName").toString().replace("办事处", "分公司"));
			}
		}
		
		return list;
	}
	
	
	
	/**
     * 根据分公司序号，查询门店Map
     * @return
	 */
	@Override
	public List<Map<String, Object>> getShopsByAreaSeq(Integer areaSeq) {
		Wrapper<ShopEntity> wrapper = new EntityWrapper<ShopEntity>();
		wrapper.setSqlSelect("Seq AS areaSeq, Name AS areaName, 4 as areaType").where("AreaSeq = {0}", areaSeq);
		return shopDao.selectMaps(wrapper);
	}
	
}
