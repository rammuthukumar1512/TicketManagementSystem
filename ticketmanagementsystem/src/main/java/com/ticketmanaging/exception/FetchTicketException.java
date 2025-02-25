package com.ticketmanaging.exception;

public class FetchTicketException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public FetchTicketException(String message) {
		super(message);
	}
}
