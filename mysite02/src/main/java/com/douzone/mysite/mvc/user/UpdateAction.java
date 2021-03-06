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

public class UpdateAction implements Action {

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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO vo = new UserVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(authUser.getNo());

		new UserDao().update(vo);
		
		//main화면에 수정된 이름을 표시하기 위한 작업
		String email = request.getParameter("email");
		UserVO userVo = new UserDao().findByEmailAndPassword(email, password);
		session.setAttribute("authUser", userVo);
		
		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
