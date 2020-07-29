package com.nuite.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AreaService;
import com.nuite.service.ShopService;
import com.nuite.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 用户管理接口
 * @author yy
 * @date 2018-11-23 13:27:49
 */
@RestController
@RequestMapping("/api/user")
@Api(tags="用户管理接口")
public class UserController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShopService shopService;
    
    
    
	/**
     * 获取用户所有直属下级区域
     */
    @Login
    @GetMapping("areaList")
    @ApiOperation("获取用户所有直属下级区域：用户角色为全国返回全国所有大区列表，为大区返回大区范围内的所有分公司列表，为分公司返回分公司范围内的所有门店列表")
    public R areaList(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		List<Map<String, Object>> areaList;
			//全国
			if(loginUser.getRoleSeq() == 1) {
				//获取所有大区
				areaList = areaService.getFirstAreasMapByBrandSeq(loginUser.getBrandSeq());
			} else if (loginUser.getRoleSeq() == 2){ //大区
				//获取大区内所有分公司
				areaList = areaService.getSecondAreasMapByParentSeq(loginUser.getUserAreaSeq());
			} else if (loginUser.getRoleSeq() == 3){ //分公司
				//获取分公司内所有门店
				areaList = shopService.getShopsMapByAreaSeq(loginUser.getUserAreaSeq());
			} else {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色不能创建账号");
			}
			
			return R.ok(areaList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
    
	/**
	 * 创建直属下级用户账号
	 */
	@Login
	@PostMapping("createUser")
	@ApiOperation("创建直属下级用户账号")
	public R createUser(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("账号") @RequestParam("userName") String userName,
			@ApiParam("密码") @RequestParam(value = "password", required = false) String password,
			@ApiParam("区域序号（账号所属的大区序号或分公司序号或门店序号 ）") @RequestParam("userAreaSeq") Integer userAreaSeq,
			@ApiParam("姓名") @RequestParam(value = "realName", required = false) String realName,
			@ApiParam("电话") @RequestParam(value = "telephone", required = false) String telephone,
			HttpServletRequest request) {
		try {
			
//			Seq				int		0	0	0	0	0	0		0	0	0	0	序号(主键)		-1			
//			UserName		varchar	50	0	0	0	0	0		0	0	0	0	账号	Chinese_PRC_CI_AS	0			
//			Password		varchar	100	0	-1	0	0	0		0	0	0	0	密码	Chinese_PRC_CI_AS	0			
//			RoleSeq			int		0	0	-1	0	0	0		0	0	0	0	角色序号		0			
//			UserAreaSeq		int		0	0	-1	0	0	0		0	0	0	0	自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0		0			
//			CreateUserSeq	int		0	0	-1	0	0	0		0	0	0	0	创建人序号		0			
//			RealName		varchar	50	0	-1	0	0	0		0	0	0	0	真实姓名	Chinese_PRC_CI_AS	0			
//			Telephone		varchar	20	0	-1	0	0	0		0	0	0	0	电话	Chinese_PRC_CI_AS	0			
//			HeadImg			varchar	100	0	-1	0	0	0		0	0	0	0	头像	Chinese_PRC_CI_AS	0			
//			IsUseful		int		0	0	0	0	0	0		0	0	0	0	是否有效( 0 : 无效、 1 : 有效 )		0			
//			InputTime		datetime0	0	-1	0	0	0		0	0	0	0	插入时间		0			
//			Del				int		0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			

			UserEntity userEntity = new UserEntity();
			userEntity.setUserName(userName);
			if(StringUtils.isBlank(password)) {
				password = "123";
			}
			userEntity.setPassword(DigestUtils.sha256Hex(password));
			
			//-1 超级管理员0后台管理员1全国用户角色 2大区角色 3分公司角色 4门店角色
			if(loginUser.getRoleSeq() != 1 && loginUser.getRoleSeq() != 2 && loginUser.getRoleSeq() != 3) {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色不能创建账号");
			}
			userEntity.setRoleSeq(loginUser.getRoleSeq() + 1);
			
			//TODO 验证所选区域序号是否为用户直属下级
			userEntity.setUserAreaSeq(userAreaSeq);
			
			userEntity.setCreateUserSeq(loginUser.getSeq());
			userEntity.setRealName(realName);
			userEntity.setTelephone(telephone);
			userEntity.setIsUseful(1);
			userEntity.setInputTime(new Date());
			userEntity.setDel(0);
			userService.createUser(userEntity, loginUser.getBrandSeq());

			return R.ok("创建账号成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("创建账号失败");
		}
	}
	
    
	
	/**
	 * 启用、停用直属下级用户账号
	 */
	@Login
	@PostMapping("disableUser")
	@ApiOperation("启用、停用直属下级用户账号")
	public R disableUser(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("下级用户账号Seq") @RequestParam("userSeq") Integer userSeq,
			@ApiParam("操作:0停用 1启用") @RequestParam("isUseful") Integer isUseful,
			HttpServletRequest request) {
		try {
			UserEntity userEntity = userService.selectById(userSeq);
			if(userEntity.getRoleSeq() != loginUser.getRoleSeq() + 1) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			//TODO 判断是否为自己的下级账号
			userEntity.setIsUseful(isUseful);
			userService.updateUser(userEntity);

			return R.ok("设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("设置失败");
		}
	}
	
	
	
	/**
	 * 删除直属下级用户账号
	 */
	@Login
	@PostMapping("deleteUser")
	@ApiOperation("删除直属下级用户账号")
	public R deleteUser(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("下级用户账号Seq") @RequestParam("userSeq") Integer userSeq,
			HttpServletRequest request) {
		try {
			//TODO 判断是否为自己的下级账号
			userService.deleteById(userSeq);
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("删除失败");
		}
	}
	
	
	
	
	/**
     * 我创建的下级用户账号列表
     */
	@Deprecated
    @Login
    @GetMapping("myCreatedUserList")
    @ApiOperation("我创建的下级用户账号列表")
    public R myCreatedUserList(@ApiIgnore @RequestAttribute("userSeq") Integer userSeq,
    		@ApiParam("起始条数") @RequestParam("start") Integer start,
    		@ApiParam("总条数") @RequestParam("num") Integer num) {
    	try {
    		//查询自己创建的下级账号列表
    		List<UserEntity> createdUserList = userService.getCreatedUserList(userSeq, start, num);
    		
    		for(UserEntity user : createdUserList) {
    			//添加用户头像
    			user.setHeadImg(getUserPictureUrl(user.getSeq().toString()) + user.getHeadImg());
    		}
    		
			return R.ok(createdUserList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
    
    
	
	/**
     * 全部直属下级用户账号列表
     */
    @Login
    @GetMapping("createdUserList")
    @ApiOperation("全部直属下级用户账号列表")
    public R getCreatedUserList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("起始条数") @RequestParam("start") Integer start,
    		@ApiParam("总条数") @RequestParam("num") Integer num) {
    	try {
    		List<Integer> areaSeqList = new ArrayList<Integer>();
    		//获取所有直属下级区域
			if(loginUser.getRoleSeq() == 1) {
				//获取所有大区
				List<AreaEntity> areaList = areaService.getFirstAreasByBrandSeq(loginUser.getBrandSeq());
				for(AreaEntity areaEntity : areaList) {
					areaSeqList.add(areaEntity.getSeq());
				}
			} else if (loginUser.getRoleSeq() == 2){ //大区
				//获取大区内所有分公司
				List<AreaEntity> areaList = areaService.getSecondAreasByParentSeq(loginUser.getUserAreaSeq());
				for(AreaEntity areaEntity : areaList) {
					areaSeqList.add(areaEntity.getSeq());
				}
			} else if (loginUser.getRoleSeq() == 3){ //分公司
				//获取分公司内所有门店
				List<ShopEntity> shopList = shopService.getShopsByAreaSeq(loginUser.getUserAreaSeq());
				for(ShopEntity shopEntity : shopList) {
					areaSeqList.add(shopEntity.getSeq());
				}
			} else {
				return R.error(HttpStatus.FORBIDDEN.value(), "此角色没有创建下级用户账号");
			}
    		
    		//查询所以下级账号列表
    		List<UserEntity> createdUserList = userService.getAllSubUserList(loginUser, areaSeqList, start, num);
    		
    		for(UserEntity user : createdUserList) {
    			//添加用户头像
    			user.setHeadImg(getUserPictureUrl(user.getSeq().toString()) + user.getHeadImg());
    		}
    		
			return R.ok(createdUserList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }
	
	
    
	/**
	 * 修改用户基本信息
	 */
	@Login
	@PostMapping("updateUserInfo")
	@ApiOperation("修改用户基本信息")
	public R updateUserInfo(@ApiIgnore @RequestAttribute("userSeq") Integer userSeq,
			@ApiParam("姓名") @RequestParam(value = "realName", required = false) String realName,
			@ApiParam("电话") @RequestParam(value = "telephone", required = false) String telephone,
			HttpServletRequest request) {
		try {
			if(realName != null || telephone != null) {
				UserEntity userEntity = new UserEntity();
				userEntity.setSeq(userSeq);
				if(realName != null && !realName.equals("")) {
					userEntity.setRealName(realName);
				}
				if(telephone != null && !telephone.equals("")) {
					userEntity.setTelephone(telephone);
				}
				userService.updateUser(userEntity);
			}
			return R.ok("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("修改失败");
		}
	}



	/**
	 * 修改用户密码
	 */
	@Login
	@PostMapping("changePassword")
	@ApiOperation("修改用户密码")
	public R changePassword(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("原密码") @RequestParam("oldPassword") String oldPassword,
			@ApiParam("新密码") @RequestParam("newPassword") String newPassword,
			HttpServletRequest request) {
		try {
			//判断密码是否正确
			if(!DigestUtils.sha256Hex(oldPassword).equals(loginUser.getPassword())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "密码错误");
			}
			UserEntity userEntity = new UserEntity();
			userEntity.setSeq(loginUser.getSeq());
			userEntity.setPassword(DigestUtils.sha256Hex(newPassword));
			userService.updateUser(userEntity);
			return R.ok("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("修改失败");
		}
	}
	
	
	
	/**
	 * 更换用户头像
	 */
	@Login
	@PostMapping("changeHeadImg")
	@ApiOperation("更换用户头像")
	public R changeHeadImg(@ApiIgnore @RequestAttribute("userSeq") Integer userSeq,
			@ApiParam("用户头像") @RequestParam("headImg") MultipartFile headImg,
			HttpServletRequest request) {
		try {
			UserEntity userEntity = new UserEntity();
			userEntity.setSeq(userSeq);
			userEntity.setHeadImg(upLoadFile(userSeq, getUserUploadUrl(request, userSeq.toString()), headImg));
			userService.updateUser(userEntity);
			
			Map<String, Object> map = new HashMap<>();
			map.put("headImg", getUserPictureUrl(userSeq.toString()) + userEntity.getHeadImg());
			return R.ok(map).put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("修改失败");
		}
	}
	
    
    

}
