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
@TableName("NWBase_Company")
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 序号(主键)
     */
    @TableId(value = "Seq")
    private Integer seq;
    /**
     * 内部编码
     */
    @TableField(value = "InnerKey")
    private String innerKey;
    /**
     * 公司集团名字
     */
    @TableField(value = "Name")
    private String name;
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
     * 设置：公司集团名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：公司集团名字
     */
    public String getName() {
        return name;
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
