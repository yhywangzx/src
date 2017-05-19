package com.iflytek.bbs.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.iflytek.bbs.dao.UserDao;
import com.iflytek.bbs.po.User;
import com.iflytek.bbs.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public void add(User user) {
	 userDao.add(user);
	
		
	}

	@Override
	public User getUser(String email, String password) {
		return userDao.getUser(email,password);
	}

	@Override
	public User getUserPassword(int userId) {
		return userDao.getUserPassword(userId);
	}

	@Override
	public void updatePassword(int userId, String newPassword) {
		userDao.updatePassword(userId, newPassword);
		
		
	}

}
