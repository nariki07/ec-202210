package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import com.example.form.InsertUserForm;

@SpringBootTest
class InsertUserControllerTest {
	
	private BindingResult bindingResult;
	
	@Autowired
	private InsertUserController insertUserController;
	
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
	
	
	@Test
	void testInsert() {
		System.out.println("正常に登録を行うテスト");
		InsertUserForm insertUserForm = new InsertUserForm();
		insertUserForm.setFirstName("太郎");
		insertUserForm.setLastName("テスト");
		insertUserForm.setPassword("12345Test?");
		insertUserForm.setZipcode("111-1111");
		insertUserForm.setAddress("東京都テスト区");
		insertUserForm.setTelephone("090-4699-8579");
		String login = insertUserController.insertUser(insertUserForm,bindingResult);
		System.out.println(login);
		assertEquals("ビニールプール",login,"検索結果が一致しません。");
	}

}
