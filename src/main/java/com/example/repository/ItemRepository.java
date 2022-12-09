package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Itemオブジェクトを生成するローマッパー.
	 * 
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	/**
	 * 商品IDから商品を1件検索する.
	 * 
	 * @param id 商品ID
	 * @return 商品
	 */
	public Item findById(Integer id) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * 全件検索を行います。
	 * 
	 * @return
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品情報を曖昧検索を行い取得します.
	 * 
	 * @param name 商品名
	 * @return 検索した商品情報
	 */
	public List<Item> findByName(String name) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name ILIKE :itemSerch";

		SqlParameterSource param = new MapSqlParameterSource().addValue("itemSerch", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}
}
