package com.iflytek.bbs.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iflytek.bbs.po.News;
import com.iflytek.bbs.po.User;
import com.iflytek.bbs.service.NewsService;


@Controller
@Scope("prototype")
public class MyNewsController {
	
	@Resource(name="newsService")
	private NewsService newsService;
	
	@RequestMapping(value = "/person/newslist",method = RequestMethod.GET)
	public ModelAndView newsList(HttpSession session){
		ModelAndView mv = new ModelAndView("person/newslist");
		
		User user = (User)session.getAttribute("User");
		
		List<News> newsList = newsService.getMyNewsPageList(user.getId(),"", 1, 5);
		int totalPage = newsService.getMyTotalPage(user.getId(),"", 5);
		
		mv.addObject("newsList", newsList);
		mv.addObject("pageIndex", 1);
		mv.addObject("totalPage", totalPage);
		
		
		return mv;
		
	}
	@RequestMapping(value="/person/newslist",method = RequestMethod.POST)
	public ModelAndView newsList(HttpSession session,String title,int pageIndex){
		ModelAndView mv = new ModelAndView("person/newslist");
		
		User user = (User)session.getAttribute("User");
		
		List<News> newsList = newsService.getMyNewsPageList(user.getId(),title, pageIndex, 5);
		int totalPage = newsService.getMyTotalPage(user.getId(),title, 5);
		
		mv.addObject("title", title);
		mv.addObject("newsList", newsList);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("totalPage", totalPage);
		
		return mv;
		
	}
	
	@RequestMapping(value="/person/newsadd",method = RequestMethod.GET)
	public String newsAdd(){
		return "person/newsadd";
	}
	
	@RequestMapping(value="/person/newsadd",method = RequestMethod.POST)
	public String newsAdd(HttpSession session,String title,String content){
		User user = (User)session.getAttribute("User");
		
		News news =new News();
		news.setTitle(title);
		news.setContent(content);
		news.setPublishDate(new Date());
		news.setBrowserCount(0);
		news.setUser(user);
		newsService.add(news);
		
		return "redirect:/person/newslist";
		
	}
	
	//@pathvariable 从传入的地址中获取value值赋给参数
	@RequestMapping(value="/person/newsedit/{newsid}",method = RequestMethod.GET)
	public ModelAndView newsEdit(@PathVariable(value="newsid") int id){
		
		ModelAndView mv = new ModelAndView("person/newsedit");
		
		News news = newsService.getNewsById(id);
		
		mv.addObject("news", news);
		
		return mv;
	}
	@RequestMapping(value="/person/newsedit",method = RequestMethod.POST)
	public String newsEdit(HttpSession session,String title,String content,int id){
		User user = (User)session.getAttribute("User");
	
		News news =new News();
		news.setTitle(title);
		news.setContent(content);
		news.setPublishDate(new Date());
		news.setBrowserCount(0);
		news.setUser(user);
		news.setId(id);
		
		newsService.update(news);
		
		return "redirect:/person/newslist";
	}
	
	//如果参数不一样,可用@requestParam注解,value表获取到的参数
	@RequestMapping(value="/delete")
	public String deleteNews(@RequestParam(value="id") int newsId){
		newsService.delete(newsId);
		return "redirect:/person/newslist";
		
	}
	

}
