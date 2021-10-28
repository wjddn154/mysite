package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.vo.SiteVO;

@Repository
public class SiteRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public SiteVO findSite() {
		return sqlSession.selectOne("site.findSite");
	}

	/* 여기서 부터 해야함 */
	//file 넘겨주기 
	public int update(SiteVO siteVO, MultipartFile file) {
		
		return sqlSession.update( "site.update", siteVO );
	}
	
	
	
	
}