package com.nuite.modules.job.task;

import com.nuite.common.utils.RedisKeys;
import com.nuite.common.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopShowTopTaskTest {
    @Autowired
    private ShopShowTopTask shopShowTopTask;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void saleShoesTop() {
        shopShowTopTask.saleShoesTop();

//        System.out.println(redisUtils.get(RedisKeys.SALE_TOP_KEY));

        //redisUtils.delete(RedisKeys.SALE_TOP_KEY);
    }

    @Test
    public void tryShoesTop() {
        shopShowTopTask.tryShoesTop();

        //System.out.println(redisUtils.get(RedisKeys.TRY_TOP_KEY));

        //redisUtils.delete(RedisKeys.TRY_TOP_KEY);
    }
}