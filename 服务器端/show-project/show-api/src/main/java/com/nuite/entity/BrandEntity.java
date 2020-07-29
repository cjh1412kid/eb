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
 * @date 2018-12-07 16:14:52
 */
@TableName("NWBase_Brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 前台系统识别码
	 */
	@TableField(value = "IdentifyCode")
	private String identifyCode;
	/**
	 * 内部编码
	 */
	@TableField(value = "InnerKey")
	private String innerKey;
	/**
	 * 用户名前缀
	 */
	@TableField(value = "Prefix")
	private String prefix;
	/**
	 * 品牌名
	 */
	@TableField(value = "BrandName")
	private String brandName;
	/**
	 * 所属公司序号
	 */
	@TableField(value = "CompanySeq")
	private Integer companySeq;
	/**
	 * 二维码（吸粉）
	 */
	@TableField(value = "QRCodeName")
	private String qRCodeName;
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
	 * 设置：前台系统识别码
	 */
	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}
	/**
	 * 获取：前台系统识别码
	 */
	public String getIdentifyCode() {
		return identifyCode;
	}
	/**
	 * 设置：内部编码
	 */
	public void setInnerKey(String innerKey) {
		this.innerKey = innerKey;
	}
	/**
	 * 获取：内部编码
	 */
	public String getInnerKey() {
		return innerKey;
	}
	/**
	 * 设置：用户名前缀
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * 获取：用户名前缀
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * 设置：品牌名
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：品牌名
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * 设置：所属公司序号
	 */
	public void setCompanySeq(Integer companySeq) {
		this.companySeq = companySeq;
	}
	/**
	 * 获取：所属公司序号
	 */
	public Integer getCompanySeq() {
		return companySeq;
	}
	/**
	 * 设置：二维码（吸粉）
	 */
	public void setQRCodeName(String qRCodeName) {
		this.qRCodeName = qRCodeName;
	}
	/**
	 * 获取：二维码（吸粉）
	 */
	public String getQRCodeName() {
		return qRCodeName;
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
