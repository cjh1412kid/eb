package com.nuite.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.dao.ShoeDao;
import com.nuite.dao.ShoeViewDao;
import com.nuite.entity.CategoryEntity;
import com.nuite.entity.ShoeEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CategoryService;
import com.nuite.service.ShoesMainpushService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版：主推接口
 * @author yy
 * @date 2019-07-29 13:11:48
 */
@RestController
@RequestMapping("/api/product/mainpush")
@Api(tags="2019产品版：主推接口")
public class ProductShoesMainpushController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShoesMainpushService shoesMainpushService;
	
    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private ShoeDao shoeDao;
    
    @Autowired
    private CategoryService categoryService;
    
    
    
	/**
     * 主推列表接口：本季主推、本店主推
     */
    @Login
    @GetMapping("mainpushShoesList")
    @ApiOperation("主推列表接口：本季主推、本店主推")
    public R mainpushShoesList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false) Integer periodSeq,
    		@ApiParam("起始条数") @RequestParam("start") Integer start,
    		@ApiParam("总条数") @RequestParam("num") Integer num) {
    	try {
    		
    		List<Map<String,Object>> list = shoesMainpushService.getMainpushShoesList(type, areaSeq, periodSeq, start, num);
    		
    		//添加鞋子详情
    		for(Map<String,Object> map : list) {
        		//根据Seq查询鞋子视图
        		ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoesSeq"));
        		map.put("shoeId", shoeViewEntity.getShoeID());
        		
        		//添加 鞋子图片，SX1~SX20
        		map.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
        	    Map<String, String> SXmap = new HashMap<String, String>();
        		for(int j = 1; j <= 20; j++) {
        			Method getSXiNameMethod = ShoeViewEntity.class.getMethod("getSX" + j + "Name");
        			Method getSXiValueMethod = ShoeViewEntity.class.getMethod("getSX" + j + "Value");
        			Object SXiName = getSXiNameMethod.invoke(shoeViewEntity);
        			Object SXiValue = getSXiValueMethod.invoke(shoeViewEntity);
        			if(SXiName != null && SXiName.toString().length() > 0 && SXiValue != null && SXiValue.toString().length() > 0) {
        				SXmap.put(SXiName.toString(), SXiValue.toString());
        			}
        		}
        		map.put("SX", SXmap);
        		
        		//添加分类
        		CategoryEntity categoryEntity = categoryService.selectById(shoeViewEntity.getCategorySeq());
        		if(categoryEntity == null) {
        			map.put("category", "无分类");
        		} else {
        			map.put("category", categoryEntity.getName());
        		}
        		
    		}
    		
			return R.ok(list);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    

    
    
    
	/**
     * 设置主推
     */
    @Login
    @PostMapping("setMainpush")
    @ApiOperation("设置主推")
    public R setMainpush(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("鞋子序号") @RequestParam("shoeSeq") Integer shoeSeq) {
    	try {
    		Integer areaType = loginUser.getRoleSeq();
    		Integer areaSeq = loginUser.getUserAreaSeq();
    		
    		//查询鞋子是否已经主推
    		boolean isMainpush = shoesMainpushService.isMainpush(areaType, areaSeq, shoeSeq);
    		if(isMainpush) {
    			return R.error("此货品已主推过");
    		}
    		
    		//根据Seq查询鞋子
    		ShoeEntity shoeEntity = shoeDao.selectById(shoeSeq);
    		
    		shoesMainpushService.setMainpush(areaType, areaSeq, shoeSeq, shoeEntity.getPeriodSeq());
    		
			return R.ok("设置成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 取消主推
     */
    @Login
    @PostMapping("cancelMainpush")
    @ApiOperation("取消主推")
    public R cancelMainpush(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("鞋子序号") @RequestParam("shoeSeq") Integer shoeSeq) {
    	try {
    		Integer areaType = loginUser.getRoleSeq();
    		Integer areaSeq = loginUser.getUserAreaSeq();
 
    		shoesMainpushService.cancelMainpush(areaType, areaSeq, shoeSeq);
    		
			return R.ok("取消成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
}
