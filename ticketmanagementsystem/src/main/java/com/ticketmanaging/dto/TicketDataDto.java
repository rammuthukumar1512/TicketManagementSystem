package com.ticketmanaging.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketDataDto {
	 @NotBlank(message="Ticket number should not empty")
	 private String ticketNumber;
	 @NotBlank(message="Employee id should not empty")
     private String employeeId;
	 @NotNull(message="Phone number should not empty")
     private long phoneNumber;
	 @NotBlank(message="LaptopSerial number should not empty")
     private String labtopSerialNumber;
	 @NotBlank(message="issueDescription should not empty")
     private String issueDescription;
     
    public String getTicketNumber() {
		return ticketNumber;
	}
     
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
		
	public String getLabtopSerialNumber() {
		return labtopSerialNumber;
	}
	
	public void setLabtopSerialNumber(String labtopSerialNumber) {
		this.labtopSerialNumber = labtopSerialNumber;
	}
	
	public String getIssueDescription() {
		return issueDescription;
	}
	
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
     
}
