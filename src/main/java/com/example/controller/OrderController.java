package com.example.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.domain.RequestCreditCardPaymentApi;
import com.example.domain.ResponseCreditCardPaymentApi;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.OrderService;

import jakarta.servlet.http.HttpSession;

/**
 * 注文情報を操作するコントローラー.
 * 
 * @author Hirabuki
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderConfirmController confirmController;

	@Autowired
	private OrderService orderService;


	@Autowired
	private HttpSession session;

	/**
	 * 注文します.
	 * 
	 * @param orderForm 注文情報格納用フォーム
	 * @param result    エラー情報格納用
	 * @return 正常系→注文完了画面へ遷移 異常系→注文確認画面へ遷移しエラー分表示
	 */
	@PostMapping("")
	public String order(@Validated OrderForm orderForm, BindingResult result, Model model, String orderId) {
		
		System.out.println(orderId);		
		orderForm.setId(orderId);
//	ここまで
		/*
		 * LocalDateTime localDateTime = LocalDateTime.now(); localDateTime =
		 * localDateTime.plusHours(3); Timestamp userOrderTimestamp =
		 * orderForm.getDeliveryTime(); LocalDateTime userOrderTime =
		 * userOrderTimestamp.toLocalDateTime(); if
		 * (localDateTime.isAfter(userOrderTime)) { result.rejectValue("deliveryDate",
		 * null, "今から3時間後の日時をご入力ください"); }
		 */
		
		if (result.hasErrors()) {
			System.out.println("result:" + result);
			return confirmController.orderConfirm(Integer.parseInt(orderForm.getId()), model, orderForm);
		}
		
		orderService.order(orderForm);
		
		return "order_finished";
	}
}
