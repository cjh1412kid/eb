package com.nuite.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.ShopEntity;

import java.util.Map;


public interface ShopService extends IService<ShopEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer getShopSeqByName(String shopName);

	Integer getBranchOfficeSeqByShopSeq(Integer localShopSeq);

	Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq);
}

