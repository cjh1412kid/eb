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
 * @date 2018-11-26 09:39:25
 */
@TableName("NWBase_Shop")
public class ShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 关联区域表序号(分公司)
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 门店编号
	 */
	@TableField(value = "ShopID")
	private String shopID;
	/**
	 * 店名
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 地址
	 */
	@TableField(value = "Address")
	private String address;
	/**
	 * 纬度
	 */
	@TableField(value = "Lat")
	private BigDecimal lat;
	/**
	 * 经度
	 */
	@TableField(value = "Lng")
	private BigDecimal lng;
	/**
	 * 安装时间
	 */
	@TableField(value = "InstallDate")
	private Date installDate;
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
	 * 设置：关联区域表序号(分公司)
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：关联区域表序号(分公司)
	 */
	public Integer getAreaSeq() {
		return areaSeq;
	}
	/**
	 * 设置：门店编号
	 */
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	/**
	 * 获取：门店编号
	 */
	public String getShopID() {
		return shopID;
	}
	/**
	 * 设置：店名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：店名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：纬度
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	/**
	 * 获取：纬度
	 */
	public BigDecimal getLat() {
		return lat;
	}
	/**
	 * 设置：经度
	 */
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	/**
	 * 获取：经度
	 */
	public BigDecimal getLng() {
		return lng;
	}
	/**
	 * 设置：安装时间
	 */
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	/**
	 * 获取：安装时间
	 */
	public Date getInstallDate() {
		return installDate;
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
