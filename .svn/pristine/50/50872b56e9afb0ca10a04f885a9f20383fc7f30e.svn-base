package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * 补单配码模板详情
 */
@TableName("NWReplenishment_SizeAllotTemplateDetail")
public class SizeAllotTemplateDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 配码模板序号
	 */
	@TableField(value = "SizeAllotTemplate_Seq")
	private Integer sizeAllotTemplateSeq;
	/**
	 * 尺码
	 */
	@TableField(value = "Size")
	private String size;
	/**
	 * 双数
	 */
	@TableField(value = "Per")
	private Integer per;
	/**
	 * 创建时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 删除标识
	 */
	@TableLogic
	@TableField(value = "Del")
	private Integer del;

	/**
	 * 设置：序号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：序号
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：配码模板序号
	 */
	public void setSizeAllotTemplateSeq(Integer sizeAllotTemplateSeq) {
		this.sizeAllotTemplateSeq = sizeAllotTemplateSeq;
	}
	/**
	 * 获取：配码模板序号
	 */
	public Integer getSizeAllotTemplateSeq() {
		return sizeAllotTemplateSeq;
	}
	/**
	 * 设置：尺码
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * 获取：尺码
	 */
	public String getSize() {
		return size;
	}
	/**
	 * 设置：双数
	 */
	public void setPer(Integer per) {
		this.per = per;
	}
	/**
	 * 获取：双数
	 */
	public Integer getPer() {
		return per;
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
	 * 设置：删除标识
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：删除标识
	 */
	public Integer getDel() {
		return del;
	}
}
