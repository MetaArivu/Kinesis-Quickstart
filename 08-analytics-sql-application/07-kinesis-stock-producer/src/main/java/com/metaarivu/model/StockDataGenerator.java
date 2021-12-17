package com.metaarivu.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StockDataGenerator {

	private LinkedHashMap<String, Stock> stocks = new LinkedHashMap<String, Stock>();

	private static final StockDataGenerator instance = new StockDataGenerator();

	private StockDataGenerator() {
		this.stocks.put("1-HDFC", new Stock("1-HDFC", "HDFCV2", "BANKIING", 1400, 1500));
		this.stocks.put("2-TCS", new Stock("2-TCS", "TCSV2", "IT", 3500, 3700));
		this.stocks.put("3-TM", new Stock("3-TM", "TMV2", "AUTOMOBILES", 450, 525));
		this.stocks.put("4-SUNPHARMA", new Stock("4-SUNPHARMA", "SUNPHARMAV2", "PHARMA", 670, 710));

		this.stocks.put("5-ICICI", new Stock("5-ICICI", "ICICIV2", "BANKIING", 740, 800));
		this.stocks.put("6-INFY", new Stock("6-INFY", "INFYV2", "IT", 1700, 1900));
		this.stocks.put("7-HEROMOTOR", new Stock("7-HEROMOTOR", "HEROMOTORV2", "AUTOMOBILES", 4500, 5250));
		this.stocks.put("8-CAPLINPOINT", new Stock("8-CAPLINPOINT", "CAPLINPOINTV2", "PHARMA", 900, 945));
		this.stocks.put("9-RELIANCE", new Stock("9-RELIANCE", "RELIANCEV2", "PETROLEUM", 2400, 2500));
		this.stocks.put("10-LT", new Stock("10-LT", "LTV2", "Construction", 1500, 1523));

	}

	public static final StockDataGenerator getInstance() {
		return instance;
	}
	
	public List<Stock> stocksWithRamdomPrice() {
		List<Stock> data = new ArrayList<Stock>();
		for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
			data.add(entry.getValue().currentStock());
			//System.out.println(entry.getKey() + " " + entry.getValue().currentStock().toJSON());
		}
		return data;
	}

	public void randomData() {
		for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().currentStock().toJSON());
		}
	}

	public static void main(String[] args) {

		try {
			for (int i = 0; i < 5; i++) {
				StockDataGenerator.getInstance().randomData();
				Thread.sleep(2000);
				System.out.println("\n\n\n");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
