package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVO;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVO findByEmailAndPassword(String email, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
	
	public boolean insert(UserVO vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}

	public UserVO findByNo(Long no) throws UserRepositoryException {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public boolean update(UserVO vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}

	public UserVO findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}
}