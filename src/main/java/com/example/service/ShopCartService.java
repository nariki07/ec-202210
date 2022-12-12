package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;
import com.example.form.InsertCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

import jakarta.servlet.http.HttpSession;

/**
 * Order,OrderItem,OrderToppingリポジトリを操作するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
@Transactional
public class ShopCartService {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	public Order showCartList(Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		return order;
	}

	public void insertItem(InsertCartForm insertCartForm,User user) {
		
		Order order = null;
		session.setAttribute("userId", session.hashCode());
		
		//ユーザー情報が空でなければユーザーIDでオーダー情報を検索。
		//ユーザー情報が空であればセッションのハッシュコードでオーダー情報を検索。
		if (user != null) {
			order = orderRepository.findByUserIdAndStatus(user.getId(), 0);
		}else {
			order = orderRepository.findByUserIdAndStatus(session.hashCode(), 0);
		}
		
		if (order == null) {
			// 新規でオーダー情報を生成.
			Order newOrder = new Order();
			// オーダーオブジェクトのuserIdにsessionIDを利用.
			if (user != null) { //ログインしているならば
				newOrder.setUserId(user.getId());
			} else {
				newOrder.setUserId(session.hashCode());
			}
			newOrder.setStatus(0);
			newOrder.setTotalPrice(0);
			newOrder = orderRepository.insert(newOrder);
			// 新規で作成したオーダー情報に紐づくOrderItem情報を新規で作成.
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(insertCartForm.getItemId());
			orderItem.setOrderId(newOrder.getId());
			orderItem.setQuantity(insertCartForm.getQuantity());
			orderItem.setSize(insertCartForm.getSize());
			orderItem = orderItemRepository.insert(orderItem);
			// 新規で作成したOrderItem情報に紐づくOrderTopping情報を作成.
			try {
				if(!insertCartForm.getToppingList().isEmpty()) {
					for (Integer toppingId : insertCartForm.getToppingList()) {
						OrderTopping orderTopping = new OrderTopping();
						orderTopping.setToppingId(toppingId);
						orderTopping.setOrderItemId(orderItem.getId());
						orderToppingRepository.insert(orderTopping);
					}
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		} else {
			// 既存のオーダー情報に紐づくOrderItem情報を新規で作成.
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(insertCartForm.getItemId());
			orderItem.setOrderId(order.getId());
			orderItem.setQuantity(insertCartForm.getQuantity());
			orderItem.setSize(insertCartForm.getSize());
			orderItem = orderItemRepository.insert(orderItem);
			// 新規で作成したOrderItem情報に紐づくOrderToppingテーブルを作成.
			try {
				if(!insertCartForm.getToppingList().isEmpty()) {
					for (Integer toppingId : insertCartForm.getToppingList()) {
						OrderTopping orderTopping = new OrderTopping();
						orderTopping.setToppingId(toppingId);
						orderTopping.setOrderItemId(orderItem.getId());
						orderToppingRepository.insert(orderTopping);
					}
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteItem(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
	}
	
	public void update(OrderItem orderItem) {
		orderItemRepository.update(orderItem);
	}
}
