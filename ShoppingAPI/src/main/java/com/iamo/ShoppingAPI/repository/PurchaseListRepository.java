package com.iamo.ShoppingAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamo.ShoppingAPI.entity.PurchaseList;

@Repository
public interface PurchaseListRepository extends JpaRepository<PurchaseList, String>{

}
