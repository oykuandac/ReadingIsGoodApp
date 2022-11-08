package com.example.ReadingIsGood.model;

public class MonthlyStatisticsModel {

	private String month;
	private String totalOrder;
	private String totalBookCount;
	private String totalPurchasedAmount;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(String totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getTotalBookCount() {
		return totalBookCount;
	}

	public void setTotalBookCount(String totalBookCount) {
		this.totalBookCount = totalBookCount;
	}

	public String getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(String totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}

}
