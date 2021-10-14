package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 인증처리(세션처리) */
//		HttpSession session = request.getSession();
//		BoardVO authUser = (BoardVO)session.getAttribute("authUser");
//		session.setAttribute("authUser", authUser);
		
		
		List<BoardDTO> list = new BoardDAO().findAll();
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);
	}
	오빠 바보

}
