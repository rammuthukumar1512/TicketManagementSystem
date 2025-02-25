package com.ticketmanaging.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ticketmanaging.dao.TicketDao;
import com.ticketmanaging.dto.AddAuthorityDto;
import com.ticketmanaging.dto.RemoveAuthorityDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.repository.TicketRepository;

import jakarta.validation.Valid;

@Component
public class TicketDaoImpl implements TicketDao{
	
	@Autowired
	TicketRepository ticketRepo;

	@Override
	public boolean RaiseTicket(TicketDataDto ticket) {
		int rows = ticketRepo.RaiseTicket(ticket);
		if(rows > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean UpdateTicket(TicketUpdateDto updateData) {
	    int rows = ticketRepo.UpdateTicket(updateData);	
	    if(rows > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean AddAuthority(AddAuthorityDto authorityData) {
		int rows = ticketRepo.AddAuthority(authorityData);	
	    if(rows > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean RemoveAuthority(@Valid RemoveAuthorityDto removeAuthorityData) {
		int rows = ticketRepo.RemoveAuthority(removeAuthorityData);	
	    if(rows > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<TicketDataDto> viewTickets() {
//		List<TicketDataDto> tickets = 
		return ticketRepo.FetchTickets();
	}
      
}
