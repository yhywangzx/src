package com.iflytek.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iflytek.bbs.po.News;


public interface NewsDao {
	public News getNewsById(@Param("newsId")int newsId);
	
	public void add(News news);
	
	public void update(News news);
	
	public List<News> getNewsPageList(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
	
	public int getTotalCount();
	
	public void delete(@Param("newsId")int newsId);
	
	
	public List<News> getMyNewsPageList(@Param("userId")int userId,@Param("title")String title,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
	
	public int getMyTotalCount(@Param("userId")int userId,@Param("title")String title);
}
