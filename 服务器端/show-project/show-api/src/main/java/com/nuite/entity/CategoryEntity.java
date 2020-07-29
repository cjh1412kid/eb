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
 * @date 2018-12-04 16:01:57
 */
@TableName("NWGoods_Category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 分类父节点序号
	 */
	@TableField(value = "ParentSeq")
	private Integer parentSeq;
	/**
	 * 品牌序号
	 */
	@TableField(value = "BrandSeq")
	private Integer brandSeq;
	/**
	 * 分类名称
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 分类代码
	 */
	@TableField(value = "CategoryCode")
	private String categoryCode;
	/**
	 * 是否可见（ 0 : 可见,   1 : 不可见 ）
	 */
	@TableField(value = "Visible")
	private Integer visible;
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
	 * 设置：分类父节点序号
	 */
	public void setParentSeq(Integer parentSeq) {
		this.parentSeq = parentSeq;
	}
	/**
	 * 获取：分类父节点序号
	 */
	public Integer getParentSeq() {
		return parentSeq;
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
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：分类代码
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * 获取：分类代码
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	/**
	 * 设置：是否可见（ 0 : 可见,   1 : 不可见 ）
	 */
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	/**
	 * 获取：是否可见（ 0 : 可见,   1 : 不可见 ）
	 */
	public Integer getVisible() {
		return visible;
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
