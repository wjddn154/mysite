package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVO> getList() {
		return guestbookRepository.findAll();
	}

	public void add(GuestbookVO vo) {
		guestbookRepository.insert(vo);
	}
	
	public void delete(GuestbookVO vo) {
		guestbookRepository.delete(vo);
	}
	
}