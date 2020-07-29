package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2018-12-10 15:41:37
 */
@TableName("NWGoods_Series")
public class SeriesEntity implements Serializable {
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
     * 系列名称
     */
    @TableField(value = "Name")
    private String name;
    /**
     * 系列描述备注
     */
    @TableField(value = "Remark")
    private String remark;
    /**
     * 用于展示系统背景虚化的图片
     */
    @TableField(value = "BgImg")
    private String bgImg;
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
     * 设置：系列名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：系列名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：系列描述备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：系列描述备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：用于展示系统背景虚化的图片
     */
    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    /**
     * 获取：用于展示系统背景虚化的图片
     */
    public String getBgImg() {
        return bgImg;
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
