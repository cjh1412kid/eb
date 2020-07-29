package com.nuite.modules.erp.service;

import com.nuite.modules.erp.entity.ErpStockEntity;
import com.nuite.modules.erp.entity.ShoeDetail;

import java.util.Date;
import java.util.List;

public interface ErpService {
    List<ErpStockEntity> queryW2SData(Date startTime, Date endTime);

    List<ErpStockEntity> queryS2SData(Date startTime, Date endTime);

    List<ErpStockEntity> queryS2WData(Date startTime, Date endTime);

    List<ErpStockEntity> querySaleData(Date startTime, Date endTimes);

    List<ShoeDetail> queryShoes(Date startTime);

    void copyDataFromErp(String endTime);
}
