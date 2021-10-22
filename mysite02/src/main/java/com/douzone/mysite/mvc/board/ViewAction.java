package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {
	private static final String COOKIE_NAME = "visitCount";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		BoardDTO boardDTO = new BoardDAO().findByNo(no);
		
		Cookie viewCookie=null;
		Cookie[] cookies=request.getCookies();

		System.out.println("cookie : "+cookies);
		
        
        if(cookies !=null) {

			for (int i = 0; i < cookies.length; i++) {
                
                //만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
				if(cookies[i].getName().equals("|"+COOKIE_NAME+no+"|")) {
					System.out.println("if문 쿠키 이름 : "+cookies[i].getName());
				
                //찾은 쿠키를 변수에 저장
					viewCookie=cookies[i];
				}
			}
			
		}else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}


		//만들어진 쿠키가 없음을 확인
		if(viewCookie==null) {
        
          	System.out.println("viewCookie 확인 로직 : 쿠키 없음");
			
            try {
            
            	//이 페이지에 왔다는 증거 쿠키 생성
				Cookie newCookie=new Cookie("|"+COOKIE_NAME+no+"|","readCount");
//				newCookie.setMaxAge(24 * 60 * 60); //1day
				newCookie.setMaxAge(60); //1min
				response.addCookie(newCookie);
                
                //쿠키가 없으니 증가 로직 진행
				//조회수 + 1
				new BoardDAO().updateHit(no);
                
			} catch (Exception e) {
				System.out.println("쿠키 넣을때 오류 유무 : "+e.getMessage());
				e.getStackTrace();

			}
		
        //만들어진 쿠키가 있으면 증가로직 진행하지 않음
		}else {
			System.out.println("viewCookie 확인 로직 : 쿠키가 존재함");
			String value=viewCookie.getValue();
			System.out.println("viewCookie 확인 로직 : 쿠키 value : "+value);
		}
		
		
		request.setAttribute("dto", boardDTO);
		MvcUtil.forward("board/view", request, response);

	}

}
