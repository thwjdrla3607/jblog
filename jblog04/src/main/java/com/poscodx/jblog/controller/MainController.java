package com.poscodx.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	public UserService userService;
	
	@RequestMapping("")
	public String main(Model model) {
		List<UserVo> list = userService.getUserList();
		model.addAttribute("userList", list);
		return "main/index";
	}
}