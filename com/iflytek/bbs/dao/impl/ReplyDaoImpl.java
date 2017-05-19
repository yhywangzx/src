package com.iflytek.bbs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.dao.ReplyDao;
import com.iflytek.bbs.po.Reply;
@Repository("replyDao")
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Reply> getReplyListByNewsId(int newsId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Reply r where id= :newsid");
		query.setParameter("newsid", newsId);
		List<Reply> list = (List<Reply>)query.list();
		return list;
	}

	@Override
	public void add(Reply reply) {
		Session session = sessionFactory.getCurrentSession();
		session.save(reply);

	}

	@Override
	public void delete(int Id) {
		Session session = sessionFactory.getCurrentSession();
		Reply reply = (Reply)session.get(Reply.class, Id);
		session.delete(reply);

	}

	@Override
	public void deleteReply(int newsId) {
		Session session = sessionFactory.getCurrentSession();
		Reply reply = (Reply)session.get(Reply.class, newsId);
		session.delete(reply);

	}

}
