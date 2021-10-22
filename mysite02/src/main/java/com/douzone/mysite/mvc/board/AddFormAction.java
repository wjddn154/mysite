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

public class AddFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안)
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);

			return;
		}

		//새 게시물 insert 하는 작업
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long no = authUser.getNo();
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setNo(no);
		
		new BoardDAO().insert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?pageno=1", request, response);
	}

}
