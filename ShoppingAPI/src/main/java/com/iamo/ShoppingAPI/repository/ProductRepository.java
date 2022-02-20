package com.iamo.ShoppingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iamo.ShoppingAPI.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	List<Product> findByProductNameContaining(String productName);
}
