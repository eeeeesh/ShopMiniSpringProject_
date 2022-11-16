package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate template; //자동주입
	
	public void memberAdd(MemberDTO m) throws Exception {
		//System.out.println(template);
		int n=template.insert("MemberMapper.memberAdd", m);
		System.out.println("insert개수: "+ n);
	}

	public MemberDTO login(Map<String, String> map) throws Exception {
		MemberDTO dto=template.selectOne("MemberMapper.login", map);
		return dto;
	}

	public MemberDTO myPage(String userid) throws Exception{
		MemberDTO dto=template.selectOne("MemberMapper.mypage", userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) throws Exception {
		template.update("MemberMapper.memberUpdate", m);
	}
}
