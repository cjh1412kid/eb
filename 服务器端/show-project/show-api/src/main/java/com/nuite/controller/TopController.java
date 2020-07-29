package com.nuite.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.CategoryEntity;
import com.nuite.entity.ColorEntity;
import com.nuite.entity.ShoeViewEntity;
import com.nuite.entity.ShoesDataEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CategoryService;
import com.nuite.service.ColorService;
import com.nuite.service.ShoeService;
import com.nuite.service.ShoesDataService;
import com.nuite.service.TopService;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * Top接口
 * @author yy
 * @date 2018-12-03 15:03:16
 */
@RestController
@RequestMapping("/api/top")
@Api(tags="Top接口")
public class TopController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private TopService topService;
    
    @Autowired
    private ShoeService shoeService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ColorService colorService;
    
    @Autowired
    private ShoesDataService shoesDataService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
    
	// "result": [
//  {
//    "goodId": "B8596321B01",
//	  "tryCount": "3000",
//	  "tryTimes": "50",
	
//    "seq": 3479
//    "img": "https://www.fyweather.com/SmartSale/picture/sr_base/Goods_Shoes/B8596321B01/b60142be_5aa8_413d_910f_28d86d1bf8f4.png",
//      "SX": {
//          "品牌": "GIVENCHY",
//          "系列": "纪梵希绑带",
//          "性别": "女"
//       		}
//    "category": "女鞋",
//    "color": "红色",
//    "stock": 2000,
//    "saleCount": 1000
//  },{...}]
    
	/**
     * 试穿Top20
     */
    @Login
    @GetMapping("tryTop")
    @ApiOperation("试穿Top20")
    public R tryTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
    		@ApiParam("波次序号（逗号分隔）") @RequestParam(value = "periodSeqs", required = false) List<Integer> periodSeqs,
    		@ApiParam("开始日期") @RequestParam("startDate") Date startDate,
    		@ApiParam("结束日期") @RequestParam("endDate") Date endDate,
    		@ApiParam("Top数量") @RequestParam(value = "topNum", required = false, defaultValue = "20") Integer topNum) {
    	try {
    		
   		 	//判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
			List<Map<String, Object>> goodIdTryCount = null;
			boolean redisFlag = false;
    		//尝试从redis获取缓存的值
			if(areaSeqs.size() == 1 && periodSeqs != null && periodSeqs.size() == 1) {  //目前只做了单选时的缓存
	    		String redisKey = RedisKeys.getTopTryTopRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), periodSeqs.get(0), startDate, endDate);
	        	@SuppressWarnings("unchecked")
				List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
        		//不为空,从redis中取值
	        	if(redisList != null) {
	        		redisFlag = true;
	        		if(redisList.size() > topNum) {
	        			goodIdTryCount = redisList.subList(0, topNum);
	        		} else {
	        			goodIdTryCount = redisList;
	        		}
	        	}
			}
        	
    		//没有从redis获取到缓存，主动查库
			List<Integer> shopSeqList = null;
    		if(!redisFlag) {
	    		//根据区域筛选条件，获取安装设备的门店序号List
	    		shopSeqList = getShopSeqListByAreaSeqs(type, areaSeqs, loginUser.getBrandSeq());
	    		if(shopSeqList == null || shopSeqList.size() == 0) {
	    			return R.error(HttpStatus.BAD_REQUEST.value(), "没有满足要求的数据");
	    		}

    			/** 获取指定门店序号List、指定波次List，指定起止日期，指定top的按试穿量从大到小 的货号、试穿次数、平均试穿时间 **/
    			goodIdTryCount = topService.getTopGoodIdTryCount(shopSeqList, periodSeqs, startDate, endDate, topNum);
    		}
    		
    		for(int i = 0; i < goodIdTryCount.size(); i++) {
    			Map<String, Object> map = goodIdTryCount.get(i);
    			//添加名次
    			map.put("rank", i+1);
    			
    			//根据货号查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeService.getShoeViewByShoeId((String)map.get("shoeId"));
    			
    			//添加 鞋子Seq，图片，SX1~SX20
    			map.put("seq", shoeViewEntity.getSeq());
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
    			map.put("category", categoryEntity.getName());
    			
    			//获取颜色+库存
    			List<ShoesDataEntity> shoesDataList = shoesDataService.getShoesDataListByShoeSeq(shoeViewEntity.getSeq());
    			Set<String> colorSet = new TreeSet<String>();
    			int stock = 0;
    			for(ShoesDataEntity shoesData : shoesDataList) {
        			ColorEntity color = colorService.selectById(shoesData.getColorSeq());
        			colorSet.add(color.getName());
        			stock += shoesData.getStock();
    			}
    			map.put("color", Joiner.on(",").join(colorSet));
    			map.put("stock", stock);

    			if(!redisFlag) { //没有从redis获取到缓存，添加销售量
	    			/** 获取指定 '门店序号'，指定 '起止日期' 时间段内某 '货号' 的销售量('波次序号' 仅用于确定要查询的分表) **/
	    			int saleCount = topService.getShoeIdSalesFromShopsInSomeDate(shopSeqList, shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID(), startDate, endDate);
	    			map.put("saleCount", saleCount);
    			}
    		}
    		
			return R.ok(goodIdTryCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	// "result": [
//  {
//    "goodId": "B8596321B01",
//	  "tryCount": "3000",
//	  "tryTimes": "50",
	
//    "seq": 3479
//    "img": "https://www.fyweather.com/SmartSale/picture/sr_base/Goods_Shoes/B8596321B01/b60142be_5aa8_413d_910f_28d86d1bf8f4.png",
//      "SX": {
//          "品牌": "GIVENCHY",
//          "系列": "纪梵希绑带",
//          "性别": "女"
//       		}
//    "category": "女鞋",
//    "color": "红色",
//    "stock": 2000,
//    "saleCount": 1000
//  },{...}]
	/**
     * 销售、滞销Top20
     */
    @Login
    @GetMapping("saleTop")
    @ApiOperation("销售、滞销Top20")
    public R saleTop(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
    		@ApiParam("波次序号（逗号分隔）") @RequestParam(value = "periodSeqs", required = false) List<Integer> periodSeqs,
    		@ApiParam("开始日期") @RequestParam("startDate") Date startDate,
    		@ApiParam("结束日期") @RequestParam("endDate") Date endDate,
    		@ApiParam("Top数量") @RequestParam(value = "topNum", required = false, defaultValue = "20") Integer topNum,
    		@ApiParam("Top类型 1:销量 2:滞销") @RequestParam("topType") Integer topType) {
    	try {
    		
   		 	//判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
			
			List<Map<String, Object>> goodIdSaleCount = null;
			boolean redisFlag = false;
    		//尝试从redis获取缓存的值
			if(areaSeqs.size() == 1 && periodSeqs != null && periodSeqs.size() == 1) {  //目前只做了单选时的缓存
	    		String redisKey = RedisKeys.getTopSaleTopRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), periodSeqs.get(0), startDate, endDate, topType);
	        	@SuppressWarnings("unchecked")
				List<Map<String, Object>> redisList = redisUtils.get(redisKey, List.class);
        		//不为空,从redis中取值
	        	if(redisList != null) {
	        		redisFlag = true;
	        		if(redisList.size() > topNum) {
	        			goodIdSaleCount = redisList.subList(0, topNum);
	        		} else {
	        			goodIdSaleCount = redisList;
	        		}
	        	}
			}
			
			
			
    		//没有从redis获取到缓存，主动查库
			List<Integer> shopSeqList = null;
    		if(!redisFlag) {
        		//根据区域筛选条件，获取安装设备的门店序号List
        		shopSeqList = getShopSeqListByAreaSeqs(type, areaSeqs, loginUser.getBrandSeq());
        		if(shopSeqList == null || shopSeqList.size() == 0) {
	    			return R.error(HttpStatus.BAD_REQUEST.value(), "没有满足要求的数据");
	    		}

        		/** 获取指定区域内、指定波次List，指定起止日期，指定top的按销量排序 的货号、销售量 **/
        		goodIdSaleCount = topService.getTopGoodIdSaleCount(type, areaSeqs, periodSeqs, startDate, endDate, topNum, topType);
    		}

    		for(int i = 0; i < goodIdSaleCount.size(); i++) {
    			Map<String, Object> map = goodIdSaleCount.get(i);
    			//添加名次
    			map.put("rank", i+1);
    			
    			//根据货号查询鞋子视图
    			ShoeViewEntity shoeViewEntity = shoeService.getShoeViewByShoeId((String)map.get("shoeId"));
    			
    			//添加 鞋子Seq，图片，SX1~SX20
    			map.put("seq", shoeViewEntity.getSeq());
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
    			map.put("category", categoryEntity.getName());
    			
    			//获取颜色+库存
    			List<ShoesDataEntity> shoesDataList = shoesDataService.getShoesDataListByShoeSeq(shoeViewEntity.getSeq());
    			Set<String> colorSet = new TreeSet<String>();
    			int stock = 0;
    			for(ShoesDataEntity shoesData : shoesDataList) {
        			ColorEntity color = colorService.selectById(shoesData.getColorSeq());
        			colorSet.add(color.getName());
        			stock += shoesData.getStock();
    			}
    			map.put("color", Joiner.on(",").join(colorSet));
    			map.put("stock", stock);

    			if(!redisFlag) { //没有从redis获取到缓存，添加试穿次数、平均试穿时间
        			/** 获取指定 '门店序号'，指定 '起止日期' 时间段内某 '货号' 的销售量('波次序号' 仅用于确定要查询的分表) **/
        			Map<String, Object> tryCountTimes = topService.getShoeIdTryCountTimesMap(shopSeqList, shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID(), startDate, endDate);
        			map.putAll(tryCountTimes);
    			}
    		}
    		
			return R.ok(goodIdSaleCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    
    
    

}
