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
 * @date 2019-01-07 17:15:58
 */
@TableName("NWReport_SaleAndStock")
public class SaleAndStockEntity implements Serializable {
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
	 * 品类Code
	 */
	@TableField(value = "SX3Code")
	private String sX3Code;
	/**
	 * 品类总销售双数
	 */
	@TableField(value = "SaleCount")
	private Integer saleCount;
	/**
	 * 品类总销售金额
	 */
	@TableField(value = "TotalPrice")
	private BigDecimal totalPrice;
	/**
	 * 品类平均单价
	 */
	@TableField(value = "AvgUnitPrice")
	private BigDecimal avgUnitPrice;
	/**
	 * SKU数量
	 */
	@TableField(value = "ShoeIdNum")
	private Integer shoeIdNum;
	/**
	 * 品类总库存
	 */
	@TableField(value = "Stock")
	private Integer stock;
	/**
	 * 所有品类总库存(即门店库存)
	 */
	@TableField(value = "TotalStock")
	private Integer totalStock;
	/**
	 * 品类库存占所有品类总库存的比
	 */
	@TableField(value = "StockPercent")
	private String stockPercent;
	/**
	 * 齐码率：品类库存除以品类SKU数
	 */
	@TableField(value = "CompleteRate")
	private String completeRate;
	/**
	 * 插入时间
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * 修改时间
	 */
	@TableField(value = "UpdateTime")
	private Date updateTime;
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
	 * 设置：品类Code
	 */
	public void setSX3Code(String sX3Code) {
		this.sX3Code = sX3Code;
	}
	/**
	 * 获取：品类Code
	 */
	public String getSX3Code() {
		return sX3Code;
	}
	/**
	 * 设置：品类总销售双数
	 */
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	/**
	 * 获取：品类总销售双数
	 */
	public Integer getSaleCount() {
		return saleCount;
	}
	/**
	 * 设置：品类总销售金额
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取：品类总销售金额
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置：品类平均单价
	 */
	public void setAvgUnitPrice(BigDecimal avgUnitPrice) {
		this.avgUnitPrice = avgUnitPrice;
	}
	/**
	 * 获取：品类平均单价
	 */
	public BigDecimal getAvgUnitPrice() {
		return avgUnitPrice;
	}
	/**
	 * 设置：SKU数量
	 */
	public void setShoeIdNum(Integer shoeIdNum) {
		this.shoeIdNum = shoeIdNum;
	}
	/**
	 * 获取：SKU数量
	 */
	public Integer getShoeIdNum() {
		return shoeIdNum;
	}
	/**
	 * 设置：品类总库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：品类总库存
	 */
	public Integer getStock() {
		return stock;
	}
	public Integer getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}
	/**
	 * 设置：品类总库存占所有品类总库存的比
	 */
	public void setStockPercent(String stockPercent) {
		this.stockPercent = stockPercent;
	}
	/**
	 * 获取：品类总库存占所有品类总库存的比
	 */
	public String getStockPercent() {
		return stockPercent;
	}
	/**
	 * 设置：齐码率
	 */
	public void setCompleteRate(String completeRate) {
		this.completeRate = completeRate;
	}
	/**
	 * 获取：齐码率
	 */
	public String getCompleteRate() {
		return completeRate;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
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
