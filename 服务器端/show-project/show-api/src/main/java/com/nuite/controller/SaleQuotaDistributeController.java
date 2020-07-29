package com.nuite.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.nuite.entity.AreaEntity;
import com.nuite.entity.SaleQuotaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.SaleQuotaDistributeService;
import com.nuite.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 销售指标分配
 * @author yy
 * @date 2018-12-18 18:26:53
 */
@RestController
@RequestMapping("/api/saleQuotaDistribute")
@Api(tags="销售指标分配接口")
public class SaleQuotaDistributeController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private SaleQuotaDistributeService saleQuotaDistributeService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    
    
    /**
     * 查询上级给我分配的销售指标
     */
	@Login
	@GetMapping("getMySaleQuota")
	@ApiOperation("查询上级给我分配的销售指标")
	public R getMySaleQuota(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年份") @RequestParam("year") Integer year,
			@ApiParam("月份") @RequestParam("month") Integer month) {
		try {
			//判断权限
			if(loginUser.getRoleSeq() != 2 && loginUser.getRoleSeq() != 3 && loginUser.getRoleSeq() != 4) {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色没有上级分配的指标");
			}		
			
			SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
			saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
			saleQuotaEntity.setYear(year);
			saleQuotaEntity.setMonth(month);
			saleQuotaEntity.setAreaType(loginUser.getRoleSeq());
			saleQuotaEntity.setAreaSeq(loginUser.getUserAreaSeq());
			saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);

	        List<SaleQuotaEntity> list = new ArrayList<SaleQuotaEntity>();
	        if (saleQuotaEntity != null) {
	            list.add(saleQuotaEntity);
	        }
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
	
	
	
	
	
    /**
     * 查询我给下级分配的销售指标
     */
	@Login
	@GetMapping("getDistributedSaleQuota")
	@ApiOperation("查询我给下级分配的销售指标")
	public R getDistributedSaleQuota(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年份") @RequestParam("year") Integer year,
			@ApiParam("月份") @RequestParam("month") Integer month) {
		try {
			List<SaleQuotaEntity> saleQuotaEntityList = new ArrayList<SaleQuotaEntity>();
			//全国
			if(loginUser.getRoleSeq() == 1) {
				//获取所有大区
				List<AreaEntity> areaList = areaService.getFirstAreasByBrandSeq(loginUser.getBrandSeq());
				for(AreaEntity areaEntity : areaList) {
					SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
					saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
					saleQuotaEntity.setYear(year);
					saleQuotaEntity.setMonth(month);
					saleQuotaEntity.setAreaType(2);
					saleQuotaEntity.setAreaSeq(areaEntity.getSeq());
					saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
					if(saleQuotaEntity != null) {
						saleQuotaEntity.setAreaName(areaEntity.getAreaName());
						saleQuotaEntityList.add(saleQuotaEntity);
					}
				}
			} else if (loginUser.getRoleSeq() == 2){ //大区
				//获取大区内所有分公司
				List<AreaEntity> areaList = areaService.getSecondAreasByParentSeq(loginUser.getUserAreaSeq());
				for(AreaEntity areaEntity : areaList) {
					SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
					saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
					saleQuotaEntity.setYear(year);
					saleQuotaEntity.setMonth(month);
					saleQuotaEntity.setAreaType(3);
					saleQuotaEntity.setAreaSeq(areaEntity.getSeq());
					saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
					if(saleQuotaEntity != null) {
						saleQuotaEntity.setAreaName(areaEntity.getAreaName());
						saleQuotaEntityList.add(saleQuotaEntity);
					}
				}
			} else if (loginUser.getRoleSeq() == 3){ //分公司
				//获取分公司内所有门店
				List<ShopEntity> shopList = shopService.getShopsByAreaSeq(loginUser.getUserAreaSeq());
				for(ShopEntity shopEntity : shopList) {
					SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
					saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
					saleQuotaEntity.setYear(year);
					saleQuotaEntity.setMonth(month);
					saleQuotaEntity.setAreaType(4);
					saleQuotaEntity.setAreaSeq(shopEntity.getSeq());
					saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
					if(saleQuotaEntity != null) {
						saleQuotaEntity.setAreaName(shopEntity.getName());
						saleQuotaEntityList.add(saleQuotaEntity);
					}
				}
			} else {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色没有给下级分配指标");
			}
			
			return R.ok(saleQuotaEntityList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
	
	
	
    
    
    
	/**
	 * 批量下发销售指标
	 */
	@Login
	@PostMapping("distributeSaleQuotas")
	@ApiOperation("批量下发销售指标（全国用户可直接下发，其他用户下发后指标总额必须与上级下发的相等）")
	public R distributeSaleQuotas(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年份") @RequestParam("year") Integer year,
			@ApiParam("月份") @RequestParam("month") Integer month,
			@ApiParam("区域类型（2大区、3分公司、4门店）") @RequestParam("areaType") Integer areaType,
			@ApiParam("区域序号(逗号分隔)（账号下级的大区序号或分公司序号或门店序号 ）") @RequestParam("areaSeqs") List<Integer> areaSeqList,
			@ApiParam("销售指标(逗号分隔)") @RequestParam("quotas") List<BigDecimal> quotaList) {
		try {
			//判断权限
			if(loginUser.getRoleSeq() != 1 && loginUser.getRoleSeq() != 2 && loginUser.getRoleSeq() != 3) {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色不能分配销售指标");
			}
			
   		 	//判断用户账号权限
			if (loginUser.getRoleSeq() != areaType - 1) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
			if(areaSeqList.size() != quotaList.size()) {
				return R.error(HttpStatus.FORBIDDEN.value(), "参数有误");
			}
			
			//判断总额是否与上级下发的一致
			if(loginUser.getRoleSeq() != 1) {
				//查询上级给他分配的指标额
				SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
				saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq()); 
				saleQuotaEntity.setYear(year); 
				saleQuotaEntity.setMonth(month); 
				saleQuotaEntity.setAreaType(loginUser.getRoleSeq()); 
				saleQuotaEntity.setAreaSeq(loginUser.getUserAreaSeq()); 
				saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
				
				if(saleQuotaEntity == null) {
					return R.error(HttpStatus.FORBIDDEN.value(), "上级尚未下发当月销售指标总额");
				}
				
				BigDecimal total = BigDecimal.ZERO;
				//计算分配下去的总额
				for(BigDecimal quota : quotaList) {
					total = total.add(quota);
				}
				//判断总额是否与上级下发的一致
				if(total.compareTo(saleQuotaEntity.getQuota()) != 0) {
					return R.error(HttpStatus.FORBIDDEN.value(), "分配的指标总额与上级下发的不一致");
				}
			}
			
//			Seq			int			0	0	0	0	0	0		0	0	0	0	序号(主键)		-1		
			
//			BrandSeq	int			0	0	-1	0	0	0		0	0	0	0	品牌序号		0			
//			Year		int			0	0	-1	0	0	0		0	0	0	0	年份		0			
//			Month		int			0	0	-1	0	0	0		0	0	0	0	月份		0	
			
//			AreaType	int			0	0	-1	0	0	0		0	0	0	0	区域类型（1大区、2分公司、3门店）		0			
//			AreaSeq		int			0	0	-1	0	0	0		0	0	0	0	区域序号（大区、分公司、门店）		0		
			
//			Quota		decimal		12	2	-1	0	0	0		0	0	0	0	区域销售指标		0		
			
//			UserSeq		int			0	0	-1	0	0	0		0	0	0	0	下发人序号		0			
//			InputTime	datetime	0	0	-1	0	0	0		0	0	0	0	插入时间		0			
//			Del			int			0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
			
			
			/**   给下级分配销售指标   **/
			List<SaleQuotaEntity> saleQuotaList = new ArrayList<SaleQuotaEntity>();
			for(int i = 0 ; i < areaSeqList.size(); i++) {
				
				Integer areaSeq = areaSeqList.get(i);
				BigDecimal quota = quotaList.get(i);
				
				SaleQuotaEntity saleQuota = new SaleQuotaEntity();
				saleQuota.setBrandSeq(loginUser.getBrandSeq());
				saleQuota.setYear(year);
				saleQuota.setMonth(month);
				saleQuota.setAreaType(areaType);
				saleQuota.setAreaSeq(areaSeq);	//TODO 验证区域序号是否为用户直属下级
				saleQuota.setQuota(quota);
				saleQuota.setUserSeq(loginUser.getSeq());
				saleQuota.setInputTime(new Date());
				saleQuota.setDel(0);
				
				saleQuotaList.add(saleQuota);
			}
			
			saleQuotaDistributeService.distributeSaleQuota(saleQuotaList);
			
			//TODO 给下级发送推送
			return R.ok("分配成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("分配失败");
		}
	}
	
	
    
    
    
	
	/**
	 * 修改已下发的销售指标（全国用户可直接修改，其他用户修改后指标总额必须与上级下发的相等）
	 */
	@Login
	@PostMapping("changeDistributedSaleQuota")
	@ApiOperation("修改已下发的销售指标（全国用户可直接修改，其他用户修改后指标总额必须与上级下发的相等）")
	public R changeDistributedSaleQuota(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年份") @RequestParam("year") Integer year,
			@ApiParam("月份") @RequestParam("month") Integer month,
			@ApiParam("指标序号+修改后的指标组成的数组 例如:[{seq:1, quota:1000},{seq:2, quota:800},{...}]") @RequestParam("seqQuotasArr") String seqQuotasArr,
			HttpServletRequest request) {
		try {
			//判断权限
			if(loginUser.getRoleSeq() != 1 && loginUser.getRoleSeq() != 2 && loginUser.getRoleSeq() != 3) {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色不能分配销售指标");
			}
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> seqQuotasList = JSONArray.fromObject(seqQuotasArr);
			
			
			//判断总额是否与上级下发的一致
			if(loginUser.getRoleSeq() != 1) {
				//查询上级给他分配的指标额
				SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
				saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
				saleQuotaEntity.setYear(year);
				saleQuotaEntity.setMonth(month);
				saleQuotaEntity.setAreaType(loginUser.getRoleSeq());
				saleQuotaEntity.setAreaSeq(loginUser.getUserAreaSeq());
				saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
				
				if(saleQuotaEntity == null) {
					return R.error(HttpStatus.FORBIDDEN.value(), "上级尚未下发当月销售指标总额");
				}
	            
				//本次修改的区域，分配下去的总额
				BigDecimal total = BigDecimal.ZERO;
				List<Integer> seqList = new ArrayList<Integer>();
				for(Map<String, Object> map : seqQuotasList) {
					total = total.add(new BigDecimal(map.get("quota").toString()));
					seqList.add((Integer)map.get("seq"));
				}
				
				//加上 其他本次未修改的区域，已经分配的总额
				@SuppressWarnings("unchecked")
				List<SaleQuotaEntity> saleQuotaEntityList = (List<SaleQuotaEntity>) this.getDistributedSaleQuota(loginUser, year, month).get("result");
				for(SaleQuotaEntity saleQuota : saleQuotaEntityList) {
					if(!seqList.contains(saleQuota.getSeq())) {
						total = total.add(saleQuota.getQuota());
					}
				}
				
				
				//判断总额是否与上级下发的一致
				if(total.compareTo(saleQuotaEntity.getQuota()) != 0) {
					return R.error(HttpStatus.FORBIDDEN.value(), "分配的指标总额与上级下发的不一致");
				}
			}
			
//			Seq			int			0	0	0	0	0	0		0	0	0	0	序号(主键)		-1		
			
//			BrandSeq	int			0	0	-1	0	0	0		0	0	0	0	品牌序号		0			
//			Year		int			0	0	-1	0	0	0		0	0	0	0	年份		0			
//			Month		int			0	0	-1	0	0	0		0	0	0	0	月份		0	
			
//			AreaType	int			0	0	-1	0	0	0		0	0	0	0	区域类型（1大区、2分公司、3门店）		0			
//			AreaSeq		int			0	0	-1	0	0	0		0	0	0	0	区域序号（大区、分公司、门店）		0		
			
//			Quota		decimal		12	2	-1	0	0	0		0	0	0	0	区域销售指标		0		
			
//			UserSeq		int			0	0	-1	0	0	0		0	0	0	0	下发人序号		0			
//			InputTime	datetime	0	0	-1	0	0	0		0	0	0	0	插入时间		0			
//			Del			int			0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
			
			
			/** 修改已分配下去的销售指标   **/
			List<SaleQuotaEntity> saleQuotaList = new ArrayList<SaleQuotaEntity>();
			for(Map<String, Object> map : seqQuotasList) {
				
				SaleQuotaEntity saleQuota = new SaleQuotaEntity();
				saleQuota.setSeq((Integer)map.get("seq"));
				saleQuota.setQuota(new BigDecimal(map.get("quota").toString()));
				saleQuota.setUserSeq(loginUser.getSeq());
				saleQuota.setInputTime(new Date());

				saleQuotaList.add(saleQuota);
			}
			
			saleQuotaDistributeService.updateBatchById(saleQuotaList);
			return R.ok("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("修改失败");
		}
	}
    
	
	
	
	
    /**
     * 查询上级给我分配的，以及我给下级分配的销售指标 历史记录
     */
	@Login
	@GetMapping("saleQuotaHistory")
	@ApiOperation("查询上级给我分配的，以及我给下级分配的销售指标 历史记录")
	public R saleQuotaHistory(@ApiIgnore @LoginUser UserEntity loginUser) {
		try {
			
			//查询本品牌有销售指标的所有年份、月份
			List<Map<String, Object>> historyYearAndMonth = saleQuotaDistributeService.getHistoryYearAndMonthByBrandSeq(loginUser.getBrandSeq());
			if(historyYearAndMonth == null || historyYearAndMonth.size() == 0) {
				return R.error(HttpStatus.FORBIDDEN.value(), "暂无历史记录");
			}
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> resultMap;
			for(Map<String, Object> yearAndMonth : historyYearAndMonth) {
				resultMap = new HashMap<String, Object>();
				
				Integer year = (Integer) yearAndMonth.get("year");
				resultMap.put("year", year);
				Integer month = (Integer) yearAndMonth.get("month");
				resultMap.put("month", month);
				
				
				
				//1.上级给我分配的指标额
					//1.2非全国用户
				if(loginUser.getRoleSeq() == 2 || loginUser.getRoleSeq() == 3 || loginUser.getRoleSeq() == 4) {
					SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
					saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
					saleQuotaEntity.setYear(year);
					saleQuotaEntity.setMonth(month);
					saleQuotaEntity.setAreaType(loginUser.getRoleSeq());
					saleQuotaEntity.setAreaSeq(loginUser.getUserAreaSeq());
					saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
					if(saleQuotaEntity == null) {
						resultMap.put("mySaleQuota", "未分配");
					} else {
						resultMap.put("mySaleQuota", saleQuotaEntity.getQuota());
					}
				}
				
				
				//2.我给下级分配的指标
				List<SaleQuotaEntity> saleQuotaEntityList = new ArrayList<SaleQuotaEntity>();
				//全国
				if(loginUser.getRoleSeq() == 1) {
					//获取所有大区
					List<AreaEntity> areaList = areaService.getFirstAreasByBrandSeq(loginUser.getBrandSeq());
					BigDecimal mySaleQuota = BigDecimal.ZERO;
					for(AreaEntity areaEntity : areaList) {
						SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
						saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
						saleQuotaEntity.setYear(year);
						saleQuotaEntity.setMonth(month);
						saleQuotaEntity.setAreaType(2);
						saleQuotaEntity.setAreaSeq(areaEntity.getSeq());
						saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
						if(saleQuotaEntity != null) {
							saleQuotaEntity.setAreaName(areaEntity.getAreaName());
							saleQuotaEntityList.add(saleQuotaEntity);
							//1.1全国用户，查询分配下去的值，累加，为上级给我分配的指标额
							mySaleQuota = mySaleQuota.add(saleQuotaEntity.getQuota());
						}
					}
					resultMap.put("mySaleQuota", mySaleQuota);
					
				} else if (loginUser.getRoleSeq() == 2){ //大区
					//获取大区内所有分公司
					List<AreaEntity> areaList = areaService.getSecondAreasByParentSeq(loginUser.getUserAreaSeq());
					for(AreaEntity areaEntity : areaList) {
						SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
						saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
						saleQuotaEntity.setYear(year);
						saleQuotaEntity.setMonth(month);
						saleQuotaEntity.setAreaType(3);
						saleQuotaEntity.setAreaSeq(areaEntity.getSeq());
						saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
						if(saleQuotaEntity != null) {
							saleQuotaEntity.setAreaName(areaEntity.getAreaName());
							saleQuotaEntityList.add(saleQuotaEntity);
						}
					}
				} else if (loginUser.getRoleSeq() == 3){ //分公司
					//获取分公司内所有门店
					List<ShopEntity> shopList = shopService.getShopsByAreaSeq(loginUser.getUserAreaSeq());
					for(ShopEntity shopEntity : shopList) {
						SaleQuotaEntity saleQuotaEntity = new SaleQuotaEntity();
						saleQuotaEntity.setBrandSeq(loginUser.getBrandSeq());
						saleQuotaEntity.setYear(year);
						saleQuotaEntity.setMonth(month);
						saleQuotaEntity.setAreaType(4);
						saleQuotaEntity.setAreaSeq(shopEntity.getSeq());
						saleQuotaEntity = saleQuotaDistributeService.getSaleQuotaByEntity(saleQuotaEntity);
						if(saleQuotaEntity != null) {
							saleQuotaEntity.setAreaName(shopEntity.getName());
							saleQuotaEntityList.add(saleQuotaEntity);
						}
					}
				}
				
				resultMap.put("distributeSaleQuota", saleQuotaEntityList);
				resultList.add(resultMap);
			}
			
			return R.ok(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
	
	
	

}
