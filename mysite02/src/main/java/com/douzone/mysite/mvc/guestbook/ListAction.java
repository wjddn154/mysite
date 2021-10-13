package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDAO;
import com.douzone.mysite.vo.GuestbookVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestbookVO> list = new GuestbookDAO().findAll();
		
		request.setAttribute("list", list);
		MvcUtil.forward("guestbook/list", request, response);
	}

}
