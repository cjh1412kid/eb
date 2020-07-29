package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-07-29 16:11:48
 */
@TableName("NWGoods_ShoesMainpush")
public class ShoesMainpushEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(type = IdType.AUTO, value = "Seq")
	private Integer seq;
	/**
	 * 区域类型1:全国 2:大区 3:分公司 4:门店
	 */
	@TableField(value = "AreaType")
	private Integer areaType;
	/**
	 * 区域序号
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 鞋子序号(外键:YHSR_Goods_Shoes表)
	 */
	@TableField(value = "Shoes_Seq")
	private Integer shoesSeq;
	/**
	 * 波次序号
	 */
	@TableField(value = "Period_Seq")
	private Integer periodSeq;
	
	
	
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
	 * 设置：区域类型1:全国 2:大区 3:分公司 4:门店
	 */
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	/**
	 * 获取：区域类型1:全国 2:大区 3:分公司 4:门店
	 */
	public Integer getAreaType() {
		return areaType;
	}
	/**
	 * 设置：区域序号
	 */
	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}
	/**
	 * 获取：区域序号
	 */
	public Integer getAreaSeq() {
		return areaSeq;
	}
	/**
	 * 设置：鞋子序号(外键:YHSR_Goods_Shoes表)
	 */
	public void setShoesSeq(Integer shoesSeq) {
		this.shoesSeq = shoesSeq;
	}
	/**
	 * 获取：鞋子序号(外键:YHSR_Goods_Shoes表)
	 */
	public Integer getShoesSeq() {
		return shoesSeq;
	}
	public Integer getPeriodSeq() {
		return periodSeq;
	}
	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}
}
