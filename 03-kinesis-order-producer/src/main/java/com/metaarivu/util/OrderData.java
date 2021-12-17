package com.metaarivu.util;

import java.util.HashMap;

public class OrderData {

	private static final OrderData instance = new OrderData();
	
	private HashMap<String, String> orderData = new HashMap<String, String>();
	
	private OrderData() {
		
	}
	
	public static OrderData getInstance() {
		return instance;
	}
	
	public void put(String key, String val) {
		this.orderData.put(key, val);
	}
	
	public String get(String key) {
		return this.orderData.get(key);
	}
	
}
