package com.nuite.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.entity.CategoryEntity;
import com.nuite.modules.sys.entity.UserEntity;


public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CategoryEntity selectLocalCategory(String name, String code);
    
    List getUserCategory(UserEntity userEntity);
}

