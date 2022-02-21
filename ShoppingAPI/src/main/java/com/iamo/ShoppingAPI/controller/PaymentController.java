package com.iamo.ShoppingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iamo.ShoppingAPI.been.RequestPaymentCredit;
import com.iamo.ShoppingAPI.been.ResponsePaymentCredit;
import com.iamo.ShoppingAPI.service.PaymentService;

@RestController
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping("/api/v1/paymentCredit")
	public ResponsePaymentCredit paymentCredit(@RequestBody RequestPaymentCredit requestPaymentCredit) {
		return paymentService.paymentCredit(requestPaymentCredit);
	}

}


