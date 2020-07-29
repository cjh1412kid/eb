package com.nuite.model;

import java.math.BigDecimal;

public class QuotaSaleModel {
    private Integer areaSeq;
    private String areaName;
    private Integer officeSeq;
    private String officeName;
    private Integer shopSeq;
    private String shopName;
    private int dayOfMonth;
    private int weekOfMonth;
    //今天当月某天的销售额
    private BigDecimal currentPrice;
    //去年同一天的销售额
    private BigDecimal beforePrice;

    public QuotaSaleModel() {
    }

    public QuotaSaleModel(Integer areaSeq, Integer officeSeq, Integer shopSeq, String areaName, String officeName, String shopName) {
        this.areaSeq = areaSeq;
        this.officeSeq = officeSeq;
        this.shopSeq = shopSeq;
        this.areaName = areaName;
        this.officeName = officeName;
        this.shopName = shopName;
    }

    public Integer getAreaSeq() {
        return areaSeq;
    }

    public void setAreaSeq(Integer areaSeq) {
        this.areaSeq = areaSeq;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getOfficeSeq() {
        return officeSeq;
    }

    public void setOfficeSeq(Integer officeSeq) {
        this.officeSeq = officeSeq;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getShopSeq() {
        return shopSeq;
    }

    public void setShopSeq(Integer shopSeq) {
        this.shopSeq = shopSeq;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public void setWeekOfMonth(int weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getBeforePrice() {
        return beforePrice;
    }

    public void setBeforePrice(BigDecimal beforePrice) {
        this.beforePrice = beforePrice;
    }

    public boolean sameShopAndDay(Integer areaSeq, Integer officeSeq, Integer shopSeq, int dayOfMonth) {
        if (this.areaSeq != null && this.areaSeq.equals(areaSeq)
                && this.officeSeq != null && this.officeSeq.equals(officeSeq)
                && this.shopSeq != null && this.shopSeq.equals(shopSeq)
                && this.dayOfMonth == dayOfMonth) {
            return true;
        } else {
            return false;
        }
    }
}
