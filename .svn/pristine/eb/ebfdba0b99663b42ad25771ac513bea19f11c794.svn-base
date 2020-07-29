package com.nuite.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
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
@TableName("NWFestival_Template")
public class FestivalTemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Seq", type = IdType.AUTO)
    private Integer seq;
    @TableField("Name")
    private String name;
    @TableField("ParentSeq")
    private Integer parentSeq;
    @TableField("NodeType")
    private Integer nodeType;
    @TableField("UpdateTime")
    private Date updateTime;
    @TableField("InputTime")
    private Date inputTime;
    @TableField("Del")
    @TableLogic
    private Integer del;
    @TableField("BelongTo")
    private Integer belongTo;
    @TableField("CreatorSeq")
    private Integer creatorSeq;

    /**
     * 有效类型标记
     * 1：生效中
     * 2：待生效
     */
    @TableField(exist = false)
    private Integer validFlag = 0;

}
