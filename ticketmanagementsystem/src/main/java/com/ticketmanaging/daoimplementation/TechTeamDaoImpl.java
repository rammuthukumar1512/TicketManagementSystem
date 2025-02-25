package com.ticketmanaging.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ticketmanaging.dao.TechTeamDao;
import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.dto.TicketStatusUpdate;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.repository.TechTeamRepository;

@Component
public class TechTeamDaoImpl implements TechTeamDao{
	
	@Autowired
    TechTeamRepository techTeamRepo;
	
	@Override
	public boolean RegisterTechMember(TechMemberDto techmember) {
		int result = techTeamRepo.RegisterTechMember(techmember);
		if(result > 0){
		   return true;
		}
		else {
		   return false;	
		}
	}

	@Override
	public List<?> fetchAllTechMembers() {
		return techTeamRepo.fetchAllTechMembers();
	}

	@Override
	public boolean UpdateTicketStatus(TicketStatusUpdate updateData) {
		return techTeamRepo.UpdateTicketStatus(updateData);
	}
    
	
}
