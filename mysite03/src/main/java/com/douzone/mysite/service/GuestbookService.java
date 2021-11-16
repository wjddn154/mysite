package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVO> getList() {
		return guestbookRepository.getList();
	}

	public void add(GuestbookVO vo) {
		guestbookRepository.insert(vo);
	}
	
	public void delete(GuestbookVO vo) {
		guestbookRepository.delete(vo);
	}

	public boolean deleteMessage(Long no, String password) {
		GuestbookVO vo = new GuestbookVO();
		vo.setNo(no);
		vo.setPassword(password);
		
		return guestbookRepository.delete(vo);
	}

	public List<GuestbookVO> findAll(Long no) {
		return guestbookRepository.findAll(no);
	}

	public Long getLastNo() {
		return guestbookRepository.findLastNo();
	}
	
}