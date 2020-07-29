package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.AreaDao;
import com.nuite.entity.AreaEntity;
import com.nuite.service.AreaService;


@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {
    
    @Autowired
    private AreaDao areaDao;
    
    
    
    /**
     * 根据品牌序号，查询所有大区实体
     */
	@Override
	public List<AreaEntity> getFirstAreasByBrandSeq(Integer brandSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.where("BrandSeq = {0} AND ParentSeq = 0", brandSeq);
		List<AreaEntity> list = areaDao.selectList(wrapper);
		// 删除 '测试大区'
		for(AreaEntity areaEntity : list) {
			if(areaEntity.getAreaName().equals("测试大区")) {
				list.remove(areaEntity);
				break;
			}
		}
		
		return list;
	}
	
	
	
    /**
     * 根据品牌序号，查询所有大区Map
     * @return [{seq: 1, name:"东北大区"},{seq: 2, name:"西南大区"}]
     */
	@Override
	public List<Map<String, Object>> getFirstAreasMapByBrandSeq(Integer brandSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.setSqlSelect("Seq AS seq, AreaName AS name").where("BrandSeq = {0} AND ParentSeq = 0", brandSeq);
		List<Map<String, Object>> list = areaDao.selectMaps(wrapper);
		// 删除 '测试大区'
		for(Map<String, Object> map : list) {
			if(map.get("name").equals("测试大区")) {
				list.remove(map);
				break;
			}
		}
		
		return list;
	}
	
	
	
	/**
     * 根据大区序号，查询所有分公司实体
	 */
	@Override
	public List<AreaEntity> getSecondAreasByParentSeq(Integer parentSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.where("ParentSeq = {0}", parentSeq);
		List<AreaEntity> list = areaDao.selectList(wrapper);
		// 将'办事处'替换为'分公司'
		for(AreaEntity areaEntity : list) {
			if(areaEntity.getAreaName() != null) {
				areaEntity.setAreaName(areaEntity.getAreaName().replace("办事处", "分公司"));
			}
		}
		
		return list;
	}
	
	
	
	/**
     * 根据大区序号，查询所有分公司Map
     * @return [{seq: 8, name:"哈尔滨办事处"},{seq: 15, name:"大连办事处"}]
	 */
	@Override
	public List<Map<String, Object>> getSecondAreasMapByParentSeq(Integer parentSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.setSqlSelect("Seq AS seq, AreaName AS name").where("ParentSeq = {0}", parentSeq);
		List<Map<String, Object>> list = areaDao.selectMaps(wrapper);
		// 将'办事处'替换为'分公司'
		for(Map<String, Object> map : list) {
			if(map.get("name") != null) {
				map.put("name", map.get("name").toString().replace("办事处", "分公司"));
			}
		}
		
		return list;
		
	}
	
	

	
	/**
     * 根据大区序号List，查询多个大区内的所有分公司实体
	 */
	@Override
	public List<AreaEntity> getSecondAreasByParentSeqList(List<Integer> parentSeqs) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.in("ParentSeq", parentSeqs);
		List<AreaEntity> list = areaDao.selectList(wrapper);
		// 将'办事处'替换为'分公司'
		for(AreaEntity areaEntity : list) {
			if(areaEntity.getAreaName() != null) {
				areaEntity.setAreaName(areaEntity.getAreaName().replace("办事处", "分公司"));
			}
		}
		
		return list;
		
	}
	
	
	
	
	/**
     * 根据大区序号List，查询多个大区内的所有分公司Map
     * @return [{seq: 8, name:"哈尔滨办事处"},{seq: 15, name:"大连办事处"}]
	 */
	@Override
	public List<Map<String, Object>> getSecondAreasMapByParentSeqList(List<Integer> parentSeqs) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.setSqlSelect("Seq AS seq, AreaName AS name").in("ParentSeq", parentSeqs);
		List<Map<String, Object>> list = areaDao.selectMaps(wrapper);
		// 将'办事处'替换为'分公司'
		for(Map<String, Object> map : list) {
			if(map.get("name") != null) {
				map.put("name", map.get("name").toString().replace("办事处", "分公司"));
			}
		}
		
		return list;
	}
	
	
	

	

	
	/**
	 * 根据品牌序号，查询所有分公司实体
     * @return
	 */
	@Override
	public List<AreaEntity> getAllSecondAreasByBrandSeq(Integer brandSeq) {
		Wrapper<AreaEntity> wrapper = new EntityWrapper<AreaEntity>();
		wrapper.where("BrandSeq = {0} AND ParentSeq != 0", brandSeq);
		List<AreaEntity> list = areaDao.selectList(wrapper);
		// 将'办事处'替换为'分公司'
		for(AreaEntity areaEntity : list) {
			if(areaEntity.getAreaName() != null) {
				areaEntity.setAreaName(areaEntity.getAreaName().replace("办事处", "分公司"));
			}
		}
		
		return list;
		
	}
	
	
	

}
