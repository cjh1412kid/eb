package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     动画内容，关联一个素材图片
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Data
@TableName("NWFestival_AnimateContent")
public class FestivalAnimateContentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Seq", type = IdType.AUTO)
    private Integer seq;
    /**
     * 风力，风向
     */
    @TableField("WindPower")
    private Integer windPower;
    /**
     * 风速
     */
    @TableField("Speed")
    private Integer speed;
    /**
     * 数量
     */
    @TableField("Count")
    private Integer count;
    /**
     * 大小
     */
    @TableField("Size")
    private Integer size;
    /**
     * 透明度
     */
    @TableField("Opacity")
    private Integer opacity;
    /**
     * 创建时间
     */
    @TableField("InputTime")
    private Date inputTime;
    /**
     * 模版序号
     */
    @TableField("TemplateSeq")
    private Integer templateSeq;
    /**
     * 素材序号
     */
    @TableField("MaterialSeq")
    private Integer materialSeq;
    /**
     * 素材对象
     */
    @TableField(exist = false)
    private FestivalMaterialEntity material;

}
