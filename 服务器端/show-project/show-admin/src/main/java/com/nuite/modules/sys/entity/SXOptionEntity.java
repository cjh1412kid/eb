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
 * @date 2019-01-11 09:33:40
 */
@TableName("NWGoods_SXOption")
public class SXOptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 属性序号( 外键:YHSR_Goods_SX表 )
	 */
	@TableField(value = "SXSeq")
	private Integer sXSeq;
	/**
	 * 属性编码
	 */
	@TableField(value = "Code")
	private String code;
	/**
	 * 属性值
	 */
	@TableField(value = "Value")
	private String value;
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
	 * 设置：属性序号( 外键:YHSR_Goods_SX表 )
	 */
	public void setSXSeq(Integer sXSeq) {
		this.sXSeq = sXSeq;
	}
	/**
	 * 获取：属性序号( 外键:YHSR_Goods_SX表 )
	 */
	public Integer getSXSeq() {
		return sXSeq;
	}
	/**
	 * 设置：属性编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：属性编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：属性值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：属性值
	 */
	public String getValue() {
		return value;
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
