package com.nuite.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
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
import com.nuite.dao.ShoeViewDao;
import com.nuite.entity.CategoryEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CategoryService;
import com.nuite.service.ShoesMainpushService;
import com.nuite.utils.ProductModuleEnum;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版：试穿销售Top接口
 * @author yy
 * @date 2019-06-11 15:03:16
 */
@RestController
@RequestMapping("/api/product/top")
@Api(tags="2019产品版：试穿销售Top接口")
public class ProductTopController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShoeViewDao shoeViewDao;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    @Autowired
    private ShoesMainpushService shoesMainpushService;
    
    
    
	/**
     * 试穿排名
     */
    @Login
    @GetMapping("tryTop")
    @ApiOperation("试穿排名")
    public R tryTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("时间范围：1:最近一日 7:上一周 9:累计") @RequestParam("days") Integer days,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false, defaultValue = "0") Integer periodSeq) {
    	try {
    		//从redis获取缓存的值
	    	String redisKey = RedisKeys.getProductModuleRedisKey(ProductModuleEnum.TRY_TRYTOP.getModuleSeq(), type, areaSeq, days, periodSeq);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
	        if(redisList == null) {
	    		return R.error("正在准备数据");
	        }
    		for(int i = 0; i < redisList.size(); i++) {
    			Map<String, Object> map = redisList.get(i);
    			//添加名次
    			map.put("tryRank", i+1);
    			
    			//根据Seq查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoeSeq"));
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
    			
    			//添加是否主推
        		Integer uesrAreaType = loginUser.getRoleSeq();
        		Integer uesrAreaSeq = loginUser.getUserAreaSeq();
        		boolean isMainpush = shoesMainpushService.isMainpush(uesrAreaType, uesrAreaSeq, (Integer)map.get("shoeSeq"));
        		map.put("isMainpush", isMainpush);
        		
    		}
    		
			return R.ok(redisList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    

	/**
     * 销售排名
     */
    @Login
    @GetMapping("saleTop")
    @ApiOperation("销售排名")
    public R saleTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("时间范围：1:最近一日 7:上一周 9:累计") @RequestParam("days") Integer days,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false, defaultValue = "0") Integer periodSeq) {
    	try {
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getProductModuleRedisKey(ProductModuleEnum.SALE_SALETOP.getModuleSeq(), type, areaSeq, days, periodSeq);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
	        if(redisList == null) {
	    		return R.error("正在准备数据");
	        }
	        
	        
    		for(int i = 0; i < redisList.size(); i++) {
    			Map<String, Object> map = redisList.get(i);
    			//添加名次
    			map.put("saleRank", i+1);
    			
    			/***过滤脏数据****/
    			if(map.get("shoeSeq") == null) {
    				map.put("img", null);
    				map.put("SX", null);
    				map.put("category", null);
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
    			
    			//根据Seq查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoeSeq"));
    			map.put("shoeId", shoeViewEntity.getShoeID());
    			
    			
    			/***过滤脏数据****/
    			if(shoeViewEntity == null || shoeViewEntity.getPeriodSeq() == null) {
    				map.put("img", null);
    				map.put("SX", null);
    				map.put("category", null);
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
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
    			
    			//添加是否主推
        		Integer uesrAreaType = loginUser.getRoleSeq();
        		Integer uesrAreaSeq = loginUser.getUserAreaSeq();
        		boolean isMainpush = shoesMainpushService.isMainpush(uesrAreaType, uesrAreaSeq, (Integer)map.get("shoeSeq"));
        		map.put("isMainpush", isMainpush);
        		
    		}
    		
			return R.ok(redisList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    
	/**
     * 销售额（贡献度）排名
     */
    @Login
    @GetMapping("salePriceTop")
    @ApiOperation("销售额（贡献度）排名")
    public R salePriceTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("时间范围：1:最近一日 7:上一周 9:累计") @RequestParam("days") Integer days,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false, defaultValue = "0") Integer periodSeq) {
    	try {
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getProductModuleRedisKey(ProductModuleEnum.SALE_SALEPRICETOP.getModuleSeq(), type, areaSeq, days, periodSeq);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
	        if(redisList == null) {
	    		return R.error("正在准备数据");
	        }
	        
	        
    		for(int i = 0; i < redisList.size(); i++) {
    			Map<String, Object> map = redisList.get(i);
    			//添加名次
    			map.put("salePriceRank", i+1);
    			
    			/***过滤脏数据****/
    			if(map.get("shoeSeq") == null) {
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
    			
    			//根据Seq查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoeSeq"));
    			map.put("shoeId", shoeViewEntity.getShoeID());
    			
    			
    			/***过滤脏数据****/
    			if(shoeViewEntity == null || shoeViewEntity.getPeriodSeq() == null) {
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
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
    			
    			//添加是否主推
        		Integer uesrAreaType = loginUser.getRoleSeq();
        		Integer uesrAreaSeq = loginUser.getUserAreaSeq();
        		boolean isMainpush = shoesMainpushService.isMainpush(uesrAreaType, uesrAreaSeq, (Integer)map.get("shoeSeq"));
        		map.put("isMainpush", isMainpush);
        		
    		}
    		
			return R.ok(redisList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 滞销排名
     */
    @Login
    @GetMapping("saleLast")
    @ApiOperation("滞销排名")
    public R saleLast(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("时间范围：1:最近一日 7:上一周 9:累计") @RequestParam("days") Integer days,
    		@ApiParam("波次序号（可不传）") @RequestParam(value = "periodSeq", required = false, defaultValue = "0") Integer periodSeq) {
    	try {
    		
    		//尝试从redis获取缓存的值
	    	String redisKey = RedisKeys.getProductModuleRedisKey(ProductModuleEnum.SALE_SALELAST.getModuleSeq(), type, areaSeq, days, periodSeq);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
	        if(redisList == null) {
	    		return R.error("正在准备数据");
	        }
	        
	        
    		for(int i = 0; i < redisList.size(); i++) {
    			Map<String, Object> map = redisList.get(i);
    			//添加名次
    			map.put("saleRank", i+1);
    			
    			/***过滤脏数据****/
    			if(map.get("shoeSeq") == null) {
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
    			
    			//根据Seq查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)map.get("shoeSeq"));
    			map.put("shoeId", shoeViewEntity.getShoeID());
    			
    			
    			/***过滤脏数据****/
    			if(shoeViewEntity == null || shoeViewEntity.getPeriodSeq() == null) {
    				continue;
    			}
    			/***过滤脏数据****/
    			
    			
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
    			
    			//添加是否主推
        		Integer uesrAreaType = loginUser.getRoleSeq();
        		Integer uesrAreaSeq = loginUser.getUserAreaSeq();
        		boolean isMainpush = shoesMainpushService.isMainpush(uesrAreaType, uesrAreaSeq, (Integer)map.get("shoeSeq"));
        		map.put("isMainpush", isMainpush);
        		
    		}
    		
			return R.ok(redisList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    

}
