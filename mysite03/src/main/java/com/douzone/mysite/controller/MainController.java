package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
//	@Auth(value="USER")	//USER로 로그인 한 사람만 접근 가능
//	@Auth(role="ADMIN")
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		
		return "하이";
	}
	
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Object message02(/* HttpServletResponse resp */) throws Exception {
//		resp.setContentType("contentType=application/json; charset=UTF-8");
//		resp.getWriter().print("{\"message\":\"Hello World\"}");
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		
		return map;
	}
	
	
}
