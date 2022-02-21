package com.iamo.ShoppingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iamo.ShoppingAPI.been.RequestAddToCart;
import com.iamo.ShoppingAPI.been.RequestCoupon;
import com.iamo.ShoppingAPI.been.ResponseAddToCart;
import com.iamo.ShoppingAPI.been.ResponseAddress;
import com.iamo.ShoppingAPI.been.ResponseCoupon;
import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.been.ResponseShoppingDetail;
import com.iamo.ShoppingAPI.service.ShoppingService;

@RestController
public class ShoppingController {

	private ShoppingService shoppingService;

	@Autowired
	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@GetMapping("/api/v1/searchproduct/{product}")
	public ResponseSearchProduct searchProduct(@PathVariable String product) {
		return shoppingService.searchProduct(product);
	}

	@GetMapping("/api/v1/getProduct/{productId}")
	public ResponseProductDetail getProduct(@PathVariable String productId) {
		return shoppingService.getProduct(productId);
	}

	@PostMapping("/api/v1/addtocart")
	public ResponseAddToCart addToCart(@RequestBody RequestAddToCart addToCart) {
		return shoppingService.addToCart(addToCart);
	}
	
	@GetMapping("/api/v1/shoppingDetail/{username}")
	public ResponseShoppingDetail shoppingDetail(@PathVariable String username) {
		return shoppingService.shoppingDetail(username);
	}
	
	@GetMapping("/api/v1/getaddress/{username}")
	public ResponseAddress getAddress(@PathVariable String username) {
		return shoppingService.getAddress(username);
	}
	
	@PostMapping("/api/v1/coupon")
	public ResponseCoupon getCoupon(@RequestBody RequestCoupon requestCoupon) {
		return shoppingService.getCoupon(requestCoupon);
	}
}
