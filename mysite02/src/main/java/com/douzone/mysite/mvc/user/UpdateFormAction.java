package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안)
		/* 게시판 만들때 이 부분도 해야함 */
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			
			return;
		}
		////////////////// 보안 끝 //////////////////////
		
		Long no = authUser.getNo();
		UserVO userVo = new UserDao().findByNo(no);
		
//		request.setAttribute("user", authUser);
		request.setAttribute("user", userVo);
		MvcUtil.forward("user/updateform", request, response);

	}

}
