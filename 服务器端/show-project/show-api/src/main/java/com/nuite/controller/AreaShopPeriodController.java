package com.nuite.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.PeriodService;
import com.nuite.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 基础信息查询（区域、门店、波次）
 * @author yy
 * @date 2018-11-27 15:12:43
 */
@RestController
@RequestMapping("/api/baseInfo")
@Api(tags="基础信息查询（区域、门店、波次）")
public class AreaShopPeriodController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private PeriodService periodService;
    
    
    
    /**
     * 获取全国所有大区
     */
    @Login
    @GetMapping("firstAreas")
    @ApiOperation("获取全国所有大区（仅限全国账号）")
    public R firstAreas(@ApiIgnore @LoginUser UserEntity loginUser
    		) {
    	try {
    		
    		List<Map<String, Object>> areaList;
    		//全国
    		if(loginUser.getRoleSeq() == 1) {
    			//获取所有大区
    			areaList = areaService.getFirstAreasMapByBrandSeq(loginUser.getBrandSeq());
    		} else {
    			return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
    		}
    		
    		return R.ok(areaList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    /**
     * 获取所选大区内所有分公司
     */
    @Login
    @GetMapping("secondAreas")
    @ApiOperation("获取所选大区内所有分公司（全国账号或大区账号）")
    public R secondAreas(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("大区序号（多个以逗号分隔，大区账号可不传）") @RequestParam(value = "areaSeqs", required = false) List<Integer> areaSeqs
    		) {
    	try {
    		
    		List<Map<String, Object>> areaList;
    		//全国
    		if(loginUser.getRoleSeq() == 1) {
    			if(areaSeqs != null && areaSeqs.size() > 0) {
	    			//获取多个大区内所有分公司
	    			areaList = areaService.getSecondAreasMapByParentSeqList(areaSeqs);
    			} else {
    				return R.error(HttpStatus.BAD_REQUEST.value(), "缺少参数：大区序号");
    			}
    		} else if (loginUser.getRoleSeq() == 2){ //大区
    			//获取大区内所有分公司
    			areaList = areaService.getSecondAreasMapByParentSeq(loginUser.getUserAreaSeq());
    		} else {
    			return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
    		}
    		
    		return R.ok(areaList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    /**
     * 获取所选分公司内所有门店
     */
    @Login
    @GetMapping("shops")
    @ApiOperation("获取所选分公司内所有门店（全国账号或大区账号或分公司账号）")
    public R shops(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("分公司序号（多个以逗号分隔，分公司账号可不传）") @RequestParam(value = "areaSeqs", required = false) List<Integer> areaSeqs
    		) {
    	try {
    		
    		List<Map<String, Object>> shopList;
    		//全国、大区
    		if(loginUser.getRoleSeq() == 1 || loginUser.getRoleSeq() == 2) {
    			if(areaSeqs != null && areaSeqs.size() > 0) {
        			//获取多个分公司内所有门店
        			shopList = shopService.getShopsMapByAreaSeqList(areaSeqs);
    			} else {
    				return R.error(HttpStatus.BAD_REQUEST.value(), "缺少参数：分公司序号");
    			}
    		} else if (loginUser.getRoleSeq() == 3){ //分公司
    			//获取分公司内所有门店
    			shopList = shopService.getShopsMapByAreaSeq(loginUser.getUserAreaSeq());
    		} else {
    			return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
    		}
    		
    		return R.ok(shopList);
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
    
}
