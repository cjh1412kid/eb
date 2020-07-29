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
 * @since 2019-01-11
 */
@Data
@TableName("NWFestival_Material")
public class FestivalMaterialEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Seq", type = IdType.AUTO)
    private Integer seq;
    @TableField("Path")
    private String path;
    @TableField("InputTime")
    private Date inputTime;
    @TableField("BelongTo")
    private Integer belongTo;
    @TableField("CreatorSeq")
    private Integer creatorSeq;


}
