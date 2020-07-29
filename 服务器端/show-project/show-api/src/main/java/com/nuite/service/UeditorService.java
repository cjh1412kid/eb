package com.nuite.service;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.entity.UeditorRecordEntity;

/**
 * @Author: yangchuang
 * @Date: 2018/7/17 14:24
 * @Version: 1.0
 * @Description:
 */
public interface UeditorService extends IService<UeditorRecordEntity> {

    /**
     * 根据企业ID和被使用状态查询记录 唯一
     */
    UeditorRecordEntity getByCompanySeqAndUsed(Integer companySeq);
    
}
