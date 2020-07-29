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
 * @date 2019-01-07 17:15:57
 */
@TableName("NWReport_PromoteExplain")
public class PromoteExplainEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 门店序号
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * 年
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 周
	 */
	@TableField(value = "Week")
	private Integer week;
	/**
	 * 周一至周四促销说明
	 */
	@TableField(value = "Explain1")
	private String explain1;
	/**
	 * 周五至周日促销说明
	 */
	@TableField(value = "Explain2")
	private String explain2;
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
	 * 设置：门店序号
	 */
	public void setShopSeq(Integer shopSeq) {
		this.shopSeq = shopSeq;
	}
	/**
	 * 获取：门店序号
	 */
	public Integer getShopSeq() {
		return shopSeq;
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
	 * 设置：周一至周四促销说明
	 */
	public void setExplain1(String explain1) {
		this.explain1 = explain1;
	}
	/**
	 * 获取：周一至周四促销说明
	 */
	public String getExplain1() {
		return explain1;
	}
	/**
	 * 设置：周五至周日促销说明
	 */
	public void setExplain2(String explain2) {
		this.explain2 = explain2;
	}
	/**
	 * 获取：周五至周日促销说明
	 */
	public String getExplain2() {
		return explain2;
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
