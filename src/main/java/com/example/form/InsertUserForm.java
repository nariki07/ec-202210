package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InsertUserForm {
	/** ID */
	private Integer id;
	/** 苗字 */
	@NotBlank(message = "苗字の入力は必須です。")
	@Size(max = 10, message = "苗字は10桁以内で入力してください")
	private String lastName;
	/** 名前 */
	@NotBlank(message = "名前の入力は必須です。")
	@Size(max = 10, message = "名前は10桁以内で入力してください")
	private String firstName;
	/** メール */
	@NotBlank(message = "Eメールの入力は必須です。")
	@Email(message = "Eメールの形式が不正です。")
	private String email;
	
	/** パスワード */
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,24}$", message = "大文字、小文字、数字、記号で入力してください。")
	@Size(min = 8, max = 127, message = "パスワードは8文字以上127文字以内で記載してください。")
	private String password;
	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号形式で入力してください。")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所の入力は必須です。")
	private String address;
	/** 電話番号 */
	@Pattern(regexp = "^0\\d{2,3}-\\d{1,4}-\\d{4}$", message = "電話番号の形式で入力してください")
	private String telephone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "InsertUserForm [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", password=" + password + ", zipcode=" + zipcode + ", address=" + address + ", telephone="
				+ telephone + "]";
	}

}
