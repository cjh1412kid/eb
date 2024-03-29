package com.nuite.task;

import com.nuite.common.utils.DateUtils;
import com.nuite.common.utils.RedisUtils;
import com.nuite.model.QuotaSaleModel;
import com.nuite.service.SaleShoesDetailService;
import com.nuite.utils.RedisKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * 销售指标页面相关定时任务
 */
@Component("quotaTask")
public class QuotaTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SaleShoesDetailService saleShoesDetailService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 定时更新当月每日的销售额，并统计到当月每周的销售额
     */
    //@Scheduled(cron = "0 0 0/1 * * ?")
    public void salesDetail() {
        Date currentFirstDate = DateUtils.getMothFirstDate();
        Date currentLastDate = DateUtils.addDateDays(DateUtils.getMothLastDate(), 1);
        List<Map<String, Object>> currentSaleDetail = saleShoesDetailService.getDetailSales(currentFirstDate, currentLastDate);

        Date beforeFirstDate = DateUtils.addDateYears(currentFirstDate, -1);
        Date beforeLastDate = DateUtils.addDateYears(currentLastDate, -1);
        List<Map<String, Object>> beforeSaleDetail = saleShoesDetailService.getDetailSales(beforeFirstDate, beforeLastDate);

        List<QuotaSaleModel> resultSaleDetail = new ArrayList<>();

        for (Map<String, Object> currentDetail : currentSaleDetail) {
            Integer areaSeq = (Integer) currentDetail.get("Area");
            String areaName = (String) currentDetail.get("AreaName");
            Integer officeSeq = (Integer) currentDetail.get("Office");
            String officeName = (String) currentDetail.get("OfficeName");
            Integer shopSeq = (Integer) currentDetail.get("Shop");
            String shopName = (String) currentDetail.get("ShopName");
            Date saleDate = (Date) currentDetail.get("SaleDate");
            BigDecimal salePrices = (BigDecimal) currentDetail.get("SalePrices");
            QuotaSaleModel quotaSaleModel = new QuotaSaleModel(areaSeq, officeSeq, shopSeq, areaName, officeName, shopName);
            Calendar ca = Calendar.getInstance();
            ca.setTime(saleDate);
            int weekOfMonth = ca.get(Calendar.WEEK_OF_MONTH);
            int dayOfMonth = ca.get(Calendar.DAY_OF_MONTH);
            quotaSaleModel.setWeekOfMonth(weekOfMonth);
            quotaSaleModel.setDayOfMonth(dayOfMonth);
            quotaSaleModel.setCurrentPrice(salePrices);
            quotaSaleModel.setBeforePrice(BigDecimal.ZERO);
            resultSaleDetail.add(quotaSaleModel);
        }

        // 将去年同期的数据增加到数据集中
        for (Map<String, Object> beforeDetail : beforeSaleDetail) {
            Integer areaSeq = (Integer) beforeDetail.get("Area");
            String areaName = (String) beforeDetail.get("AreaName");
            Integer officeSeq = (Integer) beforeDetail.get("Office");
            String officeName = (String) beforeDetail.get("OfficeName");
            Integer shopSeq = (Integer) beforeDetail.get("Shop");
            String shopName = (String) beforeDetail.get("ShopName");
            Date saleDate = (Date) beforeDetail.get("SaleDate");
            BigDecimal salePrices = (BigDecimal) beforeDetail.get("SalePrices");
            Calendar ca = Calendar.getInstance();
            ca.setTime(saleDate);
            int dayOfMonth = ca.get(Calendar.DAY_OF_MONTH);

            boolean hasSame = false;
            for (QuotaSaleModel quotaSaleModel : resultSaleDetail) {
                hasSame = quotaSaleModel.sameShopAndDay(areaSeq, officeSeq, shopSeq, dayOfMonth);
                if (hasSame) {
                    quotaSaleModel.setBeforePrice(salePrices);
                    break;
                }
            }

            // 没有去年当天的销售数据，新增一条
            if (!hasSame) {
                QuotaSaleModel quotaSaleModel = new QuotaSaleModel(areaSeq, officeSeq, shopSeq, areaName, officeName, shopName);
                quotaSaleModel.setDayOfMonth(dayOfMonth);
                quotaSaleModel.setCurrentPrice(BigDecimal.ZERO);
                quotaSaleModel.setBeforePrice(salePrices);

                //对当前时间+1年计算是这个月的第几周
                ca.add(Calendar.YEAR, 1);
                int weekOfMonth = ca.get(Calendar.WEEK_OF_MONTH);
                quotaSaleModel.setWeekOfMonth(weekOfMonth);
                resultSaleDetail.add(quotaSaleModel);
            }
        }

        try {
            redisUtils.setNotExpire(RedisKeys.QUOTA_SALE_CURRENT_MONTH, resultSaleDetail);
        } catch (Exception e) {
            logger.error("销售指标数据保存redis失败");
        }
    }
}
