package com.example.sample1app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.entity.Post;

@Controller
public class HelloController {
	
	@Autowired
	Post post;
	
	@RequestMapping("/bean")
	public ModelAndView bean(ModelAndView mav) {
		mav.setViewName("bean");
		mav.addObject("title","Bean Sample");
		mav.addObject("msg",post);
		return mav;
	}
	
}
