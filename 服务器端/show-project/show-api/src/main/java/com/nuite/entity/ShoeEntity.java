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
 * @date 2018-12-03 17:03:16
 */
@TableName("NWGoods_Shoe")
public class ShoeEntity implements Serializable {
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
	 * 二维码标号（鞋子、标签关联表内二维码一致）
	 */
	@TableField(value = "QRCode")
	private String qRCode;
	/**
	 * 货品名称
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 货号
	 */
	@TableField(value = "ShoeID")
	private String shoeID;
	/**
	 * 鞋子类别序号
	 */
	@TableField(value = "CategorySeq")
	private Integer categorySeq;
	/**
	 * 属性Code(1)
	 */
	@TableField(value = "SX1")
	private String sX1;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX2")
	private String sX2;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX3")
	private String sX3;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX4")
	private String sX4;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX5")
	private String sX5;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX6")
	private String sX6;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX7")
	private String sX7;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX8")
	private String sX8;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX9")
	private String sX9;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX10")
	private String sX10;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX11")
	private String sX11;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX12")
	private String sX12;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX13")
	private String sX13;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX14")
	private String sX14;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX15")
	private String sX15;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX16")
	private String sX16;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX17")
	private String sX17;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX18")
	private String sX18;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX19")
	private String sX19;
	/**
	 * 属性Code(20)
	 */
	@TableField(value = "SX20")
	private String sX20;
	/**
	 * 系列序号
	 */
	@TableField(value = "SeriesSeq")
	private Integer seriesSeq;
	/**
	 * 缩略图名字（展示前十大动画图片）
	 */
	@TableField(value = "Thumbnail")
	private String thumbnail;
	/**
	 * 创建时间
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
	 * 设置：二维码标号（鞋子、标签关联表内二维码一致）
	 */
	public void setQRCode(String qRCode) {
		this.qRCode = qRCode;
	}
	/**
	 * 获取：二维码标号（鞋子、标签关联表内二维码一致）
	 */
	public String getQRCode() {
		return qRCode;
	}
	/**
	 * 设置：货品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：货品名称
	 */
	public String getName() {
		return name;
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
	 * 设置：鞋子类别序号
	 */
	public void setCategorySeq(Integer categorySeq) {
		this.categorySeq = categorySeq;
	}
	/**
	 * 获取：鞋子类别序号
	 */
	public Integer getCategorySeq() {
		return categorySeq;
	}
	/**
	 * 设置：属性Code(1)
	 */
	public void setSX1(String sX1) {
		this.sX1 = sX1;
	}
	/**
	 * 获取：属性Code(1)
	 */
	public String getSX1() {
		return sX1;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX2(String sX2) {
		this.sX2 = sX2;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX2() {
		return sX2;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX3(String sX3) {
		this.sX3 = sX3;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX3() {
		return sX3;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX4(String sX4) {
		this.sX4 = sX4;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX4() {
		return sX4;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX5(String sX5) {
		this.sX5 = sX5;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX5() {
		return sX5;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX6(String sX6) {
		this.sX6 = sX6;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX6() {
		return sX6;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX7(String sX7) {
		this.sX7 = sX7;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX7() {
		return sX7;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX8(String sX8) {
		this.sX8 = sX8;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX8() {
		return sX8;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX9(String sX9) {
		this.sX9 = sX9;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX9() {
		return sX9;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX10(String sX10) {
		this.sX10 = sX10;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX10() {
		return sX10;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX11(String sX11) {
		this.sX11 = sX11;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX11() {
		return sX11;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX12(String sX12) {
		this.sX12 = sX12;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX12() {
		return sX12;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX13(String sX13) {
		this.sX13 = sX13;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX13() {
		return sX13;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX14(String sX14) {
		this.sX14 = sX14;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX14() {
		return sX14;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX15(String sX15) {
		this.sX15 = sX15;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX15() {
		return sX15;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX16(String sX16) {
		this.sX16 = sX16;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX16() {
		return sX16;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX17(String sX17) {
		this.sX17 = sX17;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX17() {
		return sX17;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX18(String sX18) {
		this.sX18 = sX18;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX18() {
		return sX18;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX19(String sX19) {
		this.sX19 = sX19;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX19() {
		return sX19;
	}
	/**
	 * 设置：属性Code(20)
	 */
	public void setSX20(String sX20) {
		this.sX20 = sX20;
	}
	/**
	 * 获取：属性Code(20)
	 */
	public String getSX20() {
		return sX20;
	}
	/**
	 * 设置：系列序号
	 */
	public void setSeriesSeq(Integer seriesSeq) {
		this.seriesSeq = seriesSeq;
	}
	/**
	 * 获取：系列序号
	 */
	public Integer getSeriesSeq() {
		return seriesSeq;
	}
	/**
	 * 设置：缩略图名字（展示前十大动画图片）
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * 获取：缩略图名字（展示前十大动画图片）
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * 设置：创建时间
	 */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * 获取：创建时间
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
