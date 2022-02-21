package com.iamo.ShoppingAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iamo.ShoppingAPI.been.ResponseException;
import com.iamo.ShoppingAPI.exception.NotFoundException;

@RestControllerAdvice
public class ShoppingControllerAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseException notFound(NotFoundException e) {
		ResponseException responseException = new ResponseException();
		responseException.setMessage(e.getMessage() + " not found");
		return responseException;
	}

}
