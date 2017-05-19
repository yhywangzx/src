package com.iflytek.bbs.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflytek.bbs.dao.ReplyDao;
import com.iflytek.bbs.po.Reply;
import com.iflytek.bbs.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public List<Reply> getReplyListByNewsId(int newsId) {
		
		return replyDao.getReplyListByNewsId(newsId);
	}

	@Override
	public void add(Reply reply) {
		// TODO Auto-generated method stub
		replyDao.add(reply);
		
	}

	@Override
	public void delete(int Id) {
		replyDao.delete(Id);
	}
	@Override
	public void deleteReply(int newsId) {
		replyDao.delete(newsId);
		
	}

}
