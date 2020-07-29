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
 * @date 2018-11-30 11:05:11
 */
@TableName("NWGoods_SaleShoesDetailFromErp")
public class SaleShoesDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 波次序号
	 */
	@TableField(value = "PeriodSeq")
	private Integer periodSeq;
	/**
	 * 大区序号
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 分公司序号
	 */
	@TableField(value = "BranchOfficeSeq")
	private Integer branchOfficeSeq;
	/**
	 * 门店序号
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * 销售时间
	 */
	@TableField(value = "SaleDate")
	private Date saleDate;
	/**
	 * 销售类型 0：线下，1：线上
	 */
	@TableField(value = "SaleType")
	private Integer saleType;
	/**
	 * 鞋子序号
	 */
	@TableField(value = "ShoeSeq")
	private Integer shoeSeq;
	/**
	 * 货号
	 */
	@TableField(value = "ShoeID")
	private String shoeID;
	/**
	 * 订单数量
	 */
	@TableField(value = "OrderCount")
	private Integer orderCount;
	/**
	 * 销售双数
	 */
	@TableField(value = "SaleCount")
	private Integer saleCount;
	/**
	 * 成本
	 */
	@TableField(value = "Cost")
	private BigDecimal cost;
	/**
	 * 吊牌价
	 */
	@TableField(value = "TagPrice")
	private BigDecimal tagPrice;
	/**
	 * 实际销售价格
	 */
	@TableField(value = "RealPrice")
	private BigDecimal realPrice;
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
	 * 设置：大区序号
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：大区序号
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
	 * 设置：销售时间
	 */
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	/**
	 * 获取：销售时间
	 */
	public Date getSaleDate() {
		return saleDate;
	}
	public Integer getSaleType() {
		return saleType;
	}
	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}
	/**
	 * 设置：鞋子序号
	 */
	public void setShoeSeq(Integer shoeSeq) {
		this.shoeSeq = shoeSeq;
	}
	/**
	 * 获取：鞋子序号
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
	 * 设置：订单数量
	 */
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	/**
	 * 获取：订单数量
	 */
	public Integer getOrderCount() {
		return orderCount;
	}
	/**
	 * 设置：销售双数
	 */
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	/**
	 * 获取：销售双数
	 */
	public Integer getSaleCount() {
		return saleCount;
	}
	/**
	 * 设置：成本
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * 获取：成本
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * 设置：吊牌价
	 */
	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}
	/**
	 * 获取：吊牌价
	 */
	public BigDecimal getTagPrice() {
		return tagPrice;
	}
	/**
	 * 设置：实际销售价格
	 */
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	/**
	 * 获取：实际销售价格
	 */
	public BigDecimal getRealPrice() {
		return realPrice;
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
}
