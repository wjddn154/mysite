package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDAO;
import com.douzone.mysite.vo.GuestbookVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestbookVO vo = new GuestbookVO();
		vo.setNo(Long.parseLong(no));
		vo.setPassword(password);
		
		new GuestbookDAO().delete(vo);
		MvcUtil.redirect(request.getContextPath() + "/guestbook", request, response);
	}

}
