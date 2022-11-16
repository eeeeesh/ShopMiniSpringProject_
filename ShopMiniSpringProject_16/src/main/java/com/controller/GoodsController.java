package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	@Autowired
	MemberService mService;
	//에러처리
	@ExceptionHandler({Exception.class})
	public String errorPage() {
		return "error/error";
	}
	@RequestMapping(value = "/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) throws Exception {//카테고리별 상품목록보기 단 로그인 된 경우
		//System.out.println(gCategory);
		if (gCategory==null) {
			gCategory="top";
		}
		List<GoodsDTO> list =service.goodsList(gCategory);
		//System.out.println(list);
		ModelAndView mav= new ModelAndView();
		mav.addObject("goodsList", list);
		//request.setAttribute("goodsList",list)와 동일
		mav.setViewName("main"); //main.jsp => goodsList.jsp에서 목록을 뿌려줌
		return mav;
	}
	@RequestMapping(value = "/goodsRetrieve") //goodsRetrieve.jsp //view에 대한 지정이 없음 url=뷰파일.jsp
	@ModelAttribute("goodsRetrieve") //key값 goodsRetrieve= dto
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) throws Exception { //상품 자세히 보기
		GoodsDTO dto=service.goodsRetrieve(gCode);
		//System.out.println(dto);
		return dto;
	}
	@RequestMapping(value = "/loginCheck/cartAdd")
	public String cartAdd(CartDTO cart, HttpSession session) throws Exception {
		//System.out.println(cart);
		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
		cart.setUserid(mDTO.getUserid());
		int n=service.cartAdd(cart);
		System.out.println("insert 개수: "+n);
		session.setAttribute("mesg", cart.getgCode());
		return "redirect:../goodsRetrieve?gCode="+cart.getgCode(); //goodsRetrieve을 다시 불러와야 함
	}
	//Model 사용================================
//	@RequestMapping(value = "/loginCheck/cartList") //interceptor 통과
//	public String cartList(HttpSession session, Model model) {
//		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
//		String userid=mDTO.getUserid();
//		List<CartDTO> list=service.cartList(userid);
//		model.addAttribute("cartList", list);
//		return "cartList"; //cartList.jsp
//	} 
	//ModelAndView 사용=================================
//	@RequestMapping(value = "/loginCheck/cartList") //interceptor 통과
//	public ModelAndView cartList(HttpSession session) {
//		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
//		String userid=mDTO.getUserid();
//		List<CartDTO> list=service.cartList(userid);
//		ModelAndView mav= new ModelAndView();
//		mav.addObject("cartList", list);
//		mav.setViewName("cartList"); //cartList.jsp
//		return mav;
//	}
	//Request 객체======================================
//	@RequestMapping(value = "/loginCheck/cartList") //interceptor 통과
//	public String cartList(HttpSession session, Model model) {
//		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
//		String userid=mDTO.getUserid();
//		List<CartDTO> list=service.cartList(userid);
//		//request.setAttribute("cartList", list);
//		model.addAttribute("cartList", list);
//		//return "cartList"; //cartList.jsp
//		return "redirect:../cartList"; //주소요청 다시 => 함수 실행 후 jsp를 찾음
//	}
	@RequestMapping(value = "/loginCheck/cartList") //interceptor 통과
	public String cartList(HttpSession session, RedirectAttributes attr) throws Exception {
		//servlet-context.xml annotation-driven 태그 필요
		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
		String userid=mDTO.getUserid();
		List<CartDTO> list=service.cartList(userid);
		attr.addFlashAttribute("cartList", list); //리다이렉트시 데이터 유지
		return "redirect:../cartList"; //servlet-context에 등록
	}
//	@RequestMapping(value = "/cartList") //interceptor 통과
//	public String test() {
//		System.out.println("/cartList 함수 실행====");
//		return "cartList"; //cartList.jsp
//	}
	@RequestMapping(value = "/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String, String> map) throws Exception {
		System.out.println(map);
		int n =service.cartUpdate(map); //db업데이트 후 개수 출력
		System.out.println("update:"+n);
	}
	@RequestMapping(value = "/loginCheck/cartDelete")
	@ResponseBody
	public void cartDelete(@RequestParam("num") int num) throws Exception {
		System.out.println(num);
		int n=service.cartDel(num);
		System.out.println("delete:"+n); //delete 개수 확인
		//return "redirect:../loginCheck/cartList"; ->나중에 다시 체크해보기
	}
	//1. HttpServletRequest 이용
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	@ResponseBody
//	public void delAllCart(HttpServletRequest request) {
//		String[] nums=request.getParameterValues("check");
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//	}
	//2. @RequestParam
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	@ResponseBody
//	public void delAllCart(@RequestParam("check") String[] nums) {
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//	}
	//3.
//	@RequestMapping(value = "/loginCheck/delAllCart")
//	@ResponseBody
//	public void delAllCart(String[] check) {
//		for (int i = 0; i < check.length; i++) {
//			System.out.println(check[i]);
//		}
//	}
	//4.
	@RequestMapping(value = "/loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check") ArrayList<String> list) throws Exception {
		//System.out.println(list);
		int n=service.cartAllDel(list);
		System.out.println("delete 개수:"+n);
		return "redirect:../loginCheck/cartList";
	}
	@RequestMapping(value = "/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") int num, HttpSession session,
			RedirectAttributes xxx) throws Exception {
		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
		String userid =mDTO.getUserid();
		mDTO = mService.myPage(userid);//사용자 정보가져오기
		CartDTO cDTO = service.orderConfirm(num); //장바구니 정보 가져오기
		
		xxx.addFlashAttribute("mDTO", mDTO); //request에 회원정보 저장
		xxx.addFlashAttribute("cDTO", cDTO); //request에 카트정보 저장
		
		return "redirect:../orderConfirm"; //servlet-context에 등록
		//jsp가 아닌 orderConfirm에 대한 주소요청
	}
	@RequestMapping(value = "/loginCheck/orderDone")
	public String orderDone(OrderDTO oDTO, @RequestParam("orderNum") int orderNum,
			HttpSession session, RedirectAttributes xxx) throws Exception {
		System.out.println(oDTO+"\t"+orderNum);
		MemberDTO mDTO=(MemberDTO) session.getAttribute("login");
		String userid=mDTO.getUserid();
		oDTO.setUserid(userid);
		service.orderDone(oDTO, orderNum); //tx처리 //insert,delete
		
		xxx.addFlashAttribute("oDTO", oDTO);
		return "redirect:../orderDone"; //servlet-context.xml에 주소 등록
	}
	
}
