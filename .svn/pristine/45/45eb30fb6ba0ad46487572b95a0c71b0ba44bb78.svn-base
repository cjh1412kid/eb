package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.ShoeDao;
import com.nuite.modules.sys.entity.ShoeEntity;
import com.nuite.modules.sys.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ShoeServiceImpl extends ServiceImpl<ShoeDao, ShoeEntity> implements ShoeService {

    @Autowired
    private ShoeDao shoeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShoeEntity> page = this.selectPage(
                new Query<ShoeEntity>(params).getPage(),
                new EntityWrapper<ShoeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ShoeEntity selectByGoodId(String shoeId) {
        if (shoeId == null) return null;
        List<ShoeEntity> shoeList = selectList(new EntityWrapper<ShoeEntity>().eq("ShoeID", shoeId));
        if (shoeList.size() > 0) {
            return shoeList.get(0);
        }
        return null;
    }

    @Override
    public void updateLocalShoe(String shoeId, ShoeEntity shoeEntity) {
        ShoeEntity oldShoeEntity = selectOne(new EntityWrapper<ShoeEntity>()
                .eq("ShoeID", shoeId));
        if (oldShoeEntity != null) {
            shoeEntity.setSeq(oldShoeEntity.getSeq());
            updateById(shoeEntity);
        } else {
            shoeDao.insertWithSeq(shoeEntity);
        }

    }


}
