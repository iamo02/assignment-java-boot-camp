package com.iamo.ShoppingAPI.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.iamo.ShoppingAPI.been.RequestCoupon;
import com.iamo.ShoppingAPI.been.ResponseCoupon;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PromotionControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("Test API coupon exception")
	void case_coupon_Exception() {

		RequestCoupon requestCoupon = new RequestCoupon();
		requestCoupon.setCouponCode("5000baht");
		requestCoupon.setTotalPrice(400);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		HttpEntity<RequestCoupon> requestBody = new HttpEntity<>(requestCoupon, headers);

		ResponseCoupon coupon = testRestTemplate.postForObject("/api/v1/coupon", requestBody, ResponseCoupon.class);
		assertEquals(coupon.getMessage(), "5000baht not found");
	}

	@Test
	@DisplayName("Test API coupon seccess")
	void case_coupon_success() {

		RequestCoupon requestCoupon = new RequestCoupon();
		requestCoupon.setCouponCode("50baht");
		requestCoupon.setTotalPrice(400);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		HttpEntity<RequestCoupon> requestBody = new HttpEntity<>(requestCoupon, headers);

		ResponseCoupon coupon = testRestTemplate.postForObject("/api/v1/coupon", requestBody, ResponseCoupon.class);
		assertEquals(coupon.getMessage(), "success");
	}
}
