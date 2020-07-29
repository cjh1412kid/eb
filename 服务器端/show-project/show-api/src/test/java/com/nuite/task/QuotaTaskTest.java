package com.nuite.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotaTaskTest {

    @Autowired
    private QuotaTask quotaTask;

    @Test
    public void testSalesDetail() {
        quotaTask.salesDetail();
    }
}
