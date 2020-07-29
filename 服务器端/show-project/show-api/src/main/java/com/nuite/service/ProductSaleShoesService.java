package com.nuite.service;

import java.math.BigDecimal;


public interface ProductSaleShoesService {

	BigDecimal getAreaSale(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff);

	BigDecimal getAreaSaleLastYear(Integer areaType, Integer areaSeq, Integer periodSeq, Integer datediff);

}

