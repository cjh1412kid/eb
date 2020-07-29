package com.nuite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.PeriodEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.PeriodService;
import com.nuite.service.ProductAreaShopPeriodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 2019产品版：基础信息查询（区域、门店、波次）
 * @author yy
 * @date 2019-06-27 15:12:43
 */
@RestController
@RequestMapping("/api/product/baseInfo")
@Api(tags="2019产品版：基础信息查询（区域、门店、波次）")
public class ProductAreaShopPeriodController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private ProductAreaShopPeriodService productAreaShopPeriodService;
    
    
    
    /**
     * 获取当前区域的所有下级区域
     */
    @Login
    @GetMapping("sonAreas")
    @ApiOperation("获取当前区域的所有下级区域")
    public R sonAreas(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("当前区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq) {
    	try {
    		
    		List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
    		
    		if(type == 1) { //全国，获取所有大区
        		areaList = productAreaShopPeriodService.getAreasByBrandSeq(loginUser.getBrandSeq());	
    		} else if (type == 2) { //大区，获取大区内所有分公司
        		areaList = productAreaShopPeriodService.getBranchOfficesByAreaSeq(areaSeq);
    		} else if (type == 3) { //分公司，获取分公司内所有门店
        		areaList = productAreaShopPeriodService.getShopsByAreaSeq(areaSeq);
    		} else {
    			
    		}
    		return R.ok(areaList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }

    
    
    /**
     * 获取所有波次
     */
    @Login
    @GetMapping("periods")
    @ApiOperation("获取所有波次")
    public R periods(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
    		List<Map<String, Object>> periodList = periodService.getPeriodsByBrandSeq(loginUser.getBrandSeq());

    		return R.ok(periodList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    /**
     * 2019产品版：获取2018年后的波次 
     */
    @Login
    @GetMapping("periodsAfter2018")
    @ApiOperation("2019产品版：获取2018年后的波次 ")
    public R periodsAfter2018(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
    		List<PeriodEntity> periodList = periodService.getPeriodsByBrandSeqAfterYear(loginUser.getBrandSeq(), 2018);

    		return R.ok(periodList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
}
