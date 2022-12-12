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

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author Hirabuki
 *
 */
@Controller
@RequestMapping("/login")
public class LoginUserController {

	
	/* @Autowired */
	/*
	 * private ShopCartService shopCartService;
	 */	
	
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
	 * @param form ユーザー情報格納用フォーム
	 * @param model エラー情報格納用
	 * @return　ログインリンクから遷移されていた場合：商品一覧を表示する。ショッピングカート画面から遷移されていた場合：商品一覧を表示する
	 */
	@PostMapping("/loginUser")
	public String login(LoginUserForm form,Model model) {
		
		//フォームで受け取ったemailとパスワードで検索をかける。
		User user = loginUserService.login(form.getEmail(), form.getPassword());
		
		if(user == null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが間違っています。");
			return toLogin(form);
		}
		
		session.setAttribute("user", user);
		
		/*
		 * //sessionに入っている仮のユーザーIDでオーダー情報を取得。 Order order =
		 * shopCartService.showCartList((int)session.getAttribute("useId"));
		 * System.out.println(order.toString()); //ログイン情報を取得. User user =
		 * loginUser.getUser(); //ログイン情報を利用してオーダーテーブルを取得. Order order2 =
		 * shopCartService.showCartList(user.getId());
		 * System.out.println(order2.toString()); if(order2 != null) { for(OrderItem
		 * orderItem : order.getOrderItemList()) { orderItem.setOrderId(order2.getId());
		 * shopCartService.update(orderItem); } }
		 */
		
	     return "redirect:/";
	
	}
	
	/*	User user = loginUserService.login(form.getEmail(), form.getPassword());
	*/	/*
		 * if(user == null) { model.addAttribute("loginError", "メールアドレスまたはパスワードが不正です。");
		 * return toLogin(form); }
		 */
		
		
			/*session.setAttribute("user", user);
			if(session.getAttribute("throughOrderConfirmation") == null ) {
				return "item_list";
			}
			session.removeAttribute("throughOrderConfirmation");
			return "order_confirm";
		}*/
}
