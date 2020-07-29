package com.nuite.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.UeditorDao;
import com.nuite.entity.UeditorRecordEntity;
import com.nuite.service.UeditorService;

/**
 * @Author: yangchuang
 * @Date: 2018/7/17 14:27
 * @Version: 1.0
 * @Description:
 */
@Service
public class UeditorServiceImpl extends ServiceImpl<UeditorDao, UeditorRecordEntity> implements UeditorService {

    /**
     * 根据企业ID和被使用状态查询记录 唯一
     */
    @Override
    public UeditorRecordEntity getByCompanySeqAndUsed(Integer companySeq) {
        EntityWrapper<UeditorRecordEntity> wrapper = new EntityWrapper<UeditorRecordEntity>();
        wrapper.eq("Company_Seq", companySeq).eq("Del", 0).eq("used", 1);
        return super.selectOne(wrapper);
    }
    
}
