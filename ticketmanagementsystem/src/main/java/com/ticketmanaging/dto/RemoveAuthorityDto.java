package com.ticketmanaging.dto;

import org.springframework.stereotype.Component;

@Component
public class RemoveAuthorityDto {
    private String userId;
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String removeAuthority;
    private int roleId;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRemoveAuthority() {
		return removeAuthority;
	}
	
	public void setRemoveAuthority(String removeAuthority) {
		this.removeAuthority = removeAuthority;
	}
}
