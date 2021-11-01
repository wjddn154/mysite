package com.douzone.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVO;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVO> findAll() throws GuestbookRepositoryException {
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public boolean delete(GuestbookVO vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;		

	}
	
	public boolean insert(GuestbookVO vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return count == 1;
	}
}