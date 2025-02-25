package com.ticketmanaging.dto;

public class CheckAuthorityDto {
       private String employeeName;
       private String authority;
       private int role;

    public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employee_name) {
		this.employeeName = employee_name;
	}       
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
