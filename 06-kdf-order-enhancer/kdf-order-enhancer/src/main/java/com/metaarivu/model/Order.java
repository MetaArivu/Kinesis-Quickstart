package com.metaarivu.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Order {

	private String id;

	private String customerId;

	private Date date;

	private List<LineItems> lineItems;

	private double total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<LineItems> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItems> lineItems) {
		this.lineItems = lineItems;
	}

	public double getTotal() {
		total = 0;
		if(this.lineItems!=null) {
			this.lineItems.forEach(lineitem ->{
				total = total + lineitem.getPrice()* lineitem.getQty();
			});
		}
		return total;
	}
	
	public double getTotalLoyaltyPoints() {
		total = 0;
		if(this.lineItems!=null) {
			this.lineItems.forEach(lineitem ->{
				total = total + lineitem.getPrice()* lineitem.getQty();
			});
		}
		return total + ((total/100)*10);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String key() {
		return this.customerId + "|" + this.id;
	}

	public String toJSON() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
