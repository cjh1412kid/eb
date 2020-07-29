package com.nuite.modules.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.AnnouncementEntity;
import com.nuite.modules.sys.service.AnnouncementService;

/**
 * 公告管理
 */
@RestController
@RequestMapping("order/notice")
public class AnnouncementController extends AbstractController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
        try {
            PageUtils shoesPage = announcementService.list(getUser(), params);
            return R.ok().put("page", shoesPage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return R.error("公告列表显示失败！");
        }
    }

    @GetMapping("del")
    public R delete(@RequestParam Integer seq) {
        try {
            announcementService.deleteById(seq);
            return R.ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return R.error("删除失败！");
        }
    }

    @GetMapping("lineEdit")
    public R lineEdit(@RequestParam Integer seq) {
        try {
            AnnouncementEntity announcement = announcementService.selectById(seq);
            return R.ok().put("announcement", announcement);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return R.error("信息获取失败！");
        }
    }

    @PostMapping("saveOrUpdate")
    public R saveOrUpdate(AnnouncementEntity announcementEntity) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date time= sdf.parse(sdf.format(new Date()));
            announcementEntity.setInputTime(time);
            announcementEntity.setCompanySeq(1);
            if (announcementService.insertOrUpdate(announcementEntity)) {
                return R.ok();
            }
            return R.error("操作失败！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return R.error("操作失败!");
        }
    }

}
