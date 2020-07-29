package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-03 10:45:55
 */
@TableName("NWReport_CompeteAnalysis")
public class CompeteAnalysisEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 商场序号
	 */
	@TableField(value = "StoreSeq")
	private Integer storeSeq;
	/**
	 * 商场品牌序号
	 */
	@TableField(value = "StoreBrandSeq")
	private Integer storeBrandSeq;
	/**
	 * 年
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 月
	 */
	@TableField(value = "Month")
	private Integer month;
	/**
	 * 日历第几周
	 */
	@TableField(value = "Week")
	private Integer week;
	/**
	 * 数据截止时间
	 */
	@TableField(value = "CutoffTime")
	private Date cutoffTime;
	/**
	 * 商场排名
	 */
	@TableField(value = "StoreNo")
	private Integer storeNo;
	/**
	 * 本周销售
	 */
	@TableField(value = "WeekSale")
	private BigDecimal weekSale;
	/**
	 * 商场总品牌个数（只有伊伴品牌有）
	 */
	@TableField(value = "BrandsNum")
	private Integer brandsNum;
	/**
	 * 月任务排名（只有伊伴品牌有）
	 */
	@TableField(value = "MonthTaskNo")
	private Integer monthTaskNo;
	/**
	 * 月任务实际排名（只有伊伴品牌有）
	 */
	@TableField(value = "MonthRealNo")
	private Integer monthRealNo;
	/**
	 * 排名分析（只在伊伴品牌有数据）
	 */
	@TableField(value = "Describe")
	private String describe;
	/**
	 * 插入时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 删除标识
	 */
	@TableLogic
	@TableField(value = "Del")
	private Integer del;
	
	
	
	
    // --自定义字段-- //
	/**
	 * 商场品牌名称
	 */
	@TableField(exist = false)
	private String storeBrandName;
    /**
     * 月销售额
     */
    @TableField(exist = false)
    private BigDecimal monthSale;
    /**
     * 上周销售额
     */
    @TableField(exist = false)
    private BigDecimal lastWeekSale;
    /**
     * 周增长
     */
    @TableField(exist = false)
    private BigDecimal weekChange;
    
	
	

	/**
	 * 设置：${column.comments}
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：商场序号
	 */
	public void setStoreSeq(Integer storeSeq) {
		this.storeSeq = storeSeq;
	}
	/**
	 * 获取：商场序号
	 */
	public Integer getStoreSeq() {
		return storeSeq;
	}
	/**
	 * 设置：商场品牌序号
	 */
	public void setStoreBrandSeq(Integer storeBrandSeq) {
		this.storeBrandSeq = storeBrandSeq;
	}
	/**
	 * 获取：商场品牌序号
	 */
	public Integer getStoreBrandSeq() {
		return storeBrandSeq;
	}
	/**
	 * 设置：年
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：年
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * 设置：月
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：月
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * 设置：日历第几周
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}
	/**
	 * 获取：日历第几周
	 */
	public Integer getWeek() {
		return week;
	}
	/**
	 * 设置：数据截止时间
	 */
	public void setCutoffTime(Date cutoffTime) {
		this.cutoffTime = cutoffTime;
	}
	/**
	 * 获取：数据截止时间
	 */
	public Date getCutoffTime() {
		return cutoffTime;
	}
	/**
	 * 设置：商场排名
	 */
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}
	/**
	 * 获取：商场排名
	 */
	public Integer getStoreNo() {
		return storeNo;
	}
	/**
	 * 设置：本周销售
	 */
	public void setWeekSale(BigDecimal weekSale) {
		this.weekSale = weekSale;
	}
	/**
	 * 获取：本周销售
	 */
	public BigDecimal getWeekSale() {
		return weekSale;
	}
	public Integer getBrandsNum() {
		return brandsNum;
	}
	public void setBrandsNum(Integer brandsNum) {
		this.brandsNum = brandsNum;
	}
	/**
	 * 设置：月任务排名（只有伊伴品牌有）
	 */
	public void setMonthTaskNo(Integer monthTaskNo) {
		this.monthTaskNo = monthTaskNo;
	}
	/**
	 * 获取：月任务排名（只有伊伴品牌有）
	 */
	public Integer getMonthTaskNo() {
		return monthTaskNo;
	}
	/**
	 * 设置：月任务实际排名（只有伊伴品牌有）
	 */
	public void setMonthRealNo(Integer monthRealNo) {
		this.monthRealNo = monthRealNo;
	}
	/**
	 * 获取：月任务实际排名（只有伊伴品牌有）
	 */
	public Integer getMonthRealNo() {
		return monthRealNo;
	}
	/**
	 * 设置：排名分析（只在伊伴品牌有数据）
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：排名分析（只在伊伴品牌有数据）
	 */
	public String getDescribe() {
		return describe;
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
	 * 设置：${column.comments}
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getDel() {
		return del;
	}
	public String getStoreBrandName() {
		return storeBrandName;
	}
	public void setStoreBrandName(String storeBrandName) {
		this.storeBrandName = storeBrandName;
	}
	public BigDecimal getMonthSale() {
		return monthSale;
	}
	public void setMonthSale(BigDecimal monthSale) {
		this.monthSale = monthSale;
	}
	public BigDecimal getLastWeekSale() {
		return lastWeekSale;
	}
	public void setLastWeekSale(BigDecimal lastWeekSale) {
		this.lastWeekSale = lastWeekSale;
	}
	public BigDecimal getWeekChange() {
		return weekChange;
	}
	public void setWeekChange(BigDecimal weekChange) {
		this.weekChange = weekChange;
	}
}
