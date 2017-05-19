package com.iflytek.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iflytek.bbs.po.User;
import com.iflytek.bbs.service.UserService;


@Controller
@Scope("prototype")
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	//cookie注解,不用使用循环		
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView login(@CookieValue(value="email",defaultValue="") String email){
		
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("email", email);
		return mv;
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null){
//			for(Cookie c : cookies){
//				if(c.getName().equals("email")){
//					mv.addObject("email", c.getValue());
//					break;
//				}
//			}
//		}
//		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method =RequestMethod.POST,produces="text/html; charset=UTF-8")
	public String login(String email,String password,HttpSession session,HttpServletResponse response){
		
		
		User user = userService.getUser(email, password);
		
		if(user != null){
			
			session.setAttribute("User", user);
			
			//保存账号到本地
			Cookie c = new Cookie("email", email);
			c.setMaxAge(24*60*60);
			
			response.addCookie(c);
			
			return "true";
		}else{
			return "false";
		}
		
	}
	
	
	
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String register(){
		return "register";
	}
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ModelAndView register(User user,String psw){
		ModelAndView mv = new ModelAndView();
		
		String password = user.getPassword();
		if(psw.equals(password)){
			userService.add(user);
			mv.setViewName("redirect:/login");
			
		}else{
			mv.addObject("error", "两次密码不一致");
			mv.addObject("email", user.getEmail());
			mv.addObject("name", user.getName());
			mv.setViewName("register");
		}
		return mv;
		
	}
	@RequestMapping(value="/cancel",method = RequestMethod.GET)
	public ModelAndView cancel(HttpSession session){
		ModelAndView mv = new ModelAndView("cancel");
		
		session.removeAttribute("User");
		
		mv.setViewName("redirect:/index");
		
		return mv;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/person/checkpassword",method=RequestMethod.POST,produces="text/html; charset=UTF-8")
	public String checkPassword(HttpSession session,String pwd){
		User user = (User)session.getAttribute("User");
		String password = user.getPassword();
		
		if(pwd.equals(password)){
			return "true";
			
		}else{
			return "false";
		}
	}
	@RequestMapping(value="/person/pwdedit",method=RequestMethod.GET)
	public String pwdEdit(){
		return "person/pwdedit";
	}
	
	@RequestMapping(value="/person/pwdedit",method=RequestMethod.POST)
	
	public ModelAndView pwdEdit(HttpSession session,String psw,String password,HttpServletResponse response,HttpServletRequest request){
		ModelAndView mv =new ModelAndView("person/pwdedit");
		
		User user = (User)session.getAttribute("User");
		
		if(psw.equals(user.getPassword())){
			mv.addObject("newmsg", "新密码不能与旧密码相同");
			mv.setViewName("/person/pwdedit");
		}
		if(!psw.equals(password)){
			mv.addObject("passwordmsg", "两次密码不一致");
			mv.addObject("psw", psw);
			mv.setViewName("/person/pwdedit");
		}
		userService.updatePassword(user.getId(),password);
		
		session.removeAttribute("User");
		
		String script = "<script>parent.window.location.href='"
				+ request.getContextPath() + "/login'</script>";
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(script);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		return mv;
		
		
	}
	
}
