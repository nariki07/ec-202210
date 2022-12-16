package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author  moriharanariki
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * メールアドレスとパスワードから管理者情報を取得します.
	 * 
	 * Userオブジェクトを生成するローマッパー
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	/**
	 * User情報を挿入します.
	 * 
	 * @param user User情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name,email,password,zipcode,address,telephone) values(:name,:email,:password,:zipcode,:address,:telephone);";
		template.update(sql, param);
	}

	/**
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public User findByMailAndPassword(String email, String password) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE email=:email AND password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * メールアドレスからユーザを取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public User findByMail(String email) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
}