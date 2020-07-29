package com.nuite.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.StoreBrandEntity;
import com.nuite.entity.StoreEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.StoreBrandService;
import com.nuite.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 商场、商场品牌管理接口
 * @author yy
 * @date 2018-12-28 18:22:19
 */
@RestController
@RequestMapping("/api/store")
@Api(tags="商场、商场品牌管理接口")
public class StoreController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private StoreService storeService;
    
    @Autowired
    private StoreBrandService storeBrandService;
    
    
    
	/**
     * 查询门店所处商场
     */
    @Login
    @GetMapping("getStore")
    @ApiOperation("查询门店所处商场")
    public R getStore(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
    		List<StoreEntity> list = new ArrayList<StoreEntity>();
    		
    		//根据门店序号获取商场品牌实体
    		StoreBrandEntity storeBrandEntity = storeService.getStoreBrandByShopSeq(loginUser.getUserAreaSeq());
    		
    		if(storeBrandEntity != null) {
	    		//商场序号
	    		Integer storeSeq = storeBrandEntity.getStoreSeq();
	    		//门店所处商场
	    		StoreEntity storeEntity = storeService.selectById(storeSeq);
	    		list.add(storeEntity);
    		}
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }

	
	
	
    
	/**
	 * 新增或修改商场
	 */
	@Login
	@PostMapping("addOrUpdateStore")
	@ApiOperation("新增或修改商场")
	public R addOrUpdateStore(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("商场名称") @RequestParam("name") String name,
			@ApiParam("省") @RequestParam("province") String province,
			@ApiParam("省编号") @RequestParam("provinceCode") Integer provinceCode,
			@ApiParam("市") @RequestParam("city") String city,
			@ApiParam("市编号") @RequestParam("cityCode") Integer cityCode,
			@ApiParam("区") @RequestParam("district") String district,
			@ApiParam("区编号") @RequestParam("districtCode") Integer districtCode,
			@ApiParam("详细地址") @RequestParam("detailAddress") String detailAddress) {
		try {
			//判断权限
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
			
//    		Seq				int		0	0	0	-1	0	0		0	0	0	0				0			
//    		Name			varchar	50	0	0	0	0	0		0	0	0	0	商场名称		Chinese_PRC_CI_AS	0			
//    		Province		varchar	30	0	-1	0	0	0		0	0	0	0	省份			Chinese_PRC_CI_AS	0			
//    		ProvinceCode	int		0	0	-1	0	0	0		0	0	0	0	省编号		0			
//    		City			varchar	30	0	-1	0	0	0		0	0	0	0	城市			Chinese_PRC_CI_AS	0			
//    		CityCode		int		0	0	-1	0	0	0		0	0	0	0	市编号		0			
//    		District		varchar	30	0	-1	0	0	0		0	0	0	0	区			Chinese_PRC_CI_AS	0			
//    		DistrictCode	int		0	0	-1	0	0	0		0	0	0	0	区编号		0			
//    		DetailAddress	varchar	255	0	-1	0	0	0		0	0	0	0	详细地址		Chinese_PRC_CI_AS	0			
//    		InputTime		datetime0	0	-1	0	0	0	(getdate())	0	0	0	0			0			
//    		Del				int		0	0	0	0	0	0	((0))	0	0	0	0			0		
    		
    		
			//判断是否已新增过商场
    		StoreBrandEntity storeBrand = storeService.getStoreBrandByShopSeq(loginUser.getUserAreaSeq());
    		
    		if(storeBrand == null) {  //没有新增过，新增商场和商场品牌关系

    			//要新增的商场实体
        		StoreEntity storeEntity = new StoreEntity();
    			storeEntity.setName(name);
    			storeEntity.setProvince(province);
    			storeEntity.setProvinceCode(provinceCode);
    			storeEntity.setCity(city);
    			storeEntity.setCityCode(cityCode);
    			storeEntity.setDistrict(district);
    			storeEntity.setDistrictCode(districtCode);
    			storeEntity.setDetailAddress(detailAddress);
    			storeEntity.setDel(0);
    			
    			//新增商场、商场品牌关系
    			storeService.addStore(storeEntity, loginUser);
    			
    		} else {  // 已有商场，修改商场
        		//商场序号
        		Integer storeSeq = storeBrand.getStoreSeq();
        		//查询门店所处商场
        		StoreEntity storeEntity = storeService.selectById(storeSeq);
    			storeEntity.setName(name);
    			storeEntity.setProvince(province);
    			storeEntity.setProvinceCode(provinceCode);
    			storeEntity.setCity(city);
    			storeEntity.setCityCode(cityCode);
    			storeEntity.setDistrict(district);
    			storeEntity.setDistrictCode(districtCode);
    			storeEntity.setDetailAddress(detailAddress);
    			storeService.updateById(storeEntity);
    		}

			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("操作失败");
		}
	}
	
	
	
	
	
	/**
     * 查询门店所有品牌
     */
    @Login
    @GetMapping("getStoreBrand")
    @ApiOperation("查询门店所有品牌")
    public R getStoreBrand(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
    		List<StoreBrandEntity> list = new ArrayList<StoreBrandEntity>();
    		
    		//根据门店序号获取商场品牌实体
    		StoreBrandEntity storeBrandEntity = storeService.getStoreBrandByShopSeq(loginUser.getUserAreaSeq());
    		
    		if(storeBrandEntity != null) {
	    		//商场序号
	    		Integer storeSeq = storeBrandEntity.getStoreSeq();
	    		//门店所处商场
	    		list = storeService.getStoreBrandListByStoreSeq(storeSeq);
    		}
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
	
	
	
    
    
	/**
	 * 新增或修改品牌
	 */
	@Login
	@PostMapping("addOrUpdateStoreBrand")
	@ApiOperation("新增或修改品牌")
	public R addOrUpdateStoreBrand(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("seq（修改时必传，否则为新增）") @RequestParam(value="seq", required=false) Integer seq,
			@ApiParam("品牌名称") @RequestParam("brandName") String brandName) {
		try {
			//判断权限
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
			
//    		Seq			int		0	0	0	-1	0	0		0	0	0	0			0			
//    		StoreSeq	int		0	0	0	0	0	0		0	0	0	0	商场序号		0			
//    		BrandName	varchar	50	0	0	0	0	0		0	0	0	0	品牌名称	Chinese_PRC_CI_AS	0			
//    		ShopSeq		int		0	0	-1	0	0	0		0	0	0	0	门店序号（伊伴品牌才会有，其他品牌没有门店序号）		0			
//    		InputTime	datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0			0			
//    		Del			int		0	0	0	0	0	0	((0))	0	0	0	0			0			
	
    		
			//判断是否已新增过商场
    		StoreBrandEntity storeBrand = storeService.getStoreBrandByShopSeq(loginUser.getUserAreaSeq());
    		if(storeBrand == null) {  //没有新增过
    			return R.error(HttpStatus.FORBIDDEN.value(), "请先维护商场信息");
    		}
    		
	        //商场序号
	        Integer storeSeq = storeBrand.getStoreSeq();
	        //查询门店所处商场
	        StoreBrandEntity storeBrandEntity = new StoreBrandEntity();
	        storeBrandEntity.setSeq(seq);
	        storeBrandEntity.setStoreSeq(storeSeq);
	        storeBrandEntity.setBrandName(brandName);
	        storeBrandService.insertOrUpdate(storeBrandEntity);
	        
			List<StoreBrandEntity> list = new ArrayList<StoreBrandEntity>();
			list.add(storeBrandEntity);
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("操作失败");
		}
	}
	
	
	
	/**
	 * 删除品牌
	 */
	@Login
	@PostMapping("deleteStoreBrand")
	@ApiOperation("删除品牌")
	public R deleteStoreBrand(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("seq") @RequestParam("seq") Integer seq) {
		try {
			//判断权限
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
	        storeBrandService.deleteById(seq);
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("删除失败");
		}
	}
	
	
	
	
    
}
