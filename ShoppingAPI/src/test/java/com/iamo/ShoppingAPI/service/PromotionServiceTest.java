package com.iamo.ShoppingAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iamo.ShoppingAPI.been.RequestCoupon;
import com.iamo.ShoppingAPI.been.ResponseCoupon;
import com.iamo.ShoppingAPI.entity.Coupon;
import com.iamo.ShoppingAPI.repository.CouponRepository;

@ExtendWith(MockitoExtension.class)
class PromotionServiceTest {

	@Mock
	private CouponRepository couponRepository;

	@Test
	@DisplayName("Test service คำนวนโปรโมชั่นเป็น เปอร์เซ็น")
	void case_getCalculatorPercent() {

		PromotionService promotionService = new PromotionService();
		promotionService.setCouponRepository(couponRepository);

		Coupon coupon = new Coupon();
		coupon.setCouponCode("50per");
		coupon.setCouponName("ลด ห้าสิบเปร์เซ็น");
		coupon.setDiscount(50);
		coupon.setType("percent");

		when(couponRepository.findById("50per")).thenReturn(Optional.of(coupon));

		RequestCoupon requestCoupon = new RequestCoupon();
		requestCoupon.setCouponCode("50per");
		requestCoupon.setTotalPrice(100);

		ResponseCoupon responseCoupon = promotionService.getCoupon(requestCoupon);

		assertEquals(responseCoupon.getMessage(), "success");

	}

	@Test
	@DisplayName("Test service คำนวนโปรโมชั่นเป็นจำนวนเงิน")
	void case_getCalculatorAmounts() {

		PromotionService promotionService = new PromotionService();
		promotionService.setCouponRepository(couponRepository);

		Coupon coupon = new Coupon();
		coupon.setCouponCode("50per");
		coupon.setCouponName("ลด ห้าสิบเปร์เซ็น");
		coupon.setDiscount(50);
		coupon.setType("amounts");

		when(couponRepository.findById("50per")).thenReturn(Optional.of(coupon));

		RequestCoupon requestCoupon = new RequestCoupon();
		requestCoupon.setCouponCode("50per");
		requestCoupon.setTotalPrice(100);

		ResponseCoupon responseCoupon = promotionService.getCoupon(requestCoupon);

		assertEquals(responseCoupon.getMessage(), "success");

	}
	
	

}
