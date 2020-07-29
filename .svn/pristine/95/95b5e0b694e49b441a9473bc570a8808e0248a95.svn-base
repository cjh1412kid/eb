package com.nuite.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.nuite.modules.sys.entity.SysMenuEntity;

public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 根据用户的区域获取菜单
     */
    List<SysMenuEntity> getRoleMenuList(Integer roleSeq);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     * @param wipeAlone  去除alone类型的菜单
     */
    List<SysMenuEntity> queryListParentId(Integer parentId, List<Integer> menuIdList, boolean wipeAlone);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 删除
     */
    void delete(Integer menuId);

    /**
     * 获取授权时使用的列表
     *
     * @param userSeq 用户序号
     */
    List<SysMenuEntity> getAuthorList(Integer userSeq);
}
