package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.InsertCartForm;
import com.example.service.ShopCartService;

import jakarta.servlet.http.HttpSession;

/**
 * オーダー情報を操作するコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("/shopCart")
public class ShopCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ShopCartService shopCartService;

	/**
	 * 一覧を表示します.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/showCartList")
	private String showCartList(Model model) {
		
		User user = (User)session.getAttribute("user");
		Order order = null;
		Integer userId = (Integer)session.getAttribute("userId");
		
		if ( user != null) {
			order = shopCartService.showCartList(user.getId());
		} else {
			order = shopCartService.showCartList(userId);
		}
		
		if (order == null) {
			model.addAttribute("NoOrder", "カート内は空です。");
		} else {
			model.addAttribute("order", order);
		}
		return "cart_list";
	}

	/**
	 * オーダーアイテム情報の追加を行います.
	 * 
	 * @param insertCartForm
	 * @return
	 */
	@PostMapping("/insertItem")
	private String insertItem(InsertCartForm insertCartForm, Integer itemId,Model model) {
		User user = (User)session.getAttribute("user");
		insertCartForm.setItemId(itemId);
		insertCartForm.setQuantity(insertCartForm.getQuantity());
		insertCartForm.setSize(insertCartForm.getSize());
		insertCartForm.setToppingList(insertCartForm.getToppingList());
		shopCartService.insertItem(insertCartForm, user);
		return showCartList(model);
	}

	/**
	 * オーダーアイテム情報を削除します.
	 * 
	 * @param orderItemId
	 * @return
	 */
	@PostMapping("/deleteItem")
	private String deleteItem(Integer orderItemId,String toOrderConfirm) {
		System.out.println("オーダーアイテムIDは" + orderItemId);
		shopCartService.deleteItem(orderItemId);
		
		if(toOrderConfirm.equals("toOrderConfirm")) {
			return "redirect:/order/orderConfirm";
		}
		return "redirect:/shopCart/showCartList";
	}
}
