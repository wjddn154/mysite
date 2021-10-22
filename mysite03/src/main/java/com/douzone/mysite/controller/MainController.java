package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//	@Auth(value="USER")	//USER로 로그인 한 사람만 접근 가능
//	@Auth(role="ADMIN")
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	
}
