package com.proyects.ecommerce.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyects.ecommerce.services.jwt.UserDetailsServiceImpl;
import com.proyects.ecommerce.utils.JwtUtils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	private final UserDetailsServiceImpl userDetailServiceImpl;
	
	private final JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		try {
		
		String token = getJwtFromHeader(request);
		String username =  jwtUtils.extractUsername(token);
	
		
		
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);
		
			
		if(jwtUtils.validateToken(token, userDetails)) {
			   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
					   userDetails.getAuthorities());
			   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			   SecurityContextHolder.getContext().setAuthentication(authToken);
			   
				   
			   }
			}
		}catch (Exception e) {
			logger.error("[] Error : " + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFromHeader(@NonNull HttpServletRequest request) {
		String header= request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}

}
