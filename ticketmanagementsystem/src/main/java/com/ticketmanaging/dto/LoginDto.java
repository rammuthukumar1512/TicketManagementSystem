package com.ticketmanaging.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto {
   private String userName;
   private String password;
   
public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}
