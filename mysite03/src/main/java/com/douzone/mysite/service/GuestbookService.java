package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public void delete(GuestbookVO vo) {
		guestbookRepository.delete(vo);
	}

	
}