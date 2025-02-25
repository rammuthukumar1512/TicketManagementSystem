package com.ticketmanaging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ticketmanaging.daoimplementation.TechTeamDaoImpl;
import com.ticketmanaging.dto.LoginDto;
import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.dto.TicketStatusUpdate;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.exception.EmployeeNotRegisterException;
import com.ticketmanaging.repository.TechTeamRepository;
import com.ticketmanaging.responsedto.SuccessStatusDto;
import com.ticketmanaging.responsedto.TokenDto;

import jakarta.validation.Valid;

@Service
public class TechTeamService {
	@Autowired
	TechTeamDaoImpl techTeamDao;
	@Autowired
	TechTeamRepository techTeamRepo;
	@Autowired
	JWTService jwtService;
	@Autowired
	AuthenticationManager authManager;
	
	public void createTable() {
		techTeamRepo.initializeDatabase();
	}
     
	public SuccessStatusDto RegisterTechTeam(TechMemberDto techMember) {
		SuccessStatusDto status = new SuccessStatusDto();
        boolean result = techTeamDao.RegisterTechMember(techMember);
        if(!result) {
        	throw new EmployeeNotRegisterException("TechMember Register Failed");
        }
        status.setStatusCode(1000);
    	status.setStatusMessage("TechMember Registered Successfully");
    	return status;
	}
	
	public List<?> ViewEmployee() {
	    return techTeamDao.fetchAllTechMembers();
	}
	
	public SuccessStatusDto UpdateTicketStatus(TicketStatusUpdate updateData) {
		SuccessStatusDto status = new SuccessStatusDto();
		boolean updateStatus = techTeamDao.UpdateTicketStatus(updateData);
		if(!updateStatus) {
        	throw new EmployeeNotRegisterException("Ticket Upadte Failed");
        }
        status.setStatusCode(1000);
    	status.setStatusMessage("Ticket Updated Successfully");
    	return status;
	}

	public TokenDto TechmemberLogin(@Valid LoginDto loginData) {
		TokenDto status = new TokenDto();
		Authentication authentication = null;
		System.out.println(loginData.getUserName());
		try {
	     authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUserName(),loginData.getPassword()));
		 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    if(!authentication.isAuthenticated()) {
	    	throw new EmployeeNotRegisterException("Employee login Failed");
	    }
	    else {
	    	BasicTechMemberData employeeData = techTeamRepo.getEmployeeRole(loginData.getUserName()); 
	    	String token = jwtService.generateToken(loginData.getUserName(),employeeData.getRole());
	    	status.setStatusCode(1000);
	    	status.setStatusMessage("Login sucess");
	    	status.setToken(token);
	    	return status;
	    }
	}
}
