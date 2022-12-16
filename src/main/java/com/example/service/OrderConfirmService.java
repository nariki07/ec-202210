package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * @author  moriharanariki
 *
 */
@Service
@Transactional
public class OrderConfirmService {
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文情報を取得します.
	 * 
	 * @param orderId　Order ID
	 * @return　注文情報
	 */
	public Order orderConfirm(Integer orderId) {
		Order order = orderRepository.load(orderId);
		return order;
	}
}
