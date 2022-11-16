package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;
@Service
public class GoodsService {
	@Autowired
	GoodsDAO dao;

	public List<GoodsDTO> goodsList(String gCategory) throws Exception {
		return dao.goodsList(gCategory);
	}

	public GoodsDTO goodsRetrieve(String gCode) throws Exception {
		return dao.goodsRetrieve(gCode);
	}

	public int cartAdd(CartDTO cart) throws Exception {
		return dao.cartAdd(cart);
	}

	public List<CartDTO> cartList(String userid) throws Exception {
		return dao.cartList(userid);
	}

	public int cartUpdate(Map<String, String> map) throws Exception {
		return dao.cartUpdate(map);
	}

	public int cartDel(int num) throws Exception {
		return dao.cartDel(num);
	}

	public int cartAllDel(ArrayList<String> list) throws Exception {
		return dao.cartAllDel(list);
	}
	
	public CartDTO orderConfirm(int num) throws Exception {
		return dao.orderConfirm(num);
	}
	@Transactional
	public void orderDone(OrderDTO oDTO, int orderNum) throws Exception {
		dao.orderDone(oDTO); //주문정보저장 insert
		dao.cartDelete(orderNum); //카트에서 삭제 두 처리를 tx 처리함 root-context.xml에
		//tx-Manager 등록 필요
	}
}
