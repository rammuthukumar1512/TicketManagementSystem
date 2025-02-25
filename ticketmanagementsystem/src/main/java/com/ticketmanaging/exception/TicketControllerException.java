package com.ticketmanaging.exception;

public class TicketControllerException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TicketControllerException(String message) {
		super(message);
	}
}
