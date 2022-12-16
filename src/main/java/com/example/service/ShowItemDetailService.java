package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.User;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

import jakarta.servlet.http.HttpSession;

/**
 * 商品詳細を取得するServiceクラス.
 * 
 * @author  moriharanariki
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private HttpSession session;

	/**
	 * 商品を1件検索します.
	 * 
	 * @param id 商品ID
	 * @return 商品
	 */
	public Item showItemDetail(Integer id) {
		User user = (User)session.getAttribute("user");
		
		Item item = new Item();
		item = itemRepository.findById(id);
		item.setToppingList(toppingRepository.findAll());
		session.setAttribute("user",user);
		return item;
	}
}
