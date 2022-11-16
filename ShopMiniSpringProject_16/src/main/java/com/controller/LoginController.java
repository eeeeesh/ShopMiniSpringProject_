package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	//에러처리
	@ExceptionHandler({Exception.class})
	public String errorPage() {
		return "error/error";
	}
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) throws Exception {
		MemberDTO dto =service.login(map);
		System.out.println(dto);
		//dto가 있는 경우 세션에 "login"키로 dto 저장 => main.jsp => top.jsp session 검사 후 회원전용메뉴
		//dto가 없는 경우 : model에 mesg "아이디, 비번없음" => loginForm.jsp
		if (dto != null) {
			session.setAttribute("login", dto);
			return "redirect:/goodsList?gCategory=top"; //로그인시 top카테고리를 보이도록 작성
		}else {
			model.addAttribute("mesg", "아이디 또는 비번이 잘못되었습니다");
			return "loginForm"; //loginForm.jsp
		}
	}
	@RequestMapping(value = "/loginCheck/logout")
	public String logout(HttpSession session) throws Exception {
		System.out.println("로그아웃 실행");
		session.invalidate();
		//return "../"; //.xml에 설정 main.jsp
		
		//회원전용 페이지는 servlet-context.xml에 주소 등록 후 이용하여 주소를 사용함
		//return "main"; //main.jsp
		return "redirect:../"; //.xml에 설정 main.jsp ../을 이용하여 /loginCheck의 상위 주소로 이동
	}
}
