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
 * @date 2018-12-27 11:04:07
 */
@TableName("NWReport_WeekSales")
public class WeekSalesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 店铺序号(NWBase_Shop表外键)
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * 店员序号(NWUser_Assistant表外键)
	 */
	@TableField(value = "AssistantSeq")
	private Integer assistantSeq;
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
	 * 第几周
	 */
	@TableField(value = "Week")
	private Integer week;
	/**
	 * 数据截止时间
	 */
	@TableField(value = "CutoffTime")
	private Date cutoffTime;
	/**
	 * 月销售目标
	 */
	@TableField(value = "MonthGoal")
	private BigDecimal monthGoal;
	/**
	 * 本周销售
	 */
	@TableField(value = "WeekGoal")
	private BigDecimal weekGoal;
	/**
	 * 周销售目标
	 */
	@TableField(value = "WeekAmount")
	private BigDecimal weekAmount;
	/**
	 * 周销售双数
	 */
	@TableField(value = "WeekPairs")
	private Integer weekPairs;
	/**
	 * 连单数(百分比)
	 */
	@TableField(value = "ContinuityPercent")
	private String continuityPercent;
	/**
	 * 店员周工作分析
	 */
	@TableField(value = "WorkerDescribe")
	private String workerDescribe;
	/**
	 * 插入时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 更新时间
	 */
	@TableField(value = "UpdateTime")
	private Date updateTime;
	/**
	 * 删除标识( 0 : 未删除、  1 : 删除 )
	 */
	@TableLogic
	@TableField(value = "Del")
	private Integer del;
	
	
	
    // --自定义字段-- //
	/**
	 * 店员名称
	 */
	@TableField(exist = false)
	private String assistantName;
	/**
	 * 店员等级
	 */
	@TableField(exist = false)
	private String assistantLevel;
	/**
	 * 店员头像
	 */
	@TableField(exist = false)
	private String headImg;
    /**
     * 月累计完成额
     */
    @TableField(exist = false)
    private BigDecimal monthTotalAmount;
    /**
     * 月累计完成率
     */
    @TableField(exist = false)
    private BigDecimal monthTotalRate;
    /**
     * 月累计完成率（带%字符串）
     */
    @TableField(exist = false)
    private String monthTotalRateStr;
    /**
     * 月累计额排名
     */
    @TableField(exist = false)
    private Integer monthTotalRank;
    /**
     * 上周销售额
     */
    @TableField(exist = false)
    private BigDecimal lastWeekAmount;
    
    
    

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
	 * 设置：店铺序号(NWBase_Shop表外键)
	 */
	public void setShopSeq(Integer shopSeq) {
		this.shopSeq = shopSeq;
	}
	/**
	 * 获取：店铺序号(NWBase_Shop表外键)
	 */
	public Integer getShopSeq() {
		return shopSeq;
	}
	/**
	 * 设置：店员序号(NWUser_Assistant表外键)
	 */
	public void setAssistantSeq(Integer assistantSeq) {
		this.assistantSeq = assistantSeq;
	}
	/**
	 * 获取：店员序号(NWUser_Assistant表外键)
	 */
	public Integer getAssistantSeq() {
		return assistantSeq;
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
	 * 设置：第几周
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}
	/**
	 * 获取：第几周
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
	 * 设置：月销售目标
	 */
	public void setMonthGoal(BigDecimal monthGoal) {
		this.monthGoal = monthGoal;
	}
	/**
	 * 获取：月销售目标
	 */
	public BigDecimal getMonthGoal() {
		return monthGoal;
	}
	/**
	 * 设置：本周销售
	 */
	public void setWeekGoal(BigDecimal weekGoal) {
		this.weekGoal = weekGoal;
	}
	/**
	 * 获取：本周销售
	 */
	public BigDecimal getWeekGoal() {
		return weekGoal;
	}
	/**
	 * 设置：周销售目标
	 */
	public void setWeekAmount(BigDecimal weekAmount) {
		this.weekAmount = weekAmount;
	}
	/**
	 * 获取：周销售目标
	 */
	public BigDecimal getWeekAmount() {
		return weekAmount;
	}
	/**
	 * 设置：周销双数
	 */
	public void setWeekPairs(Integer weekPairs) {
		this.weekPairs = weekPairs;
	}
	/**
	 * 获取：周销双数
	 */
	public Integer getWeekPairs() {
		return weekPairs;
	}
	/**
	 * 设置：连单数(百分比)
	 */
	public void setContinuityPercent(String continuityPercent) {
		this.continuityPercent = continuityPercent;
	}
	/**
	 * 获取：连单数(百分比)
	 */
	public String getContinuityPercent() {
		return continuityPercent;
	}
	/**
	 * 设置：店员周工作分析
	 */
	public void setWorkerDescribe(String workerDescribe) {
		this.workerDescribe = workerDescribe;
	}
	/**
	 * 获取：店员周工作分析
	 */
	public String getWorkerDescribe() {
		return workerDescribe;
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
	 * 设置：插入时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：插入时间
	 */
	public Date getUpdateTime() {
		return updateTime;
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
	
	
	
	
	public String getAssistantName() {
		return assistantName;
	}
	public void setAssistantName(String assistantName) {
		this.assistantName = assistantName;
	}
	public String getAssistantLevel() {
		return assistantLevel;
	}
	public void setAssistantLevel(String assistantLevel) {
		this.assistantLevel = assistantLevel;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public BigDecimal getMonthTotalAmount() {
		return monthTotalAmount;
	}
	public void setMonthTotalAmount(BigDecimal monthTotalAmount) {
		this.monthTotalAmount = monthTotalAmount;
	}
	public BigDecimal getMonthTotalRate() {
		return monthTotalRate;
	}
	public void setMonthTotalRate(BigDecimal monthTotalRate) {
		this.monthTotalRate = monthTotalRate;
	}
	public String getMonthTotalRateStr() {
		return monthTotalRateStr;
	}
	public void setMonthTotalRateStr(String monthTotalRateStr) {
		this.monthTotalRateStr = monthTotalRateStr;
	}
	public Integer getMonthTotalRank() {
		return monthTotalRank;
	}
	public void setMonthTotalRank(Integer monthTotalRank) {
		this.monthTotalRank = monthTotalRank;
	}
	public BigDecimal getLastWeekAmount() {
		return lastWeekAmount;
	}
	public void setLastWeekAmount(BigDecimal lastWeekAmount) {
		this.lastWeekAmount = lastWeekAmount;
	}
}
