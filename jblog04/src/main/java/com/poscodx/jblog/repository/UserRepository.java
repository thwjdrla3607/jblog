package com.poscodx.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean check(UserVo vo) {
		String result = sqlSession.selectOne("user.check", vo);
		return result == null ? false : true;
	}
	
	public Boolean insert(UserVo vo) {
		
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}

	public UserVo findByIdAndPassword(String id, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}

	public List<UserVo> findAll() {
		return sqlSession.selectList("user.findAll");
	}
}