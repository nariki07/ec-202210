package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラー
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 商品一覧画面を出力します.
	 * 
	 * @param model
	 * @return 商品一覧画面
	 */
	@GetMapping("/")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.showItemList();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}

	@PostMapping("/serchItem")
	public String serchItem(Model model, String name) {
		List<Item> itemList = showItemListService.serchByName(name);
		if (itemList.isEmpty()) {
			model.addAttribute("result", "該当する商品がありません。");
			List<Item> allItemList = showItemListService.showItemList();
			model.addAttribute("itemList", allItemList);
			return "item_list";
		}
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}