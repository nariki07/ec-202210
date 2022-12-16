package com.example.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.OrderItem;

@SpringBootTest
class OrderItemRepositoryTest {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(18);
		orderItem.setOrderId(123456789);
		orderItem.setQuantity(1);
		orderItem.setSize("M");
		orderItemRepository.insert(orderItem);
	}
	
	@Test
	void testUpdate() {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(18);
		orderItem.setOrderId(12345);
		orderItem.setQuantity(1);
		orderItem.setSize("M");
		orderItemRepository.update(orderItem);
	}
	
	@Test
	void testDeleteById() {
		orderItemRepository.deleteById(161);
	}
	

}
