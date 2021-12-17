package com.metaarivu.util;

import java.util.HashMap;

public class StockData {

	private static final StockData instance = new StockData();
	
	private HashMap<String, String> orderData = new HashMap<String, String>();
	
	private StockData() {
		
	}
	
	public static StockData getInstance() {
		return instance;
	}
	
	public void put(String key, String val) {
		this.orderData.put(key, val);
	}
	
	public String get(String key) {
		return this.orderData.get(key);
	}
	
}
