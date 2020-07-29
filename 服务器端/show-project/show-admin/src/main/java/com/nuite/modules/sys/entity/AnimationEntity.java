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
 * @date 2018-12-10 15:41:36
 */
@TableName("NWBase_Animation")
public class AnimationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 序号(主键)
     */
    @TableId(value = "Seq")
    private Integer seq;
    /**
     * 动画名称
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 动画组件名称
     */
    @TableField(value = "ComponentName")
    private String componentName;
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
     * 设置：动画名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：动画名称
     */
    public String getName() {
        return name;
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
     * 设置：
     * 删除标识( 0 : 未删除、  1 : 删除 )
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 获取：
     * 删除标识( 0 : 未删除、  1 : 删除 )
     */
    public Integer getDel() {
        return del;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}
