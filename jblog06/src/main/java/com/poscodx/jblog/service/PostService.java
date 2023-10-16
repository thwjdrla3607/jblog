package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;


@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public List<PostVo> getContentsList(String blogId) {
		return postRepository.findByNo(blogId);
	}

	public void addContents(PostVo postVo) {
		postRepository.insert(postVo);		
	}

	public PostVo getContent(String postNo) {
		return postRepository.findByNoOne(postNo);
	}

}
