package com.iflytek.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.iflytek.bbs.dao.NewsDao;
import com.iflytek.bbs.dao.ReplyDao;
import com.iflytek.bbs.po.News;
import com.iflytek.bbs.service.NewsService;


@Service("newsService")
public class NewsServiceImpl implements NewsService {
	@Resource(name="newsDao")
	private NewsDao newsDao;
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	
	@Override
	public News getNewsById(int newsId) {
		
		return newsDao.getNewsById(newsId);
	}

	@Override
	public void add(News news) {
		newsDao.add(news);
		
	}

	@Override
	public void update(News news) {
		newsDao.update(news);
		
		
	}

	@Override
	public List<News> getNewsPageList(int pageIndex, int pageSize) {
		return newsDao.getNewsPageList(pageIndex, pageSize);
	}

	@Override
	public int getTotalPage(int pageSize) {
		int totalCount =  newsDao.getTotalCount();
		
		if(totalCount % pageSize == 0){
			return totalCount / pageSize;
		}else{
			return totalCount / pageSize + 1;
		}
	}

	@Override
	public void delete(int newsId) {
		newsDao.delete(newsId);
		
	}

	@Override
	public List<News> getMyNewsPageList(int userId, String title,
			int pageIndex, int pageSize) {
		return newsDao.getMyNewsPageList(userId, title, pageIndex, pageSize);
	}

	@Override
	public int getMyTotalPage(int userId, String title,int pageSize) {
		int totalCount = newsDao.getMyTotalCount(userId, title);
		
		if(totalCount % pageSize == 0){
			return totalCount / pageSize;
		}else{
			return totalCount / pageSize + 1;
		}
	}
	

}
