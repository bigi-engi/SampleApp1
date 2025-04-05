package com.example.sample1app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.entity.Post;
import com.example.sample1app.service.SampleService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
	
//	@Autowired
//	Post post;
	
	@Autowired
	SampleComponent component;
	
	@Autowired
	SampleService service;
	
//	@RequestMapping("/bean")
//	public ModelAndView bean(ModelAndView mav) {
//		mav.setViewName("bean");
//		mav.addObject("title","Bean Sample");
//		mav.addObject("msg",component.message());
//		mav.addObject("data", new Post[] {service.getPost()});
//		return mav;
//	}
	
	@RequestMapping("/bean")
	public ModelAndView bean(ModelAndView mav) {
		mav.setViewName("bean");
		mav.addObject("title","Bean sample");
		mav.addObject("msg", component.message());
		mav.addObject("data",service.getLocalPosts());
		return mav;
	}
	
	@RequestMapping(value = "/bean" , method = RequestMethod.POST)
	public ModelAndView bean(HttpServletRequest request, ModelAndView mav) {
		String param = request.getParameter("find_str");
		mav.setViewName("bean");
		mav.addObject("title","Bean Sample");
		mav.addObject("msg", "get id = "+param);
		Post post = service.getAndSavePost(Integer.parseInt(param));
		mav.addObject("data",new Post[] {post});
		return mav;
	}
	
	
}
