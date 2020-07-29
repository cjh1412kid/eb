package com.nuite.modules.sys.service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.DeadlineRaiseRateEntity;


public interface DeadlineRaiseRateService extends IService<DeadlineRaiseRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

	Integer getPeriodMinSalesDaysSetting(Integer periodSeq);
	
	Date getSxValueCheckedDateSetting(Integer periodSeq, String sxValue);

	Integer getSxValueSaleOutRateSetting(Integer periodSeq, String sxValue);

	DeadlineRaiseRateEntity getSomeYearSetting(Integer year);

	Integer getSxValueYearRaiseSetting(Integer periodSeq, String sxValue);

}

