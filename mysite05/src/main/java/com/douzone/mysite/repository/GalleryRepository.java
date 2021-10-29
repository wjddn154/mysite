package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVO;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(GalleryVO vo) {
		return 1 == sqlSession.insert("gallery.insert", vo);
	}

	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("gallery.delete", no);
	}

	public List<GalleryVO> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}
}