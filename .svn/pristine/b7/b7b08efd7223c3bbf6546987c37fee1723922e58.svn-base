package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
 * @date 2018-12-10 18:26:53
 */
@TableName("NWGoods_SaleQuota")
public class SaleQuotaEntity implements Serializable {
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
	 * 年份
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 月份
	 */
	@TableField(value = "Month")
	private Integer month;
	/**
	 * 区域类型（2大区、3分公司、4门店）
	 */
	@TableField(value = "AreaType")
	private Integer areaType;
	/**
	 * 区域序号（大区、分公司、门店）
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 区域销售指标
	 */
	@TableField(value = "Quota")
	private BigDecimal quota;
	/**
	 * 区域名称（2大区名称、3分公司名称、4门店名称）
	 */
	@TableField(exist = false)
	private String areaName;
	/**
	 * 下发人序号
	 */
	@TableField(value = "UserSeq")
	private Integer userSeq;
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
	 * 设置：月份
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：月份
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * 设置：区域序号（大区、分公司、门店）
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：区域序号（大区、分公司、门店）
	 */
	public Integer getAreaSeq() {
		return areaSeq;
	}
	/**
	 * 设置：区域销售指标
	 */
	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}
	/**
	 * 获取：区域销售指标
	 */
	public BigDecimal getQuota() {
		return quota;
	}
	/**
	 * 设置：区域类型（1大区、2分公司、3门店）
	 */
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	/**
	 * 获取：区域类型（1大区、2分公司、3门店）
	 */
	public Integer getAreaType() {
		return areaType;
	}
	/**
	 * 设置：下发人序号
	 */
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * 获取：下发人序号
	 */
	public Integer getUserSeq() {
		return userSeq;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
