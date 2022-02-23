package com.iamo.ShoppingAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iamo.ShoppingAPI.been.ResponseAddress;
import com.iamo.ShoppingAPI.entity.User;
import com.iamo.ShoppingAPI.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	
	@Test
	@DisplayName("Test service แสดง ที่อยู่ของลูกค้า")
	void case_getAddress() {
		UserService service = new UserService();
		service.setUserRepository(userRepository);
		
		User user = new User();
		user.setAddress("119/39");
		user.setDistrict("บางใหญ่");
		user.setEmail("iamo.java@gmail.com	");
		user.setFullName("pongchai boonmee");
		user.setMobile("08123456789");
		user.setProvince("oom[6ou");
		user.setUsername("iamo");
		user.setZipCode("11140");
		when(userRepository.findById("iamo")).thenReturn(Optional.of(user));
		ResponseAddress responseAddress =  service.getAddress("iamo");
		
		assertEquals(responseAddress.getMessage(), "success");
		
		
	}

}
