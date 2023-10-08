package com.poscodx.jblog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void join(@Valid UserVo userVo) {
		if (userRepository.check(userVo))
			userRepository.insert(userVo);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public List<UserVo> getUserList() {
		return userRepository.findAll();
	}

}
