package com.ticketmanaging.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketmanaging.dto.AddAuthorityDto;
import com.ticketmanaging.dto.RemoveAuthorityDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketUpdateDto;

@Component
public interface TicketDao {
       boolean RaiseTicket(TicketDataDto ticket);
       boolean UpdateTicket(TicketUpdateDto updateData);
       boolean AddAuthority(AddAuthorityDto authorityData);
       boolean RemoveAuthority(RemoveAuthorityDto removeauthorityData);
       List<TicketDataDto> viewTickets();
}
