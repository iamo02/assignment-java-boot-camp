package com.iamo.ShoppingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iamo.ShoppingAPI.been.RequestCoupon;
import com.iamo.ShoppingAPI.been.ResponseCoupon;
import com.iamo.ShoppingAPI.service.PromotionService;

@RestController
public class PromotionController {

	private PromotionService promotionService;

	@Autowired
	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@PostMapping("/api/v1/coupon")
	public ResponseCoupon getCoupon(@RequestBody RequestCoupon requestCoupon) {
		return promotionService.getCoupon(requestCoupon);
	}

}
