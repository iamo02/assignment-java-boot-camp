package com.iamo.ShoppingAPI.been;

import java.util.List;

public class ResponseShoppingDetail {
	private String code;
	private String message;
	private float totalPrice;
	private List<CatrLsit> catrLsits;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CatrLsit> getCatrLsits() {
		return catrLsits;
	}

	public void setCatrLsits(List<CatrLsit> catrLsits) {
		this.catrLsits = catrLsits;
	}
	
	
}
