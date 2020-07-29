package com.nuite.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nuite.modules.sys.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-09 09:26:54
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
    List<String> queryAllPerms(@Param("roleId") Integer roleId);
}
