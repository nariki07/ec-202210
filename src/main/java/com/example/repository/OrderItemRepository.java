package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * order_itemsテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class OrderItemRepository {
	
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * オーダーアイテム情報を追加します.
	 * 
	 * @param orderItem
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		
		if(orderItem.getId() == null) {
			String sql = "insert into order_items(item_id,order_id,quantity,size)"
					+ "values(:itemId, :orderId, :quantity, :size);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			orderItem.setId(keyHolder.getKey().intValue());
		}else {
			String sql = "insert into order_items(item_id,order_id,quantity,size)"
					+ "values(:itemId, :orderId, :quantity, :size);";
			template.update(sql, param);
		}
		
		return orderItem;
	}
	
	/**
	 * オーダーアイテム情報を削除します.
	 * 
	 * @param orderItemId
	 */
	public void deleteById(Integer orderItemId) {
		String deleteSql = "DELETE FROM order_items WHERE id=:orderItemID";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemID", orderItemId);
		template.update(deleteSql, param);
	}
	
	/**
	 * OrderItemを更新する.
	 * 
	 * @param orderItem
	 */
	public void update(OrderItem orderItem) {

		String sql = "UPDATE order_items SET item_id = :itemId,order_id = :orderId, quantity = :quantity, size = :size WHERE id = :id ;";

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
	}
	
}
