package com.nuite.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuite.modules.sys.dao.AreaDao;
import com.nuite.modules.sys.dao.ShopDao;
import com.nuite.modules.sys.dao.UserDao;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.entity.ShopEntity;
import com.nuite.modules.sys.entity.UserEntity;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private ShopDao shopDao;
	
	
	protected UserEntity getUser() {
		UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
		Integer roleSeq = user.getRoleSeq();
		Integer userAreaSeq = user.getUserAreaSeq();
		if(roleSeq == 1) {
			user.setUserAreaName("全国");
		} else if(roleSeq == 2) {
			AreaEntity areaEntity = areaDao.selectById(userAreaSeq);
			user.setUserAreaName(areaEntity.getAreaName());
		} else if (roleSeq == 3) {
			AreaEntity areaEntity = areaDao.selectById(userAreaSeq);
			user.setUserAreaName(areaEntity.getAreaName());
		} else if (roleSeq == 4) {
			ShopEntity shopEntity = shopDao.selectById(userAreaSeq);
			user.setUserAreaName(shopEntity.getName());
		}
		
		return user;
	}

	protected Integer getUserId() {
		return getUser().getSeq();
	}

//	protected Long getDeptId() {
//		return getUser().getDeptId();
//	}
	 /**
     * 登录用户所属的工厂管理员
     */
    protected UserEntity getAdminUser() {
    	UserEntity userEntity = new UserEntity();
		return userDao.selectOne(userEntity);
	}
    
    /**
     * 登录用户所属的工厂管理员seq
     */
    protected Long getAdminUserSeq() {
        return (long) getAdminUser().getSeq();
    }
    
	/**
	 * 鞋子图片路径
	 * @param folder
	 * @return
	 */
	public String getShoePictureUrl(Integer periodSeq, String shoeId) {
		String baseUrl = "http://www.nuite.com.cn/ShoesManage/Resource/ShoesResource/ShoesIcons";
		return baseUrl + "/" + periodSeq + "/" + shoeId + ".jpg";
	}
}
