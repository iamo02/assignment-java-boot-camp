package com.iamo.ShoppingAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamo.ShoppingAPI.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
