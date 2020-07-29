package com.nuite.modules.sys.entity;

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
 * @date 2018-12-03 16:56:33
 */
@TableName("NWGoods_ShoeView")
public class ShoeViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * $column.comments
	 */
	@TableField(value = "PeriodSeq")
	private Integer periodSeq;
	/**
	 * $column.comments
	 */
	@TableField(value = "QRCode")
	private String qRCode;
	/**
	 * $column.comments
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * $column.comments
	 */
	@TableField(value = "ShoeID")
	private String shoeID;
	/**
	 * $column.comments
	 */
	@TableField(value = "CategorySeq")
	private Integer categorySeq;
	/**
	 * $column.comments
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
	 * $column.comments
	 */
	@TableField(value = "SX20")
	private String sX20;
	/**
	 * $column.comments
	 */
	@TableField(value = "SeriesSeq")
	private Integer seriesSeq;
	/**
	 * $column.comments
	 */
	@TableField(value = "Thumbnail")
	private String thumbnail;
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
	 * $column.comments
	 */
	@TableField(value = "BrandSeq")
	private Integer brandSeq;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX1Name")
	private String sX1Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX1Value")
	private String sX1Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX2Name")
	private String sX2Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX2Value")
	private String sX2Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX3Name")
	private String sX3Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX3Value")
	private String sX3Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX4Name")
	private String sX4Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX4Value")
	private String sX4Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX5Name")
	private String sX5Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX5Value")
	private String sX5Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX6Name")
	private String sX6Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX6Value")
	private String sX6Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX7Name")
	private String sX7Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX7Value")
	private String sX7Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX8Name")
	private String sX8Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX8Value")
	private String sX8Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX9Name")
	private String sX9Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX9Value")
	private String sX9Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX10Name")
	private String sX10Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX10Value")
	private String sX10Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX11Name")
	private String sX11Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX11Value")
	private String sX11Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX12Name")
	private String sX12Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX12Value")
	private String sX12Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX13Name")
	private String sX13Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX13Value")
	private String sX13Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX14Name")
	private String sX14Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX14Value")
	private String sX14Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX15Name")
	private String sX15Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX15Value")
	private String sX15Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX16Name")
	private String sX16Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX16Value")
	private String sX16Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX17Name")
	private String sX17Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX17Value")
	private String sX17Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX18Name")
	private String sX18Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX18Value")
	private String sX18Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX19Name")
	private String sX19Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX19Value")
	private String sX19Value;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX20Name")
	private String sX20Name;
	/**
	 * $column.comments
	 */
	@TableField(value = "SX20Value")
	private String sX20Value;

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
	 * 设置：${column.comments}
	 */
	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getPeriodSeq() {
		return periodSeq;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setQRCode(String qRCode) {
		this.qRCode = qRCode;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getQRCode() {
		return qRCode;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setShoeID(String shoeID) {
		this.shoeID = shoeID;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getShoeID() {
		return shoeID;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCategorySeq(Integer categorySeq) {
		this.categorySeq = categorySeq;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getCategorySeq() {
		return categorySeq;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX1(String sX1) {
		this.sX1 = sX1;
	}
	/**
	 * 获取：${column.comments}
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
	 * 设置：${column.comments}
	 */
	public void setSX20(String sX20) {
		this.sX20 = sX20;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX20() {
		return sX20;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSeriesSeq(Integer seriesSeq) {
		this.seriesSeq = seriesSeq;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getSeriesSeq() {
		return seriesSeq;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getThumbnail() {
		return thumbnail;
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
	/**
	 * 设置：${column.comments}
	 */
	public void setBrandSeq(Integer brandSeq) {
		this.brandSeq = brandSeq;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Integer getBrandSeq() {
		return brandSeq;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX1Name(String sX1Name) {
		this.sX1Name = sX1Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX1Name() {
		return sX1Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX1Value(String sX1Value) {
		this.sX1Value = sX1Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX1Value() {
		return sX1Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX2Name(String sX2Name) {
		this.sX2Name = sX2Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX2Name() {
		return sX2Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX2Value(String sX2Value) {
		this.sX2Value = sX2Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX2Value() {
		return sX2Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX3Name(String sX3Name) {
		this.sX3Name = sX3Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX3Name() {
		return sX3Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX3Value(String sX3Value) {
		this.sX3Value = sX3Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX3Value() {
		return sX3Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX4Name(String sX4Name) {
		this.sX4Name = sX4Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX4Name() {
		return sX4Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX4Value(String sX4Value) {
		this.sX4Value = sX4Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX4Value() {
		return sX4Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX5Name(String sX5Name) {
		this.sX5Name = sX5Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX5Name() {
		return sX5Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX5Value(String sX5Value) {
		this.sX5Value = sX5Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX5Value() {
		return sX5Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX6Name(String sX6Name) {
		this.sX6Name = sX6Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX6Name() {
		return sX6Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX6Value(String sX6Value) {
		this.sX6Value = sX6Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX6Value() {
		return sX6Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX7Name(String sX7Name) {
		this.sX7Name = sX7Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX7Name() {
		return sX7Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX7Value(String sX7Value) {
		this.sX7Value = sX7Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX7Value() {
		return sX7Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX8Name(String sX8Name) {
		this.sX8Name = sX8Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX8Name() {
		return sX8Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX8Value(String sX8Value) {
		this.sX8Value = sX8Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX8Value() {
		return sX8Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX9Name(String sX9Name) {
		this.sX9Name = sX9Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX9Name() {
		return sX9Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX9Value(String sX9Value) {
		this.sX9Value = sX9Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX9Value() {
		return sX9Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX10Name(String sX10Name) {
		this.sX10Name = sX10Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX10Name() {
		return sX10Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX10Value(String sX10Value) {
		this.sX10Value = sX10Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX10Value() {
		return sX10Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX11Name(String sX11Name) {
		this.sX11Name = sX11Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX11Name() {
		return sX11Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX11Value(String sX11Value) {
		this.sX11Value = sX11Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX11Value() {
		return sX11Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX12Name(String sX12Name) {
		this.sX12Name = sX12Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX12Name() {
		return sX12Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX12Value(String sX12Value) {
		this.sX12Value = sX12Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX12Value() {
		return sX12Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX13Name(String sX13Name) {
		this.sX13Name = sX13Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX13Name() {
		return sX13Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX13Value(String sX13Value) {
		this.sX13Value = sX13Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX13Value() {
		return sX13Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX14Name(String sX14Name) {
		this.sX14Name = sX14Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX14Name() {
		return sX14Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX14Value(String sX14Value) {
		this.sX14Value = sX14Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX14Value() {
		return sX14Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX15Name(String sX15Name) {
		this.sX15Name = sX15Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX15Name() {
		return sX15Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX15Value(String sX15Value) {
		this.sX15Value = sX15Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX15Value() {
		return sX15Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX16Name(String sX16Name) {
		this.sX16Name = sX16Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX16Name() {
		return sX16Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX16Value(String sX16Value) {
		this.sX16Value = sX16Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX16Value() {
		return sX16Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX17Name(String sX17Name) {
		this.sX17Name = sX17Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX17Name() {
		return sX17Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX17Value(String sX17Value) {
		this.sX17Value = sX17Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX17Value() {
		return sX17Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX18Name(String sX18Name) {
		this.sX18Name = sX18Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX18Name() {
		return sX18Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX18Value(String sX18Value) {
		this.sX18Value = sX18Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX18Value() {
		return sX18Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX19Name(String sX19Name) {
		this.sX19Name = sX19Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX19Name() {
		return sX19Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX19Value(String sX19Value) {
		this.sX19Value = sX19Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX19Value() {
		return sX19Value;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX20Name(String sX20Name) {
		this.sX20Name = sX20Name;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX20Name() {
		return sX20Name;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSX20Value(String sX20Value) {
		this.sX20Value = sX20Value;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSX20Value() {
		return sX20Value;
	}
}
