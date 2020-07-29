package com.nuite.modules.sys.service.impl;

import com.nuite.common.utils.MyPagination;
import com.nuite.modules.sys.entity.FestivalMaterialEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yangchuang
 * @Date: 2019/1/21 14:56
 * @Version: 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class FestivalMaterialServiceImplTest {

    @Autowired
    FestivalMaterialService materialService;

    @Test
    public void selectUserMaterial() {
        MyPagination<FestivalMaterialEntity> pageInfo = materialService.selectUserMaterial(-1, 1, 5);
        System.out.println(pageInfo);
    }

    @Test
    public void selectCompanyMaterial() {

        MyPagination<FestivalMaterialEntity> pageInfo = materialService.selectCompanyMaterial(1, 5);
        System.out.println(pageInfo);
    }
}