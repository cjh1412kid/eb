package com.nuite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.common.utils.R;
import com.nuite.common.validator.ValidatorUtils;
import com.nuite.entity.AreaEntity;
import com.nuite.entity.ShopEntity;
import com.nuite.entity.UserEntity;
import com.nuite.form.LoginForm;
import com.nuite.service.AreaService;
import com.nuite.service.LoginService;
import com.nuite.service.ShopService;
import com.nuite.service.TokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 登录接口
 * @author yy
 * @date 2018-11-20 15:31
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class LoginController extends BaseController{
    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;

    @PostMapping("login")
    @ApiOperation("登录")
    public R login(String userName, String password){
        //表单校验
    	LoginForm loginForm = new LoginForm();
    	loginForm.setUserName(userName);
    	loginForm.setPassword(password);
        ValidatorUtils.validateEntity(loginForm);

        //登录验证、生成token，失败时直接抛出异常返回
        Map<String, Object> map = loginService.login(loginForm);

        
		//添加用户头像
        UserEntity user = (UserEntity) map.get("user");
		user.setHeadImg(getUserPictureUrl(user.getSeq().toString()) + user.getHeadImg());
		
		//添加区域名称
		if(user.getRoleSeq() == 1) { //全国
			user.setUserAreaName("全国");
		} else if(user.getRoleSeq() == 2) { //大区
			AreaEntity area = areaService.selectById(user.getUserAreaSeq());
			user.setUserAreaName(area.getAreaName());
		} else if(user.getRoleSeq() == 3) { //分公司
			AreaEntity area = areaService.selectById(user.getUserAreaSeq());
			user.setUserAreaName(area.getAreaName());
		} else if(user.getRoleSeq() == 4) { //门店
			ShopEntity shop = shopService.selectById(user.getUserAreaSeq());
			user.setUserAreaName(shop.getName());
		}
		
		return R.ok(map).put("msg", "登录成功");
    }

    
    @Login
    @PostMapping("logout")
    @ApiOperation("退出登录")
    public R logout(@ApiIgnore @RequestAttribute("userSeq") int userSeq){
        tokenService.expireToken(userSeq);
        return R.ok();
    }

}
