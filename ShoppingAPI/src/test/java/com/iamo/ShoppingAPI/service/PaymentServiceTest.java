package com.iamo.ShoppingAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iamo.ShoppingAPI.been.Amount;
import com.iamo.ShoppingAPI.been.PaymentMethod;
import com.iamo.ShoppingAPI.been.RequestPaymentCredit;
import com.iamo.ShoppingAPI.been.ResponsePaymentCredit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentServiceTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	@DisplayName("Test service จ่ายเงิน")
	void paymentCredit() {
		RequestPaymentCredit requestPaymentCredit = new RequestPaymentCredit();
		Amount amount = new Amount();
		PaymentMethod paymentMethod = new PaymentMethod();

		amount.setCurrency("THB");
		amount.setValue(1000);

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

		ResponsePaymentCredit responsePaymentCredit = paymentService.paymentCredit(requestPaymentCredit);

		assertEquals(responsePaymentCredit.getMessage(), "success");
	}

}
