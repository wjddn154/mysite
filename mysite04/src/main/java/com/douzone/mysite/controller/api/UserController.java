package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;

@RestController("userApiController")	//@Controller + @ResponseBody = @RestController
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkemail")
	public JsonResult checkemail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		UserVO userVO = userService.getUser(email);
		
		return JsonResult.success(userVO != null);
	}
	

}
