package com.nuite.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.BrandDao;
import com.nuite.modules.sys.entity.BrandEntity;
import com.nuite.modules.sys.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BrandEntity> page = this.selectPage(
                new Query<BrandEntity>(params).getPage(),
                new EntityWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String queryBrandQRCode(Integer shopSeq) {
        if (shopSeq == null) return null;
        Map<String, String> data = brandDao.queryQRCode(shopSeq);
        String qrcodePath = null;
        String companyKey = data.get("CompanyKey");
        String brandKey = data.get("BrandKey");
        String qrcodeKey = data.get("QRCodeName");
        if (companyKey != null && brandKey != null && qrcodeKey != null) {
            qrcodePath = "Resource/ShoesResource/" + companyKey + "/" + brandKey + "/BrandingQRCode/" + qrcodeKey;
        }
        return qrcodePath;
    }

}
