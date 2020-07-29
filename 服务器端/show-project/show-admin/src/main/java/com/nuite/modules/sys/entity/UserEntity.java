package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-09 09:26:54
 */
@TableName("NWUser_User")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 账号
	 */
	@TableField(value = "UserName")
	private String userName;
	/**
	 * 密码
	 */
	@TableField(value = "Password")
	private String password;
	/**
	 * 角色序号
	 */
	@TableField(value = "RoleSeq")
	private Integer roleSeq;
	/**
	 * 用户所属区域序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	@TableField(value = "UserAreaSeq")
	private Integer userAreaSeq;
	/**
	 * 用户所属区域名称
	 */
	@TableField(exist = false)
	private String userAreaName;
	/**
	 * 创建人序号
	 */
	@TableField(value = "CreateUserSeq")
	private Integer createUserSeq;
	/**
	 * 真实姓名
	 */
	@TableField(value = "RealName")
	private String realName;
	/**
	 * 电话
	 */
	@TableField(value = "Telephone")
	private String telephone;
	/**
	 * 头像
	 */
	@TableField(value = "HeadImg")
	private String headImg;
	/**
	 * 是否有效( 0 : 无效、 1 : 有效 )
	 */
	@TableField(value = "IsUseful")
	private Integer isUseful;
	/**
	 * 插入时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 删除标识( 0 : 未删除、  1 : 删除 )
	 */
	@TableLogic
	@TableField(value = "Del")
	private Integer del;

	/**
	 * 设置：序号(主键)
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：序号(主键)
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：账号
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：账号
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：角色序号
	 */
	public void setRoleSeq(Integer roleSeq) {
		this.roleSeq = roleSeq;
	}
	/**
	 * 获取：角色序号
	 */
	public Integer getRoleSeq() {
		return roleSeq;
	}
	/**
	 * 设置：自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	public void setUserAreaSeq(Integer userAreaSeq) {
		this.userAreaSeq = userAreaSeq;
	}
	/**
	 * 获取：自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	public Integer getUserAreaSeq() {
		return userAreaSeq;
	}
	/**
	 * 设置：创建人序号
	 */
	public void setCreateUserSeq(Integer createUserSeq) {
		this.createUserSeq = createUserSeq;
	}
	/**
	 * 获取：创建人序号
	 */
	public Integer getCreateUserSeq() {
		return createUserSeq;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * 设置：电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * 设置：是否有效( 0 : 无效、 1 : 有效 )
	 */
	public void setIsUseful(Integer isUseful) {
		this.isUseful = isUseful;
	}
	/**
	 * 获取：是否有效( 0 : 无效、 1 : 有效 )
	 */
	public Integer getIsUseful() {
		return isUseful;
	}
	/**
	 * 设置：插入时间
	 */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * 获取：插入时间
	 */
	public Date getInputTime() {
		return inputTime;
	}
	/**
	 * 设置：删除标识( 0 : 未删除、  1 : 删除 )
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：删除标识( 0 : 未删除、  1 : 删除 )
	 */
	public Integer getDel() {
		return del;
	}
	public String getUserAreaName() {
		return userAreaName;
	}
	public void setUserAreaName(String userAreaName) {
		this.userAreaName = userAreaName;
	}
}
