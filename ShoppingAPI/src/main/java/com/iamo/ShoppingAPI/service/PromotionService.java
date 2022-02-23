package com.iamo.ShoppingAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamo.ShoppingAPI.been.RequestCoupon;
import com.iamo.ShoppingAPI.been.ResponseCoupon;
import com.iamo.ShoppingAPI.entity.Coupon;
import com.iamo.ShoppingAPI.exception.NotFoundException;
import com.iamo.ShoppingAPI.repository.CouponRepository;

@Service
public class PromotionService {
	private CouponRepository couponRepository;

	@Autowired
	public void setCouponRepository(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public ResponseCoupon getCoupon(RequestCoupon requestCoupon) {
		Optional<Coupon> optional = couponRepository.findById(requestCoupon.getCouponCode());
		ResponseCoupon responseCoupon = new ResponseCoupon();
		if (optional.isPresent()) {
			float newPrice = 0;
			float discount = 0;

			if (optional.get().getType().equals("percent")) {
				newPrice = requestCoupon.getTotalPrice()
						- (requestCoupon.getTotalPrice() * optional.get().getDiscount() / 100);

			} else if (optional.get().getType().equals("amounts")) {
				newPrice = requestCoupon.getTotalPrice() - optional.get().getDiscount();
			} else {
				newPrice = requestCoupon.getTotalPrice();
			}

			discount = requestCoupon.getTotalPrice() - newPrice;
			responseCoupon.setCode("00");
			responseCoupon.setDiscount(discount);
			responseCoupon.setMessage("success");
			responseCoupon.setNewPrice(newPrice);

			return responseCoupon;
		}
		throw new NotFoundException(requestCoupon.getCouponCode());
	}
}
