package com.bbar.sns.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		String uri = request.getRequestURI();
		
		if(userId == null) {
			if(uri.startsWith("/post")) {
				
				response.sendRedirect("/user/login-view");
				return false;
			}
		} else {
			// 로그인이 되어 있는 경우 사용자와 관련된 페이지 접근을 막는다
			// 메모 리스트 페이로 이동
			// /user 로 시작하는 url path 확인
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/timeline-view");
				return false;
			}
		}
	return true;
	}

}
