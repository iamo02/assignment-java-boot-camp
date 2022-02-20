package com.iamo.ShoppingAPI.been;

import java.util.List;

public class ResponseSearchProduct {
	private String code;
	private String message;
	private List<ProductLsit> productLsit;

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

	public List<ProductLsit> getProductLsit() {
		return productLsit;
	}

	public void setProductLsit(List<ProductLsit> productLsit) {
		this.productLsit = productLsit;
	}

}
