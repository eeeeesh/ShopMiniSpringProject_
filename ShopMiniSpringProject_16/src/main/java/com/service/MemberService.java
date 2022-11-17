package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
@Service
public class MemberService {
	@Autowired
	MemberDAO dao;
	
	public void memberAdd(MemberDTO m) throws Exception {
		//System.out.println(dao);
		dao.memberAdd(m); 
	}

	public MemberDTO login(Map<String, String> map) throws Exception {
		MemberDTO dto =dao.login(map);
		return dto;
	}

	public MemberDTO myPage(String userid) throws Exception {
		MemberDTO dto =dao.myPage(userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) throws Exception {
		dao.memberUpdate(m);
	}

	public String findID(HashMap<String, String> idInfo) {
		return dao.findID(idInfo);
	}

	public String findPW(HashMap<String, String> pwInfo) {
		return dao.findPW(pwInfo);
	}

}
