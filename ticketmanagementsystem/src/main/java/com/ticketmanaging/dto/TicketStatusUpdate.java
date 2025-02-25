package com.ticketmanaging.dto;

import org.springframework.stereotype.Component;

@Component
public class TicketStatusUpdate {
    private String ticketNumber;
    private String status;
    public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
