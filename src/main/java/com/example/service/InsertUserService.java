package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class InsertUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	public void insertUser(User user) {
		// パスワードをハッシュ化
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.insert(user);
	}
}
