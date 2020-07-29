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
 * @date 2018-12-27 11:04:07
 */
@TableName("NWUser_Assistant")
public class AssistantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 门店序号
	 */
	@TableField(value = "ShopSeq")
	private Integer shopSeq;
	/**
	 * 姓名
	 */
	@TableField(value = "Name")
	private String name;
	/**
	 * 级别
	 */
	@TableField(value = "AssistantLevel")
	private String assistantLevel;
	/**
	 * 电话
	 */
	@TableField(value = "Telephone")
	private String telephone;
	/**
	 * 头像
	 */
	@TableField(value = "HeadImg")
	private String headImg;
	/**
	 * 是否有效( 0 : 无效、1 : 有效 )
	 */
	@TableField(value = "IsUseful")
	private Integer isUseful;
	/**
	 * 创建人
	 */
	@TableField(value = "CreateUserSeq")
	private Integer createUserSeq;
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
	 * 设置：门店序号
	 */
	public void setShopSeq(Integer shopSeq) {
		this.shopSeq = shopSeq;
	}
	/**
	 * 获取：门店序号
	 */
	public Integer getShopSeq() {
		return shopSeq;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：级别
	 */
	public void setAssistantLevel(String assistantLevel) {
		this.assistantLevel = assistantLevel;
	}
	/**
	 * 获取：级别
	 */
	public String getAssistantLevel() {
		return assistantLevel;
	}
	/**
	 * 设置：电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * 设置：是否有效( 0 : 无效、1 : 有效 )
	 */
	public void setIsUseful(Integer isUseful) {
		this.isUseful = isUseful;
	}
	/**
	 * 获取：是否有效( 0 : 无效、1 : 有效 )
	 */
	public Integer getIsUseful() {
		return isUseful;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUserSeq(Integer createUserSeq) {
		this.createUserSeq = createUserSeq;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUserSeq() {
		return createUserSeq;
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
