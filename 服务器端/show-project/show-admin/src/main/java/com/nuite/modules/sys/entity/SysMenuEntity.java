package com.nuite.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

@Data
@TableName("NWUser_System_Menu")
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "Seq")
    private Integer seq;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField(value = "Parent_Seq")
    private Long parentSeq;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 菜单名称
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 菜单URL
     */
    @TableField(value = "Url")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @TableField(value = "Perms")
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @TableField(value = "Type")
    private Integer type;

    /**
     * 菜单图标
     */
    @TableField(value = "Icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "Order_Num")
    private Integer orderNum;

    /**
     * 工厂私有
     */
    @TableField(value = "Alone")
    private Boolean alone;

    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<SysMenuEntity> list;

 
}
