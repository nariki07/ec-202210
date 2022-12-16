
package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Order;

@SpringBootTest
class OrderRepositoryTest {
	
	@Autowired 
	private OrderRepository orderRepository;
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
	
	//2回目以降は使わないのでコメントアウト.
	/*
	 * @Test void testInsert() { Order order = new Order();
	 * order.setUserId(123456789); order.setStatus(0); order.setTotalPrice(10000);
	 * Date date = new Date(2022 - 12 - 14); order.setOrderDate(date);
	 * order.setDestinationName("テスト太郎"); order.setDestinationAddress("東京都テスト区");
	 * order.setDestinationZipcode("111-1111");
	 * order.setDestinationEmail("test@test12345");
	 * order.setDestinationTel("090-1234-5678"); LocalDateTime dateTime =
	 * LocalDateTime.now(); Timestamp timeStamp = Timestamp.valueOf(dateTime);
	 * order.setDeliveryTime(timeStamp); order.setPaymentMethod(1);
	 * orderRepository.insert(order); }
	 */
	
	@Test
	void testFindByIdAndStatus() {
		Order order = orderRepository.findByUserIdAndStatus(123456789,0);
		assertEquals("テスト太郎",order.getDestinationName(),"検索結果が一致しません。");
	}
	
	@Test
	void testUpdate() {
		Order order = new Order();
		order.setUserId(12345);
		order.setStatus(0);
		order.setTotalPrice(10000);
		Date date = new Date(2022 - 12 - 14);
		order.setOrderDate(date);
		order.setDestinationName("テスト太郎");
		order.setDestinationAddress("東京都テスト区");
		order.setDestinationZipcode("111-1111");
		order.setDestinationEmail("test@test12345");
		order.setDestinationTel("090-1234-5678");
		LocalDateTime dateTime = LocalDateTime.now();
		Timestamp timeStamp = Timestamp.valueOf(dateTime);
		order.setDeliveryTime(timeStamp);
		order.setPaymentMethod(1);
		orderRepository.update(order);
	}
	
	@Test
	void testLoad() {
		Order order = orderRepository.load(62);
		assertEquals("テスト太郎",order.getDestinationName(),"検索結果が一致しません。");
	}
	
}
