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
 * 
 * </p>
 *
 * @author yangchuang
 * @since 2019-02-19
 */
@Data
@TableName("NWFestival_TemplateBackground")
public class FestivalBackgroundEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Seq", type = IdType.AUTO)
    private Integer seq;
    @TableField("TemplateSeq")
    private Integer templateSeq;
    @TableField("MaterialSeq")
    private Integer materialSeq;
    @TableField("CreatorSeq")
    private Integer creatorSeq;
    @TableField("InputTime")
    private Date inputTime;

    @TableField(exist = false)
    private FestivalMaterialEntity material;
}
