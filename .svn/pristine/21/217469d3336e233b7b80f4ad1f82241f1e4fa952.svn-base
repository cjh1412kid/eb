package com.nuite.controller;

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
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.UserEntity;
import com.nuite.utils.ProductModuleEnum;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版：销售额统计接口
 * @author yy
 * @date 2019-07-11 15:03:16
 */
@RestController
@RequestMapping("/api/product/sale")
@Api(tags="2019产品版：销售额统计接口")
public class ProductSaleShoesController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private RedisUtils redisUtils;
    
    
    
//  result：[{
//  "total": 300万,
//  "lastyearTotal": 300万,
//  "detail": [{
//			     "seq": 1,
//			     "areaName": "全国",
//			     "sales": 50万,
//			     "lastyearSales": 50万,
//			     "type": 1
//			     },{
//					
//			     }]
//}]
    
    
	/**
     * 销售额统计接口
     */
    @Login
    @GetMapping("statistics")
    @ApiOperation("销售额统计接口")
    public R statistics(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("时间范围：1:最近一日 7:上一周 30:当月 365:当年") @RequestParam("days") Integer days,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false, defaultValue = "0") Integer periodSeq) {
    	try {
    		//从redis获取缓存的值
	    	String redisKey = RedisKeys.getProductModuleRedisKey(ProductModuleEnum.SALE_SALESHOESDETAIL.getModuleSeq(), type, areaSeq, days, periodSeq);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
	        if(redisList == null) {
	    		return R.error("正在准备数据");
	        }
    		
			return R.ok(redisList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }

}
