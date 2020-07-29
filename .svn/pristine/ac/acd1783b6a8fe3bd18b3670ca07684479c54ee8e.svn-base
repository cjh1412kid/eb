package com.nuite.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.AssistantEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.AssistantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 店员（导购）管理
 * @author yy
 * @date 2018-12-27 11:04:07
 */
@RestController
@RequestMapping("/api/report/assistant")
@Api(tags="报表 - 店员（导购）管理")
public class AssistantController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private AssistantService assistantService;
    
    
    
	/**
     * 门店全部店员列表
     */
    @Login
    @GetMapping("assistantList")
    @ApiOperation("门店全部店员列表")
    public R getCreatedUserList(@ApiIgnore @LoginUser UserEntity loginUser,
    		@ApiParam("起始条数") @RequestParam("start") Integer start,
    		@ApiParam("总条数") @RequestParam("num") Integer num) {
    	try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
    		//查询门店所有店员账号列表
    		List<AssistantEntity> assistantList = assistantService.getAssistantList(loginUser.getUserAreaSeq(), start, num);
    		
    		for(AssistantEntity assistant : assistantList) {
    			//添加用户头像
    			assistant.setHeadImg(getAssistantPictureUrl(assistant.getSeq().toString()) + assistant.getHeadImg());
    		}
    		
			return R.ok(assistantList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}

    }

	
	
	
    
	/**
	 * 新增或修改店员
	 */
	@Login
	@PostMapping("createOrUpdateAssistant")
	@ApiOperation("新增或修改店员")
	public R createOrUpdateAssistant(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("seq（修改时必传）") @RequestParam(value = "assistantSeq", required = false) Integer assistantSeq,
			@ApiParam("姓名") @RequestParam("name") String name,
			@ApiParam("级别") @RequestParam(value = "assistantLevel", required = false) String assistantLevel,
			@ApiParam("电话") @RequestParam(value = "telephone", required = false) String telephone,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
    		
//			Seq				int		0	0	0	0	0	0		0	0	0	0	序号(主键)		-1			
//			ShopSeq			int		0	0	-1	0	0	0		0	0	0	0	门店序号		0	
			
//			Name			varchar	50	0	-1	0	0	0		0	0	0	0	姓名			Chinese_PRC_CI_AS	0			
//			AssistantLevel	varchar	50	0	-1	0	0	0		0	0	0	0	级别			Chinese_PRC_CI_AS	0			
//			Telephone		varchar	20	0	-1	0	0	0		0	0	0	0	电话			Chinese_PRC_CI_AS	0	
			
//			HeadImg			varchar	100	0	-1	0	0	0		0	0	0	0	头像			Chinese_PRC_CI_AS	0			
//			IsUseful		int		0	0	-1	0	0	0		0	0	0	0	是否有效( 0 : 无效、1 : 有效 )		0			
//			CreateUserSeq	int		0	0	-1	0	0	0		0	0	0	0	创建人		0			
//			InputTime		datetime0	0	-1	0	0	0		0	0	0	0	插入时间		0			
//			Del				int		0	0	0	0	0	0		0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0		
    		
    		
    		AssistantEntity assistantEntity;
    		if(assistantSeq != null) {  //修改
				assistantEntity = assistantService.selectById(assistantSeq);
				if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
					return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
				}
				assistantEntity = new AssistantEntity();
				assistantEntity.setSeq(assistantSeq);
				assistantEntity.setName(name);
				assistantEntity.setAssistantLevel(assistantLevel);
				assistantEntity.setTelephone(telephone);
				
    		} else {  //新增
    			assistantEntity = new AssistantEntity();
    			assistantEntity.setShopSeq(loginUser.getUserAreaSeq());
    			assistantEntity.setName(name);
    			assistantEntity.setAssistantLevel(assistantLevel);
    			assistantEntity.setTelephone(telephone);
    			assistantEntity.setIsUseful(1);
    			assistantEntity.setCreateUserSeq(loginUser.getSeq());
    			assistantEntity.setInputTime(new Date());
    			assistantEntity.setDel(0);
    		}
			assistantService.insertOrUpdate(assistantEntity);

			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("操作失败");
		}
	}
	
    
	
	/**
	 * 启用、停用店员
	 */
	@Login
	@PostMapping("disableAssistant")
	@ApiOperation("启用、停用店员")
	public R disableAssistant(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员Seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("操作:0停用 1启用") @RequestParam("isUseful") Integer isUseful,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
			AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			assistantEntity = new AssistantEntity();
			assistantEntity.setSeq(assistantSeq);
			assistantEntity.setIsUseful(isUseful);
			assistantService.updateById(assistantEntity);

			return R.ok("设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("设置失败");
		}
	}
	
	
	
	/**
	 * 更换店员头像
	 */
	@Login
	@PostMapping("changeHeadImg")
	@ApiOperation("更换店员头像")
	public R changeHeadImg(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员Seq") @RequestParam("assistantSeq") Integer assistantSeq,
			@ApiParam("用户头像") @RequestParam("headImg") MultipartFile headImg,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
			AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			
			assistantEntity = new AssistantEntity();
			assistantEntity.setSeq(assistantSeq);
			assistantEntity.setHeadImg(upLoadFile(assistantSeq, getAssistantUploadUrl(request, assistantSeq.toString()), headImg));
			assistantService.updateById(assistantEntity);
			
			Map<String, Object> map = new HashMap<>();
			map.put("headImg", getAssistantPictureUrl(assistantSeq.toString()) + assistantEntity.getHeadImg());
			return R.ok(map).put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("修改失败");
		}
	}
	
	
	
	/**
	 * 删除店员
	 */
	@Login
	@PostMapping("deleteAssistant")
	@ApiOperation("删除店员")
	public R deleteAssistant(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("店员Seq") @RequestParam("assistantSeq") Integer assistantSeq,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
			AssistantEntity assistantEntity = assistantService.selectById(assistantSeq);
			if(!assistantEntity.getShopSeq().equals(loginUser.getUserAreaSeq())) {
				return R.error(HttpStatus.FORBIDDEN.value(), "对不起，您的权限不足");
			}
			assistantService.deleteById(assistantSeq);
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("删除失败");
		}
	}

}
