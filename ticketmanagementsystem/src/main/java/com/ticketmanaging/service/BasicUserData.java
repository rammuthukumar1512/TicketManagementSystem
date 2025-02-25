package com.ticketmanaging.service;

public class BasicUserData {
   private String employee_name;
   private String role;
   
 public BasicUserData() {
	   
 }     
   
public BasicUserData(String user_name, String role) {
   this.employee_name = user_name;
   this.role = role;
}   
   
public String getUser_name() {
	return employee_name;
}

public void setUser_name(String user_name) {
	this.employee_name = user_name;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

}
