package com.iamo.ShoppingAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamo.ShoppingAPI.been.CatrLsit;
import com.iamo.ShoppingAPI.been.ProductLsit;
import com.iamo.ShoppingAPI.been.RequestAddToCart;
import com.iamo.ShoppingAPI.been.ResponseAddToCart;
import com.iamo.ShoppingAPI.been.ResponseProductDetail;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.been.ResponseShoppingDetail;
import com.iamo.ShoppingAPI.entity.Cart;
import com.iamo.ShoppingAPI.entity.Product;
import com.iamo.ShoppingAPI.entity.Store;
import com.iamo.ShoppingAPI.exception.NotFoundException;
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

		throw new NotFoundException(productName);
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
			detail.setQuantity(opProduct.get().getQuantity());
			Optional<Store> opStore = storeRepository.findById(opProduct.get().getStoreId());
			if (opStore.isPresent()) {

				detail.setScore(opStore.get().getScore());
				detail.setStoreName(opStore.get().getStoreName());

				detail.setAddress(opStore.get().getAddress());
			}

			return detail;

		}

		throw new NotFoundException("Product ID : " + productId);
	}

	public ResponseAddToCart addToCart(RequestAddToCart addToCart) {
		ResponseAddToCart responseAddToCart = new ResponseAddToCart();

		Optional<Product> opProduct = productRepository.findById(addToCart.getProductId());
		if (opProduct.isPresent()) {
			Product product = opProduct.get();
			int quantity = opProduct.get().getQuantity() - addToCart.getQuantity();
			if (quantity < 0) {
				throw new NotFoundException("Quantity Product: " + opProduct.get().getProductName());
			}

			product.setQuantity(quantity);
			productRepository.save(product);

			Cart cart = new Cart();
			cart.setProductId(addToCart.getProductId());
			cart.setQuantity(addToCart.getQuantity());
			cart.setStoreId(addToCart.getStoreId());
			cart.setAmount(addToCart.getAmount());
			cart.setUsername(addToCart.getUsername());
			cart.setSize(addToCart.getSize());
			cartRepository.save(cart);

			responseAddToCart.setCode("00");
			responseAddToCart.setMessage("success");
			return responseAddToCart;
		}

		throw new NotFoundException("Product ID : " + addToCart.getProductId());
	}

	public ResponseShoppingDetail shoppingDetail(String username) {
		ResponseShoppingDetail responseShoppingDetail = new ResponseShoppingDetail();
		CatrLsit catrLsit = null;
		List<CatrLsit> catrLsits = new ArrayList<CatrLsit>();
		List<Cart> carts = cartRepository.findByUsername(username);
		float sumAmount = 0;

		if (carts.size() > 0) {
			for (Cart cart : carts) {
				Optional<Product> opProduct = productRepository.findById(cart.getProductId());
				if (opProduct.isPresent()) {
					catrLsit = new CatrLsit();
					float discount = (opProduct.get().getFullprice() - opProduct.get().getSaleprice()) * 100
							/ opProduct.get().getFullprice();
					catrLsit.setDiscount(discount + "%");
					catrLsit.setFullPrice(opProduct.get().getFullprice());
					catrLsit.setPicture(opProduct.get().getPicture());
					catrLsit.setProductDetails(opProduct.get().getProductDetails());
					catrLsit.setProductId(opProduct.get().getProductId());
					catrLsit.setProductName(opProduct.get().getProductName());
					catrLsit.setSalePrice(opProduct.get().getSaleprice());
					catrLsit.setSize(cart.getSize());

					Optional<Store> opStore = storeRepository.findById(opProduct.get().getStoreId());
					if (opStore.isPresent()) {

						catrLsit.setStoreName(opStore.get().getStoreName());
					}

					catrLsits.add(catrLsit);
				}
				sumAmount = sumAmount + cart.getAmount();
			}
			responseShoppingDetail.setCatrLsits(catrLsits);
			responseShoppingDetail.setCode("00");
			responseShoppingDetail.setMessage("success");
			responseShoppingDetail.setTotalPrice(sumAmount);
			return responseShoppingDetail;

		}

		throw new NotFoundException("Cart ");

	}

}
