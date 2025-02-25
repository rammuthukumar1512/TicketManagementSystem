package com.ticketmanaging.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TechMemberDto {
	
	@NotBlank(message="Member_id should not blank")
    private String memberId;
	@NotBlank(message="Membername should not blank")
    private String memberName;
	@NotNull(message="Phone number should not blank")
    private long phoneNumber;
	@NotBlank(message="Password should not blank")
    private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
