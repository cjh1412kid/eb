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
@TableName("NWUser_RolePermission")
public class RolePermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 角色序号
	 */
	@TableField(value = "RoleSeq")
	private Integer roleSeq;
	/**
	 * 权限序号
	 */
	@TableField(value = "PermissionSeq")
	private Integer permissionSeq;
	/**
	 * 插入时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 
删除标识( 0 : 未删除,   1 : 删除 )
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
	 * 设置：权限序号
	 */
	public void setPermissionSeq(Integer permissionSeq) {
		this.permissionSeq = permissionSeq;
	}
	/**
	 * 获取：权限序号
	 */
	public Integer getPermissionSeq() {
		return permissionSeq;
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
	 * 设置：
删除标识( 0 : 未删除,   1 : 删除 )
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：
删除标识( 0 : 未删除,   1 : 删除 )
	 */
	public Integer getDel() {
		return del;
	}
}
