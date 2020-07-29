package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.SaleShoesDetailEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-11-24 16:14:26
 */
public interface SaleShoesDetailDao extends BaseMapper<SaleShoesDetailEntity> {
    List<Map<String, Object>> selectShopTop10(@Param(value = "time") Date lastWeekToday);
}
