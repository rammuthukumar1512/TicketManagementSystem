package com.ticketmanaging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ticketmanaging.dto.LoginDto;
import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketStatusUpdate;
import com.ticketmanaging.responsedto.SuccessStatusDto;
import com.ticketmanaging.responsedto.TokenDto;
import com.ticketmanaging.service.TechTeamService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="techTeam")
public class TechnicalTeamController {
	@Autowired
    TechTeamService techTeamService;
	
	@PostConstruct
	public void init() {
		try {
            techTeamService.createTable();
            System.out.println("Table created successfully!");
        } catch (Exception e) {
            System.out.println("Error during table creation: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	@PostMapping("register")
	public SuccessStatusDto RegisterTechMember(@Valid @RequestBody TechMemberDto techMember) {
	   return techTeamService.RegisterTechTeam(techMember);      	  
	}
	
	@GetMapping("viewTechTeam")
	@PreAuthorize("@techTeamPermission.isAuthorize()")
	public List<?> viewEmployees() {
	   return techTeamService.ViewEmployee();
	}
	
	@PatchMapping(value="updateTicketStatus")
    @PreAuthorize("@techTeamPermission.isAuthorize()")
    public SuccessStatusDto UpdateTicketStatus(@Valid @RequestBody TicketStatusUpdate updateData) {
    	return techTeamService.UpdateTicketStatus(updateData);
    }
	
	@PostMapping(value="login")
    public TokenDto TechmemberLogin(@Valid @RequestBody LoginDto loginData) {
    	return techTeamService.TechmemberLogin(loginData);
    }
}
