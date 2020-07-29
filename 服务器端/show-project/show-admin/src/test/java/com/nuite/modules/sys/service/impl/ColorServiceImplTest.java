package com.nuite.modules.sys.service.impl;

import com.nuite.modules.sys.entity.ColorEntity;
import com.nuite.modules.sys.service.ColorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColorServiceImplTest {

    @Autowired
    private ColorService colorService;

    @Test
    public void selectLocalColor() {
//        ColorEntity colorEntity = colorService.selectLocalColor("02", "老鹅");
//        System.out.println(colorEntity.getSeq());
    }
}