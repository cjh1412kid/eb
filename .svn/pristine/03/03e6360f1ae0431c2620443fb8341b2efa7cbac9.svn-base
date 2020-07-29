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
 * @date 2019-01-02 14:43:31
 */
@TableName("NWReport_StoreBrand")
public class StoreBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 商场序号
	 */
	@TableField(value = "StoreSeq")
	private Integer storeSeq;
	/**
	 * 品牌名称
	 */
	@TableField(value = "BrandName")
	private String brandName;
	/**
	 * 门店序号（伊伴品牌才会有，其他品牌没有门店序号）
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * $column.comments
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * $column.comments
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
	 * 设置：品牌名称
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：品牌名称
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * 设置：门店序号（伊伴品牌才会有，其他品牌没有门店序号）
	 */
	public void setShopSeq(Integer shopSeq) {
		this.shopSeq = shopSeq;
	}
	/**
	 * 获取：门店序号（伊伴品牌才会有，其他品牌没有门店序号）
	 */
	public Integer getShopSeq() {
		return shopSeq;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * 获取：${column.comments}
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
}
