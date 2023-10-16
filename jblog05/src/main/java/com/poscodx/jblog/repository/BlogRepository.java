package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(BlogVo blogVo) {
		sqlSession.insert("blog.insert", blogVo);
	}
	
	public BlogVo getUser(String blogId) {
		return sqlSession.selectOne("blog.findById", blogId);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);		
	}
}
