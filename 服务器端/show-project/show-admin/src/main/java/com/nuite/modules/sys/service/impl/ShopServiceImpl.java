package com.nuite.modules.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.AreaDao;
import com.nuite.modules.sys.dao.ShopDao;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.entity.ShopEntity;
import com.nuite.modules.sys.service.ShopService;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopDao, ShopEntity> implements ShopService {

    @Autowired
    private ShopDao shopDao;
    
    @Autowired
    private AreaDao areaDao;
    
    
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShopEntity> page = this.selectPage(
                new Query<ShopEntity>(params).getPage(),
                new EntityWrapper<ShopEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer getShopSeqByName(String shopName) {
        Integer localShopSeq;
        if (shopName != null) {
            shopName = shopName.replaceAll("-伊伴", "");
            //TODO Name 不可以用like，太不严谨了！！！ 改为=（注意记录会不会报错）
            ShopEntity localShop = selectOne(new EntityWrapper<ShopEntity>().eq("Name", shopName).eq("Del", 0));
            if (localShop != null) {
                localShopSeq = localShop.getSeq();
            } else {
                localShopSeq = -1;
            }
        } else {
            localShopSeq = -2;
        }
        return localShopSeq;
    }

    
    
    
	@Override
	public Integer getBranchOfficeSeqByShopSeq(Integer localShopSeq) {
		ShopEntity shopEntity = shopDao.selectById(localShopSeq);
		return shopEntity.getAreaSeq();
	}

	
	@Override
	public Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq) {
		AreaEntity areaEntity = areaDao.selectById(branchOfficeSeq);
		return areaEntity.getParentSeq();
	}

}
