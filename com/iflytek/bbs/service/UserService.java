package com.iflytek.bbs.service;

import com.iflytek.bbs.po.User;

public interface UserService {
	//新增用户
		public void add(User user);
		
		//获取用户
		
		public User getUser(String email,String password);
		
		public User getUserPassword(int userId);
		
		public void updatePassword(int userId,String newPassword);
}
