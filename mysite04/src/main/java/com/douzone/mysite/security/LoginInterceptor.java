package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserVO authUser = userService.getUser(email, password);
		if(authUser == null) {
			request.setAttribute("result", "fail");
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			return false;
		}

		// session 처리
		System.out.println(authUser);
		
		HttpSession session = request.getSession(true);
//		HttpSession session = request.getSession();

		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());

		return false;
	}
}