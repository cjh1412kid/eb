package com.nuite.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.Constant;
import com.nuite.modules.sys.dao.SysMenuDao;
import com.nuite.modules.sys.entity.SysMenuEntity;
import com.nuite.modules.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	
    @Autowired
    private SysMenuDao sysMenuDao;
    

    @Override
    public List<SysMenuEntity> getRoleMenuList(Integer roleSeq) {
    	List<Integer> menuIdList = queryRoleAllMenuId(roleSeq);
        return getAllMenuList(menuIdList, false, false);
    }


    //角色菜单列表
	private List<Integer> queryRoleAllMenuId(Integer roleSeq) {
		Wrapper<SysMenuEntity> wrapper = new EntityWrapper<SysMenuEntity>();
		wrapper.setSqlSelect("Seq AS seq");
		wrapper.where("CHARINDEX({0}, Perms) > 0", roleSeq.toString());
    	List<Object> list = sysMenuDao.selectObjs(wrapper);
    	
    	List<Integer> menuIdList = new ArrayList<Integer>();
    	for(Object object : list) {
    		menuIdList.add(((Long) object).intValue());
    	}
    	
		return menuIdList;
	}

    
    @Override
    public List<SysMenuEntity> queryListParentId(Integer parentId, List<Integer> menuIdList, boolean wipeAlone) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (wipeAlone) {
            menuList.removeIf(SysMenuEntity::getAlone);
        }

        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getSeq())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Integer> menuIdList, boolean wipeAlone, boolean containButton) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0, menuIdList, wipeAlone);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList, wipeAlone, containButton);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Integer> menuIdList, boolean wipeAlone, boolean containButton) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            //目录
            boolean deepQuery = !containButton && entity.getType() == Constant.MenuType.CATALOG.getValue();
            deepQuery = deepQuery || (containButton && entity.getType() != Constant.MenuType.BUTTON.getValue());
            if (deepQuery) {
                List<SysMenuEntity> parentList = queryListParentId(entity.getSeq(), menuIdList, wipeAlone);
                List<SysMenuEntity> treeList = getMenuTreeList(parentList, menuIdList, wipeAlone, containButton);
                entity.setList(treeList);
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public void delete(Integer menuId) {
        //删除菜单
        this.deleteById(menuId);
        //todo 删除菜单与用户关联
        //systemRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    @Override
    public List<SysMenuEntity> getAuthorList(Integer userSeq) {
        List<SysMenuEntity> menuList;
        if (Constant.SUPER_ADMIN == userSeq) {
            menuList = this.selectList(null);
            for (SysMenuEntity sysMenuEntity : menuList) {
                SysMenuEntity parentMenuEntity = this.selectById(sysMenuEntity.getParentSeq());
                if (parentMenuEntity != null) {
                    sysMenuEntity.setParentName(parentMenuEntity.getName());
                }
            }
        } else {
            List<Integer> menuIdList = queryRoleAllMenuId(userSeq);
            menuList = getAllMenuList(menuIdList, true, true);
            changeMenuListTop(menuList);
        }
        return menuList;
    }

    private void changeMenuListTop(List<SysMenuEntity> menuList) {
        List<SysMenuEntity> childList = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity sysMenuEntity : menuList) {
            List<SysMenuEntity> entities = sysMenuEntity.getList();
            if (entities != null && entities.size() > 0) {
                changeMenuListTop(entities);
                childList.addAll(entities);
            }
            sysMenuEntity.setList(null);
        }
        if (childList.size() > 0) {
            menuList.addAll(childList);
        }
    }
}
