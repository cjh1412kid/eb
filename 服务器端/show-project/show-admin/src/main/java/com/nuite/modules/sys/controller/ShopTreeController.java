package com.nuite.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nuite.common.utils.R;
import com.nuite.modules.model.ShopTreeModel;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.entity.ShopEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.AreaService;
import com.nuite.modules.sys.service.ShopService;
import com.nuite.modules.sys.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 门店展示动画后台编辑页面门店树结构
 *
 * @Author: yangchuang
 * @Date: 2019/1/12 11:37
 * @Version: 1.0
 */

@RestController
@RequestMapping("sys/shopTree")
public class ShopTreeController {

    @Autowired
    ShopService shopService;

    @Autowired
    AreaService areaService;

    @RequestMapping("getShops")
    public R selectUserShop() {
        UserEntity userEntity = ShiroUtils.getUserEntity();

        List<ShopTreeModel> result = new ArrayList<>();
        if (userEntity.getRoleSeq() == 4) {
            //门店角色
            ShopEntity shopEntity = shopService.selectById(userEntity.getUserAreaSeq());
            ShopTreeModel shopTreeModel = new ShopTreeModel()
                    .setId("s" + shopEntity.getSeq())
                    .setSeq(shopEntity.getSeq())
                    .setPId("a" + shopEntity.getAreaSeq())
                    .setShopFlag(1)
                    .setNodeName(shopEntity.getName());
            result.add(shopTreeModel);
        } else if (userEntity.getRoleSeq() == 3) {
            //分公司，办事处
            AreaEntity areaEntity = areaService.selectById(userEntity.getUserAreaSeq());
            ShopTreeModel shopTreeModel;
            shopTreeModel = new ShopTreeModel()
                    .setId("a" + areaEntity.getSeq())
                    .setSeq(areaEntity.getSeq())
                    .setPId("a" + areaEntity.getParentSeq())
                    .setNodeName(areaEntity.getAreaName())
                    .setShopFlag(0);
            result.add(shopTreeModel);

            //分公司》门店
            List<ShopEntity> shops = shopService.selectList(new EntityWrapper<ShopEntity>()
                    .eq("AreaSeq", areaEntity.getSeq()));

            for (ShopEntity shopEntity : shops) {
                shopTreeModel = new ShopTreeModel()
                        .setId("s" + shopEntity.getSeq())
                        .setSeq(shopEntity.getSeq())
                        .setPId("a" + shopEntity.getAreaSeq())
                        .setNodeName(shopEntity.getName())
                        .setShopFlag(1);
                result.add(shopTreeModel);
            }
        } else if (userEntity.getRoleSeq() == 2) {
            //大区
            AreaEntity areaEntity = areaService.selectById(userEntity.getUserAreaSeq());
            ShopTreeModel shopTreeModel;
            shopTreeModel = new ShopTreeModel()
                    .setId("a" + areaEntity.getSeq())
                    .setSeq(areaEntity.getSeq())
                    .setPId("a" + areaEntity.getParentSeq())
                    .setNodeName(areaEntity.getAreaName())
                    .setShopFlag(0);
            result.add(shopTreeModel);

            //分公司
            List<AreaEntity> areas = areaService.selectList(new EntityWrapper<AreaEntity>()
                    .eq("ParentSeq", areaEntity.getSeq()));
            List<Integer> seqs = new ArrayList<>();
            for (AreaEntity area : areas) {
                seqs.add(area.getSeq());
                shopTreeModel = new ShopTreeModel()
                        .setId("a" + area.getSeq())
                        .setSeq(area.getSeq())
                        .setPId("a" + area.getParentSeq())
                        .setNodeName(area.getAreaName())
                        .setShopFlag(0);
                result.add(shopTreeModel);
            }

            //门店
            if (seqs != null && seqs.size() > 0) {
                List<ShopEntity> shops = shopService.selectList(new EntityWrapper<ShopEntity>().in("AreaSeq", seqs));
                for (ShopEntity shopEntity : shops) {
                    shopTreeModel = new ShopTreeModel()
                            .setId("s" + shopEntity.getSeq())
                            .setSeq(shopEntity.getSeq())
                            .setPId("a" + shopEntity.getAreaSeq())
                            .setNodeName(shopEntity.getName())
                            .setShopFlag(1);
                    result.add(shopTreeModel);
                }
            }
        } else if (userEntity.getRoleSeq() <= 1) {
            //全国
            List<AreaEntity> areas = areaService.selectList(null);
            ShopTreeModel shopTreeModel;
            for (AreaEntity areaEntity : areas) {
                if (areaEntity.getAreaName() != null &&
                        (!areaEntity.getAreaName().toLowerCase().contains("test"))) {
                    shopTreeModel = new ShopTreeModel()
                            .setId("a" + areaEntity.getSeq())
                            .setSeq(areaEntity.getSeq())
                            .setPId("a" + areaEntity.getParentSeq())
                            .setNodeName(areaEntity.getAreaName())
                            .setShopFlag(0);
                    result.add(shopTreeModel);
                }
            }

            //全国门店
            List<ShopEntity> shops = shopService.selectList(new EntityWrapper<ShopEntity>()
                    .setSqlSelect("Seq", "Name", "AreaSeq"));
            for (ShopEntity shopEntity : shops) {
                if (shopEntity.getName() != null &&
                        (!shopEntity.getName().toLowerCase().contains("test"))) {
                    shopTreeModel = new ShopTreeModel()
                            .setId("s" + shopEntity.getSeq())
                            .setSeq(shopEntity.getSeq())
                            .setPId("a" + shopEntity.getAreaSeq())
                            .setNodeName(shopEntity.getName())
                            .setShopFlag(1);
                    result.add(shopTreeModel);
                }
            }
        }

        return R.ok().result(result);
    }

    @RequestMapping("getUserRoleSeq")
    public R getUserRoleSeq() {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        return R.ok().result(userEntity.getRoleSeq());
    }
}
