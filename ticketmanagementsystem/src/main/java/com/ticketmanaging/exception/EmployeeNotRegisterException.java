package com.ticketmanaging.exception;


public class EmployeeNotRegisterException extends RuntimeException{
      
	private static final long serialVersionUID = 1L; 
	public EmployeeNotRegisterException(String message) {
		 super(message);
	 }
}
