package com.nuite.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.WeekSalesDao;
import com.nuite.entity.WeekSalesEntity;
import com.nuite.service.WeekSalesService;

@Service
public class WeekSalesServiceImpl extends ServiceImpl<WeekSalesDao, WeekSalesEntity> implements WeekSalesService {

	@Autowired
	private WeekSalesDao weekSalesDao;

	/**
	 * 获取某店员一个月所有的周销售报表
	 */
	@Override
	public List<WeekSalesEntity> getAssistantWeekSalesOneMonth(Integer year, Integer month, Integer assistantSeq) {
		Wrapper<WeekSalesEntity> wrapper = new EntityWrapper<WeekSalesEntity>();
		wrapper.where("Year = {0} AND Month = {1} AND AssistantSeq = {2}", year, month, assistantSeq).orderBy("Seq DESC");
		return weekSalesDao.selectList(wrapper);
	}

	/**
	 * 根据年、月、周、店员序号、店铺序号 的对象获取唯一报表对象
	 */
	@Override
	public WeekSalesEntity getWeekSalesByEntity(WeekSalesEntity weekSalesEntity) {
		return weekSalesDao.selectOne(weekSalesEntity);
	}
	
	
	/**
	 * 获取上周销售额
	 */
	@Override
	public WeekSalesEntity getLastWeekSalesByParams(Integer assistantSeq, Integer year, Integer month, Integer week) {
		String timeStr = year + month + week + "";
		WeekSalesEntity weekSalesEntity = weekSalesDao.getLastWeekSalesByParams(assistantSeq, timeStr);
		return weekSalesEntity;
	}
	
	

	/**
	 * 新增或修改一条报表记录
	 */
	@Override
	public void addOrUpdateWeeksales(WeekSalesEntity weekSalesEntity) {

		// 判断是否已存在
		WeekSalesEntity weekSalesExist = new WeekSalesEntity();

		weekSalesExist.setShopSeq(weekSalesEntity.getShopSeq());
		weekSalesExist.setAssistantSeq(weekSalesEntity.getAssistantSeq());
		weekSalesExist.setYear(weekSalesEntity.getYear());
		weekSalesExist.setMonth(weekSalesEntity.getMonth());
		weekSalesExist.setWeek(weekSalesEntity.getWeek());

		weekSalesExist = weekSalesDao.selectOne(weekSalesExist);

		// 不存在，新增
		if (weekSalesExist == null) {
			weekSalesEntity.setInputTime(new Date());
			weekSalesDao.insert(weekSalesEntity);
		} else { // 存在，修改
			weekSalesEntity.setSeq(weekSalesExist.getSeq());
			weekSalesEntity.setUpdateTime(new Date());
			weekSalesDao.updateAllColumnById(weekSalesEntity);
		}

	}

	
	/**
	 * 查询门店某一周所有店员的报表
	 */
	@Override
	public List<WeekSalesEntity> getAllWeekSalesOneWeek(Integer shopSeq, Integer year, Integer month, Integer week) {
		Wrapper<WeekSalesEntity> wrapper = new EntityWrapper<WeekSalesEntity>();
		wrapper.where("ShopSeq = {0} AND Year = {1} AND Month = {2} AND Week = {3}", shopSeq, year, month, week).orderBy("Seq DESC");
		return weekSalesDao.selectList(wrapper);
	}

	
	/**
	 * 获取所有店员月销售总额、降序作为排行
	 */
	@Override
	public List<Map<String, Object>> getAssistantMonthTotalAmount(Integer shopSeq, Integer year, Integer month, Integer week) {
		Wrapper<WeekSalesEntity> wrapper = new EntityWrapper<WeekSalesEntity>();
		wrapper.setSqlSelect("AssistantSeq AS assistantSeq, SUM (WeekAmount) AS monthTotalAmount")
		.where("ShopSeq = {0} AND Year = {1} AND Month = {2} AND Week <= {3}", shopSeq, year, month, week)
		.groupBy("AssistantSeq").orderBy("MonthTotalAmount DESC");
		return weekSalesDao.selectMaps(wrapper);
	}


}
