package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ServletContext servletContext;
	
//	@RequestMapping("/main/update")
//	public String main(SiteVO siteVO) {
//		siteService.update(siteVO)
//	}
	
//	SiteVO site = servletContext.getAttribute("site");
//	if(site == null) {
//		SiteVO vo = siteSetrvice.getSite();
//		servletContext.setAttribute("site", vo);
//	}
	
	@RequestMapping("")
	public String main() {
		System.out.println("2222222");
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		
		return "admin/user";
	}
	
	
	
}
