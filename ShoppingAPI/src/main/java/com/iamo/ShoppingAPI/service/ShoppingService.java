package com.iamo.ShoppingAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamo.ShoppingAPI.been.ProductLsit;
import com.iamo.ShoppingAPI.been.RequestAddToCart;
import com.iamo.ShoppingAPI.been.ResponseAddToCart;
import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.entity.Cart;
import com.iamo.ShoppingAPI.entity.Product;
import com.iamo.ShoppingAPI.entity.Store;
import com.iamo.ShoppingAPI.repository.CartRepository;
import com.iamo.ShoppingAPI.repository.ProductRepository;
import com.iamo.ShoppingAPI.repository.StoreRepository;

@Service
public class ShoppingService {

	private ProductRepository productRepository;
	private StoreRepository storeRepository;
	private CartRepository cartRepository;

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Autowired
	public void setStoreRepository(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ResponseSearchProduct searchProduct(String productName) {

		List<Product> products = productRepository.findByProductNameContaining(productName);
		if (products.size() > 0) {

			ResponseSearchProduct responseSearchProduct = new ResponseSearchProduct();
			List<ProductLsit> productLsits = new ArrayList<ProductLsit>();
			ProductLsit productLsit = null;
			for (Product product : products) {
				productLsit = new ProductLsit();
				float discount = (product.getFullprice() - product.getSaleprice()) * 100 / product.getFullprice();
				productLsit.setDiscount(discount + "%");
				productLsit.setFullPrice(product.getFullprice());
				productLsit.setPicture(product.getPicture());
				productLsit.setProductId(product.getProductId());
				productLsit.setProductName(product.getProductName());
				productLsit.setSalePrice(product.getSaleprice());

				Optional<Store> optional = storeRepository.findById(product.getStoreId());
				if (optional.isPresent()) {
					productLsit.setScore(optional.get().getScore());
					productLsit.setAddress(optional.get().getAddress());
				}

				productLsits.add(productLsit);

			}
			responseSearchProduct.setProductLsit(productLsits);
			responseSearchProduct.setCode("00");
			responseSearchProduct.setMessage("success");
			return responseSearchProduct;
		}

		return null;
	}

	public ResponseProductDetail getProduct(String productId) {
		ResponseProductDetail detail = new ResponseProductDetail();

		Optional<Product> opProduct = productRepository.findById(productId);
		if (opProduct.isPresent()) {

			detail.setCode("00");
			float discount = (opProduct.get().getFullprice() - opProduct.get().getSaleprice()) * 100
					/ opProduct.get().getFullprice();
			detail.setDiscount(discount + "%");
			detail.setFullPrice(opProduct.get().getFullprice());
			detail.setMessage("success");
			detail.setPictures(opProduct.get().getPicture());
			detail.setProductDetails(opProduct.get().getProductDetails());
			detail.setProductId(opProduct.get().getProductId());
			detail.setProductName(opProduct.get().getProductName());
			detail.setPromotionExpDate(opProduct.get().getPromotionExpDate());
			detail.setSalePrice(opProduct.get().getSaleprice());
			detail.setSize(opProduct.get().getSize());
			detail.setSku(opProduct.get().getSku());
			Optional<Store> opStore = storeRepository.findById(opProduct.get().getStoreId());
			if (opStore.isPresent()) {

				detail.setScore(opStore.get().getScore());
				detail.setStoreName(opStore.get().getStoreName());

				detail.setAddress(opStore.get().getAddress());
			}

			return detail;

		}

		return null;
	}

	public ResponseAddToCart addToCart(RequestAddToCart addToCart) {
		ResponseAddToCart responseAddToCart = new ResponseAddToCart();

		Optional<Product> opProduct = productRepository.findById(addToCart.getProductId());
		if (opProduct.isPresent()) {
			Product product = opProduct.get();
			int sku = opProduct.get().getSku() - addToCart.getSku();
			if (sku < 0) {
				return null;
			}

			product.setSku(sku);
			productRepository.save(product);

			Cart cart = new Cart();
			cart.setProductId(addToCart.getProductId());
			cart.setSku(sku);
			cart.setStoreId(addToCart.getStoreId());
			cart.setAmount(addToCart.getAmount());
			cart.setUsername(addToCart.getUsername());

			cartRepository.save(cart);

			responseAddToCart.setCode("00");
			responseAddToCart.setMessage("success");
			return responseAddToCart;
		}

		return null;

	}

}
