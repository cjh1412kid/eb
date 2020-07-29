package com.nuite.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 * @author yy
 * @date 2018-01-25
 */
@ApiModel(value = "登录表单")
public class LoginForm {
    @ApiModelProperty(value = "用户名")
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;


    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
