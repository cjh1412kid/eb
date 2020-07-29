package com.nuite.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.google.common.base.Joiner;
import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.dao.ShoeDao;
import com.nuite.entity.AnimationEntity;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShoeEntity;
import com.nuite.entity.ShopAnimationControlEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.ShopShowControlEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.ShopService;
import com.nuite.service.ShopShowControlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 门店动画和展示控制
 * @author yy
 * @date 2018-12-24 17:35:23
 */
@RestController
@RequestMapping("/api/shopShowControl")
@Api(tags="门店动画和展示控制")
public class ShopShowControlController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShopShowControlService shopShowControlService;
	
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private ShoeDao shoeDao;
    
    
    
    /**
     * 获取所有可选择的动画
     */
	@Login
	@GetMapping("getAnimations")
	@ApiOperation("获取所有可选择的动画")
	public R getAnimations(@ApiIgnore @LoginUser UserEntity loginUser) {
		try {
			List<AnimationEntity> animationList = shopShowControlService.getAllAnimations();
			return R.ok(animationList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
	
	
	
	/**
	 * 设置门店展示的动画和鞋子
	 */
	@Login
	@PostMapping("setAnimationAndShow")
	@ApiOperation("设置门店展示的动画和鞋子")
	public R setAnimationAndShow(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("区域类型（1全国、2大区、3分公司、4门店）") @RequestParam("areaType") Integer areaType,
			@ApiParam("区域序号(逗号分隔)（全国为0）") @RequestParam("areaSeqs") List<Integer> areaSeqList,
			@ApiParam("动画序号") @RequestParam("animationSeq") Integer animationSeq,
			@ApiParam("销售Top10（0不展示、1展示）") @RequestParam("defaultTry") Integer defaultTry,
			@ApiParam("试穿Top10（0不展示、1展示）") @RequestParam("defaultSale") Integer defaultSale,
			@ApiParam("推荐展示的鞋子序号(逗号分隔)") @RequestParam(value = "recommendShoes", required = false) List<Integer> recommendShoes) {
		try {
			
			//TODO 验证区域序号是否为用户下级
			//根据区域类型和区域序号，获取所有安装设备的门店序号
			List<Integer> shopSeqList = getShopSeqListByAreaSeqs(areaType, areaSeqList, loginUser.getBrandSeq());
			if(shopSeqList == null || shopSeqList.size() == 0) {
    			return R.error(HttpStatus.BAD_REQUEST.value(), "区域内没有找到安装设备的门店");
    		}
    		
//			Seq				int			0	0	0	0	0	0		0	0	0	0	序号(主键)		-1			
//			ShopSeqs		varchar(MAX)-1	0	-1	0	0	0		0	0	0	0	设置的门店范围	Chinese_PRC_CI_AS	0	
//			AnimationSeq	int			0	0	-1	0	0	0		0	0	0	0	动画序号		0			
//			SValidDate		datetime	0	0	-1	0	0	0		0	0	0	0	起始时间		0			
//			EValidDate		datetime	0	0	-1	0	0	0		0	0	0	0	结束时间		0	
//			UserSeq			int			0	0	-1	0	0	0		0	0	0	0	控制的用户序号		0			
//			InputTime		datetime	0	0	-1	0	0	0		0	0	0	0	入库时间		0			
//			Del				int			0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			

			
			// 1.设置动画
			ShopAnimationControlEntity shopAnimationControl = new ShopAnimationControlEntity();
			shopAnimationControl.setShopSeqs(Joiner.on(",").join(shopSeqList));
			shopAnimationControl.setAnimationSeq(animationSeq);
			shopAnimationControl.setUserSeq(loginUser.getSeq());
			shopAnimationControl.setInputTime(new Date());
			shopAnimationControl.setDel(0);
			shopShowControlService.addShopAnimationControl(shopAnimationControl);
			
			
//			Seq				int			0	0	0	0	0	0		0	0	0	0	序号(主键)		-1			
//			ShopSeqs		varchar(MAX)-1	0	-1	0	0	0		0	0	0	0	设置的门店范围	Chinese_PRC_CI_AS	0			
//			DefaultTry		int			0	0	-1	0	0	0		0	0	0	0	前十大试穿（0不展示、1展示）		0			
//			DefaultSale		int			0	0	-1	0	0	0		0	0	0	0	前十大销售（0不展示、1展示）		0			
//			RecommendShoes	varchar(MAX)-1	0	-1	0	0	0		0	0	0	0	推荐展示的鞋子( 如: "1，2......" )	Chinese_PRC_CI_AS	0			
//			SValidDate		datetime	0	0	-1	0	0	0		0	0	0	0	起始时间		0			
//			EValidDate		datetime	0	0	-1	0	0	0		0	0	0	0	结束时间		0			
//			UserSeq			int			0	0	-1	0	0	0		0	0	0	0	设置人序号		0			
//			InputTime		datetime	0	0	-1	0	0	0		0	0	0	0	入库时间		0			
//			Del				int			0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			

			//2.设置门店展示
			ShopShowControlEntity shopShowControl = new ShopShowControlEntity();
			shopShowControl.setShopSeqs(Joiner.on(",").join(shopSeqList));
			shopShowControl.setDefaultTry(defaultTry);
			shopShowControl.setDefaultSale(defaultSale);
			if(recommendShoes != null && recommendShoes.size() > 0) {
				shopShowControl.setRecommendShoes(Joiner.on(",").join(recommendShoes));
			}
			shopShowControl.setUserSeq(loginUser.getSeq());
			shopShowControl.setInputTime(new Date());
			shopShowControl.setDel(0);
			shopShowControlService.addShopShowControl(shopShowControl);
			
			return R.ok("设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("设置失败");
		}
	}
	
	
	
	
    /**
     * 获取当前区域内所有门店的设置情况
     */
	@Login
	@GetMapping("getShopsNowControl")
	@ApiOperation("获取当前区域内所有门店的设置情况")
	public R getShopsNowControl(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("区域类型（1全国、2大区、3分公司、4门店）") @RequestParam("type") Integer type,
			@ApiParam("区域序号(逗号分隔)（全国为0）") @RequestParam("areaSeqs") List<Integer> areaSeqs) {
		try {
   		 	//判断用户账号权限
			if (loginUser.getRoleSeq() > type) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			} else if (loginUser.getRoleSeq() == type && type != 1 && (areaSeqs.size() != 1 || !areaSeqs.get(0).equals(loginUser.getUserAreaSeq()))) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
    		//根据区域筛选条件，获取安装设备的门店序号List
			//所有要查询的门店
			List<ShopEntity> shopList;
			//全国
			if(type == 1) {
				//获取品牌所有门店
				shopList = shopService.getShopsByBrandSeq(loginUser.getBrandSeq());
			} else if(type == 2 && areaSeqs.size() > 0) { //大区
	        	//1.根据大区序号List查询分公司
	        	List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeqList(areaSeqs);
	        	    //组装分公司序号List
	        	List<Integer> secondAreaSeqList = new ArrayList<Integer>();
	        	for(AreaEntity area : secondAreaList) {
	        		secondAreaSeqList.add(area.getSeq());
	        	}
	        	//2.根据分公司序号List查询门店
	        	shopList = shopService.getShopsByAreaSeqList(secondAreaSeqList);
				
			} else if(type == 3 && areaSeqs.size() > 0) { //分公司
	    		//根据分公司序号List查询门店
	    		shopList = shopService.getShopsByAreaSeqList(areaSeqs);
	    		
			} else if(type == 4 && areaSeqs.size() > 0) { //门店
				//根据门店序号List查询门店
				shopList = shopService.selectBatchIds(areaSeqs);
				
			} else {
				return R.error(HttpStatus.BAD_REQUEST.value(), "参数有误");
			}
			
			
			//组装门店序号List
			List<Map<String, Object>> shopNowControlList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map;
			for(ShopEntity shopEntity : shopList) {
				//没有安装设备的门店不统计
				if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
						&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
				
					map = new HashMap<String, Object>();
					
					map.put("shopSeq", shopEntity.getSeq());
					map.put("shopName", shopEntity.getName());
					map.put("shopId", shopEntity.getShopID());
					//查询动画设置
					ShopAnimationControlEntity shopAnimationControlEntity = shopShowControlService.getShopAnimationControl(shopEntity.getSeq());
					if(shopAnimationControlEntity != null) {
						Integer animationSeq = shopAnimationControlEntity.getAnimationSeq();
						AnimationEntity animationEntity = shopShowControlService.getAnimationBySeq(animationSeq);
						map.put("animationSeq", animationSeq);
						map.put("animationName", animationEntity.getName());
					} else { //没有设置过，返回默认的情况
						map.put("animationSeq", 0);
						map.put("animationName", "默认动画");
					}
					
					//查询展示设置
					ShopShowControlEntity shopShowControlEntity = shopShowControlService.getShopShowControlEntity(shopEntity.getSeq());
					if(shopShowControlEntity != null) {
						map.put("defaultTry", shopShowControlEntity.getDefaultTry());
						map.put("defaultSale", shopShowControlEntity.getDefaultSale());
						String shoeSeqs = shopShowControlEntity.getRecommendShoes();
						if(!StringUtils.isEmpty(shoeSeqs)) {
							String[] shoeSeqArr = shoeSeqs.split(",");
							
							List<Map<String, Object>> shoeList = new ArrayList<Map<String, Object>>();
							for(String seq : shoeSeqArr) {
								ShoeEntity shoeEntity = shoeDao.selectById(seq);
								Map<String, Object> shoeMap = new HashMap<String, Object>();
								shoeMap.put("shoeSeq", shoeEntity.getSeq());
								shoeMap.put("shoeName", shoeEntity.getName());
								shoeMap.put("shoeID", shoeEntity.getShoeID());
								shoeMap.put("img", getShoePictureUrl(shoeEntity.getPeriodSeq(), shoeEntity.getShoeID()));
								shoeList.add(shoeMap);
							}
							map.put("recommendShoes", shoeList);
						} else {
							map.put("recommendShoes", null);
						}
					} else { //没有设置过，返回默认的情况
						map.put("defaultTry", 1);
						map.put("defaultSale", 1);
						map.put("recommendShoes", null);
					}
					
					shopNowControlList.add(map);
				}
			}
    		
			return R.ok(shopNowControlList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
    
    


}
