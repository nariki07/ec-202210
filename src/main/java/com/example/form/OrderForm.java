package com.example.form;

import java.sql.Timestamp;

/**
 * 注文時に使うフォーム.
 * 
 * @author Hirabuki
 *
 */
public class OrderForm {

	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先住所 */
	private String destinationAddress;
	/** 宛先TEL */
	private String distinationTel;
	/** 配達日 */
	private String deliveryDate;
	/** 配達時間 */
	private String deliveryTime;
	/** 支払い方法 */
	private String paymentMethod;
	/** オーダーID */
	private String orderId;
	/** クレジットカード番号 */
	private String card_number;
	/** 有効期限（月） */
	private String card_exp_month;
	/** 有効期限（年） */
	private String card_exp_year;
	/** クレジットカード名義人 */
	private String card_name;
	/** セキュリティコード */
	private String card_cvv;

	public int getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public int getIntOrderId() {
		return Integer.parseInt(orderId);
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDistinationTel() {
		return distinationTel;
	}

	public void setDistinationTel(String distinationTel) {
		this.distinationTel = distinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public Integer intGetPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderId() {
		return orderId;
	}

	public Integer intGetOrderId() {
		return Integer.parseInt(orderId);
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}

	public Timestamp getDeliveryTimestamp() {
		String str = deliveryDate + " " + deliveryTime + ":00:00";
		Timestamp timestamp = Timestamp.valueOf(str);

		return timestamp;
	}

	@Override
	public String toString() {
		return "OrderForm [destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", distinationTel=" + distinationTel + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + ", paymentMethod=" + paymentMethod + ", orderId=" + orderId + ", card_number="
				+ card_number + ", card_exp_month=" + card_exp_month + ", card_exp_year=" + card_exp_year
				+ ", card_name=" + card_name + ", card_cvv=" + card_cvv + "]";
	}
}