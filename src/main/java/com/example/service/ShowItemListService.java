package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * Item,Toppingリポジトリを操作するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
@Transactional
public class ShowItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 商品情報を取得します。
	 * 
	 * @return 商品情報リスト
	 */
	public List<Item> showItemList(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	/**
	 * 商品の曖昧検索を行います。
	 * 
	 * @param name 商品名
	 * @return 商品情報リスト
	 */
	public List<Item> serchByName(String name){
		List<Item> itemList = itemRepository.findByName(name);
		return itemList;
	}
	 
	
	
}
