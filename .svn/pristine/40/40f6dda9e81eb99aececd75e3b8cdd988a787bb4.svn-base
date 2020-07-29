package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-24 17:35:23
 */
@TableName("NWBase_ShopShowControl")
public class ShopShowControlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 设置的门店范围
	 */
	@TableField(value = "ShopSeqs")
	private String shopSeqs;
	/**
	 * 前十大试穿（0不展示、1展示）
	 */
	@TableField(value = "DefaultTry")
	private Integer defaultTry;
	/**
	 * 前十大销售（0不展示、1展示）
	 */
	@TableField(value = "DefaultSale")
	private Integer defaultSale;
	/**
	 * 推荐展示的鞋子( 如: "1，2......" )
	 */
	@TableField(value = "RecommendShoes")
	private String recommendShoes;
	/**
	 * 起始时间
	 */
	@TableField(value = "SValidDate")
	private Date sValidDate;
	/**
	 * 结束时间
	 */
	@TableField(value = "EValidDate")
	private Date eValidDate;
	/**
	 * 设置人序号
	 */
	@TableField(value = "UserSeq")
	private Integer userSeq;
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
	 * 设置：设置人序号
	 */
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * 获取：设置人序号
	 */
	public Integer getUserSeq() {
		return userSeq;
	}
	/**
	 * 设置：设置的门店范围
	 */
	public void setShopSeqs(String shopSeqs) {
		this.shopSeqs = shopSeqs;
	}
	/**
	 * 获取：设置的门店范围
	 */
	public String getShopSeqs() {
		return shopSeqs;
	}
	/**
	 * 设置：前十大试穿（0不展示、1展示）
	 */
	public void setDefaultTry(Integer defaultTry) {
		this.defaultTry = defaultTry;
	}
	/**
	 * 获取：前十大试穿（0不展示、1展示）
	 */
	public Integer getDefaultTry() {
		return defaultTry;
	}
	/**
	 * 设置：前十大销售（0不展示、1展示）
	 */
	public void setDefaultSale(Integer defaultSale) {
		this.defaultSale = defaultSale;
	}
	/**
	 * 获取：前十大销售（0不展示、1展示）
	 */
	public Integer getDefaultSale() {
		return defaultSale;
	}
	/**
	 * 设置：推荐展示的鞋子( 如: "1，2......" )
	 */
	public void setRecommendShoes(String recommendShoes) {
		this.recommendShoes = recommendShoes;
	}
	/**
	 * 获取：推荐展示的鞋子( 如: "1，2......" )
	 */
	public String getRecommendShoes() {
		return recommendShoes;
	}
	/**
	 * 设置：起始时间
	 */
	public void setSValidDate(Date sValidDate) {
		this.sValidDate = sValidDate;
	}
	/**
	 * 获取：起始时间
	 */
	public Date getSValidDate() {
		return sValidDate;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEValidDate(Date eValidDate) {
		this.eValidDate = eValidDate;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEValidDate() {
		return eValidDate;
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
}
