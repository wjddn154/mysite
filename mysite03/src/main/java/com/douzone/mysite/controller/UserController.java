package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	// 접근제어(Access Control List)
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVO authUser, Model model) {
		UserVO userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVO authUser, UserVO userVo) {
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	
	
}