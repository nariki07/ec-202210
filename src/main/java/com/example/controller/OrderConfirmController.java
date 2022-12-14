package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.OrderConfirmService;
import com.example.service.ShopCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderConfirmController {
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private OrderConfirmService orderConfirmService;

	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param orderId オーダーID
	 * @return 注文確認画面
	 */
	@RequestMapping("/orderConfirm")
	public String orderConfirm(Integer orderId, Model model, OrderForm orderForm) {
		
		//ログインしていないユーザーをログイン画面に遷移させる.
		if(session.getAttribute("user") == null ) {
			session.setAttribute("throughOrderConfirmation", true);
			return "login";
		}
				
		// オーダーIDが空だった場合(カートリスト以外から遷移してきた場合)セッションに含まれるUserIdからオーダーIDを取得して代入.
		if (orderId == null) {
			User user = (User)session.getAttribute("user");
			System.out.println(user.toString());
			Order order = shopCartService.showCartList(user.getId());
			orderId = order.getId();
		}
		
		System.out.println("オーダーIDは" + orderId);
		Order order = orderConfirmService.orderConfirm(orderId);
		
		if (order.getOrderItemList().isEmpty()) {
			model.addAttribute("NoOrder", "カート内は空です。");
		}
		
		/*
		 * double tax = order.getCalcTotalPrice(); tax = tax / 1.1; tax = tax * 0.1;
		 * model.addAttribute("order",order); model.addAttribute("tax", tax);
		 * model.addAttribute("totalPrice", order.getCalcTotalPrice());
		 */
		session.setAttribute("order", order);
		return "order_confirm";
	}
}