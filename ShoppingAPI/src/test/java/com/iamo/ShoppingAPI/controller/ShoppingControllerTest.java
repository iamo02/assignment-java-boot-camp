package com.iamo.ShoppingAPI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.iamo.ShoppingAPI.been.RequestAddToCart;
import com.iamo.ShoppingAPI.been.ResponseAddToCart;
import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.been.ResponseShoppingDetail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("Test API searchproduct seccess")
	void case_searchproduct_success() {
		ResponseSearchProduct responseSearchProduct = testRestTemplate.getForObject("/api/v1/searchproduct/NMD",
				ResponseSearchProduct.class);
		assertEquals(responseSearchProduct.getMessage(), "success");
	}

	@Test
	@DisplayName("Test API searchproduct exception")
	void case_searchproduct_Exception() {
		ResponseSearchProduct responseSearchProduct = testRestTemplate.getForObject("/api/v1/searchproduct/ZZZZ",
				ResponseSearchProduct.class);
		assertEquals(responseSearchProduct.getMessage(), "ZZZZ not found");
	}

	@Test
	@DisplayName("Test API getProduct seccess")
	void case_getProduct_success() {
		ResponseProductDetail responseSearchProduct = testRestTemplate.getForObject("/api/v1/getProduct/1",
				ResponseProductDetail.class);
		assertEquals(responseSearchProduct.getMessage(), "success");
	}

	@Test
	@DisplayName("Test API getProduct exception")
	void case_getProduct_Exception() {
		ResponseProductDetail responseSearchProduct = testRestTemplate.getForObject("/api/v1/getProduct/500",
				ResponseProductDetail.class);
		assertEquals(responseSearchProduct.getMessage(), "Product ID : 500 not found");
	}

	@Test
	@DisplayName("Test API addtocart seccess")
	void case_addtocart_success() {

		RequestAddToCart requestAddToCart = new RequestAddToCart();
		requestAddToCart.setAmount(400);
		requestAddToCart.setProductId("1");
		requestAddToCart.setSize("8");
		requestAddToCart.setQuantity(1);
		requestAddToCart.setStoreId("1");
		requestAddToCart.setUsername("iamo");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		HttpEntity<RequestAddToCart> requestBody = new HttpEntity<>(requestAddToCart, headers);

		ResponseAddToCart responseAddToCart = testRestTemplate.postForObject("/api/v1/addtocart", requestBody,
				ResponseAddToCart.class);
		assertEquals(responseAddToCart.getMessage(), "success");
	}

	@Test
	@DisplayName("Test API addtocart exception")
	void case_addtocart_Exception() {

		RequestAddToCart requestAddToCart = new RequestAddToCart();
		requestAddToCart.setAmount(400);
		requestAddToCart.setProductId("500");
		requestAddToCart.setSize("8");
		requestAddToCart.setQuantity(1);
		requestAddToCart.setStoreId("20");
		requestAddToCart.setUsername("iamo");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		HttpEntity<RequestAddToCart> requestBody = new HttpEntity<>(requestAddToCart, headers);

		ResponseAddToCart responseAddToCart = testRestTemplate.postForObject("/api/v1/addtocart", requestBody,
				ResponseAddToCart.class);
		assertEquals(responseAddToCart.getMessage(), "Product ID : 500 not found");
	}

	@Test
	@DisplayName("Test API shoppingDetail seccess")
	void case_shoppingDetail_success() {
		ResponseShoppingDetail responseShoppingDetail = testRestTemplate.getForObject("/api/v1/shoppingDetail/iamo",
				ResponseShoppingDetail.class);
		assertEquals(responseShoppingDetail.getMessage(), "success");
	}

	@Test
	@DisplayName("Test API shoppingDetail exception")
	void case_shoppingDetail_Exception() {
		ResponseShoppingDetail responseShoppingDetail = testRestTemplate.getForObject("/api/v1/shoppingDetail/xxx",
				ResponseShoppingDetail.class);
		assertEquals(responseShoppingDetail.getMessage(), "Cart  not found");
	}

}
