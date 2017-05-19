package com.iflytek.bbs.service;

import java.util.List;

import com.iflytek.bbs.po.Reply;

public interface ReplyService {
	public List<Reply> getReplyListByNewsId(int newsId);
	
	public void add(Reply reply);
	
	public void delete(int Id);
	
	public void deleteReply(int newsId);
}
