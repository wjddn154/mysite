package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@Auth
	@RequestMapping(value="")
	public String index(Model model) {
		List<GuestbookVO> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}

	@RequestMapping("/spa")
	public String spa() {
		return "guestbook/index-spa";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestbookVO vo) {
		guestbookService.add(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
	public String delete(HttpSession session, @PathVariable("no") Long no) {
		session.setAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVO vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
	
	
	
	
}