package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> getContentsList(String no) {
		return categoryRepository.findByNo(no);
	}

	public void addContents(CategoryVo categoryVo) {
		if (categoryRepository.check(categoryVo))
			categoryRepository.insert(categoryVo);		
	}

	public void deleteContents(String categoryNo) {
		categoryRepository.delete(categoryNo);		
	}

	public CategoryVo getContent(String categoryNo) {
		return categoryRepository.findByNoOne(categoryNo);
	}
}
