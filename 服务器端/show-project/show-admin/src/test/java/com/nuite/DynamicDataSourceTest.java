package com.nuite;


import com.nuite.modules.erp.service.ErpService;
import com.nuite.modules.job.task.EBlanErpTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {

    @Resource(name = "EBlanErpService")
    private ErpService erpService;

    @Resource(name = "EBlanErpTask")
    private EBlanErpTask eBlanErpTask;

    @Test
    public void test2() throws Exception {
        eBlanErpTask.shopShoeStock();
    }

    @Test
    public void test3() {
        erpService.copyDataFromErp("2019-09-30 23:59:59.999");
    }

}
