package com.power.using.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.power.using.domain.Book;

public class Cart implements Serializable {

	private Map<String,CartItem> items=new HashMap<>();
	
	private int totalQuantity;
	private float totalMoney;
	public int getTotalQuantity() {
		totalQuantity=0;
		
		for (Map.Entry<String, CartItem> me :items.entrySet()) {
			totalQuantity+=me.getValue().getQuantity();
		}		
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getTotalMoney() {
		totalMoney=0;
		for (Map.Entry<String, CartItem> me :items.entrySet()) {
			totalMoney+=me.getValue().getMoney();
		}
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Map<String, CartItem> getItems() {
		return items;
	}
	
	public void addBook(Book book){
		if(items.containsKey(book.getId())){
			CartItem item = items.get(book.getId());
			item.setQuantity(item.getQuantity()+1);
		}else{
			CartItem cartItem = new CartItem(book);
			cartItem.setQuantity(1);
			items.put(book.getId(), cartItem);
		}
	}
	
	
}
