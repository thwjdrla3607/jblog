package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findByNo(String no) {
		return sqlSession.selectList("category.findByNo", no);
	}

	public void insert(CategoryVo categoryVo) {
        sqlSession.insert("category.insert", categoryVo);	
	}

	public boolean check(CategoryVo categoryVo) {
		String result = sqlSession.selectOne("category.check", categoryVo);
		return result == null ? true : false;
	}

	public void delete(String categoryNo) {
        sqlSession.delete("category.delete", categoryNo);		
	}

	public CategoryVo findByNoOne(String categoryNo) {
		return sqlSession.selectOne("category.findByNoOne", categoryNo);
	}

}
