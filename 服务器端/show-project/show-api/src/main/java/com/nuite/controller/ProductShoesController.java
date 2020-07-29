package com.nuite.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
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
import com.nuite.dao.ShoeViewDao;
import com.nuite.entity.CategoryEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CategoryService;
import com.nuite.service.ShoesMainpushService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版：货品接口
 * @author yy
 * @date 2019-06-11 15:03:16
 */
@RestController
@RequestMapping("/api/product/shoe")
@Api(tags="2019产品版：货品接口")
public class ProductShoesController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ShoesMainpushService shoesMainpushService;
    
    
	/**
     * 商品详情接口
     */
    @Login
    @GetMapping("shoeDetai")
    @ApiOperation("商品详情接口")
    public R tryTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("鞋子序号") @RequestParam("shoeSeq") Integer shoeSeq) {
    	try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
    		//根据Seq查询鞋子视图
    		ShoeViewEntity shoeViewEntity = shoeViewDao.selectById(shoeSeq);
    		
    		//添加 鞋子图片，SX1~SX20
    		resultMap.put("img", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
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
    		resultMap.put("SX", SXmap);
    		
    		//添加分类
    		CategoryEntity categoryEntity = categoryService.selectById(shoeViewEntity.getCategorySeq());
    		if(categoryEntity == null) {
    			resultMap.put("category", "无分类");
    		} else {
    			resultMap.put("category", categoryEntity.getName());
    		}
    		
			//添加是否主推
    		Integer uesrAreaType = loginUser.getRoleSeq();
    		Integer uesrAreaSeq = loginUser.getUserAreaSeq();
    		boolean isMainpush = shoesMainpushService.isMainpush(uesrAreaType, uesrAreaSeq, shoeSeq);
    		resultMap.put("isMainpush", isMainpush);
    		
			return R.ok(resultMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }

}
