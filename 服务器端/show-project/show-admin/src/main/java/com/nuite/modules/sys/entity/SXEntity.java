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
 * @date 2019-01-11 09:33:40
 */
@TableName("NWGoods_SX")
public class SXEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 品牌序号
	 */
	@TableField(value = "BrandSeq")
	private Integer brandSeq;
	/**
	 * 属性对应Goods表字段( 如 SX1,SX2...)
	 */
	@TableField(value = "SXID")
	private String sXID;
	/**
	 * 属性中文含义
	 */
	@TableField(value = "SXName")
	private String sXName;
	/**
	 * 是否可见（0 : 可见,    1 : 不可见）
	 */
	@TableField(value = "Visible")
	private Integer visible;
	/**
	 * 创建时间
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
	 * 设置：品牌序号
	 */
	public void setBrandSeq(Integer brandSeq) {
		this.brandSeq = brandSeq;
	}
	/**
	 * 获取：品牌序号
	 */
	public Integer getBrandSeq() {
		return brandSeq;
	}
	/**
	 * 设置：属性对应Goods表字段( 如 SX1,SX2...)
	 */
	public void setSXID(String sXID) {
		this.sXID = sXID;
	}
	/**
	 * 获取：属性对应Goods表字段( 如 SX1,SX2...)
	 */
	public String getSXID() {
		return sXID;
	}
	/**
	 * 设置：属性中文含义
	 */
	public void setSXName(String sXName) {
		this.sXName = sXName;
	}
	/**
	 * 获取：属性中文含义
	 */
	public String getSXName() {
		return sXName;
	}
	/**
	 * 设置：是否可见（0 : 可见,    1 : 不可见）
	 */
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	/**
	 * 获取：是否可见（0 : 可见,    1 : 不可见）
	 */
	public Integer getVisible() {
		return visible;
	}
	/**
	 * 设置：创建时间
	 */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * 获取：创建时间
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
