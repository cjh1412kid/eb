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
 * @date 2018-11-24 16:14:25
 */
@TableName("NWBase_Area")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 区域父节点编号( 父节点为 0 的是大区 )
	 */
	@TableField(value = "ParentSeq")
	private Integer parentSeq;
	/**
	 * 品牌
	 */
	@TableField(value = "BrandSeq")
	private Integer brandSeq;
	/**
	 * 区域名称
	 */
	@TableField(value = "AreaName")
	private String areaName;
	/**
	 * 区域的地理范围
	 */
	@TableField(value = "Bound")
	private String bound;
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
	 * 设置：区域父节点编号( 父节点为 0 的是大区 )
	 */
	public void setParentSeq(Integer parentSeq) {
		this.parentSeq = parentSeq;
	}
	/**
	 * 获取：区域父节点编号( 父节点为 0 的是大区 )
	 */
	public Integer getParentSeq() {
		return parentSeq;
	}
	/**
	 * 设置：品牌
	 */
	public void setBrandSeq(Integer brandSeq) {
		this.brandSeq = brandSeq;
	}
	/**
	 * 获取：品牌
	 */
	public Integer getBrandSeq() {
		return brandSeq;
	}
	/**
	 * 设置：区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：区域名称
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：区域的地理范围
	 */
	public void setBound(String bound) {
		this.bound = bound;
	}
	/**
	 * 获取：区域的地理范围
	 */
	public String getBound() {
		return bound;
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
