package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购计划管理
 */
@TableName("NWReplenishment_PurchasePlan")
public class PurchasePlanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 年份
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 波次序号
	 */
	@TableField(value = "PeriodSeq")
	private Integer periodSeq;
	/**
	 * 品类
	 */
	@TableField(value = "SXValue")
	private String sxValue;
	/**
	 * 计划的日期（每年的春季夏季可能从前一年的11  12月就开始补）
	 */
	@TableField(value = "PlanDate")
	private Date planDate;
	/**
	 * 计划的数量，用户计算每一次的比例
	 */
	@TableField(value = "PlanNum")
	private Integer planNum;
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
	 * 区域序号
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	
	/**
	 *办事处序号
	 */
	@TableField(value = "BranchOfficeSeq")
	private Integer branchOfficeSeq;
	
	/**
	 * 每个品类的计划总量，会有重复出现，此记录是每个办事处或总公司一个数据
	 */
	@TableField(value = "SXPlanTotalNum")
	private Integer sxPlanTotalNum;

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
	 * 设置：年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：年份
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * 设置：波次序号
	 */
	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}
	/**
	 * 获取：波次序号
	 */
	public Integer getPeriodSeq() {
		return periodSeq;
	}
	/**
	 * 设置：品类
	 */
	public void setSxValue(String sxValue) {
		this.sxValue = sxValue;
	}
	/**
	 * 获取：品类
	 */
	public String getSxValue() {
		return sxValue;
	}
	/**
	 * 设置：计划的日期（每年的春季夏季可能从前一年的11  12月就开始补）
	 */
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	/**
	 * 获取：计划的日期（每年的春季夏季可能从前一年的11  12月就开始补）
	 */
	public Date getPlanDate() {
		return planDate;
	}
	/**
	 * 设置：计划的数量，用户计算每一次的比例
	 */
	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}
	/**
	 * 获取：计划的数量，用户计算每一次的比例
	 */
	public Integer getPlanNum() {
		return planNum;
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
	public Integer getAreaSeq() {
		return areaSeq;
	}
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	public Integer getBranchOfficeSeq() {
		return branchOfficeSeq;
	}
	public void setBranchOfficeSeq(Integer branchOfficeSeq) {
		this.branchOfficeSeq = branchOfficeSeq;
	}
	public Integer getSxPlanTotalNum() {
		return sxPlanTotalNum;
	}
	public void setSxPlanTotalNum(Integer sxPlanTotalNum) {
		this.sxPlanTotalNum = sxPlanTotalNum;
	}
	
	
}
