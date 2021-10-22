package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안)
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			
			return;
		}
		
		
		//board delete 작업 추가
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVO vo = new BoardVO();
		vo.setTitle("삭제된 게시물 및 댓글입니다.");
		vo.setHit(-1L);
		vo.setNo(no);
		
		new BoardDAO().deleteboard(vo);
		
		
		MvcUtil.redirect(request.getContextPath() + "/board?pageno=1", request, response);
	}

}
