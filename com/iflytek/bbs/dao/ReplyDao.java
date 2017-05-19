package com.iflytek.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.po.Reply;

public interface ReplyDao {
	
	public List<Reply> getReplyListByNewsId(@Param("newsId")int newsId);
	
	public void add(Reply reply);
	
	public void delete(@Param("Id")int Id);
	
	public void deleteReply(@Param("newsId") int newsId);

}
