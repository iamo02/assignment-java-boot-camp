package com.iamo.ShoppingAPI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.iamo.ShoppingAPI.been.Amount;
import com.iamo.ShoppingAPI.been.PaymentMethod;
import com.iamo.ShoppingAPI.been.RequestPaymentCredit;
import com.iamo.ShoppingAPI.been.ResponsePaymentCredit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("Test service Payment seccess")
	void case1() {

		RequestPaymentCredit requestPaymentCredit = new RequestPaymentCredit();
		Amount amount = new Amount();
		PaymentMethod paymentMethod = new PaymentMethod();

		amount.setCurrency("THB");
		amount.setValue(1000);
		;

		paymentMethod.setCvc("737");
		paymentMethod.setExpiryMonth("03");
		paymentMethod.setExpiryYear("2030");
		paymentMethod.setHolderName("John Smith");
		paymentMethod.setNumber("4111111111111111");
		paymentMethod.setType("JCB");

		requestPaymentCredit.setAmount(amount);
		requestPaymentCredit.setPaymentMethod(paymentMethod);
		requestPaymentCredit.setProductId("1,2,3");
		requestPaymentCredit.setReturnUrl("https://your-company.com/...");
		requestPaymentCredit.setStoreId("1");
		requestPaymentCredit.setUsername("iamo");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		HttpEntity<RequestPaymentCredit> requestBody = new HttpEntity<>(requestPaymentCredit, headers);

		ResponsePaymentCredit paymentCredit = testRestTemplate.postForObject("/api/v1/paymentCredit", requestBody,
				ResponsePaymentCredit.class);

		assertEquals(paymentCredit.getCode(), "00");

	}

}

//{
//	  "amount": {
//	    "currency": "USD",
//	    "value": 1000
//	  },
//	  "paymentMethod": {
//	    "type": "scheme",
//	    "number": "4111111111111111",
//	    "expiryMonth": "03",
//	    "expiryYear": "2030",
//	    "holderName": "John Smith",
//	    "cvc": "737"
//	  },
//	  "returnUrl": "https://your-company.com/...",
//	  "productId":"1,2,3",
//	  "storeId":"1",
//	  "username":"iamo"
//	}
