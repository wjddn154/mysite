package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVO;

@Service
public class SiteService {

	
	@Autowired
	private SiteRepository siteRepository;

	public SiteVO getSite() {
		SiteVO siteVO = siteRepository.findSite();

		return siteVO;
	}

	
	public boolean modifySite(SiteVO siteVO) {
		
		int count = siteRepository.update( siteVO );
		return count == 1;
	}

	
	
}
