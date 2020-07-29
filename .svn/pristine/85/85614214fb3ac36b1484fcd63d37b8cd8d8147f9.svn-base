package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 分公司补单表
 * @author yy
 * @date 2019-10-21 10:20:05
 */
@TableName("NWReplenishment_Patch")
public class PatchEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 年份
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 周
	 */
	@TableField(value = "Week")
	private Integer week;
	/**
	 * 开始日期
	 */
	@TableField(value = "StartDate")
	private Date startDate;
	/**
	 * 结束日期
	 */
	@TableField(value = "EndDate")
	private Date endDate;
	/**
	 * 大区seq
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 大区名
	 */
	@TableField(exist = false)
	private String areaName;
	/**
	 * 分公司序号
	 */
	@TableField(value = "BranchOfficeSeq")
	private Integer branchOfficeSeq;
	/**
	 * 分公司名
	 */
	@TableField(exist = false)
	private String branchOfficeName;
	/**
	 * 用户序号
	 */
	@TableField(value = "UserSeq")
	private Integer userSeq;
	/**
	 * 季节
	 */
	@TableField(value = "PeriodSeq")
	private Integer periodSeq;
	/**
	 * 季节名
	 */
	@TableField(exist = false)
	private String periodName;
	/**
	 * 品类
	 */
	@TableField(value = "SXValue")
	private String sxValue;
	/**
	 * 鞋子seq
	 */
	@TableField(value = "ShoeSeq")
	private Integer shoeSeq;
	/**
	 * 货号
	 */
	@TableField(value = "ShoeID")
	private String shoeID;
	/**
	 * 补单数量
	 */
	@TableField(value = "PatchNum")
	private Integer patchNum;
	/**
	 * 状态：0待处理 1已确认 2已取消
	 */
	@TableField(value = "State")
	private Integer state;
	/**
	 * 状态：0待处理 1已确认 2已取消
	 */
	@TableField(exist = false)
	private String stateName;
	/**
	 * 入库时间
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
	 * 补单配码详情Map {配码模板seq：件数}
	 */
	@TableField(exist = false)
	private Map<Integer, Integer> patchDetailMap;
	
	
	
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
	 * 设置：周
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}
	/**
	 * 获取：周
	 */
	public Integer getWeek() {
		return week;
	}
	/**
	 * 设置：开始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：开始日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：大区seq
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：大区seq
	 */
	public Integer getAreaSeq() {
		return areaSeq;
	}
	/**
	 * 设置：分公司序号
	 */
	public void setBranchOfficeSeq(Integer branchOfficeSeq) {
		this.branchOfficeSeq = branchOfficeSeq;
	}
	/**
	 * 获取：分公司序号
	 */
	public Integer getBranchOfficeSeq() {
		return branchOfficeSeq;
	}
	/**
	 * 设置：入库时间
	 */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * 获取：入库时间
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
	/**
	 * 设置：季节
	 */
	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}
	/**
	 * 获取：季节
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
	 * 设置：补单数量
	 */
	public void setPatchNum(Integer patchNum) {
		this.patchNum = patchNum;
	}
	/**
	 * 获取：补单数量
	 */
	public Integer getPatchNum() {
		return patchNum;
	}
	/**
	 * 设置：鞋子seq
	 */
	public void setShoeSeq(Integer shoeSeq) {
		this.shoeSeq = shoeSeq;
	}
	/**
	 * 获取：鞋子seq
	 */
	public Integer getShoeSeq() {
		return shoeSeq;
	}
	/**
	 * 设置：货号
	 */
	public void setShoeID(String shoeID) {
		this.shoeID = shoeID;
	}
	/**
	 * 获取：货号
	 */
	public String getShoeID() {
		return shoeID;
	}
	/**
	 * 设置：状态：0待处理 1已确认 2已取消
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态：0待处理 1已确认 2已取消
	 */
	public Integer getState() {
		return state;
	}
	public Integer getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getBranchOfficeName() {
		return branchOfficeName;
	}
	public void setBranchOfficeName(String branchOfficeName) {
		this.branchOfficeName = branchOfficeName;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Map<Integer, Integer> getPatchDetailMap() {
		return patchDetailMap;
	}
	public void setPatchDetailMap(Map<Integer, Integer> patchDetailMap) {
		this.patchDetailMap = patchDetailMap;
	}
}
