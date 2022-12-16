package com.example.form;

/**
 * ログイン時に使うフォーム.
 * 
 * @author  moriharanariki
 */
public class LoginUserForm {

	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
