package com.nuite.modules.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.CategoryDao;
import com.nuite.modules.sys.entity.CategoryEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.CategoryService;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.selectPage(
                new Query<CategoryEntity>(params).getPage(),
                new EntityWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CategoryEntity selectLocalCategory(String name, String code) {
        CategoryEntity categoryEntity = selectOne(new EntityWrapper<CategoryEntity>().eq("Name", name));
        if (categoryEntity == null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setParentSeq(0);
            categoryEntity.setBrandSeq(1);
            categoryEntity.setName(name);
            categoryEntity.setCategoryCode(code);
            categoryEntity.setVisible(1);
            categoryEntity.setInputTime(new Date());
            categoryEntity.setDel(0);
            categoryDao.insertWithSeq(categoryEntity);
        } else {
            if (!code.equals(categoryEntity.getCategoryCode())) {
                categoryEntity.setCategoryCode(code);
                updateById(categoryEntity);
            }
        }
        return categoryEntity;
    }

	@Override
	public List getUserCategory(UserEntity userEntity) {
		 EntityWrapper<CategoryEntity> ew = new EntityWrapper<>();
         ew.eq("BrandSeq", 1).eq("Del", 0);
         return categoryDao.selectList(ew);
	}

}
