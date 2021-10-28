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
import com.douzone.mysite.service.FileUploadService;
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
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
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
		
		try {
			String profile = fileUploadService.getProfile(file);
			siteVO.setProfile(profile);
		} catch(Exception e) {
			System.out.println("이미지가 없어서 기존 이미지 사용");
		}
		
		System.out.println("siteVO : " + siteVO);
		siteService.modifySite(siteVO);
		servletContext.setAttribute("siteVO", siteVO);
		
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
