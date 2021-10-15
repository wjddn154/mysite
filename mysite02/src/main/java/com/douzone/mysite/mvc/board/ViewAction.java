package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		BoardDTO boardDTO = new BoardDAO().findByNo(no);
		
		//조회수 + 1
		new BoardDAO().updateHit(no);
		
		request.setAttribute("dto", boardDTO);
		MvcUtil.forward("board/view", request, response);

	}

}
