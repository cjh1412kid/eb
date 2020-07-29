package com.nuite.modules.shop.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuite.common.utils.R;
import com.nuite.common.utils.RedisKeys;
import com.nuite.common.utils.RedisUtils;
import com.nuite.modules.model.ShopAnimationTemplateModel;
import com.nuite.modules.sys.service.BrandService;
import com.nuite.modules.sys.service.ShopAnimationControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shop-data")
public class ShopDataController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ShopAnimationControlService shopAnimationControlService;

    @Autowired
    private BrandService brandService;

    /**
     * 门店展示使用的前10大计算
     * 包括销量前10大和试穿前10大
     *
     * @param shopSeq 查询门店的shopId
     * @return 返回前十大货号数组
     */
    @RequestMapping("top")
    public String top(Integer shopSeq) {
        JSONArray result = new JSONArray();
        if (shopSeq == null) {
            JSONObject object = new JSONObject();
            object.put("error", "门店id为空");
            result.add(object);
        } else {
            try {
                Map<Integer, List<String>> saleDataJson = (Map<Integer, List<String>>) JSON.parseObject(redisUtils.get(RedisKeys.SALE_TOP_KEY), Map.class);
                Map<Integer, List<String>> tryDataJson = (Map<Integer, List<String>>) JSON.parseObject(redisUtils.get(RedisKeys.TRY_TOP_KEY), Map.class);
                if (saleDataJson != null && !saleDataJson.isEmpty()) {
                    List<String> shopSaleArray = saleDataJson.getOrDefault(shopSeq, new ArrayList<>(0));
                    result.addAll(shopSaleArray);
                }

                if (tryDataJson != null && !tryDataJson.isEmpty()) {
                    List<String> shopTryArray = tryDataJson.getOrDefault(shopSeq, new ArrayList<>(0));
                    result.addAll(shopTryArray);
                }
            } catch (Exception e) {
                logger.error("get redis error", e);
            }
        }

        String arrayList = result.toJSONString();
        byte[] resultBytes = ZipUtil.gzip(arrayList, CharsetUtil.UTF_8);

        return HexUtil.encodeHexStr(resultBytes);
    }

    /**
     * 后台设置信息，包括animate动画、qrcode二维码
     *
     * @param shopSeq 门店序号
     * @return 动画名称、二维码路径
     */
    @RequestMapping("setting")
    public R setting(Integer shopSeq) {
        R result = new R();
        String animate = shopAnimationControlService.queryShopAnimate(shopSeq);
        if (animate == null) {
            result.put("animate", "");
        } else {
            result.put("animate", animate);
        }

        queryQrCode(result, shopSeq);
        return result;
    }

    /**
     * 后台设置信息，包括animate动画、qrcode二维码
     *
     * @param shopSeq 门店序号
     * @return 动画名称、二维码路径
     */
    @RequestMapping("setup/v2")
    public R setting2(Integer shopSeq) {
        R result = new R();
        ShopAnimationTemplateModel template = shopAnimationControlService.queryShopAnimateTemplate(shopSeq);
        if (template == null) {
            result.put("animate", "");
        } else {
            result.put("animate", JSON.toJSONString(template));
        }

        queryQrCode(result, shopSeq);
        return result;
    }

    private void queryQrCode(R result, Integer shopSeq) {
        String qrcodePath = brandService.queryBrandQRCode(shopSeq);
        if (qrcodePath == null) {
            result.put("qrcode", "");
        } else {
            result.put("qrcode", qrcodePath);
        }

    }
}
