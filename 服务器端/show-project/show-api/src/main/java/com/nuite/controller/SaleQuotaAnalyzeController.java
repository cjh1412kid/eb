package com.nuite.controller;

import com.alibaba.fastjson.JSONArray;
import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisUtils;
import com.nuite.entity.SaleQuotaEntity;
import com.nuite.entity.UserEntity;
import com.nuite.model.QuotaSaleModel;
import com.nuite.service.SaleQuotaService;
import com.nuite.utils.RedisKeys;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/salequotaanalyze")
@Api(tags = "销售指标分析接口")
public class SaleQuotaAnalyzeController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SaleQuotaService saleQuotaService;

    @Login
    @RequestMapping("home")
    @ApiOperation("销售指标首页数据")
    public R home(@ApiIgnore @LoginUser UserEntity loginUser,
                  @ApiParam("选择地区、门店的seq") @RequestParam(value = "areaSeq", required = false) Integer areaSeq,
                  @ApiParam("是下一级地区，传入上级数据返回的roleType") @RequestParam(value = "roleType", required = false) Integer roleType) {
        Integer roleSeq = loginUser.getRoleSeq();
        Integer userAreaSeq = loginUser.getUserAreaSeq();
        Integer brandSeq = loginUser.getBrandSeq();
        if (areaSeq != null && roleType != null) {
            if (roleSeq.equals(roleType) && !userAreaSeq.equals(areaSeq)) {
                return R.error(-1, "数据权限错误！");
            } else if (roleSeq.equals(roleType)) {
                //和为null一样不做处理，查询当前用户对应的所有数据
            } else if (roleType < roleSeq) {
                return R.error(-1, "数据权限错误！");
            } else {
                userAreaSeq = areaSeq;
                roleSeq = roleType;
            }
        }


        if (roleSeq == 1 || roleSeq == 2 || roleSeq == 3 || roleSeq == 4) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            String saleQuotaString = redisUtils.get(RedisKeys.QUOTA_SALE_CURRENT_MONTH);
            List<QuotaSaleModel> saleList = JSONArray.parseArray(saleQuotaString, QuotaSaleModel.class);
            if (saleList == null) saleList = new ArrayList<>(0);

            Map<Object, Object> total;
            List<Map<Object, Object>> list;
            List<Map<Object, Object>> monthSales;
            if (roleSeq == 1) {
                //全国数据
                //查询所有大区的销售指标
                List<SaleQuotaEntity> quotaList = saleQuotaService.selectAreaQuotaList(year, month, brandSeq);
                List<SaleQuotaEntity> beforeQuotaList = saleQuotaService.selectAreaQuotaList(year - 1, month, brandSeq);
                total = new HashMap<>();
                getTotalQuota(total, quotaList, beforeQuotaList);
                getTotalSales(total, saleList, userAreaSeq, roleSeq);

                list = getList(quotaList, beforeQuotaList, saleList, userAreaSeq, roleSeq);

                monthSales = getMonthSales(saleList, userAreaSeq, roleSeq);
            } else if (roleSeq == 2) {
                //大区数据
                //查询所有分公司的销售指标
                List<SaleQuotaEntity> quotaList = saleQuotaService.selectOfficeQuotaList(year, month, brandSeq, userAreaSeq);
                List<SaleQuotaEntity> beforeQuotaList = saleQuotaService.selectOfficeQuotaList(year - 1, month, brandSeq, userAreaSeq);
                total = new HashMap<>();
                getTotalQuota(total, quotaList, beforeQuotaList);
                getTotalSales(total, saleList, userAreaSeq, roleSeq);

                list = getList(quotaList, beforeQuotaList, saleList, userAreaSeq, roleSeq);

                monthSales = getMonthSales(saleList, userAreaSeq, roleSeq);
            } else if (roleSeq == 3) {
                //分公司数据
                //查询所有门店的销售指标
                List<SaleQuotaEntity> quotaList = saleQuotaService.selectShopQuotaList(year, month, brandSeq, userAreaSeq);
                List<SaleQuotaEntity> beforeQuotaList = saleQuotaService.selectShopQuotaList(year - 1, month, brandSeq, userAreaSeq);
                total = new HashMap<>();
                getTotalQuota(total, quotaList, beforeQuotaList);
                getTotalSales(total, saleList, userAreaSeq, roleSeq);

                list = getList(quotaList, beforeQuotaList, saleList, userAreaSeq, roleSeq);

                monthSales = getMonthSales(saleList, userAreaSeq, roleSeq);
            } else {
                //门店数据
                //查询当前门店的销售指标
                SaleQuotaEntity shopQuota = saleQuotaService.selectShopQuota(year, month, brandSeq, userAreaSeq);
                SaleQuotaEntity beforeShopQuota = saleQuotaService.selectShopQuota(year - 1, month, brandSeq, userAreaSeq);
                total = new HashMap<>();
                total.put("lastYearQuota", beforeShopQuota == null ? BigDecimal.ZERO : beforeShopQuota.getQuota());
                total.put("thisYearQuota", shopQuota == null ? BigDecimal.ZERO : shopQuota.getQuota());
                getTotalSales(total, saleList, userAreaSeq, roleSeq);
                list = new ArrayList<Map<Object, Object>>(0);

                monthSales = getMonthSales(saleList, userAreaSeq, roleSeq);
            }
            R r = R.ok();
            r.put("total", total);
            r.put("list", list);
            r.put("month", monthSales);
            return r;
        } else {
            //其他数据
            return R.ok();
        }
    }

    private void getTotalQuota(Map<Object, Object> total, List<SaleQuotaEntity> quotaList, List<SaleQuotaEntity> beforeQuotaList) {
        BigDecimal currentTotalQuota = BigDecimal.ZERO;
        for (SaleQuotaEntity saleQuotaEntity : quotaList) {
            currentTotalQuota = currentTotalQuota.add(saleQuotaEntity.getQuota());
        }
        BigDecimal beforeTotalQuota = BigDecimal.ZERO;
        for (SaleQuotaEntity saleQuotaEntity : beforeQuotaList) {
            beforeTotalQuota = beforeTotalQuota.add(saleQuotaEntity.getQuota());
        }
        total.put("lastYearQuota", beforeTotalQuota);
        total.put("thisYearQuota", currentTotalQuota);
    }

    private void getTotalSales(Map<Object, Object> total, List<QuotaSaleModel> saleList, Integer userAreaSeq, int type) {
        BigDecimal currentSalePrices = BigDecimal.ZERO;
        BigDecimal beforeSalePrices = BigDecimal.ZERO;
        Map<Integer, BigDecimal> weekSales = new HashMap<>();

        // 循环获取当月销售额总和、去年当月销售额总和、今年当月各星期销售额总和
        for (QuotaSaleModel quotaSaleModel : saleList) {
            if (type == 1) {
            } else if (type == 2 && quotaSaleModel.getAreaSeq().equals(userAreaSeq)) {
            } else if (type == 3 && quotaSaleModel.getOfficeSeq().equals(userAreaSeq)) {
            } else if (type == 4 && quotaSaleModel.getShopSeq().equals(userAreaSeq)) {
            } else {
                continue;
            }
            currentSalePrices = currentSalePrices.add(quotaSaleModel.getCurrentPrice());
            beforeSalePrices = beforeSalePrices.add(quotaSaleModel.getBeforePrice());
            int weekOfMonth = quotaSaleModel.getWeekOfMonth();
            BigDecimal weekPrices;
            if (weekSales.containsKey(weekOfMonth)) {
                weekPrices = weekSales.get(weekOfMonth);
            } else {
                weekPrices = BigDecimal.ZERO;
                weekSales.put(weekOfMonth, weekPrices);
            }
            weekPrices = weekPrices.add(quotaSaleModel.getCurrentPrice());
            weekSales.put(weekOfMonth, weekPrices);
        }

        Calendar calendar = Calendar.getInstance();
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        List<Map<String, Object>> weekList = new ArrayList<>();
        for (int weekIndex = 1; weekIndex <= weekOfMonth; weekIndex++) {
            BigDecimal bigDecimal = weekSales.getOrDefault(weekIndex, BigDecimal.ZERO);
            Map<String, Object> weekData = new HashMap<>();
            weekData.put("index", weekIndex);
            weekData.put("value", bigDecimal);
            weekList.add(weekData);
        }

        total.put("lastYearSales", beforeSalePrices);
        total.put("thisYearSales", currentSalePrices);
        total.put("thisYearWeeks", weekList);
    }

    //计算各下级地区的列表
    private List<Map<Object, Object>> getList(List<SaleQuotaEntity> quotaList, List<SaleQuotaEntity> beforeQuotaList,
                                              List<QuotaSaleModel> saleList, Integer userAreaSeq, int type) {
        Map<Integer, Map<Object, Object>> map = new HashMap<>();
        int keyRoleType = type + 1;
        for (QuotaSaleModel quotaSaleModel : saleList) {
            Integer keySeq;
            String keyName;
            if (type == 1) {
                keySeq = quotaSaleModel.getAreaSeq();
                keyName = quotaSaleModel.getAreaName();
            } else if (type == 2 && quotaSaleModel.getAreaSeq().equals(userAreaSeq)) {
                keySeq = quotaSaleModel.getOfficeSeq();
                keyName = quotaSaleModel.getOfficeName();
            } else if (type == 3 && quotaSaleModel.getOfficeSeq().equals(userAreaSeq)) {
                keySeq = quotaSaleModel.getShopSeq();
                keyName = quotaSaleModel.getShopName();
            } else {
                continue;
            }
            Map<Object, Object> data;
            if (map.containsKey(keySeq)) {
                data = map.get(keySeq);
            } else {
                data = new HashMap<>();
                map.put(keySeq, data);
            }
            data.put("areaSeq", keySeq);
            data.put("areaName", keyName);
            data.put("roleType", keyRoleType);
            BigDecimal lastSales = (BigDecimal) data.get("lastYearSales");
            if (lastSales == null) lastSales = BigDecimal.ZERO;
            lastSales = lastSales.add(quotaSaleModel.getBeforePrice());
            data.put("lastYearSales", lastSales);

            BigDecimal thisSales = (BigDecimal) data.get("thisYearSales");
            if (thisSales == null) thisSales = BigDecimal.ZERO;
            thisSales = thisSales.add(quotaSaleModel.getCurrentPrice());
            data.put("thisYearSales", thisSales);

            //设置初始数据，以防下面循环无数据
            data.put("thisYearQuota", BigDecimal.ZERO);
            data.put("lastYearQuota", BigDecimal.ZERO);
        }

        for (SaleQuotaEntity saleQuotaEntity : quotaList) {
            Map<Object, Object> data;
            if (map.containsKey(saleQuotaEntity.getAreaSeq())) {
                data = map.get(saleQuotaEntity.getAreaSeq());
            } else {
                data = new HashMap<>();
                data.put("areaSeq", saleQuotaEntity.getAreaSeq());
                data.put("areaName", saleQuotaEntity.getAreaName());
                data.put("roleType", keyRoleType);
                data.put("lastYearSales", BigDecimal.ZERO);
                data.put("thisYearSales", BigDecimal.ZERO);
                map.put(saleQuotaEntity.getAreaSeq(), data);
            }

            data.put("thisYearQuota", saleQuotaEntity.getQuota());
            data.put("lastYearQuota", BigDecimal.ZERO);
        }

        for (SaleQuotaEntity saleQuotaEntity : beforeQuotaList) {
            Map<Object, Object> data;
            if (map.containsKey(saleQuotaEntity.getAreaSeq())) {
                data = map.get(saleQuotaEntity.getAreaSeq());
            } else {
                data = new HashMap<>();
                data.put("areaSeq", saleQuotaEntity.getAreaSeq());
                data.put("areaName", saleQuotaEntity.getAreaName());
                data.put("roleType", keyRoleType);
                data.put("lastYearSales", BigDecimal.ZERO);
                data.put("thisYearSales", BigDecimal.ZERO);
                data.put("thisYearQuota", BigDecimal.ZERO);
                map.put(saleQuotaEntity.getAreaSeq(), data);
            }

            data.put("lastYearQuota", saleQuotaEntity.getQuota());
        }
        List<Map<Object, Object>> valueList = new ArrayList<>(map.values());
        valueList.sort((o1, o2) -> {
            BigDecimal o1Sales = (BigDecimal) o1.get("thisYearSales");
            BigDecimal o2Sales = (BigDecimal) o2.get("thisYearSales");
            return o2Sales.compareTo(o1Sales);
        });

        return valueList;
    }

    private List<Map<Object, Object>> getMonthSales(List<QuotaSaleModel> saleList, Integer userAreaSeq, int type) {
        Calendar ca = Calendar.getInstance();
        int maxDay = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<Map<Object, Object>> list = new ArrayList<>();
        for (int i = 1; i <= maxDay; i++) {
            BigDecimal currentSalePrices = BigDecimal.ZERO;
            BigDecimal beforeSalePrices = BigDecimal.ZERO;
            for (QuotaSaleModel quotaSaleModel : saleList) {
                if (type == 1) {
                } else if (type == 2 && quotaSaleModel.getAreaSeq().equals(userAreaSeq)) {
                } else if (type == 3 && quotaSaleModel.getOfficeSeq().equals(userAreaSeq)) {
                } else if (type == 4 && quotaSaleModel.getShopSeq().equals(userAreaSeq)) {
                } else {
                    continue;
                }
                if (quotaSaleModel.getDayOfMonth() == i) {
                    currentSalePrices = currentSalePrices.add(quotaSaleModel.getCurrentPrice());
                    beforeSalePrices = beforeSalePrices.add(quotaSaleModel.getBeforePrice());
                }
            }
            Map<Object, Object> map = new HashMap<>();
            map.put("thisYear", currentSalePrices);
            map.put("lastYear", beforeSalePrices);
            list.add(map);
        }
        return list;
    }
}
