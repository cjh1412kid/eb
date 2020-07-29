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
import com.nuite.modules.sys.entity.GoodsInitialDataEntity;
import com.nuite.modules.sys.service.GoodsInitialDataService;

import io.swagger.annotations.Api;


/**
 * 首单、补单、预留数据管理
 * @author yy
 * @date 2019-10-28 16:42:42
 */
@RestController
@RequestMapping("sys/goodsInitialData")
@Api(tags="GoodsInitialData接口")
public class GoodsInitialDataController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private GoodsInitialDataService goodsInitialDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsInitialDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seq}")
    public R info(@PathVariable("seq") Integer seq){
        GoodsInitialDataEntity goodsInitialData = goodsInitialDataService.selectById(seq);

        return R.ok().put("goodsInitialData", goodsInitialData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GoodsInitialDataEntity goodsInitialData){
        goodsInitialDataService.insert(goodsInitialData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GoodsInitialDataEntity goodsInitialData){
        ValidatorUtils.validateEntity(goodsInitialData);
        goodsInitialDataService.updateAllColumnById(goodsInitialData);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] seqs){
        goodsInitialDataService.deleteBatchIds(Arrays.asList(seqs));

        return R.ok();
    }

}
