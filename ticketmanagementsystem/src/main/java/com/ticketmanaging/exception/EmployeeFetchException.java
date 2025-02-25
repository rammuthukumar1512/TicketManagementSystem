package com.ticketmanaging.exception;

public class EmployeeFetchException extends RuntimeException{
     
	private static final long serialVersionUID = 1L;

	public EmployeeFetchException(String message){
		 super(message); 
	}
}
