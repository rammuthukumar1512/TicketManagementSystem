package com.ticketmanaging.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class TicketUpdateDto {
     
	@NotBlank(message="Ticket number should not empty")
	private String ticketNumber;
    @NotBlank(message="issueDescription should not empty")
	private String issueDescription;
 
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
		
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}	 
	 
}
