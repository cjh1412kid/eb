package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.SXOptionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-11 09:33:40
 */
@Mapper
public interface SXOptionDao extends BaseMapper<SXOptionEntity> {
    void insertWithSeq(SXOptionEntity sxOptionEntity);
}
