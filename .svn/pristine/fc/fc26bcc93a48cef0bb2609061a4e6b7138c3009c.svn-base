package com.nuite.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.UserEntity;
import com.nuite.service.ConvertService;
import com.nuite.service.PeriodService;
import com.nuite.utils.RedisKeys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 转化率接口
 * @author yy
 * @date 2018-12-12 09:26:53
 */
@RestController
@RequestMapping("/api/convert")
@Api(tags="转化率接口")
public class ConvertController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private ConvertService convertService;
    
    @Autowired
    private RedisUtils redisUtils;
    
    
    
    
//	result :[{
//	"rate" :  [{name : "试穿",value : 16},{name :"销售",value : 10}]
//	"detail" : [{"name":"试穿","values":[
//										{"day":"2018-11-12","value":123},
//										{"day":"2018-11-13","value":222},
//										{"day":"2018-11-14","value":333}]
//				},
//	 			{"name":"销售","values":[
//										{"day":"","value":123},
//										{"day":"","value":123}]
//	 			}]
//  }]
	/**
     * 试穿销售转化率 :按区域、波次、起止时间统计
     */
    @Login
    @GetMapping("convert")
    @ApiOperation("试穿销售转化率 :按区域、波次、起止时间统计")
    public R convert(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("统计范围：1:全国 2:大区 3:分公司 4:门店") @RequestParam("type") Integer type,
    		@ApiParam("统计范围对应的序号（逗号分隔）") @RequestParam("areaSeqs") List<Integer> areaSeqs,
    		@ApiParam("波次序号（逗号分隔）") @RequestParam(value = "periodSeqs", required = false) List<Integer> periodSeqs,
    		@ApiParam("货号（逗号分隔）:计算单个货品转化率时必传，此时波次序号可不传，只能为货号对应的波次") @RequestParam(value = "shoeIds", required = false) List<String> shoeIds,
    		@ApiParam("起始日期") @RequestParam("startDate") Date startDate,
    		@ApiParam("结束日期") @RequestParam("endDate") Date endDate) {
    	try {
    		
   		    //判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
    		
			
			
			
    		//尝试从redis获取缓存的值
			if(areaSeqs.size() == 1 && (periodSeqs == null || periodSeqs.size() == 0) ) {  //目前只做了单选区域、不选波次时的缓存
				
				String redisKey = null;
				if(shoeIds == null || shoeIds.size() == 0) { //总转化率
					redisKey = RedisKeys.getConvertAllConvertRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), startDate, endDate);
				} else if(shoeIds.size() == 1) {  //单个货品转化率
					redisKey = RedisKeys.getConvertShoeIdConvertRedisKey(loginUser.getBrandSeq(), type, areaSeqs.get(0), shoeIds.get(0), startDate, endDate);
				}
				if(redisKey != null) {
		        	@SuppressWarnings("unchecked")
		        	Map<String, Object> redisMap = redisUtils.get(redisKey, Map.class);
	        		//不为空,从redis中取值
		        	if(redisMap != null) {
		        		return R.ok(redisMap);
		        	}
				}
			}
			
			
			
			
    		//根据区域筛选条件，获取安装设备的门店序号List
    		List<Integer> shopSeqList = getShopSeqListByAreaSeqs(type, areaSeqs, loginUser.getBrandSeq());
    		if(shopSeqList == null || shopSeqList.size() == 0) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "没有满足要求的数据");
    		}
    		
    		//如果波次序号为空，查询品牌的所有波次
    		if(periodSeqs == null || periodSeqs.size() == 0) {
    			periodSeqs = periodService.getPeriodSeqsByBrandSeq(loginUser.getBrandSeq());
    		}
    		
    		
    		//1.试穿销售转化率 （环形图数据）
    		int tryTotalNum = 0;
    		int saleTotalNum = 0;
    		
    		//2.详细的每日试穿+销售数据 （双纵坐标平滑折线图）
    		List<Map<String, Object>> dayTryCountList = new ArrayList<Map<String, Object>>();
    		List<Map<String, Object>> daySaleCountList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> dayTryCountMap;
    		Map<String, Object> daySaleCountMap;
    		
    		Calendar calendar = Calendar.getInstance();
    		while (!startDate.after(endDate)) {
    			dayTryCountMap = new HashMap<String, Object>();
    			daySaleCountMap = new HashMap<String, Object>();
    			dayTryCountMap.put("day", startDate);
    			daySaleCountMap.put("day", startDate);
    			//获取多个门店多个波次某一天内有效试穿次数
    			int tryNum = convertService.getShopsTryValidNumOneDay(shopSeqList, periodSeqs, shoeIds, startDate);
    			tryTotalNum += tryNum;
    			 //获取所选区域内多个波次某一天销售量
    			int saleNum = convertService.getAreasSaleNumOneDay(type, areaSeqs, periodSeqs, shoeIds, startDate);
    			saleTotalNum += saleNum;
    			dayTryCountMap.put("value", tryNum);
    			daySaleCountMap.put("value", saleNum);
    			dayTryCountList.add(dayTryCountMap);
    			daySaleCountList.add(daySaleCountMap);
    			
    			calendar.setTime(startDate);
    			calendar.add(Calendar.DAY_OF_MONTH, 1); //+1天
    			startDate = calendar.getTime();
    		}
    		
    		
    		/**组装返回结果集**/
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		
    		//1.试穿销售转化率 （环形图数据）
    		List<Map<String, Object>> rateList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> tryTotalMap = new HashMap<String, Object>();
    		tryTotalMap.put("name", "试穿");
    		tryTotalMap.put("value", tryTotalNum);
    		rateList.add(tryTotalMap);
    		Map<String, Object> saleTotalMap = new HashMap<String, Object>();
    		saleTotalMap.put("name", "销售");
    		saleTotalMap.put("value", saleTotalNum);
    		rateList.add(saleTotalMap);
    		
    		resultMap.put("rate", rateList);
    		
    		//2.详细的每日试穿+销售数据 （双纵坐标平滑折线图）
    		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
    		Map<String, Object> tryMap = new HashMap<String, Object>();
    		tryMap.put("name", "试穿");
    		tryMap.put("values", dayTryCountList);
    		detailList.add(tryMap);
    		Map<String, Object> saleMap = new HashMap<String, Object>();
    		saleMap.put("name", "销售");
    		saleMap.put("values", daySaleCountList);
    		detailList.add(saleMap);

    		resultMap.put("detail", detailList);
    		
    		return R.ok(resultMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    

}
