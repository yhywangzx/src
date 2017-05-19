package com.iflytek.bbs.service;

import java.util.List;

import com.iflytek.bbs.po.News;

public interface NewsService {
	//根据帖子ID获取该帖子对象
	public News getNewsById(int newsId);
	//添加帖子
	public void add(News news);
	//修改帖子
	public void update(News news);
	//分页获取
	public List<News> getNewsPageList(int pageIndex,int pageSize);
	//所有帖子总数
	public int getTotalPage(int pageSize);
	//删除帖子
	public void delete(int newsId);
	//获取我的帖子
	public List<News> getMyNewsPageList(int userId,String title,int pageIndex,int pageSize);
	//获取我的帖子的数量
	public int getMyTotalPage(int userId,String title,int pageSize);
}
