package com.proyects.ecommerce.controller;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import com.proyects.ecommerce.dto.AuthenticationRequest;
import com.proyects.ecommerce.entity.User;
import com.proyects.ecommerce.repository.UserRepository;
import com.proyects.ecommerce.utils.JwtUtils;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSException;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsService userDetailsService;
	
	private final UserRepository userRepository;
	
	private final JwtUtils jwtUtils;
	
	public static final String TOKEN_PREFIX= "Bearer";
	
	public static final String HEADER_STRING = "Authorization";
	
	@PostMapping("/Authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws  JSException,  JSONException, java.io.IOException {
		
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
					authenticationRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password.");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	    Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
	    final String jwt = jwtUtils.generateToken(userDetails.getUsername());
	    
	    if(optionalUser.isPresent()) {
	    	response.getWriter().write(new JSONObject()
	    			.put("userId", optionalUser.get().getId())
	    			.put("role", optionalUser.get().getId())
	    			.toString()
	    			);
	    
	    
	    	
	    
	    response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
	}
	    	
	}
}
	

