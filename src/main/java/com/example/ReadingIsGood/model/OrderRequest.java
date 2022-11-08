package com.example.ReadingIsGood.model;

import java.util.HashMap;
import java.util.Map;

public class OrderRequest {

	private Long customerId;
	private Map<Long, Integer> bookIdAndAmount = new HashMap<>();

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<Long, Integer> getBookIdAndAmount() {
		return bookIdAndAmount;
	}

	public void setBookIdAndAmount(Map<Long, Integer> bookIdAndAmount) {
		this.bookIdAndAmount = bookIdAndAmount;
	}

}
