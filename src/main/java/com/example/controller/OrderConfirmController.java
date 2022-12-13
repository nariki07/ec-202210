package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderConfirmService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderConfirmController {

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

		Order confirmToOrder = new Order();
		// オーダーIDが空だった場合、セッションからorder情報を取得して代入.
		if (orderId == null) {
			confirmToOrder = (Order) session.getAttribute("order");
			orderId = confirmToOrder.getId();
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