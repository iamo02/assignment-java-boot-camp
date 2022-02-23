package com.iamo.ShoppingAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iamo.ShoppingAPI.been.RequestAddToCart;
import com.iamo.ShoppingAPI.been.ResponseAddToCart;
import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.been.ResponseShoppingDetail;
import com.iamo.ShoppingAPI.entity.Cart;
import com.iamo.ShoppingAPI.entity.Product;
import com.iamo.ShoppingAPI.entity.Store;
import com.iamo.ShoppingAPI.repository.CartRepository;
import com.iamo.ShoppingAPI.repository.ProductRepository;
import com.iamo.ShoppingAPI.repository.StoreRepository;

@ExtendWith(MockitoExtension.class)
class ShoppingServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private StoreRepository storeRepository;

	@Mock
	private CartRepository cartRepository;

	@Test
	@DisplayName("Test service ค้าหาสินค้า แล้วเจอ")
	void case_searchProduct() {
		ShoppingService shoppingService = new ShoppingService();
		shoppingService.setProductRepository(productRepository);
		shoppingService.setStoreRepository(storeRepository);

		Product product4 = new Product("4", "[สินค้ายอดนิยม] Buga ที่เติมไฟแช็ค จาก TH", 20, 40, new Date(), "2",
				"ไฟเซ็ก", "", "C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\20.jpg", 20);
		List<Product> products = new ArrayList<Product>();
		products.add(product4);
		when(productRepository.findByProductNameContaining("Buga")).thenReturn(products);

		Store store = new Store("2", "No Brand", 4f, "กทม");
		when(storeRepository.findById("2")).thenReturn(Optional.of(store));

		ResponseSearchProduct searchProduct = shoppingService.searchProduct("Buga");

		assertEquals(searchProduct.getMessage(), "success");

	}

	@Test
	@DisplayName("Test service แสดงข้อมูลสินค้า")
	void case_getProduct() {

		ShoppingService shoppingService = new ShoppingService();
		shoppingService.setProductRepository(productRepository);
		shoppingService.setStoreRepository(storeRepository);
		Product product = new Product("1", "[สินค้ายอดนิยม] Buga ที่เติมไฟแช็ค จาก TH", 20, 40, new Date(), "2",
				"ไฟเซ็ก", "", "C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\20.jpg", 20);

		when(productRepository.findById("1")).thenReturn(Optional.of(product));

		Store store = new Store("2", "No Brand", 4f, "กทม");
		when(storeRepository.findById("2")).thenReturn(Optional.of(store));

		ResponseProductDetail responseProductDetail = shoppingService.getProduct("1");

		assertEquals(responseProductDetail.getMessage(), "success");

	}

	@Test
	@DisplayName("Test service เพิ่มสินค้าในรถเข็น")
	void case_addToCart() {

		ShoppingService shoppingService = new ShoppingService();
		shoppingService.setProductRepository(productRepository);
		shoppingService.setCartRepository(cartRepository);

		Product product = new Product("1", "[สินค้ายอดนิยม] Buga ที่เติมไฟแช็ค จาก TH", 20, 40, new Date(), "2",
				"ไฟเซ็ก", "", "C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\20.jpg", 20);

		when(productRepository.findById("1")).thenReturn(Optional.of(product));

		when(productRepository.save(product)).thenReturn(product);

		Cart cart = new Cart();
		cart.setProductId("1");
		cart.setSku(1);
		cart.setStoreId("2");
		cart.setAmount(300);
		cart.setUsername("iamo");
		cart.setSize("8");
		
		RequestAddToCart requestAddToCart = new RequestAddToCart();
		requestAddToCart.setAmount(300);
		requestAddToCart.setProductId("1");
		requestAddToCart.setSize("8");
		requestAddToCart.setSku(1);
		requestAddToCart.setStoreId("20");
		requestAddToCart.setUsername("iamo");

		ResponseAddToCart responseAddToCart = shoppingService.addToCart(requestAddToCart);
		assertEquals(responseAddToCart.getMessage(), "success");
	}
	
	@Test
	@DisplayName("Test service แสดงสินค้าในรถเข็น")
	void case_shoppingDetail() {
		
		ShoppingService shoppingService = new ShoppingService();
		shoppingService.setStoreRepository(storeRepository);
		shoppingService.setCartRepository(cartRepository);
		shoppingService.setProductRepository(productRepository);
		
		List<Cart> carts = new ArrayList<Cart>();
		Cart cart = new Cart();
		cart.setProductId("1");
		cart.setSku(1);
		cart.setStoreId("2");
		cart.setAmount(300);
		cart.setUsername("iamo");
		cart.setSize("8");
		
		carts.add(cart);
		when(cartRepository.findByUsername("iamo")).thenReturn(carts);
		
		Product product = new Product("1", "[สินค้ายอดนิยม] Buga ที่เติมไฟแช็ค จาก TH", 20, 40, new Date(), "2",
				"ไฟเซ็ก", "", "C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\20.jpg", 20);

		when(productRepository.findById("1")).thenReturn(Optional.of(product));

		Store store = new Store("2", "No Brand", 4f, "กทม");
		when(storeRepository.findById("2")).thenReturn(Optional.of(store));
		
		
		ResponseShoppingDetail responseShoppingDetail = shoppingService.shoppingDetail("iamo");
		assertEquals(responseShoppingDetail.getMessage(), "success");
		
	}
}
