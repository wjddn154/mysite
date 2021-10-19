package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String join() {
		return "guestbook/list";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String join(GuestbookVO vo) {
		guestbookService.delete(vo);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
}