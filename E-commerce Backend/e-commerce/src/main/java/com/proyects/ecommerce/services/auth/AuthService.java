package com.proyects.ecommerce.services.auth;



import com.proyects.ecommerce.dto.SignupRequest;
import com.proyects.ecommerce.dto.UserDto;



public interface AuthService {

	UserDto createUser(SignupRequest signupRequest);
	
	 Boolean hasUserWithEmail(String email);
}
