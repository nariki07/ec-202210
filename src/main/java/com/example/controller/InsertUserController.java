package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.InsertUserForm;
import com.example.service.InsertUserService;
/**
 * @author  moriharanariki
 *
 */
@Controller
@RequestMapping("/insertUser")
public class InsertUserController {
	
	@Autowired
	private InsertUserService insertUserService;
	
	/**
	 * 	ユーザー登録画面を出力
	 * 
	 * @return ユーザー登録画面
	 */
	@GetMapping("/")
	public String toResisterAdmin(InsertUserForm form) {
		return "register_admin";
	}
	
	/**
	 * ユーザー情報を登録します.
	 * 
	 * @param form ユーザー登録用フォーム
	 * @return　ログイン画面に遷移
	 */
	@PostMapping("/insert")
	public String insertUser(@Validated InsertUserForm form,BindingResult result) {
		
		System.out.println("@@@@@@@@@@@@@@@@@@@" + form.toString()); 
		
		if(result.hasErrors()) {
			return toResisterAdmin(form);
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setName(form.getLastName() + form.getFirstName());
		insertUserService.insertUser(user);

		return "redirect:/login/";

	}
}
