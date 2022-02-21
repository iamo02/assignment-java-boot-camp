package com.iamo.ShoppingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamo.ShoppingAPI.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	List<Cart> findByUsername(String username);
	void deleteByUsername(String username);
}
