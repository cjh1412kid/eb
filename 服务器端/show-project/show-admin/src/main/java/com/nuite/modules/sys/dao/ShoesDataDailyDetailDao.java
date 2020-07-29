package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.ShoesDataDailyDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每日库存存储详情
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-10 17:51:14
 */
@Mapper
public interface ShoesDataDailyDetailDao extends BaseMapper<ShoesDataDailyDetailEntity> {
    boolean insertList(@Param("list") List<ShoesDataDailyDetailEntity> list);
}
