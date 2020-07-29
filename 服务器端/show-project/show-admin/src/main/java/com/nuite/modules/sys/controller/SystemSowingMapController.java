package com.nuite.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.MapUtils;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.PageInfo;
import com.nuite.modules.sys.entity.SystemSowingMapEntity;
import com.nuite.modules.sys.service.CategoryService;
import com.nuite.modules.sys.service.SystemSowingMapService;

import io.swagger.annotations.ApiOperation;

/**
 * 订货app轮播管理
 */
@RestController
@RequestMapping("sowingMap")
public class SystemSowingMapController extends AbstractController {
    @Autowired
    private SystemSowingMapService systemSowingMapService;
    
    @Autowired
    private CategoryService categoryService;
 
    @RequestMapping("/list")
    public R list() {
        try {
            PageUtils companyBrandPage = systemSowingMapService.sowingMapList(1);
            return R.ok().put("page", companyBrandPage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error("服务器异常");
        }
    }

    @RequestMapping("/goodList")
    public R goodList(@RequestParam Map<String, Object> params) {
        try {
        	PageInfo pageInfo = new PageInfo(params);
            PageUtils companyBrandPage = systemSowingMapService.goodList(1, pageInfo);
            return R.ok().put("page", companyBrandPage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error("服务器异常");
        }
    }
    
    @RequestMapping("/periodList")
    public R periodList(@RequestParam Map<String, Object> params) {
        try {
            PageUtils pageUtil = systemSowingMapService.periodList(1, params);
            return R.ok().put("page", pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error("服务器异常");
        }
    }

    @RequestMapping("/edit")
    public R edit(@RequestParam("seq") Integer seq) {
        try {
            SystemSowingMapEntity systemSowingMapEntity = systemSowingMapService.edit(seq);
            return R.ok().put("sowingMap", systemSowingMapEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error("服务器异常");
        }
    }

    @RequestMapping("/update")
    public R update(SystemSowingMapEntity systemSowingMapEntity) {
        try {
            if (systemSowingMapEntity != null) {
                systemSowingMapService.update(1, systemSowingMapEntity);
                return R.ok();
            }
            return R.error("参数错误！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error("服务器异常");
        }
    }

    @RequestMapping("/save")
    public R save(SystemSowingMapEntity systemSowingMapEntity) {
        try {
            Integer i = systemSowingMapService.getCreatedNumber(1);
            if (i < 5) {
                systemSowingMapService.save(1, systemSowingMapEntity);
                return R.ok();
            }
            return R.error("轮播图最多只能创建5个");
        } catch (Exception e) {
            return R.error("添加失败！");
        }
    }

    @PostMapping("del")
    public R delete(Integer seq) {
        if (seq == null) {
            return R.error("参数错误！");
        }
        try {
            systemSowingMapService.deleteByMap(new MapUtils().put("Brand_Seq", 1).put("Seq", seq));
            return R.ok();
        } catch (Exception e) {
            return R.error("添加失败！");
        }
    }
    
    @Deprecated
    @GetMapping("/category")
    @ApiOperation("获取当前用户的所有鞋子分类")
    public R getUserCategory() {
        List result = categoryService.getUserCategory(getUser());
        return R.ok().put("categorys", result);
    }
}
