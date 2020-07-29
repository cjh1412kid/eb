package com.nuite.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品版所有模块seq、名称
 */
public enum ProductModuleEnum {
	
    TRY_TRYMAP(1, "试穿专题", "试穿地图"),
    TRY_TRYCONVERT(2, "试穿专题", "试穿转化率"),
    TRY_TRYSHOESDETAIL(3, "试穿专题", "试穿数据分析"),
    TRY_TRYTOP(4, "试穿专题", "试穿排名"),
    SALE_SALESHOESDETAIL(5, "销售专题", "销售额统计"),
    SALE_SALEQUOTAANALYZE(6, "销售专题", "销售指标分析"),
    SALE_SALETOP(7, "销售专题", "销售排名"),
    SALE_SALEPRICETOP(8, "销售专题", "贡献度排名"),
    SALE_SALELAST(9, "销售专题", "滞销排名"),
    REPORT_WEEKSALES(10, "报表专题", "导购销售周报"),
    REPORT_COMPETEANALYSIS(11, "报表专题", "竞争力分析"),
    REPORT_SALEANDSTOCK(12, "报表专题", "品类销售及库存分析"),
	REPORT_COMPETEANDSALESFOLLOW(13, "报表专题", "商场竞品销售跟进表");

    private int moduleSeq;
    private String topicName;
    private String moduleName;

    private ProductModuleEnum(int moduleSeq, String topicName, String moduleName) {
        this.moduleSeq = moduleSeq;
        this.topicName = topicName;
        this.moduleName = moduleName;
    }

    public int getModuleSeq() {
		return moduleSeq;
	}

	public void setModuleSeq(int moduleSeq) {
		this.moduleSeq = moduleSeq;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	private static final Map<Integer, ProductModuleEnum> lookup = new HashMap<Integer, ProductModuleEnum>();

    static {
        for (ProductModuleEnum s : EnumSet.allOf(ProductModuleEnum.class)) {
            lookup.put(s.getModuleSeq(), s);
        }
    }

    //根据数字获取枚举对象
    public static ProductModuleEnum get(Integer code) {
        return lookup.get(code);
    }

}
