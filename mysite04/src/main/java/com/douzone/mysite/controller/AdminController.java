package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVO;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	
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
	public String main(Model model) {
		SiteVO siteVO = siteService.getSite();
		model.addAttribute( "siteVO", siteVO );
		return "admin/main";
	}
	

	@RequestMapping("/main/update")
	public String modify(
			@ModelAttribute SiteVO siteVO,
			@RequestParam("file") MultipartFile file) {
		
		siteService.modifySite( siteVO, file );
		
		return "redirect:/admin";
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
