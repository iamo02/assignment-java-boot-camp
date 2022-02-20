package com.iamo.ShoppingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
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
}
