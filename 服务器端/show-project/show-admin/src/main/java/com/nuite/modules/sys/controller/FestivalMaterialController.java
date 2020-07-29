package com.nuite.modules.sys.controller;

import com.nuite.common.utils.MyPagination;
import com.nuite.common.utils.PathUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.sys.entity.FestivalMaterialEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.FestivalMaterialService;
import com.nuite.modules.sys.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangchuang
 * @since 2019-01-11
 */
@Slf4j
@RestController
@RequestMapping("/sys/festival/material")
public class FestivalMaterialController {

    @Autowired
    FestivalMaterialService materialService;

    @RequestMapping("fromUser")
    public R selectUserMaterial() {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        List<FestivalMaterialEntity> materialEntities = materialService.selectUserMaterial(userEntity.getSeq());
        return R.ok().result(materialEntities);
    }

    @RequestMapping("fromUser/{pageSize}/{pageNumber}")
    public R selectUserMaterial2(@PathVariable("pageSize") Integer pageSize,
                                 @PathVariable("pageNumber") Integer pageNumber) {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        MyPagination<FestivalMaterialEntity> pageInfo = materialService.selectUserMaterial(userEntity.getSeq(), pageNumber, pageSize);
        return R.ok().result(pageInfo);
    }

    @RequestMapping("fromCompany")
    public R selectCompanyMaterial() {
        List<FestivalMaterialEntity> materialEntities = materialService.selectCompanyMaterial();
        return R.ok().result(materialEntities);
    }

    @RequestMapping("fromCompany/{pageSize}/{pageNumber}")
    public R selectCompanyMaterial2(@PathVariable("pageSize") Integer pageSize,
                                    @PathVariable("pageNumber") Integer pageNumber) {
        MyPagination<FestivalMaterialEntity> pageInfo = materialService.selectCompanyMaterial(pageNumber, pageSize);
        return R.ok().result(pageInfo);
    }

    @RequestMapping("del")
    public R delMaterial(Integer seq) {
        //获取素材名称
        FestivalMaterialEntity materialEntity = materialService.selectById(seq);

        if (materialEntity != null) {
            //删除素材文件
            String imagePath = "picture" + File.separator + "shop-model";
            String imageDir = PathUtils.getWebAppsPath() + File.separator + imagePath;
            File material = new File(imageDir + File.separator + materialEntity.getPath());
            if (material.exists()) {
                material.delete();
            }

            //删除素材文件数据库记录
            materialService.delMaterial(seq);
        }

        return R.ok();
    }

    @RequestMapping("upload")
    public R upload(MultipartFile[] files, Integer belongTo, HttpServletRequest request) throws IOException {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        //log.info("files.length: ", files.length);
        if (files != null && files.length > 0) {

            FestivalMaterialEntity materialEntity;
            for (MultipartFile file : files) {
                String webRootPath = PathUtils.getWebAppsPath();
                String originalFilename = file.getOriginalFilename();
                String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = System.currentTimeMillis() + "_" + file.getSize() + ext;
                String imagePath = "picture" + File.separator + "shop-model";
                String absolutePath = webRootPath + File.separator + imagePath;

                File dir = new File(absolutePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dest = new File(dir, newFileName);
                file.transferTo(dest);

                //保存记录
                materialEntity = new FestivalMaterialEntity();
                materialEntity.setPath(newFileName);
                materialEntity.setBelongTo(belongTo);
                materialEntity.setCreatorSeq(userEntity.getSeq());
                materialService.addNewMaterial(materialEntity);
            }

        } else {
            return R.error("上传文件不能为空");
        }

        return R.ok();
    }
}

