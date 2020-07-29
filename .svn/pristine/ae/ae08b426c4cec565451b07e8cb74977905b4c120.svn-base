package com.nuite.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface LargeScreenApiService {

	int getCountryValidTryTimes(Integer brandSeq, String startDate);

	int getCountryInvalidTryTimes(Integer brandSeq, String startDate);

	BigDecimal getCountryTotalSales(Date date);

	List<Object> getPeriodsByYear(Integer brandSeq, Integer year);

	Integer getSaleNumByYearAndSx3code(Date startDate, Date endDate, List<Object> periods, String sx3Code);

	Integer getTotalNumByYearAndSx3code(Date startDate, Date endDate, List<Object> periods, String sx3Code);

	BigDecimal getCountryOneDaySales(Date date);

	BigDecimal getCountryYearToDaySales(Date date);

	BigDecimal getCountryYearTotalSales(Date date);

}

