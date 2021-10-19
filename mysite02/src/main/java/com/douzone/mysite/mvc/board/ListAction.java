package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.board.paging.PagingVO;
import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long pageNo = Long.parseLong(request.getParameter("pageno"));
		if(pageNo.equals(null) || pageNo == null || pageNo == 0) {
			pageNo=1L;
		}
		
		List<BoardDTO> list = new BoardDAO().findAll(pageNo);
		request.setAttribute("list", list);


        PagingVO pageVO = new PagingVO();
        int no = new BoardDAO().countBoard();
        pageVO.setPageNo(1);
        pageVO.setPageSize(10);
        pageVO.setTotalCount(no);

		request.setAttribute("page", pageVO);
		
		MvcUtil.forward("board/list", request, response);
	}

}
