package com.nuite.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.nuite.dao.UserBrandDao;
import com.nuite.dao.UserDao;
import com.nuite.entity.UserEntity;
import com.nuite.service.UserService;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserBrandDao userBrandDao;
	
    
    /**
     * 根据用户Seq查询用户详细信息
     */
	@Override
	public UserEntity getUserBySeq(int userSeq) {
		return userDao.getUserBySeq(userSeq);
	}
	
	
    /**
     * 根据用户名查询用户
     */
	@Override
	public UserEntity getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	
	/**
	 * 新增用户
	 */
	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void createUser(UserEntity userEntity, Integer brandSeq) {
		//新增用户
		userDao.insertUser(userEntity);
		//新增用户品牌关系
		userBrandDao.insertUserBrand(userEntity.getSeq(), brandSeq);
	}
	
	
    /**
     * 修改用户信息
     */
	@Override
	public void updateUser(UserEntity userEntity) {
		userDao.updateById(userEntity);
	}


	/**
	 * 获取已创建的账号列表
	 */
	@Override
	public List<UserEntity> getCreatedUserList(Integer userSeq, Integer start, Integer num) {
		Wrapper<UserEntity> wrapper = new EntityWrapper<UserEntity>();
		wrapper.where("CreateUserSeq = {0}", userSeq).orderBy("Seq DESC");
		return userDao.selectPage(new RowBounds(start - 1, num), wrapper);
	}


	
	/**
	 * 获取所有下级账号列表
	 */
	@Override
	public List<UserEntity> getAllSubUserList(UserEntity loginUser, List<Integer> areaSeqList, Integer start, Integer num) {
		int pageSize = num;
		int pageNo = ((start - 1) / num) + 1;
        Page<Map<String, Object>> page = new Page<>(pageNo, pageSize);
        
    	String areaSeqs = Joiner.on(",").join(areaSeqList);
    	
        List<UserEntity> list = userDao.getAllSubUserList(page, loginUser.getBrandSeq(), loginUser.getRoleSeq() + 1, areaSeqs);
		return list;
	}




}
