package com.nuite.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日库存存储详情
 *
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-01-10 17:51:14
 */
@TableName("NWGoods_ShoesData_Daily_Detail")
public class ShoesDataDailyDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.comments
     */
    @TableId(value = "Seq")
    private Integer seq;

    @TableField(value = "ErpOrderNum")
    private String erpOrderNum;
    /**
     * $column.comments
     */
    @TableField(value = "Shop_Seq")
    private Integer shopSeq;

    /**
     * $column.comments
     */
    @TableField(value = "ShopName")
    private String shopName;
    /**
     * $column.comments
     */
    @TableField(value = "Shoes_Seq")
    private Integer shoesSeq;
    /**
     * $column.comments
     */
    @TableField(value = "ShoesId")
    private String shoesId;
    /**
     * 对应的erp数据类型 0：仓出店 1：店退仓 2：店转店 3：销售
     */
    @TableField(value = "Type")
    private Integer type;
    /**
     * $column.comments
     */
    @TableField(value = "Color")
    private Integer color;
    /**
     * $column.comments
     */
    @TableField(value = "Size")
    private Integer size;
    /**
     * $column.comments
     */
    @TableField(value = "Count")
    private Integer count;
    /**
     * 数据实际时间
     */
    @TableField(value = "UpdateTime")
    private Date updateTime;

    @TableField(value = "JoinStock")
    private boolean joinStock;

	/**
	 * 波次序号
	 */
	@TableField(value = "PeriodSeq")
	private Integer periodSeq;
	/**
	 * 大区序号
	 */
	@TableField(value = "AreaSeq")
	private Integer areaSeq;
	/**
	 * 分公司序号
	 */
	@TableField(value = "BranchOfficeSeq")
	private Integer branchOfficeSeq;
    /**
     * 入库时间
     */
    @TableField(value = "InputTime")
    private Date inputTime;
    
	
    /**
     * 设置：${column.comments}
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置：${column.comments}
     */
    public void setShopSeq(Integer shopSeq) {
        this.shopSeq = shopSeq;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getShopSeq() {
        return shopSeq;
    }

    /**
     * 设置：${column.comments}
     */
    public void setShoesSeq(Integer shoesSeq) {
        this.shoesSeq = shoesSeq;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getShoesSeq() {
        return shoesSeq;
    }

    /**
     * 设置：${column.comments}
     */
    public void setShoesId(String shoesId) {
        this.shoesId = shoesId;
    }

    /**
     * 获取：${column.comments}
     */
    public String getShoesId() {
        return shoesId;
    }

    /**
     * 设置：对应的erp数据类型 0：仓出店 1：店退仓 2：店转店 3：销售
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：对应的erp数据类型 0：仓出店 1：店退仓 2：店转店 3：销售
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：${column.comments}
     */
    public void setColor(Integer color) {
        this.color = color;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getColor() {
        return color;
    }

    /**
     * 设置：${column.comments}
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 设置：${column.comments}
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取：${column.comments}
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置：${column.comments}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：${column.comments}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getErpOrderNum() {
        return erpOrderNum;
    }

    public void setErpOrderNum(String erpOrderNum) {
        this.erpOrderNum = erpOrderNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

	public boolean isJoinStock() {
		return joinStock;
	}

	public void setJoinStock(boolean joinStock) {
		this.joinStock = joinStock;
	}

	public Integer getPeriodSeq() {
		return periodSeq;
	}

	public void setPeriodSeq(Integer periodSeq) {
		this.periodSeq = periodSeq;
	}

	public Integer getAreaSeq() {
		return areaSeq;
	}

	public void setAreaSeq(Integer areaSeq) {
		this.areaSeq = areaSeq;
	}

	public Integer getBranchOfficeSeq() {
		return branchOfficeSeq;
	}

	public void setBranchOfficeSeq(Integer branchOfficeSeq) {
		this.branchOfficeSeq = branchOfficeSeq;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

}
