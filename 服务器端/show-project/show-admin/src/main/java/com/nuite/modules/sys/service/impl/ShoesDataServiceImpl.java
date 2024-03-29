package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.ShoesDataDao;
import com.nuite.modules.sys.entity.ShoesDataEntity;
import com.nuite.modules.sys.service.ShoesDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ShoesDataServiceImpl extends ServiceImpl<ShoesDataDao, ShoesDataEntity> implements ShoesDataService {

	@Autowired
	private ShoesDataDao shoesDataDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShoesDataEntity> page = this.selectPage(
                new Query<ShoesDataEntity>(params).getPage(),
                new EntityWrapper<ShoesDataEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public ShoesDataEntity getOneByShoeSeq(Integer shoeSeq) {
		EntityWrapper<ShoesDataEntity> wrapper=new EntityWrapper<ShoesDataEntity>();
		wrapper.where("ShoeSeq={0}", shoeSeq);
		List<ShoesDataEntity> shoesDataEntities=shoesDataDao.selectList(wrapper);
		if(shoesDataEntities!=null&&shoesDataEntities.size()>0) {
			return shoesDataEntities.get(0);
		}
		return null;
	}

	
	
	/**
	 * 获取鞋子全部库存数据List
	 */
	@Override
	public List<Map<String, Object>> getShoeSizeNumStockSale(Integer areaType, Integer areaSeq, Integer shoeSeq){
		EntityWrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("SizeSeq AS sizeSeq, SUM(Num) AS num, SUM(Stock) AS stock, SUM(Num) - SUM(Stock) AS saleNum");
		wrapper.where("ShoeSeq={0}", shoeSeq);
		
		//区域条件判断
		if(areaType == 1) { //全国
			
		} else if (areaType == 2) {  //大区
			wrapper.where("AreaSeq = {0}", areaSeq);
		} else if (areaType == 3) {  //分公司
			wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		} else if (areaType == 4) {  //门店
			wrapper.where("ShopSeq = {0}", areaSeq);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
		
		wrapper.groupBy("SizeSeq");
		
		return shoesDataDao.selectMaps(wrapper);
	}

}
