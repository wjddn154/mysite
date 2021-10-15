package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//상위 데이터 update 작업 추가
		
		
		
		
		
		
		//board delete 작업 추가

		
		
		
		
		
		
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
