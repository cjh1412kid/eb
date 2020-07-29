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
 * @date 2019-01-12 13:46:50
 */
@TableName("NWGoods_Period")
public class PeriodEntity implements Serializable {
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
	 * 内部编码
	 */
	@TableField(value = "InnerKey")
	private String innerKey;
	/**
	 * 波次名字
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 对应波次下鞋子素材文件的起始下载时间
	 */
	@TableField(value = "FileDownLoadDate")
	private Date fileDownLoadDate;
	/**
	 * 上架销售时间
	 */
	@TableField(value = "SaleDate")
	private Date saleDate;
	/**
	 * 0：无效 1：有效
	 */
	@TableField(value = "isValid")
	private Integer isValid;
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
	 * 设置：波次名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：波次名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：对应波次下鞋子素材文件的起始下载时间
	 */
	public void setFileDownLoadDate(Date fileDownLoadDate) {
		this.fileDownLoadDate = fileDownLoadDate;
	}
	/**
	 * 获取：对应波次下鞋子素材文件的起始下载时间
	 */
	public Date getFileDownLoadDate() {
		return fileDownLoadDate;
	}
	/**
	 * 设置：上架销售时间
	 */
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	/**
	 * 获取：上架销售时间
	 */
	public Date getSaleDate() {
		return saleDate;
	}
	/**
	 * 设置：0：无效 1：有效
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	/**
	 * 获取：0：无效 1：有效
	 */
	public Integer getIsValid() {
		return isValid;
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
