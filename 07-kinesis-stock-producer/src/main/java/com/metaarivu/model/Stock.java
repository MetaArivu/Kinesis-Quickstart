package com.metaarivu.model;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(value = Include.NON_NULL)
public class Stock {

	private String stockId;
	private String stockCode;
	private String sector;
	private float currentPrice;
	private Date dateTime;

	private int minPrice;
	private int maxPrice;

	public Stock(String stockId, String stockCode, String sector, int minPrice, int maxPrice) {
		super();
		this.stockId = stockId;
		this.stockCode = stockCode;
		this.sector = sector;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public Stock(String stockId, String stockCode, String sector, float currentPrice) {
		super();
		this.stockId = stockId;
		this.stockCode = stockCode;
		this.sector = sector;
		this.currentPrice = currentPrice;
		this.dateTime = new Date();
	}

	public String getStockId() {
		return stockId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public String getSector() {
		return sector;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public Stock currentStock() {
		Random random = new Random();
		int cp = random.nextInt(maxPrice - minPrice) + minPrice;
		return new Stock(stockId, stockCode, sector, Float.valueOf(cp + ""));
	}

	public String toJSON() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return stockId + "|" + stockCode + "|" + currentPrice + "|" + minPrice + "|" + maxPrice + "|" + dateTime;
	}

}
