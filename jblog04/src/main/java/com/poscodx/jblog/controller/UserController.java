package com.poscodx.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo uservo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		//System.out.println("start");
		if (result.hasErrors()) {
			//System.out.println("1");
			model.addAllAttributes(result.getModel());
			//System.out.println("2");
			return "user/join";
		}
		
		//System.out.println("3");
		userService.join(userVo);
		//System.out.println("4");
		
		BlogVo blogVo = new BlogVo(userVo.getId());
		blogService.addUser(blogVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joingsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
}
