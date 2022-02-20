package com.iamo.ShoppingAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamo.ShoppingAPI.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, String>{

}
