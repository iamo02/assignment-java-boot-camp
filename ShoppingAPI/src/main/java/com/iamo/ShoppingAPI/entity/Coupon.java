package com.iamo.ShoppingAPI.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {

	@Id
	private String couponCode;
	private String couponName;
	private float discount;
	private String type;

	public Coupon(String couponCode, String couponName, float discount, String type) {
		this.couponCode = couponCode;
		this.couponName = couponName;
		this.discount = discount;
		this.type = type;
	}

	public Coupon() {
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
