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
 * <p>
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Data
@TableName("NWFestival_StaticContent")
public class FestivalStaticContentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Seq", type = IdType.AUTO)
    private Integer seq;
    @TableField("Position")
    private Integer position;
    @TableField("Opacity")
    private Integer opacity;
    @TableField("Wper")
    private Integer WPer;
    @TableField("Hper")
    private Integer HPer;
    @TableField("TemplateSeq")
    private Integer templateSeq;
    @TableField("MaterialSeq")
    private Integer materialSeq;
    @TableField("InputTime")
    private Date inputTime;

    @TableField(exist = false)
    private FestivalMaterialEntity material;

}
