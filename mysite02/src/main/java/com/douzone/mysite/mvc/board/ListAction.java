package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.PagingVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//페이지 값 및 유효성
		Long pageNo = (request.getParameter("pageno") != null) ? Long.parseLong(request.getParameter("pageno")) : 1L;

		//찾기 값 및 유효성
		String findKey = (request.getParameter("kwd") != null) ? request.getParameter("kwd") : "";
		
		//페이지 작업
        PagingVO pageVO = new PagingVO();
        int no = new BoardDAO().countBoard(findKey);
        pageVO.setPageNo(pageNo.intValue());	//현재 페이지
        pageVO.setPageSize(3);	// 페이지 사이즈 설정
        pageVO.setTotalCount(no);

        //리스트
		List<BoardDTO> list = new BoardDAO().findAll(pageNo, findKey, pageVO.getPageSize());
		request.setAttribute("list", list);
		request.setAttribute("page", pageVO);
		request.setAttribute("findkey", findKey);
		
		MvcUtil.forward("board/list", request, response);
	}

}
