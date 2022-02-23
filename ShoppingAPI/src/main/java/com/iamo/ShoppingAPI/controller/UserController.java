package com.iamo.ShoppingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iamo.ShoppingAPI.been.ResponseAddress;
import com.iamo.ShoppingAPI.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/v1/getaddress/{username}")
	public ResponseAddress getAddress(@PathVariable String username) {
		return userService.getAddress(username);
	}
}
