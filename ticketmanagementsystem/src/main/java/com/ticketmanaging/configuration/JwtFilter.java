package com.ticketmanaging.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ticketmanaging.service.JWTService;
import com.ticketmanaging.service.UserDataService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
	@Autowired
	JWTService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("hi");
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		 if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtService.extractUserName(token);
	     }
		
		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 System.out.println("hi");
			 UserDetails userDetails = context.getBean(UserDataService.class).loadUserByUsername(username);
			 System.out.println(userDetails.getUsername());
	            if (jwtService.validateToken(token, userDetails)) {
	            	System.out.println("hi");
	            	 System.out.println("hi"+userDetails.getUsername());
	                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                 authToken.setDetails(new WebAuthenticationDetailsSource()
	                        .buildDetails(request));
	                 SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	}
}