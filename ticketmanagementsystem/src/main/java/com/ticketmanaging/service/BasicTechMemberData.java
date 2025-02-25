package com.ticketmanaging.service;

public class BasicTechMemberData {
	 private String member_name;
	 private String role;
	   
	 public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BasicTechMemberData() {
		   
	 }     
	   
	public BasicTechMemberData(String user_name, String role) {
	   this.member_name = user_name;
	   this.role = role;
	}   

}
