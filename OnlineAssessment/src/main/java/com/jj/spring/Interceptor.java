package com.jj.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String path = request.getRequestURI();
		
		if(path.contains("login") || path.contains("assets")) { 
			return true;
		}
		
			String name = (String)request.getSession().getAttribute("username");
			if(StringUtils.isEmpty(name)){
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}
			
			if(path.contains("admin")){
				if(!name.contains("admin")){
					response.sendRedirect(request.getContextPath()+"/login");
					return false;
				}
			}
			
			return true;
	}
}