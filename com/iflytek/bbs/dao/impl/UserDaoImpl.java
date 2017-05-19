package com.iflytek.bbs.dao.impl;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.dao.UserDao;
import com.iflytek.bbs.po.User;
import com.iflytek.bbs.util.HibernateUtil;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	@Override
	public User getUser(String email, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.email = :email and u.password = :password");
		
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		List<User> list = (List<User>)query.list();
		return list.get(0);
	}

	@Override
	public User getUserPassword(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userId);
		return user;
	}

	@Override
	public void updatePassword(int userId, String newPassword) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("update User u set u.password = :password where u.id = :userid");
		query.setParameter("userid", userId);
		query.setParameter("password", newPassword);
		
		query.executeUpdate();

	}

}
