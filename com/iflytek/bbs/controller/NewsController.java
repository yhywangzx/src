package com.iflytek.bbs.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iflytek.bbs.po.News;
import com.iflytek.bbs.po.Reply;
import com.iflytek.bbs.po.User;
import com.iflytek.bbs.service.NewsService;
import com.iflytek.bbs.service.ReplyService;




@Controller
@Scope("prototype")//指定每次请求产生一个新的对象
public class NewsController {
	@Resource(name="newsService")
	private NewsService newsService;
	@Resource(name="replyService")
	private ReplyService replyService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView newsList(){
		ModelAndView mv = new ModelAndView("index");
		
		List<News> newsList = newsService.getNewsPageList(1, 5);
		int totalPage = newsService.getTotalPage(5);
		
		mv.addObject("newsList", newsList);
		mv.addObject("pageIndex", 1);
		mv.addObject("totalPage", totalPage);
		
		
		return mv;
	}
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public ModelAndView newsList(int pageIndex){
		ModelAndView mv = new ModelAndView("index");
		
		List<News> newsList = newsService.getNewsPageList(pageIndex, 5);
		int totalPage = newsService.getTotalPage(5);
		
		mv.addObject("newsList", newsList);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("totalPage", totalPage);
	
		return mv;
	}
	
	@RequestMapping(value="/newsview",method=RequestMethod.GET)
	public ModelAndView newsView(int id){
		ModelAndView mv = new ModelAndView("newsview");
		
		News news = newsService.getNewsById(id);
		
		mv.addObject("news", news);
		
		List<Reply> replyList = replyService.getReplyListByNewsId(id);
		mv.addObject("replyList", replyList);
		
		
		return mv;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/newsview",method=RequestMethod.POST,produces="text/html; charset=UTF-8")
	public String newsView(HttpSession session,String content,int newsid){
		
		
		
		User user = (User)session.getAttribute("User");
		
		if(content != null){
			Reply reply = new Reply();
			reply.setContent(content);
			reply.setReplyDate(new Date());
			//reply.setUserId(user.getId());
			//reply.setUserName(user.getName());
			//reply.setNewsId(newsid);
			reply.setUser(user);
			
			replyService.add(reply);
			
			return "true";
		
		}else{
			return "false";
		}
		
	
	}
	
	@ResponseBody
	@RequestMapping(value="/replydelete",method={RequestMethod.GET,RequestMethod.POST},produces="text/html; charset=UTF-8")
	public String replyDelete(int id){
		replyService.delete(id);
		return "true";
	}
	@RequestMapping(value="/account",method=RequestMethod.GET)
	public String account(){
		return "account";
	}

}
