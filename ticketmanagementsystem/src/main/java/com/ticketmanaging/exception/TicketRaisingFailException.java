package com.ticketmanaging.exception;

public class TicketRaisingFailException extends RuntimeException{
   
	private static final long serialVersionUID = 1L;

	public TicketRaisingFailException(String message) {
		super(message);
	}
}
