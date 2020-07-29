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
@TableName("NWUser_UserAreaSubscribed")
public class UserAreaSubscribedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 角色序号
	 */
	@TableField(value = "User_Seq")
	private Integer userSeq;
	/**
	 * 自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	@TableField(value = "AreaType")
	private Integer areaType;
	/**
	 * 创建人序号
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 真实姓名
	 */
	@TableField(value = "AreaName")
	private String areaName;
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
	 * 设置：角色序号
	 */
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * 获取：角色序号
	 */
	public Integer getUserSeq() {
		return userSeq;
	}
	/**
	 * 设置：自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	/**
	 * 获取：自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0
	 */
	public Integer getAreaType() {
		return areaType;
	}
	/**
	 * 设置：创建人序号
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：创建人序号
	 */
	public Integer getAreaSeq() {
		return areaSeq;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getAreaName() {
		return areaName;
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
