package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.BrandEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-11-24 16:14:26
 */
public interface BrandDao extends BaseMapper<BrandEntity> {
    Map<String, String> queryQRCode(@Param("ShopSeq") Integer shopSeq);
}
