package com.nuite.modules.sys.service.impl;

import com.nuite.modules.sys.service.BrandService;
import com.nuite.modules.sys.service.ShopAnimationControlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopAnimationControlServiceImplTest {

    @Autowired
    private ShopAnimationControlService shopAnimationControlService;

    @Autowired
    private BrandService brandService;

    @Test
    public void queryShopAnimate() {
        shopAnimationControlService.queryShopAnimate(5);
    }

    @Test
    public void queryBrandQRCode() {
        System.out.println(brandService.queryBrandQRCode(2));
    }

    @Test
    public void queryShopAnimateTemplate() {
        shopAnimationControlService.queryShopAnimateTemplate(2);
    }
}