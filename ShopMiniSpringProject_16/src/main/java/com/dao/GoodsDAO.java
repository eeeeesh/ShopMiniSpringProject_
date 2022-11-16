package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;
@Repository
public class GoodsDAO {
	@Autowired
	SqlSessionTemplate template;

	public List<GoodsDTO> goodsList(String gCategory) throws Exception {
		List<GoodsDTO> list =template.selectList("GoodsMapper.goodsList", gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) throws Exception {
		GoodsDTO dto =template.selectOne("GoodsMapper.goodsRetrieve", gCode);
		return dto;
	}

	public int cartAdd(CartDTO cart) throws Exception {
		int n=template.insert("CartMapper.cartAdd", cart);
		return n;
	}

	public List<CartDTO> cartList(String userid) throws Exception {
		List<CartDTO> list=template.selectList("CartMapper.cartList", userid);
		return list;
	}

	public int cartUpdate(Map<String, String> map) throws Exception {
		int n =template.update("CartMapper.cartUpdate", map);
		return n;
	}

	public int cartDel(int num) throws Exception {
		int n=template.delete("CartMapper.cartDel", num);
		return n;
	}

	public int cartAllDel(ArrayList<String> list) throws Exception {
		int n=template.delete("CartMapper.cartAllDel", list);
		return n;
	}

	public CartDTO orderConfirm(int num) throws Exception {
		CartDTO dto =template.selectOne("CartMapper.cartbyNum", num);
		return dto;
	}

	public void orderDone(OrderDTO oDTO) throws Exception {
		int n=template.insert("CartMapper.orderDone", oDTO);
		System.out.println("insert 개수:"+ n);
	}

	public void cartDelete(int orderNum) throws Exception {
		int n= template.delete("CartMapper.cartDel", orderNum);
		System.out.println("delete 개수:"+ n);
	}

}
