package com.metaarivu;

public class Order {

	private String ordId;
	private String prdId;
	private int qty;

	
	private String prdName;
	private double unitPrice;

	private double totalPrice;

	public Order(String record) {
		String ordRecord[] = record.split(",");
		this.ordId = ordRecord[0];
		this.prdId = ordRecord[1];
		this.qty = Integer.valueOf(ordRecord[2]);
	}

	public Order(String ordId, String prdId, int qty, String prdName, double unitPrice) {
		super();
		this.ordId = ordId;
		this.prdId = prdId;
		this.qty = qty;
		this.prdName = prdName;
		this.unitPrice = unitPrice;
		this.totalPrice = this.unitPrice * this.qty;
	}

	public String getOrdId() {
		return ordId;
	}

	public String getPrdId() {
		return prdId;
	}

	public int getQty() {
		return qty;
	}

	public String getPrdName() {
		return prdName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "ordId=" + ordId + ", prdId=" + prdId + ", qty=" + qty + ", prdName=" + prdName + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice ;
	}

	
}
