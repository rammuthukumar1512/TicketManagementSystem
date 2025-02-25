package com.ticketmanaging.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    
	private String secretkey = "3e8d497c3f8f3f2d8b6c8a72ad881b08a6b3fc43e7a0d76abf3cd0c34889d906";
	
	public String generateToken(String username,String role) {
	    return Jwts.builder()
	    		.claim("role",role)
	    		.subject(username)
	    		.issuedAt(new Date(System.currentTimeMillis()))
	    		.expiration(new Date(System.currentTimeMillis() + 60*60*1000*10))
	    		.signWith(getKey())
	    		.compact();
	}
	
	public SecretKey getKey() {
		byte[] keybytes = secretkey.getBytes();
		return Keys.hmacShaKeyFor(keybytes);
	}

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token.trim());
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
