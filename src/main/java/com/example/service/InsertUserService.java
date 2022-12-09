package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class InsertUserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	public void insertUser(User user) {
		/*
		 * user.setPassword(passwordEncoder.encode(user.getPassword()));
		 */		userRepository.insert(user);
	}
}
