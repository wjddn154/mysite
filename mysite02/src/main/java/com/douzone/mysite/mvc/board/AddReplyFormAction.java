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

public class AddReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안)
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);

			return;
		}
		
		Long checkNo = Long.parseLong(request.getParameter("no"));
		
		//상위 게시물 확인
		BoardVO pvo = new BoardDAO().findHigerBoard(checkNo);
		System.out.println("no : " + pvo.getNo());
		System.out.println("group_no : " + pvo.getGroupNo());
		System.out.println("order_no : " + pvo.getOrderNo());
		System.out.println("depth : " + pvo.getDepth());
		
		//답글 insert 작업
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long no = authUser.getNo();
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setNo(no);
		vo.setGroupNo(pvo.getGroupNo());
		vo.setOrderNo(pvo.getOrderNo()+1);
		vo.setDepth(pvo.getDepth()+1);
		
		new BoardDAO().insertReply(vo);
		
		//상위 게시물 order_no + 1
		new BoardDAO().updateParentBoard(pvo);
		
		
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
