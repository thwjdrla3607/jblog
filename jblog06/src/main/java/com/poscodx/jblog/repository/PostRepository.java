package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> findByNo(String id) {
		return sqlSession.selectList("post.findByNo", id);
	}

	public void insert(PostVo postVo) {
        sqlSession.insert("post.insert", postVo);		
	}

	public PostVo findByNoOne(String postNo) {
		return sqlSession.selectOne("post.findByNoOne", postNo);
	}

}
