package com.iamo.ShoppingAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamo.ShoppingAPI.been.ProductLsit;
import com.iamo.ShoppingAPI.been.ResponseSearchProduct;
import com.iamo.ShoppingAPI.entity.Product;
import com.iamo.ShoppingAPI.entity.Store;
import com.iamo.ShoppingAPI.repository.ProductRepository;
import com.iamo.ShoppingAPI.repository.StoreRepository;

@Service
public class ShoppingService {

	private ProductRepository productRepository;
	private StoreRepository storeRepository;

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

}
