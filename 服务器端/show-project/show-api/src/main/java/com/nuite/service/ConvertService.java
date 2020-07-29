package com.nuite.service;

import java.util.Date;
import java.util.List;

public interface ConvertService {

	/**
	 * 获取 多个门店、 多个波次或某货号、 某一天内 有效试穿次数
	 * @param shopSeqList   门店序号
	 * @param periodSeqs  波次序号
	 * @param shoeIds  货号 （如果货号不为空，则波次序号无效，为货号对应波次）
	 * @param date 某一天
	 * @return
	 */
	int getShopsTryValidNumOneDay(List<Integer> shopSeqList, List<Integer> periodSeqs, List<String> shoeIds, Date date);
	
	
	
	/**
	 * 获取 某一区域、 多个波次或某货号、 某一天内 销量
	 * @param type  区域类型
	 * @param areaSeqList  区域seq
	 * @param periodSeqs  波次序号
	 * @param shoeIds  号 （如果货号不为空，则波次序号无效，为货号对应波次）
	 * @param date  某一天
	 * @return
	 */
	int getAreasSaleNumOneDay(Integer type, List<Integer> areaSeqList, List<Integer> periodSeqs, List<String> shoeIds, Date date);
	
	
}

