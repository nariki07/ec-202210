package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * order_toppingsテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class OrderToppingRepository {
	
	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = (rs, i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
		return orderTopping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public void insert(OrderTopping orderTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		String sql = "insert into order_toppings(topping_id,order_item_id)"
				+ "values(:toppingId, :orderItemId);";
		template.update(sql, param);
	}
}
