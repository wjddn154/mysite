package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVO;

@Controller
@RequestMapping("/api/guestbook")
public class GuestbookApiController {
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonResult ex1(@RequestBody GuestbookVO vo) {
		// vo = guestbookService.addMessage(vo)를 사용해서 등록작업
		guestbookService.add(vo);
		
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult ex2(
		@RequestParam(value="sn", required=true, defaultValue="-1") Long no) {
		// list = guestbookService.findAll(no)를 사용해서 리스트 가져오기
		System.out.println("no : " + no);
		List<GuestbookVO> list = new ArrayList<>();
		list = guestbookService.findAll(no);

		return JsonResult.success(list);
	}
		
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult ex3(@PathVariable("no") Long no, String password) {
		// result = guestbookService.deleteMessage(no, password)를 사용해서 삭제작업
		boolean result = guestbookService.deleteMessage(no, password);
		Long data = 0L;
		
		//1. 삭제가 안된 경우
		if(result == false) {
			data = -1L;
			
		//2. 삭제가 된 경우
		} else {
			data = no;
		}
		
		return JsonResult.success(data);
	}
	
}