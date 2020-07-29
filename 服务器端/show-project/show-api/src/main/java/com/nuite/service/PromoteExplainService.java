package com.nuite.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.PromoteExplainEntity;


public interface PromoteExplainService extends IService<PromoteExplainEntity> {

	void addOrUpdatePromoteExplain(PromoteExplainEntity promoteExplainEntity);

}

