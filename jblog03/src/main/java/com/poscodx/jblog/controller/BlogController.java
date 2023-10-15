package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.AuthUser;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.CategoryService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.service.PostService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(@PathVariable("id") String blogId, 
						@PathVariable("categoryNo") Optional<String> categoryNo, 
						@PathVariable("postNo") Optional<String> postNo, 
						Model model) {
		BlogVo blogVo = blogService.getUser(blogId);
		model.addAttribute("blogVo", blogVo);
		
		if (postNo.isPresent()) {
			PostVo postVo = postService.getContent(postNo.get());
			model.addAttribute("postVo", postVo);
			model.addAttribute("selected", "post");
		} else if (categoryNo.isPresent()) {
			CategoryVo categoryVo = categoryService.getContent(categoryNo.get());
			model.addAttribute("categoryVo", categoryVo);
			model.addAttribute("selected", "category");
		} else {
			System.out.println(blogVo.toString());
			List<PostVo> post_list = postService.getContentsList(blogId);
			model.addAttribute("post_list", post_list);
			model.addAttribute("selected", "main");
		}

		List<CategoryVo> category_list = categoryService.getContentsList(blogId);
		model.addAttribute("category_list", category_list);
		
		return "blog/main";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String blogId, @AuthUser UserVo userVo, Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}

		BlogVo blogVo = blogService.getUser(blogId);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("selected", "basic");
		return "blog/admin-basic";
	}

	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String blogId, 
							@AuthUser UserVo userVo, 
							@RequestParam(value="title") String title,
							@RequestParam(value="file") MultipartFile file,
							Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}
		
		BlogVo blogVo = blogService.getUser(blogId);
		blogVo.setTitle(title);
		
		String uploadfile = fileuploadService.restore(file);
		if(uploadfile == null) {
			uploadfile = blogVo.getImage();
		}
		blogVo.setImage(uploadfile);
		blogService.updateContents(blogVo);
		return "redirect:/" + blogId;
	}
	
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id")String blogId, @AuthUser UserVo userVo, Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}

		BlogVo blogVo = blogService.getUser(blogId);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("selected", "category");

		List<CategoryVo> list = categoryService.getContentsList(blogId);
		model.addAttribute("list", list);
		return "blog/admin-category";
	}

	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id")String blogId, 
								@AuthUser UserVo userVo, 
								@RequestParam(value="name", required=true, defaultValue="") String name,
								@RequestParam(value="desc", required=true, defaultValue="") String desc,
								Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}
		
		CategoryVo categoryVo = new CategoryVo(name, desc, blogId);
		categoryService.addContents(categoryVo);
		return "redirect:/" + blogId;
	}
	
	@RequestMapping(value="/admin/category/delete/{no:(?!assets).*}", method=RequestMethod.GET)
	public String categoryDelete(@PathVariable("id")String blogId,
							@PathVariable("no")String categoryNo,
							@AuthUser UserVo userVo,
							Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}
		
		categoryService.deleteContents(categoryNo);
		return "redirect:/" + blogId;
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id")String blogId, @AuthUser UserVo userVo, Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}

		BlogVo blogVo = blogService.getUser(blogId);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("selected", "write");
		

		List<CategoryVo> list = categoryService.getContentsList(blogId);
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id")String blogId,
							@AuthUser UserVo userVo,
							PostVo postVo,
	                        @RequestParam(value="category", required=true, defaultValue="") String categoryNo,
							Model model) {
		if (!blogId.equals(userVo.getId())) {
			return "main/index";
		}
		
		postVo.setCategoryNo(categoryNo);
		postService.addContents(postVo);
		return "redirect:/" + blogId;
	}
}
	