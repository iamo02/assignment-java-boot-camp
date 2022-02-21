package com.iamo.ShoppingAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamo.ShoppingAPI.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String>{

}
