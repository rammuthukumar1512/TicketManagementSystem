package com.ticketmanaging.dao;

import java.util.List;

import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.dto.TicketStatusUpdate;
import com.ticketmanaging.dto.TicketUpdateDto;

public interface TechTeamDao {
	boolean RegisterTechMember(TechMemberDto techmember);
	List<?> fetchAllTechMembers();
	boolean UpdateTicketStatus(TicketStatusUpdate updateData);
}
