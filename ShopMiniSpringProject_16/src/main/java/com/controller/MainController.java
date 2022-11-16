package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class MainController {
	@Autowired
	GoodsService service;
	//에러처리
//	@ExceptionHandler({Exception.class})
//	public String errorPage() {
//		return "error/error";
//	}
	@RequestMapping(value = "/") //리스트를 무조건 뿌리기
	public ModelAndView goodsList() throws Exception {
		//int result=10/0; //exception발생
		List<GoodsDTO> list = service.goodsList("top");
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		mav.setViewName("main"); //main.jsp include=>goodsList.jsp
		return mav;
	}
	
}
