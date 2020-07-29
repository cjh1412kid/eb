package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-06-11 17:13:19
 */
@TableName("NWUser_UserModuleSubscribed")
public class UserModuleSubscribedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 用户序号
	 */
	@TableField(value = "User_Seq")
	private Integer userSeq;
	/**
	 * 用户所订阅区域序号（不是UserAreaSeq用户所属区域序号）
	 */
	@TableField(value = "UserAreaSubscribed_Seq")
	private Integer userAreaSubscribedSeq;
	/**
	 * 模块序号
	 */
	@TableField(value = "Module_Seq")
	private Integer moduleSeq;
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
	 * 设置：用户序号
	 */
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * 获取：用户序号
	 */
	public Integer getUserSeq() {
		return userSeq;
	}
	/**
	 * 设置：用户所订阅区域序号（不是UserAreaSeq用户所属区域序号）
	 */
	public void setUserAreaSubscribedSeq(Integer userAreaSubscribedSeq) {
		this.userAreaSubscribedSeq = userAreaSubscribedSeq;
	}
	/**
	 * 获取：用户所订阅区域序号（不是UserAreaSeq用户所属区域序号）
	 */
	public Integer getUserAreaSubscribedSeq() {
		return userAreaSubscribedSeq;
	}
	/**
	 * 设置：模块序号
	 */
	public void setModuleSeq(Integer moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
	/**
	 * 获取：模块序号
	 */
	public Integer getModuleSeq() {
		return moduleSeq;
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
}
