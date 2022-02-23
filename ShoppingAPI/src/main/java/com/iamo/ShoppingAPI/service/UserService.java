package com.iamo.ShoppingAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamo.ShoppingAPI.been.ResponseAddress;
import com.iamo.ShoppingAPI.entity.User;
import com.iamo.ShoppingAPI.exception.NotFoundException;
import com.iamo.ShoppingAPI.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public ResponseAddress getAddress(String username) {

		ResponseAddress address = new ResponseAddress();

		Optional<User> optional = userRepository.findById(username);
		if (optional.isPresent()) {
			address.setAddress(optional.get().getAddress());
			address.setCode("00");
			address.setDistrict(optional.get().getDistrict());
			address.setEmail(optional.get().getEmail());
			address.setFullName(optional.get().getFullName());
			address.setMessage("success");
			address.setMobile(optional.get().getMobile());
			address.setProvince(optional.get().getProvince());
			address.setZipCode(optional.get().getZipCode());
			return address;
		}

		throw new NotFoundException("Address");

	}
}
