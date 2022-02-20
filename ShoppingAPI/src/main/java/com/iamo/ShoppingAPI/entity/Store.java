package com.iamo.ShoppingAPI.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Store {

	@Id
	private String storeId;
	private String storeName;
	private float score;
	private String address;
	
	public Store() {

	}

	public Store(String storeId, String storeName, float score, String address) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.score = score;
		this.address = address;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
