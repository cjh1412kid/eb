package com.nuite.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.ShopService;
import com.nuite.utils.ConfigUtils;


public class BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected ConfigUtils configUtils;
	
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;


	//基础库访问目录
	private String baseDir() {
		return configUtils.getPictureUrl() + "/";
	}
	
	//基础库上传目录
	private String baseUploadDir(HttpServletRequest request) {
		//tomcat所在目录
	    String tomcatPath = System.getProperty("catalina.home");
	    String filePath = tomcatPath + File.separator + "webapps" + File.separator;
	    
		String uploadProject = filePath + configUtils.getPictureUploadProject() + File.separator;
		return uploadProject;
	}
	

	
	
	//访问路径
	/**
	 * 用户信息图片路径
	 * @param folder
	 * @return
	 */
	protected String getUserPictureUrl(String folder) {
		return baseDir() + configUtils.getNwUser() + "/" + folder + "/";
	}
	
	
	/**
	 * 鞋子图片路径
	 * @param folder
	 * @return
	 */
	public String getShoePictureUrl(Integer periodSeq, String shoeId) {
		String baseUrl = "http://www.nuite.com.cn/ShoesManage/Resource/ShoesResource/ShoesIcons";
		return baseUrl + "/" + periodSeq + "/" + shoeId + ".jpg";
	}
	
	
	/**
	 * 店员图片路径
	 * @param folder
	 * @return
	 */
	protected String getAssistantPictureUrl(String folder) {
		return baseDir() + configUtils.getAssistant() + "/" + folder + "/";
	}
	
	
	/**
	 * 轮播图图片路径
	 * @param folder
	 * @return
	 */
	protected String getHomeCarouselPictureUrl(String folder) {
		return baseDir() + configUtils.getSowingMap() + "/" + folder + "/";
	}
	
	
	
	
	
	
	//上传路径
	/**
	 * 用户图片上传路径
	 * @param request
	 * @param folder
	 * @return
	 */
	protected String getUserUploadUrl(HttpServletRequest request, String folder) {
		return baseUploadDir(request) + configUtils.getNwUser() + File.separator + folder + File.separator;
	}
	
	
	/**
	 * 店员图片上传路径
	 * @param request
	 * @param folder
	 * @return
	 */
	protected String getAssistantUploadUrl(HttpServletRequest request, String folder) {
		return baseUploadDir(request) + configUtils.getAssistant() + File.separator + folder + File.separator;
	}
	
	
	
	
	/**
	 * 上传文件，返回文件名
	 * 
	 * @param userSeq
	 * @param dir
	 * @param img
	 * @return
	 * @throws IOException
	 */
	protected String upLoadFile(Integer userSeq, String imgDir, MultipartFile img) throws IOException {

		// 存放目录
		File fileDir = new File(imgDir);
		// 如果目录不存在就创建
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		// 原文件名
		String originalFilename = img.getOriginalFilename();
		logger.info("originalFilename:" + originalFilename);
		// 重新定义文件名
		String fileName = userSeq + "_" + System.currentTimeMillis() + "_" + originalFilename;
		logger.info("fileName:" + fileName);
		// 上传文件
		File file = new File(fileDir, fileName);
		file.createNewFile();
		img.transferTo(file);
		logger.info("AbsolutePath:" + file.getAbsolutePath());

		return fileName;
	}
	
	
	
	
	
	/**
	 * 根据用户权限，获取安装设备的门店序号List
	 * @param loginUser
	 * @return
	 */
	protected List<Integer> getShopEntityListByLoginUser(UserEntity loginUser) {
		List<ShopEntity> shopList;
		//全国
		if(loginUser.getRoleSeq() == 1) {
			//获取品牌所有门店
			shopList = shopService.getShopsByBrandSeq(loginUser.getBrandSeq());
			
		} else if(loginUser.getRoleSeq() == 2) { //大区
			//1.获取大区内所有分公司
			List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(loginUser.getUserAreaSeq());
			  //分公司序号列表
			List<Integer> secondAreaSeqList = new ArrayList<Integer>();
			for(AreaEntity area : secondAreaList) {
				secondAreaSeqList.add(area.getSeq());
			}
			
			//2.根据分公司序号列表查询门店
			shopList = shopService.getShopsByAreaSeqList(secondAreaSeqList);
			
		} else if(loginUser.getRoleSeq() == 3) { //分公司
			//根据分公司序号查询门店
			shopList = shopService.getShopsByAreaSeq(loginUser.getUserAreaSeq());
			
		} else if(loginUser.getRoleSeq() == 4) { //门店
			shopList = new ArrayList<ShopEntity>();
			shopList.add(shopService.selectById(loginUser.getUserAreaSeq()));
			
		} else {
			return null;
		}
		
		//组装门店序号List
		List<Integer> shopSeqList = new ArrayList<Integer>();
		for(ShopEntity shopEntity : shopList) {
			//没有安装设备的门店不统计
			if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
					&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
			
				shopSeqList.add(shopEntity.getSeq());
			}
		}
		return shopSeqList;
	}
	
	
	/**
	 * 根据区域筛选条件，获取安装设备的门店序号List
	 * @param type  统计范围：1:全国 2:大区 3:分公司 4:门店
	 * @param areaSeqs 统计范围对应的序号List
	 * @param brandSeq 品牌序号
	 * @return
	 */
	protected List<Integer> getShopSeqListByAreaSeqs(Integer type, List<Integer> areaSeqs, Integer brandSeq) {
		
		//所有要查询的门店
		List<ShopEntity> shopList;
		//全国
		if(type == 1) {
			//获取品牌所有门店
			shopList = shopService.getShopsByBrandSeq(brandSeq);
			
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
			return null;
		}
		
		
		//组装门店序号List
		List<Integer> shopSeqList = new ArrayList<Integer>();
		for(ShopEntity shopEntity : shopList) {
			//没有安装设备的门店不统计
			if(shopEntity.getInstallDate() != null && shopEntity.getLat() != null && shopEntity.getLat().compareTo(BigDecimal.valueOf(0)) > 0 
					&& shopEntity.getLng() != null && shopEntity.getLng().compareTo(BigDecimal.valueOf(0)) > 0) {
			
				shopSeqList.add(shopEntity.getSeq());
			}
		}
		return shopSeqList;
		
		
	}
	
	
	
	
	/**
	 * 根据区域筛选条件，获取全部的门店详细（包括）,并按
	 * @param type  统计范围：1:全国 2:大区 3:分公司 4:门店
	 * @param areaSeqs 统计范围对应的序号List
	 * @param brandSeq 品牌序号
	 * @return
	 */
	protected List<Map<String, Object>> getAllGroupShopListByAreaSeqs(Integer type, List<Integer> areaSeqs, Integer brandSeq) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		//全国
		if(type == 1) {
			//查询全国所有大区
			List<AreaEntity> firstAreaList = areaService.getFirstAreasByBrandSeq(brandSeq);
			for(AreaEntity firstArea : firstAreaList) {
				String firstAreaName = firstArea.getAreaName(); //大区名称
				
				//获取所有分公司
	        	List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(firstArea.getSeq());
				for(AreaEntity secondArea : secondAreaList) {
					String secondAreaName = secondArea.getAreaName(); //分公司名称
					
		    		//根据分公司序号查询门店
					List<ShopEntity> shopList = shopService.getShopsByAreaSeq(secondArea.getSeq());
					for(ShopEntity shopEntity : shopList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("firstAreaName", firstAreaName);
						map.put("secondAreaName", secondAreaName);
						map.put("shopName", shopEntity.getName());
						map.put("shopSeq", shopEntity.getSeq());
						list.add(map);
					}
					
				}
				
			}
			
		} else if(type == 2 && areaSeqs.size() > 0) { //大区
			
			//根据大区序号List查询大区
			List<AreaEntity> firstAreaList = areaService.selectBatchIds(areaSeqs);
			for(AreaEntity firstArea : firstAreaList) {
				String firstAreaName = firstArea.getAreaName(); //大区名称
				
				//获取所有分公司
	        	List<AreaEntity> secondAreaList = areaService.getSecondAreasByParentSeq(firstArea.getSeq());
				for(AreaEntity secondArea : secondAreaList) {
					String secondAreaName = secondArea.getAreaName(); //分公司名称
					
		    		//根据分公司序号查询门店
					List<ShopEntity> shopList = shopService.getShopsByAreaSeq(secondArea.getSeq());
					for(ShopEntity shopEntity : shopList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("firstAreaName", firstAreaName);
						map.put("secondAreaName", secondAreaName);
						map.put("shopName", shopEntity.getName());
						map.put("shopSeq", shopEntity.getSeq());
						list.add(map);
					}
					
				}
				
			}
			
		} else if(type == 3 && areaSeqs.size() > 0) { //分公司
			//根据分公司序号获取大区
			List<AreaEntity> secondAreaList = areaService.selectBatchIds(areaSeqs);
			List<Integer> firstAreaSeqList = new ArrayList<Integer>();
			for(AreaEntity secondArea : secondAreaList) {
				firstAreaSeqList.add(secondArea.getParentSeq());
			}
			
			//根据大区序号List查询大区
			List<AreaEntity> firstAreaList = areaService.selectBatchIds(firstAreaSeqList);
			for(AreaEntity firstArea : firstAreaList) {
				String firstAreaName = firstArea.getAreaName(); //大区名称
				
				//循环所有分公司挑出大区内的分公司
				for(AreaEntity secondArea : secondAreaList) {
					if(secondArea.getParentSeq().equals(firstArea.getSeq())) {
						String secondAreaName = secondArea.getAreaName(); //分公司名称
						
			    		//根据分公司序号查询门店
						List<ShopEntity> shopList = shopService.getShopsByAreaSeq(secondArea.getSeq());
						for(ShopEntity shopEntity : shopList) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("firstAreaName", firstAreaName);
							map.put("secondAreaName", secondAreaName);
							map.put("shopName", shopEntity.getName());
							map.put("shopSeq", shopEntity.getSeq());
							list.add(map);
						}
					}
				}
				
			}
			
		} else if(type == 4 && areaSeqs.size() > 0) { //门店
			
			//根据门店序号获取分公司序号
			List<ShopEntity> shopList = shopService.selectBatchIds(areaSeqs);
			List<Integer> secondAreaSeqList = new ArrayList<Integer>();
			for(ShopEntity shopEntity : shopList) {
				secondAreaSeqList.add(shopEntity.getAreaSeq());
			}
			
			//根据分公司序号获取大区
			List<AreaEntity> secondAreaList = areaService.selectBatchIds(secondAreaSeqList);
			List<Integer> firstAreaSeqList = new ArrayList<Integer>();
			for(AreaEntity secondArea : secondAreaList) {
				firstAreaSeqList.add(secondArea.getParentSeq());
			}
			
			//根据大区序号List查询大区
			List<AreaEntity> firstAreaList = areaService.selectBatchIds(firstAreaSeqList);
			for(AreaEntity firstArea : firstAreaList) {
				String firstAreaName = firstArea.getAreaName(); //大区名称
				
				//循环所有分公司挑出大区内的分公司
				for(AreaEntity secondArea : secondAreaList) {
					if(secondArea.getParentSeq().equals(firstArea.getSeq())) {
						String secondAreaName = secondArea.getAreaName(); //分公司名称
						
			    		//循环门店挑出分公司内的门店
						for(ShopEntity shopEntity : shopList) {
							if(shopEntity.getAreaSeq().equals(secondArea.getSeq())) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("firstAreaName", firstAreaName);
								map.put("secondAreaName", secondAreaName);
								map.put("shopName", shopEntity.getName());
								map.put("shopSeq", shopEntity.getSeq());
								list.add(map);
							}
						}
					}
				}
				
			}
			
		} else {
			return null;
		}
		
		return list;
		
	}
	

}
