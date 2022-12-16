package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.User;

@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	//インサートテスト用.(2回目以降エラーが出るためコメントアウト.)
	/*
	 * @Test void testInsert() { User user = new User(); user.setName("テスト太郎");
	 * user.setEmail("test@test12345"); user.setPassword("12345Test?");
	 * user.setZipcode("111-1111"); user.setAddress("東京都テスト区");
	 * user.setTelephone("090-1234-5678"); userRepository.insert(user); }
	 */
	
	@Test
	void testFindByMailAndPassword() {
		String email = "test@test12345";
		String password = "12345Test?";
		User user = userRepository.findByMailAndPassword(email, password);
		assertEquals("テスト太郎",user.getName(),"検索結果が一致しません。");
	}
	
	@Test
	void testFindMail() {
		User user = userRepository.findByMail("test@test12345");
		assertEquals("テスト太郎",user.getName(),"検索結果が一致しません。");
	}

}
