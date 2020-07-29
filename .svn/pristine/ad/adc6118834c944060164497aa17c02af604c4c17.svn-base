package com.nuite.service;

import com.nuite.common.utils.DateUtils;
import com.nuite.datasources.DataSourceNames;
import com.nuite.datasources.annotation.DataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * 测试多数据源
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-28
 */
@Service
public class DataSourceTestService {
//    @Autowired
//    private SysUserService sysUserService;

    @Test
    public void queryUser(){
        System.out.println(DateUtils.getMothFirstDate());
        Calendar ca = Calendar.getInstance();
        System.out.println(ca.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
//
//    @DataSource(name = DataSourceNames.SECOND)
//    public SysUserEntity queryUser2(Long userId){
//        return sysUserService.selectById(userId);
//    }
}
