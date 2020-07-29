package com.nuite.modules.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 门店树节点
 *
 * @Author: yangchuang
 * @Date: 2019/1/12 12:56
 * @Version: 1.0
 */

@Data
@Accessors(chain = true)
public class ShopTreeModel implements Serializable {

    private static final long serialVersionUID = -4621801721937952437L;

    /**
     * 树节点id
     */
    private String id;

    /**
     * 树节点的父节点id
     */
    private String pId;

    /**
     * 节点元素的序号
     */
    private Integer seq;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 门店标记（1是，0否）
     */
    private int shopFlag;
}
