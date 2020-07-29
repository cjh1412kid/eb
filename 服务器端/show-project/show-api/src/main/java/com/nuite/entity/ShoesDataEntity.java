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
 * @date 2018-12-04 16:20:39
 */
@TableName("NWGoods_ShoesData")
public class ShoesDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 门店序号
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * 鞋子序号
	 */
	@TableField(value = "ShoeSeq")
	private Integer shoeSeq;
	/**
	 * 颜色seq
	 */
	@TableField(value = "ColorSeq")
	private Integer colorSeq;
	/**
	 * 尺码seq
	 */
	@TableField(value = "SizeSeq")
	private Integer sizeSeq;
	/**
	 * 总数量
	 */
	@TableField(value = "Num")
	private Integer num;
	/**
	 * 库存修改时间
	 */
	@TableField(value = "StockDate")
	private Date stockDate;
	/**
	 * 库存
	 */
	@TableField(value = "Stock")
	private Integer stock;
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
	public Integer getShopSeq() {
		return shopSeq;
	}
	public void setShopSeq(Integer shopSeq) {
		this.shopSeq = shopSeq;
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
	 * 设置：颜色seq
	 */
	public void setColorSeq(Integer colorSeq) {
		this.colorSeq = colorSeq;
	}
	/**
	 * 获取：颜色seq
	 */
	public Integer getColorSeq() {
		return colorSeq;
	}
	/**
	 * 设置：尺码seq
	 */
	public void setSizeSeq(Integer sizeSeq) {
		this.sizeSeq = sizeSeq;
	}
	/**
	 * 获取：尺码seq
	 */
	public Integer getSizeSeq() {
		return sizeSeq;
	}
	/**
	 * 设置：总数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：总数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：库存修改时间
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	/**
	 * 获取：库存修改时间
	 */
	public Date getStockDate() {
		return stockDate;
	}
	/**
	 * 设置：库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStock() {
		return stock;
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
	public Integer getPeriodSeq() {
		return periodSeq;
	}
	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}
	public Integer getAreaSeq() {
		return areaSeq;
	}
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	public Integer getBranchOfficeSeq() {
		return branchOfficeSeq;
	}
	public void setBranchOfficeSeq(Integer branchOfficeSeq) {
		this.branchOfficeSeq = branchOfficeSeq;
	}
	
}
