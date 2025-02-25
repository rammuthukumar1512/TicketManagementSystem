package com.ticketmanaging.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ticketmanaging.configuration.PermissionConfig;
import com.ticketmanaging.configuration.TicketPermissionConfig;
import com.ticketmanaging.dto.AddAuthorityDto;
import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.RemoveAuthorityDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.responsedto.SuccessStatusDto;
import com.ticketmanaging.service.TicketRaisingService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="ticket")
public class TicketManagementController {
        
	@Autowired
	TicketRaisingService ticketRaisingService;
	@Autowired
	TicketPermissionConfig ticketPermission;
	
	
	@PostConstruct
	public void init() {
		try {
			ticketRaisingService.createTable();
            System.out.println("Table created successfully!");
        } catch (Exception e) {
            System.out.println("Error during table creation: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
    @PostMapping(value="raiseTicket")
    public SuccessStatusDto RaisingTicket(@Valid @RequestBody TicketDataDto ticket) {
    	return ticketRaisingService.RaiseTicket(ticket);
    }
    
    @PatchMapping(value="update")
    @PreAuthorize("@ticketPermission.isAuthorize()")
    public SuccessStatusDto UpdateTicket(@Valid @RequestBody TicketUpdateDto updateData) {
    	System.out.println("update reach");
    	return ticketRaisingService.UpdateTicket(updateData);
    }
    
    @PostMapping(value="addAuthority")
    @PreAuthorize("@ticketPermission.isAuthorize()")
    public SuccessStatusDto AddAuthority(@Valid @RequestBody AddAuthorityDto authorityData) {
    	return ticketRaisingService.AddAuthority(authorityData);
    }
    
    @PostMapping(value="removeAuthority")
    @PreAuthorize("@ticketPermission.isAuthorize()")
    public SuccessStatusDto RemoveAuthority(@Valid @RequestBody RemoveAuthorityDto removeAuthorityData) {
    	return ticketRaisingService.removeAuthority(removeAuthorityData);
    }
    
    @GetMapping(value="viewTickets")
    @PreAuthorize("@ticketPermission.isAuthorize()")
    public List<TicketDataDto> ViewTickets() {
    	return ticketRaisingService.viewTickets();
    }

}
