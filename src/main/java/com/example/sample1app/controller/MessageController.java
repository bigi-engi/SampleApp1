package com.example.sample1app.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.dao.PersonDAOMessageImpl;
import com.example.sample1app.entity.MessageEntity;
import com.example.sample1app.repository.MessageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Controller
@RequestMapping("/msg")
public class MessageController {
	@Autowired
	MessageRepository repository;
	
	@Autowired
	PersonDAOMessageImpl dao;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav,@ModelAttribute("formModel") MessageEntity message){
		mav.setViewName("messages/index");
		mav.addObject("title","Message");
		mav.addObject("msg","Messageのサンプルです。");
		mav.addObject("formModel",message);
//		Optional<MessageEntity> list= repository.findByContent("test");
		List<MessageEntity> list = (List<MessageEntity>)repository.findAll();
		mav.addObject("data",list);
		return mav;
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	@Transactional
	public ModelAndView msgform(ModelAndView mav,@ModelAttribute("formModel") MessageEntity message) {
		mav.setViewName("showMessage");
		message.setDatetime(Calendar.getInstance().getTime());
		repository.saveAndFlush(message);
		mav.addObject("title","Message");
		mav.addObject("msg","新しいMessageを受付ました。");
		return new ModelAndView("redirect:/msg/");
	}
	
	@RequestMapping(value="/2",method=RequestMethod.GET)
	public ModelAndView index2(ModelAndView mav, @ModelAttribute("formModel") MessageEntity message){
		mav.setViewName("messages/index");
		mav.addObject("title","Message");
		mav.addObject("msg","Messageのサンプルです");
		mav.addObject("formModel",message);
		List<MessageEntity> list = dao.getAll();
		mav.addObject("data",list);
		return mav;
	}
}
