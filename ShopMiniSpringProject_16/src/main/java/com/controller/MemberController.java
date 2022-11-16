package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	//에러처리
//	@ExceptionHandler({Exception.class})
//	public String errorPage() {
//		return "error/error";
//	}
	@RequestMapping(value = "/MemberAdd")
	public String memberAdd(MemberDTO m, Model model) throws Exception {
		//System.out.println(m);
		//System.out.println(service);
		service.memberAdd(m);
		model.addAttribute("success", m.getUserid()+"님 회원가입성공");
		return "main"; //main.jsp
	}
	@RequestMapping(value = "/loginCheck/myPage")
	public String myPage(HttpSession session) throws Exception {
		//interceptor 인증 후 이므로 dto가 null이 나올 수가 없음
		MemberDTO dto=(MemberDTO) session.getAttribute("login");
		String userid= dto.getUserid(); //세션에서 id얻기
		dto= service.myPage(userid);
		System.out.println(dto);
		
		session.setAttribute("login", dto);
		return "redirect:../myPage"; //주소에 해당하는 페이지를 servlet-context에 등록사용
		//return "myPage";
	}
	@RequestMapping(value = "/idDuplicateCheck", produces = "text/plain;charset=UTF-8")  //한글처리(응답)
	public @ResponseBody String idDuplicatedCheck(@RequestParam("id") String userid) throws Exception {
		//System.out.println(userid);
		MemberDTO dto=service.myPage(userid);
		System.out.println("idDuplicatedCheck===  "+dto);
		String mesg="아이디 사용가능";
		if (dto != null) {
			mesg="아이디 중복";
		}
		return mesg;
	}
	@RequestMapping(value = "/loginCheck/memberUpdate")
	public String memberUpdate(MemberDTO m, HttpSession session) throws Exception {//회원정보수정
		System.out.println(m);
		//int n=service.memberUpdate(m);
		//System.out.println("update개수 : "+n);
		service.memberUpdate(m);
		MemberDTO dto=(MemberDTO) session.getAttribute("login");
		String userid= dto.getUserid();
		dto=service.myPage(userid);
		session.setAttribute("login", dto); //새정보로 세션 덮어쓰기
		return "redirect:../loginCheck/myPage"; //다시 요청
		//return "redirect:../myPage"; //servlet-context.xml 주소 요청
	}
	@RequestMapping(value = "/findID")
	public String findID(@RequestParam("userName") String userName, 
			@RequestParam("emailAdress1") String emailAdress1,
			@RequestParam("emailAdress2") String emailAdress2, 
			RedirectAttributes attr) {
		System.out.println("이름:"+userName);
		System.out.println("이메일주소1:"+emailAdress1);
		System.out.println("이메일주소2:"+emailAdress2);
		HashMap<String, String> idInfo= new HashMap<String, String>();
		idInfo.put("userName", userName);
		idInfo.put("emailAdress1", emailAdress1);
		idInfo.put("emailAdress2", emailAdress2);
		String id = null;
		id =service.findID(idInfo);
		if (id==null) {
			id="id 없음";
		}
		System.out.println("id:"+id);
		attr.addFlashAttribute("idInfo", id);
		return "redirect:findId";
	}
	 


}
