package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * 补单配置表
 * @author yy
 * @date 2019-10-12 14:33:01
 */
@TableName("NWReplenishment_DeadlineRaiseRate")
public class DeadlineRaiseRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(主键)
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * 年份
	 */
	@TableField(value = "Year")
	private Integer year;
	/**
	 * 春深 考核截止日期
	 */
	@TableField(value = "SpringDeep")
	private Date springDeep;
	/**
	 * 春浅 考核截止日期
	 */
	@TableField(value = "SpringShallow")
	private Date springShallow;
	/**
	 * 半凉 考核截止日期
	 */
	@TableField(value = "HalfCold")
	private Date halfCold;
	/**
	 * 全凉 考核截止日期
	 */
	@TableField(value = "FullCold")
	private Date fullCold;
	/**
	 * 秋浅 考核截止日期
	 */
	@TableField(value = "AutumnShallow")
	private Date autumnShallow;
	/**
	 * 秋深 考核截止日期
	 */
	@TableField(value = "AutumnDeep")
	private Date autumnDeep;
	/**
	 * 单靴 考核截止日期
	 */
	@TableField(value = "SingleBoot")
	private Date singleBoot;
	/**
	 * 二棉 考核截止日期
	 */
	@TableField(value = "TwoCotton")
	private Date twoCotton;
	/**
	 * 大棉 考核截止日期
	 */
	@TableField(value = "BigCotton")
	private Date bigCotton;
	
	
	/**
	 * 春深 售罄率考核值
	 */
	@TableField(value = "SpringDeepSaleOutRate")
	private Integer springDeepSaleOutRate;
	/**
	 * 春浅 售罄率考核值
	 */
	@TableField(value = "SpringShallowSaleOutRate")
	private Integer springShallowSaleOutRate;
	/**
	 * 半凉 售罄率考核值
	 */
	@TableField(value = "HalfColdSaleOutRate")
	private Integer halfColdSaleOutRate;
	/**
	 * 全凉 售罄率考核值
	 */
	@TableField(value = "FullColdSaleOutRate")
	private Integer fullColdSaleOutRate;
	/**
	 * 秋浅 售罄率考核值
	 */
	@TableField(value = "AutumnShallowSaleOutRate")
	private Integer autumnShallowSaleOutRate;
	/**
	 * 秋深 售罄率考核值
	 */
	@TableField(value = "AutumnDeepSaleOutRate")
	private Integer autumnDeepSaleOutRate;
	/**
	 * 单靴 售罄率考核值
	 */
	@TableField(value = "SingleBootSaleOutRate")
	private Integer singleBootSaleOutRate;
	/**
	 * 二棉 售罄率考核值
	 */
	@TableField(value = "TwoCottonSaleOutRate")
	private Integer twoCottonSaleOutRate;
	/**
	 * 大棉 售罄率考核值
	 */
	@TableField(value = "BigCottonSaleOutRate")
	private Integer bigCottonSaleOutRate;
	
	
	/**
	 * 春深 默认同比增长率
	 */
	@TableField(value = "SpringDeepYearRaise")
	private Integer springDeepYearRaise;
	/**
	 * 春浅 默认同比增长率
	 */
	@TableField(value = "SpringShallowYearRaise")
	private Integer springShallowYearRaise;
	/**
	 * 半凉 默认同比增长率
	 */
	@TableField(value = "HalfColdYearRaise")
	private Integer halfColdYearRaise;
	/**
	 * 全凉 默认同比增长率
	 */
	@TableField(value = "FullColdYearRaise")
	private Integer fullColdYearRaise;
	/**
	 * 秋浅 默认同比增长率
	 */
	@TableField(value = "AutumnShallowYearRaise")
	private Integer autumnShallowYearRaise;
	/**
	 * 秋深 默认同比增长率
	 */
	@TableField(value = "AutumnDeepYearRaise")
	private Integer autumnDeepYearRaise;
	/**
	 * 单靴 默认同比增长率
	 */
	@TableField(value = "SingleBootYearRaise")
	private Integer singleBootYearRaise;
	/**
	 * 二棉 默认同比增长率
	 */
	@TableField(value = "TwoCottonYearRaise")
	private Integer twoCottonYearRaise;
	/**
	 * 大棉 默认同比增长率
	 */
	@TableField(value = "BigCottonYearRaise")
	private Integer bigCottonYearRaise;
	
	/**
	 * 补单提前天数（补单截止日期距离考核截止日期提前的天数）
	 */
	@TableField(value = "PatchAdvanceDays")
	private Integer patchAdvanceDays;
	/**
	 * 销售对比最少销售天数（销售满一定天数才与 去年销量 算 同比增长率，否则使用 默认同比增长率）
	 */
	@TableField(value = "MinSalesDays")
	private Integer minSalesDays;
	/**
	 * 黑马 允许最大上柜天数
	 */
	@TableField(value = "DarkHorseCabinetDays")
	private Integer darkHorseCabinetDays;
	
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
	 * 设置：年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：年份
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * 设置：春深
	 */
	public void setSpringDeep(Date springDeep) {
		this.springDeep = springDeep;
	}
	/**
	 * 获取：春深
	 */
	public Date getSpringDeep() {
		return springDeep;
	}
	/**
	 * 设置：春浅
	 */
	public void setSpringShallow(Date springShallow) {
		this.springShallow = springShallow;
	}
	/**
	 * 获取：春浅
	 */
	public Date getSpringShallow() {
		return springShallow;
	}
	/**
	 * 设置：半凉
	 */
	public void setHalfCold(Date halfCold) {
		this.halfCold = halfCold;
	}
	/**
	 * 获取：半凉
	 */
	public Date getHalfCold() {
		return halfCold;
	}
	/**
	 * 设置：全凉
	 */
	public void setFullCold(Date fullCold) {
		this.fullCold = fullCold;
	}
	/**
	 * 获取：全凉
	 */
	public Date getFullCold() {
		return fullCold;
	}
	/**
	 * 设置：秋浅
	 */
	public void setAutumnShallow(Date autumnShallow) {
		this.autumnShallow = autumnShallow;
	}
	/**
	 * 获取：秋浅
	 */
	public Date getAutumnShallow() {
		return autumnShallow;
	}
	/**
	 * 设置：秋深
	 */
	public void setAutumnDeep(Date autumnDeep) {
		this.autumnDeep = autumnDeep;
	}
	/**
	 * 获取：秋深
	 */
	public Date getAutumnDeep() {
		return autumnDeep;
	}
	/**
	 * 设置：单靴
	 */
	public void setSingleBoot(Date singleBoot) {
		this.singleBoot = singleBoot;
	}
	/**
	 * 获取：单靴
	 */
	public Date getSingleBoot() {
		return singleBoot;
	}
	/**
	 * 设置：二棉
	 */
	public void setTwoCotton(Date twoCotton) {
		this.twoCotton = twoCotton;
	}
	/**
	 * 获取：二棉
	 */
	public Date getTwoCotton() {
		return twoCotton;
	}
	/**
	 * 设置：大棉
	 */
	public void setBigCotton(Date bigCotton) {
		this.bigCotton = bigCotton;
	}
	/**
	 * 获取：大棉
	 */
	public Date getBigCotton() {
		return bigCotton;
	}
	/**
	 * 设置：销售对比最少销售天数
	 */
	public void setMinSalesDays(Integer minSalesDays) {
		this.minSalesDays = minSalesDays;
	}
	/**
	 * 获取：销售对比最少销售天数
	 */
	public Integer getMinSalesDays() {
		return minSalesDays;
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
	public Integer getPatchAdvanceDays() {
		return patchAdvanceDays;
	}
	public void setPatchAdvanceDays(Integer patchAdvanceDays) {
		this.patchAdvanceDays = patchAdvanceDays;
	}
	public Integer getSpringDeepSaleOutRate() {
		return springDeepSaleOutRate;
	}
	public void setSpringDeepSaleOutRate(Integer springDeepSaleOutRate) {
		this.springDeepSaleOutRate = springDeepSaleOutRate;
	}
	public Integer getSpringShallowSaleOutRate() {
		return springShallowSaleOutRate;
	}
	public void setSpringShallowSaleOutRate(Integer springShallowSaleOutRate) {
		this.springShallowSaleOutRate = springShallowSaleOutRate;
	}
	public Integer getHalfColdSaleOutRate() {
		return halfColdSaleOutRate;
	}
	public void setHalfColdSaleOutRate(Integer halfColdSaleOutRate) {
		this.halfColdSaleOutRate = halfColdSaleOutRate;
	}
	public Integer getFullColdSaleOutRate() {
		return fullColdSaleOutRate;
	}
	public void setFullColdSaleOutRate(Integer fullColdSaleOutRate) {
		this.fullColdSaleOutRate = fullColdSaleOutRate;
	}
	public Integer getAutumnShallowSaleOutRate() {
		return autumnShallowSaleOutRate;
	}
	public void setAutumnShallowSaleOutRate(Integer autumnShallowSaleOutRate) {
		this.autumnShallowSaleOutRate = autumnShallowSaleOutRate;
	}
	public Integer getAutumnDeepSaleOutRate() {
		return autumnDeepSaleOutRate;
	}
	public void setAutumnDeepSaleOutRate(Integer autumnDeepSaleOutRate) {
		this.autumnDeepSaleOutRate = autumnDeepSaleOutRate;
	}
	public Integer getSingleBootSaleOutRate() {
		return singleBootSaleOutRate;
	}
	public void setSingleBootSaleOutRate(Integer singleBootSaleOutRate) {
		this.singleBootSaleOutRate = singleBootSaleOutRate;
	}
	public Integer getTwoCottonSaleOutRate() {
		return twoCottonSaleOutRate;
	}
	public void setTwoCottonSaleOutRate(Integer twoCottonSaleOutRate) {
		this.twoCottonSaleOutRate = twoCottonSaleOutRate;
	}
	public Integer getBigCottonSaleOutRate() {
		return bigCottonSaleOutRate;
	}
	public void setBigCottonSaleOutRate(Integer bigCottonSaleOutRate) {
		this.bigCottonSaleOutRate = bigCottonSaleOutRate;
	}
	public Integer getSpringDeepYearRaise() {
		return springDeepYearRaise;
	}
	public void setSpringDeepYearRaise(Integer springDeepYearRaise) {
		this.springDeepYearRaise = springDeepYearRaise;
	}
	public Integer getSpringShallowYearRaise() {
		return springShallowYearRaise;
	}
	public void setSpringShallowYearRaise(Integer springShallowYearRaise) {
		this.springShallowYearRaise = springShallowYearRaise;
	}
	public Integer getHalfColdYearRaise() {
		return halfColdYearRaise;
	}
	public void setHalfColdYearRaise(Integer halfColdYearRaise) {
		this.halfColdYearRaise = halfColdYearRaise;
	}
	public Integer getFullColdYearRaise() {
		return fullColdYearRaise;
	}
	public void setFullColdYearRaise(Integer fullColdYearRaise) {
		this.fullColdYearRaise = fullColdYearRaise;
	}
	public Integer getAutumnShallowYearRaise() {
		return autumnShallowYearRaise;
	}
	public void setAutumnShallowYearRaise(Integer autumnShallowYearRaise) {
		this.autumnShallowYearRaise = autumnShallowYearRaise;
	}
	public Integer getAutumnDeepYearRaise() {
		return autumnDeepYearRaise;
	}
	public void setAutumnDeepYearRaise(Integer autumnDeepYearRaise) {
		this.autumnDeepYearRaise = autumnDeepYearRaise;
	}
	public Integer getSingleBootYearRaise() {
		return singleBootYearRaise;
	}
	public void setSingleBootYearRaise(Integer singleBootYearRaise) {
		this.singleBootYearRaise = singleBootYearRaise;
	}
	public Integer getTwoCottonYearRaise() {
		return twoCottonYearRaise;
	}
	public void setTwoCottonYearRaise(Integer twoCottonYearRaise) {
		this.twoCottonYearRaise = twoCottonYearRaise;
	}
	public Integer getBigCottonYearRaise() {
		return bigCottonYearRaise;
	}
	public void setBigCottonYearRaise(Integer bigCottonYearRaise) {
		this.bigCottonYearRaise = bigCottonYearRaise;
	}
	public Integer getDarkHorseCabinetDays() {
		return darkHorseCabinetDays;
	}
	public void setDarkHorseCabinetDays(Integer darkHorseCabinetDays) {
		this.darkHorseCabinetDays = darkHorseCabinetDays;
	}
}
