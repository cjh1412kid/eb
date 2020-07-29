package com.nuite.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.nuite.dao.UserAreaSubscribedDao;
import com.nuite.dao.UserModuleSubscribedDao;
import com.nuite.entity.ModuleEntity;
import com.nuite.entity.UserAreaSubscribedEntity;
import com.nuite.entity.UserEntity;
import com.nuite.entity.UserModuleSubscribedEntity;
import com.nuite.service.ModuleService;
import com.nuite.service.UserAreaSubscribedService;
import com.nuite.service.UserModuleSubscribedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版：用户订阅接口
 * @author yy
 * @date 2019-06-11 15:03:16
 */
@RestController
@RequestMapping("/api/product/userSubscribe")
@Api(tags="2019产品版：用户订阅接口")
public class UserSubscribeController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @Autowired
    private ModuleService moduleService;
    
    @Autowired
    private UserAreaSubscribedDao userAreaSubscribedDao;
    
    @Autowired
    private UserAreaSubscribedService userAreaSubscribedService;
    
    
    @Autowired
    private UserModuleSubscribedDao userModuleSubscribedDao;
    
    @Autowired
    private UserModuleSubscribedService userModuleSubscribedService;

    
    
	/**
     * 获取用户已订阅区域
     */
    @Login
    @GetMapping("getUserAreaSubscribed")
    @ApiOperation("获取用户已订阅区域")
    public R getUserAreaSubscribed(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
    		List<UserAreaSubscribedEntity> userAreaSubscribedList = userAreaSubscribedService.getUserAreaSubscribed(loginUser.getSeq());
    		
    		return R.ok(userAreaSubscribedList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
	/**
     * 用户添加订阅区域
     */
    @Login
    @PostMapping("addUserAreaSubscribed")
    @ApiOperation("用户添加订阅区域")
    public R addUserAreaSubscribed(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("区域类型：1:全国 2:大区 3:分公司 4:门店") @RequestParam("areaType") Integer areaType,
			@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
			@ApiParam("区域名称") @RequestParam("areaName") String areaName) {
    	try {
    		
    		//判断用户是否已经订阅过此区域
    		UserAreaSubscribedEntity userAreaSubscribedEntity = new UserAreaSubscribedEntity();
    		userAreaSubscribedEntity.setUserSeq(loginUser.getSeq());
    		userAreaSubscribedEntity.setAreaType(areaType);
    		userAreaSubscribedEntity.setAreaSeq(areaSeq);
    		UserAreaSubscribedEntity userAreaSubscribedExist = userAreaSubscribedDao.selectOne(userAreaSubscribedEntity);
    		if(userAreaSubscribedExist != null) {
        		return R.error("已经订阅过此区域");
    		}
    		
    		//添加用户订阅区域
//    		Seq				int	0	0	0	-1	0	0		0	0	0	0	序号(主键)		-1			
//    		User_Seq		int	0	0	-1	0	0	0		0	0	0	0	角色序号		0			
//    		AreaType		int	0	0	-1	0	0	0		0	0	0	0	自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0		0			
//    		AreaSeq			int	0	0	-1	0	0	0		0	0	0	0	创建人序号		0			
//    		AreaName		varchar	50	0	-1	0	0	0		0	0	0	0	真实姓名	Chinese_PRC_CI_AS	0			
//    		InputTime		datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	插入时间		0			
//    		Del				int	0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
    		userAreaSubscribedEntity.setAreaName(areaName);
    		userAreaSubscribedEntity.setInputTime(new Date());
    		userAreaSubscribedEntity.setDel(0);
    		
    		userAreaSubscribedDao.insert(userAreaSubscribedEntity);
    		
    		return R.ok("添加成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 用户删除订阅区域
     */
    @Login
    @PostMapping("deleteUserAreaSubscribed")
    @ApiOperation("用户删除订阅区域")
    public R deleteUserAreaSubscribed(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("用户订阅区域seq") @RequestParam("userAreaSubscribedSeq") Integer userAreaSubscribedSeq) {
    	try {
    		
    		userAreaSubscribedDao.deleteById(userAreaSubscribedSeq);
    		
    		return R.ok("删除成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    
    
	/**
     * 获取所有模块
     */
    @Login
    @GetMapping("getAllModules")
    @ApiOperation("获取所有模块")
    public R getAllModules(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		
//    		[{topicName : 试穿专题, modules : [ {moduleName : 试穿排行, moduleSeq : 1}, {...}   ]}, {...}]
    		
    		//获取所有专题
    		List<Object> topicList = moduleService.getAllTopics();
    		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    		for(Object topicName : topicList) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("topicName", topicName.toString());
    			
    			List<Map<String, Object>> moduleList = moduleService.getTopicAllModules(topicName.toString());
    			map.put("modules", moduleList);
    			
    			resultList.add(map);
    		}
    		
    		return R.ok(resultList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
    
	/**
     * 获取用户的某个区域已订阅模块
     */
    @Login
    @GetMapping("getUserAreaSubscribedModules")
    @ApiOperation("获取用户的某个区域已订阅模块")
    public R getUserAreaSubscribedModules(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("用户订阅区域seq") @RequestParam("userAreaSubscribedSeq") Integer userAreaSubscribedSeq) {
    	try {
    		List<Object> moduleSeqList = userModuleSubscribedService.getUserAreaSubscribedModuleSeqs(loginUser.getSeq(), userAreaSubscribedSeq);
    		
    		List<ModuleEntity> list = moduleService.getModulesBySeqs(moduleSeqList);
    		
    		return R.ok(list);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 用户某个区域添加订阅模块
     */
    @Login
    @PostMapping("addUserAreaSubscribedModules")
    @ApiOperation("用户某个区域添加订阅模块")
    public R addUserAreaSubscribedModules(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("用户订阅区域seq") @RequestParam("userAreaSubscribedSeq") Integer userAreaSubscribedSeq,
    		@ApiParam("模块seq") @RequestParam("moduleSeq") Integer moduleSeq) {
    	try {
    		
    		//判断用户区域是否已经订阅过此模块
    		UserModuleSubscribedEntity userModuleSubscribedEntity = new UserModuleSubscribedEntity();
    		userModuleSubscribedEntity.setUserSeq(loginUser.getSeq());
    		userModuleSubscribedEntity.setUserAreaSubscribedSeq(userAreaSubscribedSeq);
    		userModuleSubscribedEntity.setModuleSeq(moduleSeq);
    		UserModuleSubscribedEntity userModuleSubscribedExist = userModuleSubscribedDao.selectOne(userModuleSubscribedEntity);
    		if(userModuleSubscribedExist != null) {
        		return R.error("已经订阅过此模块");
    		}
    		
    		//添加用户区域订阅模块
    		userModuleSubscribedEntity.setInputTime(new Date());
    		userModuleSubscribedEntity.setDel(0);
//    		Seq						int	0	0	0	-1	0	0		0	0	0	0	序号(主键)		-1			
//    		User_Seq				int	0	0	-1	0	0	0		0	0	0	0	用户序号		0			
//    		UserAreaSubscribed_Seq	int	0	0	-1	0	0	0		0	0	0	0	用户所订阅区域序号（不是UserAreaSeq用户所属区域序号）		0			
//    		Module_Seq				int	0	0	-1	0	0	0		0	0	0	0	模块序号		0			
//    		InputTime				datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	插入时间		0			
//    		Del						int	0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
    		
    		userModuleSubscribedDao.insert(userModuleSubscribedEntity);
    		
    		return R.ok("添加成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
	/**
     * 用户某个区域删除订阅模块
     */
    @Login
    @PostMapping("deleteUserAreaSubscribedModules")
    @ApiOperation("用户某个区域删除订阅模块")
    public R deleteUserAreaSubscribedModules(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("用户订阅区域seq") @RequestParam("userAreaSubscribedSeq") Integer userAreaSubscribedSeq,
    		@ApiParam("模块seq") @RequestParam("moduleSeq") Integer moduleSeq) {
    	try {
    		
    		userModuleSubscribedService.deleteUserAreaSubscribedModules(loginUser.getSeq(), userAreaSubscribedSeq, moduleSeq);
    		
    		return R.ok("删除成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage(), e);
    		return R.error("服务器开小差了");
    	}
    	
    }
    
    
    
    
}
