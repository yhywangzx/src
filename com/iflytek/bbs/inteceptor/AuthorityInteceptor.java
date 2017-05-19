package com.iflytek.bbs.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorityInteceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("User")==null){
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
