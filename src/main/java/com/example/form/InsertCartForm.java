package com.example.form;

import java.util.List;

/**
 * カートに入れるアイテム情報を受け取るフォーム.
 * 
 * @author moriharanariki
 *
 */
public class InsertCartForm {
	/* サイズ */
	private String size; //ローマッパーでcharcter型が受け取れなかったため一旦string型に変更.
	/* 数量 */
	private Integer quantity;
	/* トッピングリスト */
	private List<Integer> toppingList;
	/* アイテムID */
	private Integer itemId;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "InsertCartForm [size=" + size + ", quantity=" + quantity + ", toppingList=" + toppingList + ", itemId="
				+ itemId + "]";
	}

}
