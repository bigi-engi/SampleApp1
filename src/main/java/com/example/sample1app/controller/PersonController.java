package com.example.sample1app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.dao.PersonDAOPersonImpl;
import com.example.sample1app.entity.PersonEntity;
import com.example.sample1app.repository.PersonRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class PersonController {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonDAOPersonImpl dao;
	
	@RequestMapping(value = "/page/{page}",method=RequestMethod.GET)
	public ModelAndView page(ModelAndView mav,@PathVariable int page) {
		mav.setViewName("find");
		mav.addObject("title","ページ表示サンプル");
		mav.addObject("mas","["+page+"]ページ目の表示");
		int num= 2; //ページあたりの項目数
		Iterable<PersonEntity> list = dao.getPage(page,num);
		mav.addObject("data",list);
		return mav;
		
		
	}
	
	@RequestMapping(value = "/find3", method = RequestMethod.POST)
	public ModelAndView search3(HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("find3");
		String param = request.getParameter("find_str");
		if(param == "") {
			return new ModelAndView("redirect:/find3");
		} else {
			mav.addObject("title","Find3 Result");
			mav.addObject("msg","["+param+"]の検索結果");
			mav.addObject("value",param);
			List<PersonEntity> list = dao.find3(param);
			mav.addObject("data",list);			
		}
		return mav;
	}

	@RequestMapping(value = "/find3", method = RequestMethod.GET)
	public ModelAndView find3(ModelAndView mav) {
		mav.setViewName("find3");
		mav.addObject("title", "Find3 Program");
		mav.addObject("msg", "Test Message");
		Iterable<PersonEntity> list = dao.getAll();
		mav.addObject("data", list);
		return mav;
	}
	
	@RequestMapping(value="/find2", method=RequestMethod.POST)
	public ModelAndView search2(HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("find2");
		String param = request.getParameter("find_str");
		if(param == "") {
			return new ModelAndView("redirect:/find2");
		} else {
			mav.addObject("title","Find2 Result");
			mav.addObject("msg","「"+param+"」の検索結果");
			mav.addObject("value",param);
			List<PersonEntity> list = dao.findByName2(param);
			mav.addObject("data",list);
		}
		return mav;		
	}
	
	@RequestMapping(value="find2", method= RequestMethod.GET)
	public ModelAndView find2(ModelAndView mav) {
		mav.setViewName("find2");
		Iterable<PersonEntity> list = dao.getAll();
		mav.addObject("data",list);
		return mav;
	}
	
	@RequestMapping(value="/find",method=RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("find");
		String param=request.getParameter("find_str");
		if(param == "") {
			mav = new ModelAndView("redirect:/find");
		}else 
		{
			String[] params = param.split(",");
			mav.addObject("title","Find result");
			mav.addObject("msg","「"+param+"」の検索結果");
			mav.addObject("value",param); 
			List<PersonEntity> list = dao.findByName2(param);
			mav.addObject("data",list);
		}
		return mav;
	}
	
	@RequestMapping(value = "/find",method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("msg", "Personのサンプルです");
		Iterable<PersonEntity> list = dao.getAll();
		mav.addObject("data", list);
		return mav;
	}

	@RequestMapping("/")
	public ModelAndView index(@ModelAttribute("formModel") PersonEntity Person, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Hello page");
		mav.addObject("msg", "This is JPA sample data.");
//		List<PersonEntity> list = repository.findAll();
		List<PersonEntity> list = dao.getAll2();
		mav.addObject("data", list);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(@ModelAttribute("formModel") @Validated PersonEntity Person, BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		System.out.println(result.getFieldErrors());
		if (!result.hasErrors()) {
			repository.saveAndFlush(Person);
			return new ModelAndView("redirect:/");
		} else {
			mav.setViewName("index");
			mav.addObject("title", "Hello Page");
			mav.addObject("msg", "sorry, error is occurred...");
			Iterable<PersonEntity> list = repository.findAll();
			mav.addObject("datalist", list);
			res = mav;
		}
		return res;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute PersonEntity person, @PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit Person");
		Optional<PersonEntity> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute PersonEntity Person, ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "Delete Person");
		mav.addObject("msg", "Can I delete this record?");
		Optional<PersonEntity> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
}
