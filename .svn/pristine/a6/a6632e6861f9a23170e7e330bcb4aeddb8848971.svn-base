package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 补单配码模板
 */
@TableName("NWReplenishment_SizeAllotTemplate")
public class SizeAllotTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 模板名
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 南北编码标识（1南编码，2北编码）
	 */
	@TableField(value = "NBFlag")
	private Integer nbFlag;
	/**
	 * 总数量
	 */
	@TableField(value = "TotalNum")
	private Integer totalNum;
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
	 * 页面上的的配码件数字段 （仅在页面使用，因为没有字段时vue输错）
	 */
	@TableField(exist = false)
	private Integer count;
	
	/**
	 * 配码模板详情List
	 */
	@TableField(exist = false)
	private List<SizeAllotTemplateDetailEntity> templateDetailList;
	
	/**
	 * 配码模板详情Array(用一个数组表示，固定位置为固定的尺码（33~40），包含所有尺码，没有的数量填0)
	 */
	@TableField(exist = false)
	private Integer[] templateDetailArr;
	
	
	
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
	 * 设置：模板名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：模板名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：南北编码标识（1南编码，2北编码）
	 */
	public void setNbFlag(Integer nbFlag) {
		this.nbFlag = nbFlag;
	}
	/**
	 * 获取：南北编码标识（1南编码，2北编码）
	 */
	public Integer getNbFlag() {
		return nbFlag;
	}
	/**
	 * 设置：总数量
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 获取：总数量
	 */
	public Integer getTotalNum() {
		return totalNum;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<SizeAllotTemplateDetailEntity> getTemplateDetailList() {
		return templateDetailList;
	}
	public void setTemplateDetailList(List<SizeAllotTemplateDetailEntity> templateDetailList) {
		this.templateDetailList = templateDetailList;
	}
	public Integer[] getTemplateDetailArr() {
		return templateDetailArr;
	}
	public void setTemplateDetailArr(Integer[] templateDetailArr) {
		this.templateDetailArr = templateDetailArr;
	}
}
