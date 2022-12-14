package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.repository.OrderRepository;
import com.example.service.LoginUserService;
import com.example.service.ShopCartService;

import jakarta.servlet.http.HttpSession;

/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("/login")
public class LoginUserController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ShopCartService shopCartService;

	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private HttpSession session;

	@GetMapping("/")
	public String toLogin(LoginUserForm form) {
		return "login";
	}

	/**
	 * ログインします.
	 * 
	 * @param form  ユーザー情報格納用フォーム
	 * @param model エラー情報格納用
	 * @return ログインリンクから遷移されていた場合：商品一覧を表示する。ショッピングカート画面から遷移されていた場合：商品一覧を表示する
	 */
	@PostMapping("/loginUser")
	public String login(LoginUserForm form, Model model) {
		
		Order dummyOrder = null;
		// sessionに入っている仮のユーザーIDでオーダー情報を取得。
		if(session.getAttribute("userId") != null) {
			dummyOrder = shopCartService.showCartList((int) session.getAttribute("userId"));
		}
		
		// フォームで受け取ったemailとパスワードで検索をかける。
		User user = loginUserService.login(form.getEmail(), form.getPassword());

		// userが空であればログイン画面に遷移させる.
		if (user == null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが間違っています。");
			return toLogin(form);
		}
		session.setAttribute("user", user);
		
		// ログイン情報を利用してオーダーテーブルを取得.
		Order loginUserOrder = shopCartService.showCartList(user.getId());		
		
		if (dummyOrder != null) {
			if(loginUserOrder == null) {
				Order newloginUserOrder = new Order();
				newloginUserOrder.setUserId(user.getId());
				newloginUserOrder.setStatus(0);
				newloginUserOrder.setTotalPrice(0);
				newloginUserOrder = orderRepository.insert(newloginUserOrder);
				for (OrderItem orderItem : dummyOrder.getOrderItemList()) {
					orderItem.setOrderId(newloginUserOrder.getId());
					shopCartService.update(orderItem);
				}
			}else {
				for (OrderItem orderItem : dummyOrder.getOrderItemList()) {
					orderItem.setOrderId(loginUserOrder.getId());
					shopCartService.update(orderItem);
				}
			}
		}

		// 注文確認画面から遷移していた場合の処理.
		Boolean isThroughOrderConfirmation = (Boolean) session.getAttribute("throughOrderConfirmation");
		if (isThroughOrderConfirmation != null && isThroughOrderConfirmation.booleanValue()) {
			return "redirect:/order/orderConfirm";
		}

		return "redirect:/";

	}

}
