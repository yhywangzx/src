package com.iflytek.bbs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.dao.NewsDao;
import com.iflytek.bbs.po.News;
@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public News getNewsById(int newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.get(News.class, newsId);
		return news;
	}

	@Override
	public void add(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.save(news);

	}

	@Override
	public void update(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.update(news);

	}

	@Override
	public List<News> getNewsPageList(int pageIndex, int pageSize) {
		Session session =  sessionFactory.getCurrentSession();
		Query query = session.createQuery("from News");
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		List<News> list = (List<News>)query.list();
		return list;
	}

	@Override
	public int getTotalCount() {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("select count(*) from News");
		
		Long row = (Long)query.uniqueResult();
		
		return row.intValue();
	}

	@Override
	public void delete(int newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = (News)session.get(News.class, newsId);
		session.delete(news);

	}

	@Override
	public List<News> getMyNewsPageList(int userId, String title,
			int pageIndex, int pageSize) {
Session session =  sessionFactory.getCurrentSession();
		
		String hql = "from News n where n.user.id = :userid";
		if(title !=null && !title.trim().equals("")){
			hql += " and n.title like :title"; 
		}
		Query query = session.createQuery(hql);
		query.setParameter("userid", userId);
		if(title !=null && !title.trim().equals("")){
			query.setParameter("title", "%"+ title.trim()+ "%");
		}
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		List<News> list = (List<News>)query.list();
		return list;
	}

	@Override
	public int getMyTotalCount(int userId, String title) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from News n where n.user.id = :userid";
		if(title !=null && !title.trim().equals("")){
			sql += " and n.title like :title"; 
		}
		Query query = session.createQuery(sql);
		query.setParameter("userid", userId);
		if(title !=null && !title.trim().equals("")){
			query.setParameter("title", "%"+title+"%");
		}
		
		Long row = (Long)query.uniqueResult();
		
		return row.intValue();
	}

}
