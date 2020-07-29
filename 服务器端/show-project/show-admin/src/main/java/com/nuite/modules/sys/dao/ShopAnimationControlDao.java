package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.ShopAnimationControlEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-10 16:10:26
 */
@Mapper
public interface ShopAnimationControlDao extends BaseMapper<ShopAnimationControlEntity> {

    Integer findMaxSeq();
}
