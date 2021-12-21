package com.metaarivu;

public class Product {

	private String prdId;
	private String prdName;
	private double unitPrice;

	
	public Product(String record) {
		String prdRecord[] = record.split(",");
		this.prdId = prdRecord[0];
		this.prdName = prdRecord[1];
		this.unitPrice = Double.valueOf(prdRecord[2]);
	}

	public String getPrdId() {
		return prdId;
	}

	public String getPrdName() {
		return prdName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	@Override
	public String toString() {
		return "prdId=" + prdId + ", prdName=" + prdName+ ", unitPrice=" + unitPrice;
	}

}
