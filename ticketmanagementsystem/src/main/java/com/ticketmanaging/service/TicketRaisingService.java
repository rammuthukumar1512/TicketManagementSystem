package com.ticketmanaging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketmanaging.daoimplementation.EmployeeDaoImpl;
import com.ticketmanaging.daoimplementation.TicketDaoImpl;
import com.ticketmanaging.dto.AddAuthorityDto;
import com.ticketmanaging.dto.RemoveAuthorityDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.exception.TicketControllerException;
import com.ticketmanaging.exception.TicketRaisingFailException;
import com.ticketmanaging.repository.EmployeeRepository;
import com.ticketmanaging.repository.TicketRepository;
import com.ticketmanaging.responsedto.SuccessStatusDto;

import jakarta.validation.Valid;

@Service
public class TicketRaisingService {
	@Autowired
	TicketDaoImpl ticketDao;
	@Autowired
	TicketRepository ticketrepo;
	
	public void createTable() {
		ticketrepo.initializeDatabase();
	}
	
	public SuccessStatusDto RaiseTicket(TicketDataDto ticket) {
		SuccessStatusDto status = new SuccessStatusDto();
		boolean ticket_raise_result = ticketDao.RaiseTicket(ticket);
		if(!ticket_raise_result) {
			throw new TicketRaisingFailException("Ticket Raing Failed,Please try again");
		}
	    status.setStatusCode(1001);
	    status.setStatusMessage("Ticket Raised Successfully");
	    return status;
	}
	
	public SuccessStatusDto UpdateTicket(TicketUpdateDto updateData) {
		SuccessStatusDto status = new SuccessStatusDto();
		boolean updateStatus = ticketDao.UpdateTicket(updateData);
		if(!updateStatus) {
			throw new TicketControllerException("Ticket Updated Failed,Please try again");
		}
	    status.setStatusCode(1004);
	    status.setStatusMessage("Ticket Updated Successfully");
	    return status;
	}

	public SuccessStatusDto AddAuthority(@Valid AddAuthorityDto authorityData) {
		SuccessStatusDto status = new SuccessStatusDto();
		boolean authorityStatus = ticketDao.AddAuthority(authorityData);
		if(!authorityStatus) {
			throw new TicketControllerException("Authority Added Failed,Please try again");
		}
	    status.setStatusCode(1005);
	    status.setStatusMessage("Authority added Successfully");
	    return status;	
	}

	public SuccessStatusDto removeAuthority( @Valid RemoveAuthorityDto removeAuthorityData) {
		SuccessStatusDto status = new SuccessStatusDto();
		boolean authorityStatus = ticketDao.RemoveAuthority(removeAuthorityData);
		if(!authorityStatus) {
			throw new TicketControllerException("Remove Authority Failed,Please try again");
		}
	    status.setStatusCode(1005);
	    status.setStatusMessage("Authority removed Successfully");
	    return status;	
	}

	public List<TicketDataDto> viewTickets() {
		return ticketDao.viewTickets();
	}
	
}
