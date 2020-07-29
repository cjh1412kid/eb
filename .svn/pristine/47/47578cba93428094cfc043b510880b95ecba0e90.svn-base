package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * 补单配码详情
 */
@TableName("NWReplenishment_PatchDetail")
public class PatchDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 补单记录序号
	 */
	@TableField(value = "Patch_Seq")
	private Integer patchSeq;
	/**
	 * 配码模板序号
	 */
	@TableField(value = "SizeAllotTemplate_Seq")
	private Integer sizeAllotTemplateSeq;
	/**
	 * 件数
	 */
	@TableField(value = "BoxCount")
	private Integer boxCount;
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
	 * 每件数量（冗余字段）
	 */
	@TableField(value = "PerBoxNum")
	private Integer perBoxNum;

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
	 * 设置：补单记录序号
	 */
	public void setPatchSeq(Integer patchSeq) {
		this.patchSeq = patchSeq;
	}
	/**
	 * 获取：补单记录序号
	 */
	public Integer getPatchSeq() {
		return patchSeq;
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
	 * 设置：件数
	 */
	public void setBoxCount(Integer boxCount) {
		this.boxCount = boxCount;
	}
	/**
	 * 获取：件数
	 */
	public Integer getBoxCount() {
		return boxCount;
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
	/**
	 * 设置：每件数量（冗余字段）
	 */
	public void setPerBoxNum(Integer perBoxNum) {
		this.perBoxNum = perBoxNum;
	}
	/**
	 * 获取：每件数量（冗余字段）
	 */
	public Integer getPerBoxNum() {
		return perBoxNum;
	}
}
