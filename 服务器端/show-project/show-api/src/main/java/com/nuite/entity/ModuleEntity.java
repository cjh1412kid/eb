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
 * @date 2019-06-11 17:13:19
 */
@TableName("NWBase_Module")
public class ModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 专题名称
	 */
	@TableField(value = "TopicName")
	private String topicName;
	/**
	 * 专题排序
	 */
	@TableField(value = "TopicSort")
	private Integer topicSort;
	/**
	 * 模块名称
	 */
	@TableField(value = "ModuleName")
	private String moduleName;
	/**
	 * 模块排序
	 */
	@TableField(value = "ModuleSort")
	private Integer moduleSort;
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
	 * 设置：专题名称
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * 获取：专题名称
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * 设置：专题排序
	 */
	public void setTopicSort(Integer topicSort) {
		this.topicSort = topicSort;
	}
	/**
	 * 获取：专题排序
	 */
	public Integer getTopicSort() {
		return topicSort;
	}
	/**
	 * 设置：模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * 获取：模块名称
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * 设置：模块排序
	 */
	public void setModuleSort(Integer moduleSort) {
		this.moduleSort = moduleSort;
	}
	/**
	 * 获取：模块排序
	 */
	public Integer getModuleSort() {
		return moduleSort;
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
