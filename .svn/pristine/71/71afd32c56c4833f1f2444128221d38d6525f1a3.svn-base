package com.nuite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.PromoteExplainDao;
import com.nuite.entity.PromoteExplainEntity;
import com.nuite.service.PromoteExplainService;


@Service
public class PromoteExplainServiceImpl extends ServiceImpl<PromoteExplainDao, PromoteExplainEntity> implements PromoteExplainService {

	
	@Autowired
	private PromoteExplainDao promoteExplainDao;
	
	
	
	/**
	 * 新增或修改一条报表记录
	 */
	@Override
	public void addOrUpdatePromoteExplain(PromoteExplainEntity promoteExplainEntity) {
		//判断是否存在
		PromoteExplainEntity promoteExplainExist = new PromoteExplainEntity();
		promoteExplainExist.setShopSeq(promoteExplainEntity.getShopSeq());
		promoteExplainExist.setYear(promoteExplainEntity.getYear());
		promoteExplainExist.setWeek(promoteExplainEntity.getWeek());
		promoteExplainExist = promoteExplainDao.selectOne(promoteExplainExist);

		// 不存在，新增
		if (promoteExplainExist == null) {
			promoteExplainDao.insert(promoteExplainEntity);
		} else { // 存在，修改
			promoteExplainEntity.setSeq(promoteExplainExist.getSeq());
			promoteExplainDao.updateAllColumnById(promoteExplainEntity);
		}
	}
	

}
