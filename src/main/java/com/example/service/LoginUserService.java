package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author  moriharanariki
 *
 */
@Service
@Transactional
public class LoginUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	/**
	 * ログインします.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報 存在しない場合はnullが返ります
	 */
	public User login(String email, String password) {
		User user = userRepository.findByMail(email);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
}
