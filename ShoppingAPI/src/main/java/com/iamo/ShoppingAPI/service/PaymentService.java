package com.iamo.ShoppingAPI.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.iamo.ShoppingAPI.been.RequestPaymentCredit;
import com.iamo.ShoppingAPI.been.ResponsePaymentCredit;
import com.iamo.ShoppingAPI.entity.PurchaseList;
import com.iamo.ShoppingAPI.repository.CartRepository;
import com.iamo.ShoppingAPI.repository.PurchaseListRepository;

@Service
public class PaymentService {

	@Autowired
	private RestTemplate restTemplate;

	private PurchaseListRepository purchaseListRepository;
	private CartRepository cartRepository;
	
	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Autowired
	public void setPurchaseListRepository(PurchaseListRepository purchaseListRepository) {
		this.purchaseListRepository = purchaseListRepository;
	}

	private String URL;

	public PaymentService(@Value("${base_url}") String URL) {
		this.URL = URL;
	}

	@Transactional
	public ResponsePaymentCredit paymentCredit(RequestPaymentCredit requestPaymentCredit) {
		ResponsePaymentCredit responsePaymentCredit = new ResponsePaymentCredit();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		PurchaseList purchaseList = new PurchaseList();

		HttpEntity<RequestPaymentCredit> requestBody = new HttpEntity<>(requestPaymentCredit, headers);

		String response = restTemplate.postForObject(URL, requestBody, String.class);
		if (response.equals("00")) {
			purchaseList.setOrderNumber(UUID.randomUUID().toString());
			purchaseList.setAmounts(requestPaymentCredit.getAmount().getValue());
			purchaseList.setPayType("Credit");
			purchaseList.setProductId(requestPaymentCredit.getProductId());
			purchaseList.setStatus("จ่ายเงินสำเร็จ");
			purchaseList.setStoreId(requestPaymentCredit.getStoreId());
			purchaseList.setUsername(requestPaymentCredit.getUsername());
			
			PurchaseList purchaseListSave=  purchaseListRepository.save(purchaseList);
			
			if(!purchaseListSave.equals(null)) {
				cartRepository.deleteByUsername(requestPaymentCredit.getUsername());
			}
			responsePaymentCredit.setCode("00");
			responsePaymentCredit.setDate(purchaseListSave.getCreateDate());
			responsePaymentCredit.setMessage("success");
			responsePaymentCredit.setName(requestPaymentCredit.getPaymentMethod().getHolderName());
			responsePaymentCredit.setOrderNo(purchaseListSave.getOrderNumber());
			responsePaymentCredit.setReturnUrl(requestPaymentCredit.getReturnUrl());
			return responsePaymentCredit;
		}
		return null;
	}

}
