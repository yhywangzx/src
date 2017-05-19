package com.iflytek.bbs.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.po.User;


public interface UserDao {
	//新增用户
	public void add(User user);
	
	//获取用户
	
	public User getUser(@Param("email") String email,@Param("password") String password);
	
	public User getUserPassword(@Param("userId") int userId);
	
	public void updatePassword(@Param("userId") int userId,@Param("newPassword") String newPassword);

}
