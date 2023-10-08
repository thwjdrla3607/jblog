package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;


@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public void addUser(BlogVo blogVo) {
		blogRepository.insert(blogVo);
	}

	public BlogVo getUser(String blogId) {
		return blogRepository.getUser(blogId);
	}

	public void updateContents(BlogVo blogVo) {
		blogRepository.update(blogVo);		
	}
}
