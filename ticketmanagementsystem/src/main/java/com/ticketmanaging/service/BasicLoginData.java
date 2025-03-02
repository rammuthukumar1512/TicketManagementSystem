package com.ticketmanaging.service;


public class BasicLoginData {
	
	private String username;
    private String password;
	private String role;
	
	public BasicLoginData(String username, String role) {
		this.username = username;
		this.role = role;
	}
	
    public BasicLoginData(String username,String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
    
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
