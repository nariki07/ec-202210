package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * ログアウト機能を制御するコントローラー.
 * 
 * @author  moriharanariki
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ログアウトします.
	 * 
	 * @return 商品一覧
	 */
	@GetMapping("")
	public String logout() {
		session.invalidate();
		return "login";
	}

}
