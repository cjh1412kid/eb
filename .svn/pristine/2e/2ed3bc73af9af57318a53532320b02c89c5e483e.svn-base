package com.nuite.modules.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.DeadlineRaiseRateDao;
import com.nuite.modules.sys.dao.PeriodDao;
import com.nuite.modules.sys.entity.DeadlineRaiseRateEntity;
import com.nuite.modules.sys.entity.PeriodEntity;
import com.nuite.modules.sys.service.DeadlineRaiseRateService;


@Service
public class DeadlineRaiseRateServiceImpl extends ServiceImpl<DeadlineRaiseRateDao, DeadlineRaiseRateEntity> implements DeadlineRaiseRateService {

	
    @Autowired
    private DeadlineRaiseRateDao deadlineRaiseRateDao;
    
    @Autowired
    private PeriodDao periodDao;
    
    
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DeadlineRaiseRateEntity> page = this.selectPage(
                new Query<DeadlineRaiseRateEntity>(params).getPage(),
                new EntityWrapper<DeadlineRaiseRateEntity>()
        );

        return new PageUtils(page);
    }

    
    
    
    /**
     * 获取波次配置的"销售对比最少销售天数"
     */
	@Override
	public Integer getPeriodMinSalesDaysSetting(Integer periodSeq) {
		PeriodEntity periodEntity = periodDao.selectById(periodSeq);
		String periodName = periodEntity.getName();
		Integer year = Integer.parseInt(periodName.substring(0, 4));
		  
		Wrapper<DeadlineRaiseRateEntity> wrapper = new EntityWrapper<DeadlineRaiseRateEntity>();
		wrapper.where("Year = {0}", year).orderBy("Seq desc");
		List<DeadlineRaiseRateEntity> list = deadlineRaiseRateDao.selectList(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			return list.get(0).getMinSalesDays();
		} else {
			return null;
		}
	}
	
	
	
	/**
	 * 获取波次品类配置的"考核截止日期"
	 */
	@Override
	public Date getSxValueCheckedDateSetting(Integer periodSeq, String sxValue) {
		PeriodEntity periodEntity = periodDao.selectById(periodSeq);
		String periodName = periodEntity.getName();
		Integer year = Integer.parseInt(periodName.substring(0, 4));
		
		Wrapper<DeadlineRaiseRateEntity> wrapper = new EntityWrapper<DeadlineRaiseRateEntity>();
		wrapper.where("Year = {0}", year).orderBy("Seq desc");
		List<DeadlineRaiseRateEntity> list = deadlineRaiseRateDao.selectList(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			DeadlineRaiseRateEntity deadlineRaiseRateEntity = list.get(0);
			Date checkedDate = null;
			if(sxValue.equals("春深")) {
				checkedDate = deadlineRaiseRateEntity.getSpringDeep();
			} else if(sxValue.equals("春浅")) {
				checkedDate = deadlineRaiseRateEntity.getSpringShallow();
			} else if(sxValue.equals("半凉")) {
				checkedDate = deadlineRaiseRateEntity.getHalfCold();
			} else if(sxValue.equals("全凉")) {
				checkedDate = deadlineRaiseRateEntity.getFullCold();
			} else if(sxValue.equals("秋浅")) {
				checkedDate = deadlineRaiseRateEntity.getAutumnShallow();
			} else if(sxValue.equals("秋深")) {
				checkedDate = deadlineRaiseRateEntity.getAutumnDeep();
			} else if(sxValue.equals("单靴")) {
				checkedDate = deadlineRaiseRateEntity.getSingleBoot();
			} else if(sxValue.equals("二棉")) {
				checkedDate = deadlineRaiseRateEntity.getTwoCotton();
			} else if(sxValue.equals("大棉")) {
				checkedDate = deadlineRaiseRateEntity.getBigCotton();
			}
			return checkedDate;
			
		} else {
			return null;
		}
	}
	
	
	
	
	/**
	 * 获取波次品类配置的"售罄率考核值"
	 */
	@Override
	public Integer getSxValueSaleOutRateSetting(Integer periodSeq, String sxValue) {
		PeriodEntity periodEntity = periodDao.selectById(periodSeq);
		String periodName = periodEntity.getName();
		Integer year = Integer.parseInt(periodName.substring(0, 4));
		
		Wrapper<DeadlineRaiseRateEntity> wrapper = new EntityWrapper<DeadlineRaiseRateEntity>();
		wrapper.where("Year = {0}", year).orderBy("Seq desc");
		List<DeadlineRaiseRateEntity> list = deadlineRaiseRateDao.selectList(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			DeadlineRaiseRateEntity deadlineRaiseRateEntity = list.get(0);
			Integer saleOutRate = null;
			if(sxValue.equals("春深")) {
				saleOutRate = deadlineRaiseRateEntity.getSpringDeepSaleOutRate();
			} else if(sxValue.equals("春浅")) {
				saleOutRate = deadlineRaiseRateEntity.getSpringShallowSaleOutRate();
			} else if(sxValue.equals("半凉")) {
				saleOutRate = deadlineRaiseRateEntity.getHalfColdSaleOutRate();
			} else if(sxValue.equals("全凉")) {
				saleOutRate = deadlineRaiseRateEntity.getFullColdSaleOutRate();
			} else if(sxValue.equals("秋浅")) {
				saleOutRate = deadlineRaiseRateEntity.getAutumnShallowSaleOutRate();
			} else if(sxValue.equals("秋深")) {
				saleOutRate = deadlineRaiseRateEntity.getAutumnDeepSaleOutRate();
			} else if(sxValue.equals("单靴")) {
				saleOutRate = deadlineRaiseRateEntity.getSingleBootSaleOutRate();
			} else if(sxValue.equals("二棉")) {
				saleOutRate = deadlineRaiseRateEntity.getTwoCottonSaleOutRate();
			} else if(sxValue.equals("大棉")) {
				saleOutRate = deadlineRaiseRateEntity.getBigCottonSaleOutRate();
			}
			return saleOutRate;
			
		} else {
			return null;
		}
	}




	/**
	 * 获取某一年的配置
	 */
	@Override
	public DeadlineRaiseRateEntity getSomeYearSetting(Integer year) {
		Wrapper<DeadlineRaiseRateEntity> wrapper = new EntityWrapper<DeadlineRaiseRateEntity>();
		wrapper.where("Year = {0}", year).orderBy("Seq desc");;
		List<DeadlineRaiseRateEntity> list = deadlineRaiseRateDao.selectList(wrapper);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}



	/**
	 * 获取波次品类配置的"默认同比增长率"
	 */
	@Override
	public Integer getSxValueYearRaiseSetting(Integer periodSeq, String sxValue) {
		PeriodEntity periodEntity = periodDao.selectById(periodSeq);
		String periodName = periodEntity.getName();
		Integer year = Integer.parseInt(periodName.substring(0, 4));
		
		Wrapper<DeadlineRaiseRateEntity> wrapper = new EntityWrapper<DeadlineRaiseRateEntity>();
		wrapper.where("Year = {0}", year).orderBy("Seq desc");
		List<DeadlineRaiseRateEntity> list = deadlineRaiseRateDao.selectList(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null) {
			DeadlineRaiseRateEntity deadlineRaiseRateEntity = list.get(0);
			Integer yearRaise = null;
			if(sxValue.equals("春深")) {
				yearRaise = deadlineRaiseRateEntity.getSpringDeepYearRaise();
			} else if(sxValue.equals("春浅")) {
				yearRaise = deadlineRaiseRateEntity.getSpringShallowYearRaise();
			} else if(sxValue.equals("半凉")) {
				yearRaise = deadlineRaiseRateEntity.getHalfColdYearRaise();
			} else if(sxValue.equals("全凉")) {
				yearRaise = deadlineRaiseRateEntity.getFullColdYearRaise();
			} else if(sxValue.equals("秋浅")) {
				yearRaise = deadlineRaiseRateEntity.getAutumnShallowYearRaise();
			} else if(sxValue.equals("秋深")) {
				yearRaise = deadlineRaiseRateEntity.getAutumnDeepYearRaise();
			} else if(sxValue.equals("单靴")) {
				yearRaise = deadlineRaiseRateEntity.getSingleBootYearRaise();
			} else if(sxValue.equals("二棉")) {
				yearRaise = deadlineRaiseRateEntity.getTwoCottonYearRaise();
			} else if(sxValue.equals("大棉")) {
				yearRaise = deadlineRaiseRateEntity.getBigCottonYearRaise();
			}
			return yearRaise;
			
		} else {
			return null;
		}
	}





}
