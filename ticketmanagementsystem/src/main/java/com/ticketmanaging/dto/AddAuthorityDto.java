package com.ticketmanaging.dto;

import org.springframework.stereotype.Component;

@Component
public class AddAuthorityDto {
     private String userId;
     public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String authority;
     private int role_id;
	public int getRole_id() {
		return role_id;
	}
	
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
		
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
