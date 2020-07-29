package com.nuite.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.R;
import com.nuite.common.validator.ValidatorUtils;
import com.nuite.modules.sys.entity.PurchasePlanEntity;
import com.nuite.modules.sys.service.PurchasePlanService;

import io.swagger.annotations.Api;


/**
 * 采购计划管理
 * @author yy
 * @date 2019-10-28 16:42:42
 */
@RestController
@RequestMapping("sys/purchasePlan")
@Api(tags="PurchasePlan接口")
public class PurchasePlanController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private PurchasePlanService purchasePlanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchasePlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seq}")
    public R info(@PathVariable("seq") Integer seq){
        PurchasePlanEntity purchasePlan = purchasePlanService.selectById(seq);

        return R.ok().put("purchasePlan", purchasePlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchasePlanEntity purchasePlan){
        purchasePlanService.insert(purchasePlan);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchasePlanEntity purchasePlan){
        ValidatorUtils.validateEntity(purchasePlan);
        purchasePlanService.updateAllColumnById(purchasePlan);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] seqs){
        purchasePlanService.deleteBatchIds(Arrays.asList(seqs));

        return R.ok();
    }

}
