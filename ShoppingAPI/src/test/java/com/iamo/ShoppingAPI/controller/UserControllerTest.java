package com.iamo.ShoppingAPI.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.iamo.ShoppingAPI.been.ResponseAddress;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("Test API getaddress seccess")
	void case_getaddress_success() {
		ResponseAddress responseAddress = testRestTemplate.getForObject("/api/v1/getaddress/iamo",
				ResponseAddress.class);
		assertEquals(responseAddress.getMessage(), "success");
	}

	@Test
	@DisplayName("Test API getaddress exception")
	void case_getaddress_Exception() {
		ResponseAddress responseAddress = testRestTemplate.getForObject("/api/v1/getaddress/xxx",
				ResponseAddress.class);
		assertEquals(responseAddress.getMessage(), "Address not found");
	}

}
